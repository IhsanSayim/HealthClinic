
package com.hastane.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hastane.model.Cerrahpasa_Process;
import com.hastane.model.Doktor;
import com.hastane.model.Hasta;

@Repository
@Transactional
public class ProcessDao {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {

		return _sessionFactory.getCurrentSession();
	}

	public void save(Cerrahpasa_Process process) {
		getSession().save(process);
		return;
	}

	public void delete(Cerrahpasa_Process process) {
		getSession().delete(process);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<Cerrahpasa_Process> getAll() {
		return getSession().createQuery("from Cerrahpasa_Process").list();
	}

	@SuppressWarnings("unchecked")
	public List<Cerrahpasa_Process> getByDoktor(Doktor doktor) {
		return (List<Cerrahpasa_Process>) getSession().createQuery("from Cerrahpasa_Process where doktor = :doktor")
				.setParameter("doktor", doktor).list();

	}

	@SuppressWarnings("unchecked")
	public List<Cerrahpasa_Process> getByHasta(Hasta hasta) {
		return (List<Cerrahpasa_Process>) getSession().createQuery("from Cerrahpasa_Process where hasta = :hasta")
				.setParameter("hasta", hasta).list();

	}

	public Cerrahpasa_Process getByIslemId(Long islemId) {
		return (Cerrahpasa_Process) getSession().createQuery("from Cerrahpasa_Process where islemId = :islemId")
				.setParameter("islemId", islemId).uniqueResult();
	}

	public void update(Cerrahpasa_Process process) {
		getSession().update(process);
		return;
	}

}