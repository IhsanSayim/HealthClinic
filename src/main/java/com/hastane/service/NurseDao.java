package com.hastane.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hastane.model.Nurse;

@Repository
@Transactional
public class NurseDao {
	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(Nurse nurse) {
		getSession().save(nurse);
		return;
	}

	public void delete(Nurse nurse) {
		getSession().delete(nurse);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<Nurse> getAll() {
		return getSession().createQuery("from Nurse").list();
	}

	public Nurse getByNurseId(Long nurseId) {
		return (Nurse) getSession().createQuery("from Nurse where nurseId = :nurseId").setParameter("nurseId", nurseId)
				.uniqueResult();
	}

	public void update(Nurse nurse) {
		getSession().update(nurse);
		return;
	}

}
