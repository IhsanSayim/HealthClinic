package com.hastane.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CERRAHPASA_SECOND_PROCESS")
public class SecondProcess {

	@SequenceGenerator(name = "S_SECONDPROCESS_G", sequenceName = "S_SECONDPROCESS")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "S_SECONDPROCESS_G")

	@Column(name = "second_process_id")
	private Long secondProcessId;

	@Column(name = "second_process_name")
	private String secondProcessName;

	@Column(name = "second_process_tarihi")
	@Temporal(TemporalType.TIMESTAMP)
	private Date secondProcessTarihi;

	@ManyToOne
	@JoinColumn(name = "HASTA_ID")
	private Hasta hasta;

	@ManyToOne
	@JoinColumn(name = "NURSE_ID")
	private Nurse nurse;

	public SecondProcess(Long secondProcessId, String secondProcessName, Date secondProcessTarihi, Hasta hasta,
			Nurse nurse) {
		super();
		this.secondProcessId = secondProcessId;
		this.secondProcessName = secondProcessName;
		this.secondProcessTarihi = secondProcessTarihi;
		this.hasta = hasta;
		this.nurse = nurse;
	}

	public Hasta getHasta() {
		return hasta;
	}

	public void setHasta(Hasta hasta) {
		this.hasta = hasta;
	}

	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	public Long getSecondProcessId() {
		return secondProcessId;
	}

	public void setSecondProcessId(Long secondProcessId) {
		this.secondProcessId = secondProcessId;
	}

	public String getSecondProcessName() {
		return secondProcessName;
	}

	public void setSecondProcessName(String secondProcessName) {
		this.secondProcessName = secondProcessName;
	}

	public Date getSecondProcessTarihi() {
		return secondProcessTarihi;
	}

	public void setSecondProcessTarihi(Date secondProcessTarihi) {
		this.secondProcessTarihi = secondProcessTarihi;
	}

	public SecondProcess() {
		super();
	}

	public SecondProcess(Long secondProcessId) {
		super();
		this.secondProcessId = secondProcessId;
	}

	public SecondProcess(String secondProcessName, Date secondProcessTarihi, Hasta hasta, Nurse nurse) {
		super();
		this.secondProcessName = secondProcessName;
		this.secondProcessTarihi = secondProcessTarihi;
		this.hasta = hasta;
		this.nurse = nurse;
	}

}
