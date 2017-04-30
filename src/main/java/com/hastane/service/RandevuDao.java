package com.hastane.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hastane.model.Doktor;
import com.hastane.model.Hasta;
import com.hastane.model.Randevu;
import com.hastane.util.DateUtil;

@Repository
@Transactional
public class RandevuDao {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(Randevu randevu) {
		getSession().save(randevu);
		return;
	}

	public void delete(Randevu randevu) {
		getSession().delete(randevu);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<Randevu> getAll() {
		return getSession().createQuery("from Randevu").list();
	}

	public Randevu getByRandevuId(Long randevuId) {
		return (Randevu) getSession().createQuery("from Randevu where randevuId = :randevuId")
				.setParameter("randevuId", randevuId).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Randevu> getByDoktor(Doktor doktor) {
		return (List<Randevu>) getSession().createQuery("from Randevu where doktor=:doktor")
				.setParameter("doktor", doktor).list();
	}

	@SuppressWarnings("unchecked")
	public boolean checkDoktorFree(Doktor doktor, Date randevuTarihi) {
		List<Randevu> rlist = (List<Randevu>) getSession()
				.createQuery(
						"from Randevu where doktor=:doktor and to_char(randevu_tarihi, 'YYYYMMDD HH24:MI') = :randevuTarihi ")
				.setParameter("doktor", doktor)
				.setParameter("randevuTarihi", DateUtil.toString(randevuTarihi, "yyyyMMdd HH:mm")).list();
		if (rlist.size() > 0)
			return false;
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Randevu> getByHasta(Hasta hasta) {
		return (List<Randevu>) getSession().createQuery("from Randevu where hasta=:hasta").setParameter("hasta", hasta)
				.list();
	}

	public void update(Randevu randevu) {
		getSession().update(randevu);
		return;
	}

}