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

import com.hastane.model.Nurse;
import com.hastane.service.NurseDao;

@Controller
@RequestMapping("/nurse")

public class NurseController {
	@Autowired
	private NurseDao nurseDao;

	@RequestMapping(value = "/delete/{nurse-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Nurse> delete(@PathVariable("nurse-id") Long nurseId) {
		try {
			Nurse nurse = new Nurse(nurseId);
			nurseDao.delete(nurse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nurseDao.getAll();

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Nurse> getList() {
		try {
			return nurseDao.getAll();
		} catch (Exception e) {
			return new ArrayList<Nurse>();
		}
	}

	@RequestMapping(value = "{nurse-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Nurse readOneNurse(@PathVariable("nurse-id") Long nurseId) {
		try {
			return nurseDao.getByNurseId(nurseId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Nurse> create(@RequestBody Map<String, String> map) {
		try {
			String nurseName = map.get("nurseName");
			String nurseLastname = map.get("nurseLastname");
			String nurseEmail = map.get("nurseEmail");
			String nurseTelephoneNumber = map.get("nurseTelephoneNumber");

			Nurse nurse = new Nurse(nurseName, nurseLastname, nurseEmail, nurseTelephoneNumber);
			nurseDao.save(nurse);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return nurseDao.getAll();
	}

	@RequestMapping(value = "update/{nurse-id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Nurse> update(@PathVariable("nurse-id") Long nurseId,
			@RequestBody Map<String, String> map) {
		Nurse nurse = nurseDao.getByNurseId(nurseId);
		if (nurse == null)
			return nurseDao.getAll();

		String nurseName = map.get("nurseName");
		String nurseLastname = map.get("nurseLastname");
		String nurseEmail = map.get("nurseEmail");
		String nurseTelephoneNumber = map.get("nurseTelephoneNumber");

		if (nurseName != null)
			nurse.setNurseName(nurseName);
		if (nurseLastname != null)
			nurse.setNurseLastname(nurseLastname);
		if (nurseEmail != null)
			nurse.setNurseEmail(nurseEmail);
		if (nurseName != null)
			nurse.setNurseTelephoneNumber(nurseTelephoneNumber);

		nurseDao.update(nurse);

		return nurseDao.getAll();
	}

}
