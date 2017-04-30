
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

import com.hastane.model.Cerrahpasa_Process;
import com.hastane.model.Doktor;
import com.hastane.model.Hasta;
import com.hastane.service.DoktorDao;
import com.hastane.service.HastaDao;
import com.hastane.service.ProcessDao;

@Controller
@RequestMapping("/process")
public class ProcessController {

	@Autowired
	private ProcessDao processDao;
	@Autowired
	private DoktorDao doktorDao;
	@Autowired
	private HastaDao hastaDao;

	@RequestMapping(value = "/delete/{islem-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cerrahpasa_Process> delete(@PathVariable("islem-id") Long islemId) {
		try {
			Cerrahpasa_Process process = new Cerrahpasa_Process(islemId);
			processDao.delete(process);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return processDao.getAll();

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cerrahpasa_Process> getList() {
		try {
			return processDao.getAll();
		} catch (Exception e) {
			return new ArrayList<Cerrahpasa_Process>();
		}
	}

	@RequestMapping(value = "{islem-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Cerrahpasa_Process readOneProcess(@PathVariable("islem-id") Long islemId) {
		try {
			return processDao.getByIslemId(islemId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/doktor/{doktor-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cerrahpasa_Process> getDoktorList(@PathVariable("doktor-id") Long doktorId) {
		try {
			Doktor doktor = doktorDao.getByDoktorId(doktorId);
			return processDao.getByDoktor(doktor);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@RequestMapping(value = "/hasta/{hasta-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cerrahpasa_Process> getHastaList(@PathVariable("hasta-id") Long hastaId) {
		try {
			Hasta hasta = hastaDao.getByHastaId(hastaId);
			return processDao.getByHasta(hasta);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cerrahpasa_Process> create(@RequestBody Map<String, String> map) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
			String date = map.get("islemTarihi");
			Date islemTarihi = formatter.parse(date);
			String s = formatter.format(islemTarihi);

			String islemName = map.get("islemName");
			String islemRecete = map.get("islemRecete");

			String doktorId = map.get("doktorId");
			Doktor doktor = doktorDao.getByDoktorId(Long.valueOf(doktorId));
			if (doktor == null) {
				throw new Exception("doktor id bos olamaz");
			}

			String hastaId = map.get("hastaId");
			Hasta hasta = hastaDao.getByHastaId(Long.valueOf(hastaId));
			if (hasta == null) {
				throw new Exception("hasta id bos olamaz");
			}

			Cerrahpasa_Process process = new Cerrahpasa_Process(islemName, islemTarihi, islemRecete, doktor, hasta);
			processDao.save(process);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return processDao.getAll();
	}

	@RequestMapping(value = "update/{islem-id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cerrahpasa_Process> update(@PathVariable("islem-id") Long islemId,
			@RequestBody Map<String, String> map) throws ParseException {
		Cerrahpasa_Process process = processDao.getByIslemId(islemId);
		if (process == null)
			return processDao.getAll();

		String islemName = map.get("islemName");
		String islemRecete = map.get("islemRecete");
		String doktorId = map.get("doktorId");
		String hastaId = map.get("hastaId");

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
		String date = map.get("islemTarihi");
		Date islemTarihi = formatter.parse(date);

		Doktor doktor = doktorDao.getByDoktorId(Long.valueOf(doktorId));
		if (doktor != null) {
			process.setDoktor(doktor);
		}
		Hasta hasta = hastaDao.getByHastaId(Long.valueOf(hastaId));
		if (hasta != null) {
			process.setHasta(hasta);
		}

		if (islemName != null)
			process.setIslemName(islemName);
		if (islemTarihi != null)
			process.setIslemTarihi(islemTarihi);
		if (islemRecete != null)
			process.setIslemRecete(islemRecete);

		processDao.update(process);

		return processDao.getAll();
	}

}
