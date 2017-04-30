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
@Table(name = "CERRAHPASA_RANDEVU")

public class Randevu {
	@SequenceGenerator(name = "S_RANDEVU_G", sequenceName = "S_RANDEVU")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "S_RANDEVU_G")

	@Column(name = "randevu_id")
	private Long randevuId;

	@Column(name = "randevu_tarihi")
	@Temporal(TemporalType.TIMESTAMP)
	private Date randevuTarihi;

	@ManyToOne
	@JoinColumn(name = "hasta_id")
	private Hasta hasta;

	@ManyToOne
	@JoinColumn(name = "doktor_id")
	private Doktor doktor;

	public Randevu(Date randevuTarihi, Hasta hasta, Doktor doktor) {
		super();
		this.randevuTarihi = randevuTarihi;
		this.hasta = hasta;
		this.doktor = doktor;
	}

	public Randevu(Long randevuId, Date randevuTarihi, Hasta hasta, Doktor doktor) {
		super();
		this.randevuId = randevuId;
		this.randevuTarihi = randevuTarihi;
		this.hasta = hasta;
		this.doktor = doktor;
	}

	public Doktor getDoktor() {
		return doktor;
	}

	public void setDoktor(Doktor doktor) {
		this.doktor = doktor;
	}

	public Randevu(Long randevuId, Date randevuTarihi, Hasta hasta) {
		super();
		this.randevuId = randevuId;
		this.randevuTarihi = randevuTarihi;
		this.hasta = hasta;
	}

	public Hasta getHasta() {
		return hasta;
	}

	public void setHasta(Hasta hasta) {
		this.hasta = hasta;
	}

	public Randevu(Long randevuId, Date randevuTarihi) {
		super();
		this.randevuId = randevuId;
		this.randevuTarihi = randevuTarihi;
	}

	public Randevu(Long randevuId) {
		super();
		this.randevuId = randevuId;
	}

	public Randevu(Date randevuTarihi) {
		super();
		this.randevuTarihi = randevuTarihi;
	}

	public Randevu() {
		super();
	}

	public Long getRandevuId() {
		return randevuId;
	}

	public void setRandevuId(Long randevuId) {
		this.randevuId = randevuId;
	}

	public Date getRandevuTarihi() {
		return randevuTarihi;
	}

	public void setRandevuTarihi(Date randevuTarihi) {
		this.randevuTarihi = randevuTarihi;
	}

}