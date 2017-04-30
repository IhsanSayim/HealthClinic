package com.hastane.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CERRAHPASA_NURSE")
public class Nurse {

	@SequenceGenerator(name = "S_NURSE_G", sequenceName = "S_NURSE")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "S_NURSE_G")

	@Column(name = "nurse_id")
	private Long nurseId;

	@Column(name = "nurse_name")
	private String nurseName;

	@Column(name = "nurse_lastname")
	private String nurseLastname;

	@Column(name = "nurse_email")
	private String nurseEmail;

	@Column(name = "nurse_telephone_number")
	private String nurseTelephoneNumber;

	public Long getNurseId() {
		return nurseId;
	}

	public void setNurseId(Long nurseId) {
		this.nurseId = nurseId;
	}

	public String getNurseName() {
		return nurseName;
	}

	public void setNurseName(String nurseName) {
		this.nurseName = nurseName;
	}

	public String getNurseLastname() {
		return nurseLastname;
	}

	public void setNurseLastname(String nurseLastname) {
		this.nurseLastname = nurseLastname;
	}

	public String getNurseEmail() {
		return nurseEmail;
	}

	public void setNurseEmail(String nurseEmail) {
		this.nurseEmail = nurseEmail;
	}

	public String getNurseTelephoneNumber() {
		return nurseTelephoneNumber;
	}

	public void setNurseTelephoneNumber(String nurseTelephoneNumber) {
		this.nurseTelephoneNumber = nurseTelephoneNumber;
	}

	public Nurse(String nurseName, String nurseLastname, String nurseEmail, String nurseTelephoneNumber) {
		super();
		this.nurseName = nurseName;
		this.nurseLastname = nurseLastname;
		this.nurseEmail = nurseEmail;
		this.nurseTelephoneNumber = nurseTelephoneNumber;
	}

	public Nurse(Long nurseId) {
		super();
		this.nurseId = nurseId;
	}

	public Nurse() {
		super();
	}

}
