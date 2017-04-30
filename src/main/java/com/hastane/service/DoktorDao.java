package com.hastane.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hastane.model.Doktor;

@Repository
@Transactional
public class DoktorDao {
	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(Doktor doktor) {
		getSession().save(doktor);
		return;
	}

	public void delete(Doktor doktor) {
		getSession().delete(doktor);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<Doktor> getAll() {
		return getSession().createQuery("from Doktor").list();
	}

	public Doktor getByDoktorId(Long doktorId) {
		return (Doktor) getSession().createQuery("from Doktor where doktorId = :doktorId")
				.setParameter("doktorId", doktorId).uniqueResult();
	}

	public void update(Doktor doktor) {
		getSession().update(doktor);
		return;
	}

}
