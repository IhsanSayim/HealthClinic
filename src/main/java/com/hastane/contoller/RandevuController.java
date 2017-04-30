package com.hastane.contoller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hastane.model.Doktor;
import com.hastane.model.Hasta;
import com.hastane.model.Randevu;
import com.hastane.service.DoktorDao;
import com.hastane.service.HastaDao;
import com.hastane.service.RandevuDao;

@Controller
@RequestMapping("/randevu")
public class RandevuController {

	@Autowired
	private RandevuDao randevuDao;
	@Autowired
	private HastaDao hastaDao;
	@Autowired
	private DoktorDao doktorDao;

	@RequestMapping(value = "/delete/{randevu-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Randevu> delete(@PathVariable("randevu-id") Long randevuId) {
		try {
			Randevu randevu = new Randevu(randevuId);
			randevuDao.delete(randevu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return randevuDao.getAll();

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Randevu> getList() {
		try {
			return randevuDao.getAll();
		} catch (Exception e) {
			return new ArrayList<Randevu>();
		}
	}

	@RequestMapping(value = "{randevu-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Randevu readOneRandevu(@PathVariable("randevu-id") Long randevuId) {
		try {
			return randevuDao.getByRandevuId(randevuId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "doktor/{doktor-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Randevu> getDoktorList(@PathVariable("doktor-id") Long doktorId) {
		try {
			Doktor doktor = doktorDao.getByDoktorId(doktorId);
			return randevuDao.getByDoktor(doktor);

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@RequestMapping(value = "hasta/{hasta-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Randevu> getHastaList(@PathVariable("hasta-id") Long hastaId) {
		try {
			Hasta hasta = hastaDao.getByHastaId(hastaId);
			return randevuDao.getByHasta(hasta);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Randevu> create(@RequestBody Map<String, String> map) throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
		String date = map.get("randevuTarihi");
		Date randevuTarihi = formatter.parse(date);
		String s = formatter.format(randevuTarihi);

		String hastaId = map.get("hastaId");
		Hasta hasta = hastaDao.getByHastaId(Long.valueOf(hastaId));

		if (hasta == null) {
			throw new Exception("hasta id bos olamaz");
		}

		String doktorId = map.get("doktorId");
		Doktor doktor = doktorDao.getByDoktorId(Long.valueOf(doktorId));

		if (doktor == null) {
			throw new Exception("doktor id bos olamaz");
		}
		boolean checkDoktorFree = randevuDao.checkDoktorFree(doktor, randevuTarihi);

		if (!checkDoktorFree) {
			throw new Exception("Doktor dolu");
		}

		Randevu randevu = new Randevu(randevuTarihi, hasta, doktor);

		randevuDao.save(randevu);

		return randevuDao.getAll();
	}

	@RequestMapping(value = "update/{randevu-id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Randevu> update(@PathVariable("randevu-id") Long randevuId,
			@RequestBody Map<String, String> map) throws ParseException {
		Randevu randevu = randevuDao.getByRandevuId(randevuId);
		if (randevu == null)
			return randevuDao.getAll();
		String doktorId = map.get("doktorId");
		String hastaId = map.get("hastaId");

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
		String date = map.get("randevuTarihi");
		Date randevuTarihi = formatter.parse(date);

		Doktor doktor = doktorDao.getByDoktorId(Long.valueOf(doktorId));
		Hasta hasta = hastaDao.getByHastaId(Long.valueOf(hastaId));
		if (randevuTarihi != null)
			randevu.setRandevuTarihi(randevuTarihi);
		if (doktor != null)
			randevu.setDoktor(doktor);
		if (hasta != null)
			randevu.setHasta(hasta);

		randevuDao.update(randevu);

		return randevuDao.getAll();
	}

}
