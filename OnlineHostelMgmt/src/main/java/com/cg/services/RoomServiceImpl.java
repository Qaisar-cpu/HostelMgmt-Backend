package com.cg.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IHostelDao;
import com.cg.dao.IRoomDao;
import com.cg.dto.RoomDTO;
import com.cg.entities.Hostel;
import com.cg.entities.Room;
import com.cg.exceptions.FloorNotFoundException;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.RoomNotFoundException;

@Service
public class RoomServiceImpl implements IRoomService{
	
	@Autowired
	IRoomDao roomdao;
	
	@Autowired
	IHostelDao hosteldao;
	Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);
	@Override
	public Room addRoom(RoomDTO roomdto) throws HostelNotFoundException {
		logger.info("Adding room");
		// TODO Auto-generated method stub
		Hostel hostel=hosteldao.findById(roomdto.getHostel_id()).orElseThrow(()->new HostelNotFoundException("Hostel not found"));
		
		Room room =new Room();
		room.setRoomNo(roomdto.getRoomNo());
		room.setFloor(roomdto.getFloor());
		room.setRoomDesc(roomdto.getRoomDesc());
		room.setMaximumSize(roomdto.getMaximumSize());
		room.setHostel(hostel);
		
		return roomdao.save(room);
	}

	@Override
	public List<Room> getRoomsByHostelId(Long hostel_id) throws HostelNotFoundException, RoomNotFoundException {
		// TODO Auto-generated method stub
		Hostel hostel=hosteldao.findById(hostel_id).orElseThrow(()->new HostelNotFoundException("Hostel not found"));
		List<Room> rooms=hostel.getRooms().stream().collect(Collectors.toList());
		if(rooms.isEmpty())
			throw new RoomNotFoundException("Room not found");
		return rooms;
	}

	@Override
	public List<Room> getRoomsByFloorAndHostelId(Integer floor, Long hostel_id)
			throws HostelNotFoundException, FloorNotFoundException, RoomNotFoundException {
		// TODO Auto-generated method stub
		Hostel hostel=hosteldao.findById(hostel_id).orElseThrow(()->new HostelNotFoundException("Hostel not found"));
		if(floor<=0 || floor>hostel.getTotalFloors())
			throw new FloorNotFoundException("Invalid Floor");
//		List<Room> rooms=hostel.getRooms().stream().collect(Collectors.toList());
		List<Room> rooms = roomdao.findByHostelIdAndFloor(hostel_id,floor);
		if(rooms.isEmpty())
			throw new RoomNotFoundException("Room not found");
		return rooms;
		
		
	}

	@Override
	public List<Room> getRoomsAvailableByHostelId(Long hostel_id)
			throws HostelNotFoundException, RoomNotFoundException {
		// TODO Auto-generated method stub
		Hostel hostel=hosteldao.findById(hostel_id).orElseThrow(()->new HostelNotFoundException("Hostel not found"));
		List<Room> rooms=hostel.getRooms().stream().collect(Collectors.toList());
		if(rooms.isEmpty())
			throw new RoomNotFoundException("Room not found");
		List<Room> list=rooms.stream().filter(r->r.getMaximumSize()>0).collect(Collectors.toList());
		
		return list;
		
		
	}

	@Override
	public List<Room> getRoomsAvailable() throws RoomNotFoundException {
		// TODO Auto-generated method stub
		
		return null;
	}

	
}
