package com.hastane.contoller;

import java.util.ArrayList;
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

import com.hastane.service.DoktorDao;

@Controller
@RequestMapping("/doktor")
public class DoktorController {
	@Autowired
	private DoktorDao doktorDao;

	@RequestMapping(value = "/delete/{doktor-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Doktor> delete(@PathVariable("doktor-id") Long doktorId) {
		try {
			Doktor doktor = new Doktor(doktorId);
			doktorDao.delete(doktor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doktorDao.getAll();

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Doktor> getList() {
		try {
			return doktorDao.getAll();
		} catch (Exception e) {
			return new ArrayList<Doktor>();
		}
	}

	@RequestMapping(value = "{doktor-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Doktor readOneDoktor(@PathVariable("doktor-id") Long doktorId) {
		try {
			return doktorDao.getByDoktorId(doktorId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Doktor> create(@RequestBody Map<String, String> map) {
		try {
			String doktorName = map.get("doktorName");
			String doktorLastname = map.get("doktorLastname");
			String doktorEmail = map.get("doktorEmail");
			String doktorTelefonNumara = map.get("doktorTelefonNumara");

			Doktor doktor = new Doktor(doktorName, doktorLastname, doktorEmail, doktorTelefonNumara);
			doktorDao.save(doktor);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return doktorDao.getAll();
	}

	@RequestMapping(value = "update/{doktor-id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Doktor> update(@PathVariable("doktor-id") Long doktorId,
			@RequestBody Map<String, String> map) {
		Doktor doktor = doktorDao.getByDoktorId(doktorId);
		if (doktor == null)
			return doktorDao.getAll();

		String doktorName = map.get("doktorName");
		String doktorLastname = map.get("doktorLastname");
		String doktorEmail = map.get("doktorEmail");
		String doktorTelefonNumara = map.get("doktorTelefonNumara");

		if (doktorName != null)
			doktor.setDoktorName(doktorName);
		if (doktorLastname != null)
			doktor.setDoktorLastname(doktorLastname);
		if (doktorEmail != null)
			doktor.setDoktorEmail(doktorEmail);
		if (doktorTelefonNumara != null)
			doktor.setDoktorTelefonNumara(doktorTelefonNumara);

		doktorDao.update(doktor);

		return doktorDao.getAll();
	}

}
