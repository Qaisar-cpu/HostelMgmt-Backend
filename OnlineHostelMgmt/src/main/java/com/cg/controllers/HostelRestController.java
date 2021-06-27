package com.cg.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.HostelDto;
import com.cg.dto.SuccessMessage;
import com.cg.entities.Hostel;
import com.cg.entities.Student;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.exceptions.ValidateHostelException;
import com.cg.services.IHostelService;

@RestController
@RequestMapping("/hostel")
@CrossOrigin("*")
public class HostelRestController {
	
	@Autowired
	IHostelService hostelService;

	@GetMapping("/get")
	public List<Hostel> viewAllHostel() throws HostelNotFoundException {
		return hostelService.viewAllHostel();
	}
	
	@GetMapping("/get/{hid}")
	public Hostel viewHostelById(@PathVariable("hid") Integer hostelId) throws HostelNotFoundException {
		return hostelService.viewHostelById(hostelId);
	}

	@PostMapping("/add")
	public SuccessMessage addHostel(@Valid @RequestBody HostelDto hostelDto, BindingResult br) throws ValidateHostelException{	
		
		if (br.hasErrors()) {
			throw new ValidateHostelException(br.getFieldErrors());
		}
		Integer hostelId = hostelService.addHostel(hostelDto);
		return new SuccessMessage("Your generated ID is " + hostelId);
		
	}
	
	@GetMapping("/get/name/{name}")
	public ResponseEntity<List<Hostel>> viewHostelByName(@PathVariable("name") String name)
			throws HostelNotFoundException {
		return new ResponseEntity<List<Hostel>>(hostelService.getHostelByName(name), HttpStatus.OK);
	}
}
