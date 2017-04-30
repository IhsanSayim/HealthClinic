package com.hastane.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hastane.model.Hasta;

@Repository
@Transactional
public class HastaDao {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(Hasta hasta) {
		getSession().save(hasta);
		return;
	}

	public void delete(Hasta hasta) {
		getSession().delete(hasta);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<Hasta> getAll() {
		return getSession().createQuery("from Hasta").list();
	}

	public Hasta getByHastaEmail(String hastaEmail) {
		return (Hasta) getSession().createQuery("from Hasta where hastaEmail = :hastaEmail")
				.setParameter("email", hastaEmail).uniqueResult();
	}

	public Hasta getByHastaId(Long hastaId) {
		return (Hasta) getSession().createQuery("from Hasta where hastaId = :hastaId").setParameter("hastaId", hastaId)
				.uniqueResult();
	}

	public Hasta getByHastaTcKimlik(String hastaTcKimlik) {
		return (Hasta) getSession().createQuery("from Hasta where hastaTcKimlik = :hastaTcKimlik")
				.setParameter("hastaTcKimlik", hastaTcKimlik).uniqueResult();
	}

	public void update(Hasta hasta) {
		getSession().update(hasta);
		return;
	}

}
