package com.hastane.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.hastane.myenum.Cinsiyet;
import com.hastane.myenum.KanGrubu;

@Entity
@Table(name = "CERRAHPASA_HASTA")

public class Hasta {

	@SequenceGenerator(name = "S_HASTA_G", sequenceName = "S_HASTA")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "S_HASTA_G")

	@Column(name = "hasta_id")
	private Long hastaId;

	@Column(name = "hasta_name")
	private String hastaName;

	@Column(name = "hasta_lastname")
	private String hastaLastname;

	@Column(name = "hasta_email")
	private String hastaEmail;

	@Column(name = "hasta_telephone_number")
	private String hastaTelephoneNumber;

	@Column(name = "hasta_tc_kimlik")
	private String hastaTcKimlik;

	@Column(name = "hasta_cinsiyet")
	@Enumerated(EnumType.STRING)
	private Cinsiyet cinsiyet;

	@Column(name = "hasta_kan_grubu")
	@Enumerated(EnumType.STRING)
	private KanGrubu kanGrubu;

	public Cinsiyet getCinsiyet() {
		return cinsiyet;
	}

	public void setCinsiyet(Cinsiyet cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	public Hasta(KanGrubu kanGrubu) {
		super();
		this.kanGrubu = kanGrubu;
	}

	public KanGrubu getKanGrubu() {
		return kanGrubu;
	}

	public void setKanGrubu(KanGrubu kanGrubu) {
		this.kanGrubu = kanGrubu;
	}

	public Long getHastaId() {
		return hastaId;
	}

	public void setHastaId(Long hastaId) {
		this.hastaId = hastaId;
	}

	public String getHastaName() {
		return hastaName;
	}

	public void setHastaName(String hastaName) {
		this.hastaName = hastaName;
	}

	public String getHastaLastname() {
		return hastaLastname;
	}

	public void setHastaLastname(String hastaLastname) {
		this.hastaLastname = hastaLastname;
	}

	public String getHastaEmail() {
		return hastaEmail;
	}

	public void setHastaEmail(String hastaEmail) {
		this.hastaEmail = hastaEmail;
	}

	public String getHastaTelephoneNumber() {
		return hastaTelephoneNumber;
	}

	public void setHastaTelephoneNumber(String hastaTelephoneNumber) {
		this.hastaTelephoneNumber = hastaTelephoneNumber;
	}

	public String getHastaTcKimlik() {
		return hastaTcKimlik;
	}

	public void setHastaTcKimlik(String hastaTcKimlik) {
		this.hastaTcKimlik = hastaTcKimlik;
	}

	public Hasta() {
		super();
	}

	public Hasta(Long hastaId) {
		super();
		this.hastaId = hastaId;
	}

	public Hasta(String hastaName, String hastaLastname, String hastaEmail, String hastaTelephoneNumber,
			String hastaTcKimlik, Cinsiyet cinsiyet, KanGrubu kanGrubu) {
		super();
		this.hastaName = hastaName;
		this.hastaLastname = hastaLastname;
		this.hastaEmail = hastaEmail;
		this.hastaTelephoneNumber = hastaTelephoneNumber;
		this.hastaTcKimlik = hastaTcKimlik;
		this.cinsiyet = cinsiyet;
		this.kanGrubu = kanGrubu;
	}

}
