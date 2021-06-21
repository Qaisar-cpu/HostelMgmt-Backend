package com.cg.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.AllotmentDto;
import com.cg.dto.SuccessMessage;
import com.cg.entities.Allotment;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.GenderTypeMismatchException;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.HostelRoomMismatchException;
import com.cg.exceptions.RoomNotFoundException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.exceptions.ValidateAllotmentException;
import com.cg.services.IAllotmentService;

//Check these in postman
@RestController
@RequestMapping("/allotment")
@CrossOrigin("*")
public class AllotmentRestController {

	@Autowired
	IAllotmentService allotmentService;

	@PostMapping("/add")
	public SuccessMessage addAllotment(@Valid @RequestBody AllotmentDto allotmentDto, BindingResult br)
			throws ValidateAllotmentException, HostelNotFoundException, RoomNotFoundException,
			StudentNotFoundException, GenderTypeMismatchException, HostelRoomMismatchException {

		if (br.hasErrors()) {
			throw new ValidateAllotmentException(br.getFieldErrors());
		}
		Integer allotmentId = allotmentService.addAllotment(allotmentDto);
		return new SuccessMessage("Your generated allotment id is " + allotmentId);
	}

	@DeleteMapping("/remove/{aid}")
	public SuccessMessage deallocate(@PathVariable("aid") Integer allotmentId)
			throws AllotmentNotFoundException, RoomNotFoundException {

		allotmentService.removeAllotment(allotmentId);
		return new SuccessMessage("Deallocated for allotment Id= " + allotmentId);

	}

	@GetMapping("/get/{hid}")
	public List<Allotment> viewByHostelId(@PathVariable("hid") Integer hostelId)
			throws RoomNotFoundException, AllotmentNotFoundException,HostelNotFoundException {

		return allotmentService.viewAllotmentByHostelId(hostelId);
	}

}
