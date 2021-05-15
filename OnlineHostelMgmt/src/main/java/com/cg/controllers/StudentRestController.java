package com.cg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.StudentDTO;
import com.cg.entities.Student;
import com.cg.services.IStudentService;

@RestController
@RequestMapping("/student")
public class StudentRestController {

	@Autowired
	IStudentService studentService;

	@PostMapping("/add")
	public ResponseEntity<Student> addStudent(@RequestBody StudentDTO studentDto) {
		return new ResponseEntity<Student>(studentService.addStudent(studentDto), HttpStatus.CREATED);

	}

	@GetMapping("/get")
	public ResponseEntity<List<Student>> viewStudents() {
		return new ResponseEntity<List<Student>>(studentService.getStudents(), HttpStatus.OK);
	}
}