package com.hastane.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CERRAHPASA_DOKTOR")

public class Doktor {

	@SequenceGenerator(name = "S_DOKTOR_G", sequenceName = "S_DOKTOR")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "S_DOKTOR_G")
	@Column(name = "doktor_id")
	private long doktorId;

	@Column(name = "doktor_name")
	private String doktorName;

	@Column(name = "doktor_lastname")
	private String doktorLastname;

	@Column(name = "doktor_email")
	private String doktorEmail;

	@Column(name = "doktor_telefon_numara")
	private String doktorTelefonNumara;

	public long getDoktorId() {
		return doktorId;
	}

	public void setDoktorId(long doktorId) {
		this.doktorId = doktorId;
	}

	public String getDoktorName() {
		return doktorName;
	}

	public void setDoktorName(String doktorName) {
		this.doktorName = doktorName;
	}

	public String getDoktorLastname() {
		return doktorLastname;
	}

	public void setDoktorLastname(String doktorLastname) {
		this.doktorLastname = doktorLastname;
	}

	public String getDoktorEmail() {
		return doktorEmail;
	}

	public void setDoktorEmail(String doktorEmail) {
		this.doktorEmail = doktorEmail;
	}

	public String getDoktorTelefonNumara() {
		return doktorTelefonNumara;
	}

	public void setDoktorTelefonNumara(String doktorTelefonNumara) {
		this.doktorTelefonNumara = doktorTelefonNumara;
	}

	public Doktor() {
		super();
	}

	public Doktor(String doktorName, String doktorLastname, String doktorEmail, String doktorTelefonNumara) {
		super();
		this.doktorName = doktorName;
		this.doktorLastname = doktorLastname;
		this.doktorEmail = doktorEmail;
		this.doktorTelefonNumara = doktorTelefonNumara;
	}

	public Doktor(long doktorId) {
		super();
		this.doktorId = doktorId;
	}

}
