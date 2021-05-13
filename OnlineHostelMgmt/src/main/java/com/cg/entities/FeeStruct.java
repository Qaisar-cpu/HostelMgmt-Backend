package com.cg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fee_structures")
public class FeeStruct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="fee_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="student_id",referencedColumnName="student_id")
	private Student student;
	
	@Column(name="total_fees")
	private Double totalFees;
	
	@ManyToOne
	@JoinColumn(name="allotment_id",referencedColumnName="allotment_id")
	private Allotment allotment;

	public FeeStruct() {
		super();
	}

	public FeeStruct(Integer id, Student student, Double totalFees, Allotment allotment) {
		super();
		this.id = id;
		this.student = student;
		this.totalFees = totalFees;
		this.allotment = allotment;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Double getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(Double totalFees) {
		this.totalFees = totalFees;
	}

	public Allotment getAllotment() {
		return allotment;
	}

	public void setAllotment(Allotment allotment) {
		this.allotment = allotment;
	}

	@Override
	public String toString() {
		return  id + "" + totalFees ;
	}

}
