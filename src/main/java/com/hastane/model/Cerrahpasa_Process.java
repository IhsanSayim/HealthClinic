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
@Table(name = "CERRAHPASA_PROCESS")

public class Cerrahpasa_Process {

	@SequenceGenerator(name = "S_PROCESS_G", sequenceName = "S_PROCESS")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "S_PROCESS_G")

	@Column(name = "islem_id")
	private Long islemId;

	@Column(name = "islem_name")
	private String islemName;

	@Column(name = "islem_tarihi")
	@Temporal(TemporalType.TIMESTAMP)
	private Date islemTarihi;

	@Column(name = "islem_recete")
	private String islemRecete;

	@ManyToOne
	@JoinColumn(name = "DOKTOR_ID")
	private Doktor doktor;

	@ManyToOne
	@JoinColumn(name = "HASTA_ID")
	private Hasta hasta;

	public Long getIslemId() {
		return islemId;
	}

	public void setIslemId(Long islemId) {
		this.islemId = islemId;
	}

	public String getIslemName() {
		return islemName;
	}

	public void setIslemName(String islemName) {
		this.islemName = islemName;
	}

	public Date getIslemTarihi() {
		return islemTarihi;
	}

	public void setIslemTarihi(Date islemTarihi) {
		this.islemTarihi = islemTarihi;
	}

	public String getIslemRecete() {
		return islemRecete;
	}

	public void setIslemRecete(String islemRecete) {
		this.islemRecete = islemRecete;
	}

	public Doktor getDoktor() {
		return doktor;
	}

	public void setDoktor(Doktor doktor) {
		this.doktor = doktor;
	}

	public Hasta getHasta() {
		return hasta;
	}

	public void setHasta(Hasta hasta) {
		this.hasta = hasta;
	}

	public Cerrahpasa_Process() {
		super();
	}

	public Cerrahpasa_Process(String islemName, Date islemTarihi, String islemRecete, Doktor doktor, Hasta hasta) {
		super();
		this.islemName = islemName;
		this.islemTarihi = islemTarihi;
		this.islemRecete = islemRecete;
		this.doktor = doktor;
		this.hasta = hasta;
	}

	public Cerrahpasa_Process(Long islemId) {
		super();
		this.islemId = islemId;
	}

}
