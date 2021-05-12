package com.cg.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rooms")
public class Room {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int room_id;
	
	private String room_no;
	private Hostel hostel;
	private String room_desc;
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Room(int room_id, String room_no, Hostel hostel, String room_desc) {
		super();
		this.room_id = room_id;
		this.room_no = room_no;
		this.hostel = hostel;
		this.room_desc = room_desc;
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public String getRoom_no() {
		return room_no;
	}
	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}
	public Hostel getHostel() {
		return hostel;
	}
	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
	}
	public String getRoom_desc() {
		return room_desc;
	}
	public void setRoom_desc(String room_desc) {
		this.room_desc = room_desc;
	}
	@Override
	public String toString() {
		return "Room [room_id=" + room_id + ", room_no=" + room_no + ", hostel=" + hostel + ", room_desc="
				+ room_desc + "]";
	}

}
