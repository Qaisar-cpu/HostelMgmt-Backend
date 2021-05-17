package com.cg.dto;

import javax.validation.constraints.*;

public class WardenDto {
	
	
	private Integer id;
	
	@NotBlank(message = "Email must not be blank")
	@Pattern(regexp = "[a-zA-Z0-9_]+@[a-zA-Z]+.[a-zA-Z]+")
	private String email;
	
	@NotBlank(message = "Name must not be blank")
	private String name;
	
	@NotNull(message = "Hostel Id must not be blank")
	private Long hostelId;
	
	
	public WardenDto() {
		super();
	}

	public WardenDto(Integer id, String email, String name, Long hostelId) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.hostelId = hostelId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getHostelId() {
		return hostelId;
	}

	public void setHostel(Long hostelId) {
		this.hostelId = hostelId;
	}

}
