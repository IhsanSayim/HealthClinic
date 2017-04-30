package com.hastane.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hastane.model.SecondProcess;

@Repository
@Transactional
public class SecondProcessDao {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(SecondProcess secondProcess) {
		getSession().save(secondProcess);
		return;
	}

	public void delete(SecondProcess secondProcess) {
		getSession().delete(secondProcess);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<SecondProcess> getAll() {
		return getSession().createQuery("from SecondProcess").list();
	}

	public SecondProcess getBySecondProcessId(Long secondProcessId) {
		return (SecondProcess) getSession().createQuery("from SecondProcess where secondProcessId = :secondProcessId")
				.setParameter("secondProcessId", secondProcessId).uniqueResult();
	}

	public void update(SecondProcess secondProcess) {
		getSession().update(secondProcess);
		return;
	}

}
