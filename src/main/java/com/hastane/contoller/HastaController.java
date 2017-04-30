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

import com.hastane.model.Hasta;
import com.hastane.myenum.Cinsiyet;
import com.hastane.myenum.KanGrubu;
import com.hastane.service.HastaDao;

@Controller
@RequestMapping("/hasta")
public class HastaController {
	@Autowired
	private HastaDao hastaDao;

	@RequestMapping(value = "/delete/{hasta-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Hasta> delete(@PathVariable("hasta-id") Long hastaId) {
		try {
			Hasta hasta = new Hasta(hastaId);
			hastaDao.delete(hasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hastaDao.getAll();

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Hasta> getList() {
		try {
			return hastaDao.getAll();
		} catch (Exception e) {
			return new ArrayList<Hasta>();
		}
	}

	@RequestMapping(value = "{hasta-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Hasta readOneHasta(@PathVariable("hasta-id") Long hastaId) {
		try {
			return hastaDao.getByHastaId(hastaId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/get-by-email/{hasta-email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Hasta getByEmail(@PathVariable("hasta-email") String hastaEmail) {
		try {
			return hastaDao.getByHastaEmail(hastaEmail);
		} catch (Exception ex) {
			return null;
		}
	}

	@RequestMapping(value = "/get-by-tc-kimlik/{hasta-tc-kimlik}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Hasta getByHastaTcKimlik(@PathVariable("hasta-tc-kimlik") String hastaTcKimlik) {
		try {
			return hastaDao.getByHastaTcKimlik(hastaTcKimlik);
		} catch (Exception ex) {
			return null;
		}
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Hasta> create(@RequestBody Map<String, String> map) {
		try {
			String hastaName = map.get("hastaName");
			String hastaLastname = map.get("hastaLastname");
			String hastaEmail = map.get("hastaEmail");
			String hastaTelephoneNumber = map.get("hastaTelephoneNumber");
			String hastaTcKimlik = map.get("hastaTcKimlik");
			String cins = map.get("cinsiyet");
			Cinsiyet cinsiyet = Cinsiyet.getCinsiyet(cins);
			String kan = map.get("kanGrubu");
			KanGrubu kanGrubu = KanGrubu.getKanGrubu(kan);

			Hasta hasta = new Hasta(hastaName, hastaLastname, hastaEmail, hastaTelephoneNumber, hastaTcKimlik, cinsiyet,
					kanGrubu);
			hastaDao.save(hasta);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return hastaDao.getAll();
	}

	@RequestMapping(value = "update/{hasta-id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Hasta> update(@PathVariable("hasta-id") Long hastaId,
			@RequestBody Map<String, String> map) {
		Hasta hasta = hastaDao.getByHastaId(hastaId);
		if (hasta == null)
			return hastaDao.getAll();

		String hastaName = map.get("hastaName");
		String hastaLastname = map.get("hastaLastname");
		String hastaEmail = map.get("hastaEmail");
		String hastaTelephoneNumber = map.get("hastaTelephoneNumber");
		String hastaTcKimlik = map.get("hastaTcKimlik");
		String cins = map.get("cinsiyet");
		Cinsiyet cinsiyet = Cinsiyet.getCinsiyet(cins);
		String kan = map.get("kanGrubu");
		KanGrubu kanGrubu = KanGrubu.getKanGrubu(kan);

		if (hastaName != null)
			hasta.setHastaName(hastaName);
		if (hastaLastname != null)
			hasta.setHastaLastname(hastaLastname);
		if (hastaEmail != null)
			hasta.setHastaEmail(hastaEmail);
		if (hastaTelephoneNumber != null)
			hasta.setHastaTelephoneNumber(hastaTelephoneNumber);
		if (hastaTcKimlik != null)
			hasta.setHastaTcKimlik(hastaTcKimlik);
		if (cinsiyet != null)
			hasta.setCinsiyet(cinsiyet);
		if (kanGrubu != null)
			hasta.setKanGrubu(kanGrubu);

		hastaDao.update(hasta);

		return hastaDao.getAll();
	}

}
