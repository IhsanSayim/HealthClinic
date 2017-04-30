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

import com.hastane.model.Hasta;
import com.hastane.model.Nurse;
import com.hastane.model.SecondProcess;
import com.hastane.service.HastaDao;
import com.hastane.service.NurseDao;
import com.hastane.service.SecondProcessDao;

@Controller
@RequestMapping("/secondprocess")
public class SecondProcessController {

	@Autowired
	private SecondProcessDao secondProcessDao;
	@Autowired
	private HastaDao hastaDao;
	@Autowired
	private NurseDao nurseDao;

	@RequestMapping(value = "/delete/{secondprocess-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SecondProcess> delete(@PathVariable("secondprocess-id") Long secondProcessId) {
		try {
			SecondProcess secondProcess = new SecondProcess(secondProcessId);
			secondProcessDao.delete(secondProcess);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return secondProcessDao.getAll();

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SecondProcess> getList() {
		try {
			return secondProcessDao.getAll();
		} catch (Exception e) {
			return new ArrayList<SecondProcess>();
		}
	}

	@RequestMapping(value = "{secondprocess-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SecondProcess readOneNurse(@PathVariable("secondprocess-id") Long secondProcessId) {
		try {
			return secondProcessDao.getBySecondProcessId(secondProcessId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SecondProcess> create(@RequestBody Map<String, String> map) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
			String date = map.get("secondProcessTarihi");
			Date secondProcessTarihi = formatter.parse(date);

			String secondProcessName = map.get("secondProcessName");
			String hastaId = map.get("hastaId");
			Hasta hasta = hastaDao.getByHastaId(Long.valueOf(hastaId));

			if (hasta == null) {
				throw new Exception("hasta id bos olamaz");
			}

			String nurseId = map.get("nurseId");
			Nurse nurse = nurseDao.getByNurseId(Long.valueOf(nurseId));

			if (nurse == null) {
				throw new Exception("nurse id bos olamaz");
			}

			SecondProcess secondProcess = new SecondProcess(secondProcessName, secondProcessTarihi, hasta, nurse);
			secondProcessDao.save(secondProcess);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return secondProcessDao.getAll();
	}

	@RequestMapping(value = "update/{secondprocess-id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SecondProcess> update(@PathVariable("secondprocess-id") Long secondProcessId,
			@RequestBody Map<String, String> map) throws ParseException {
		SecondProcess secondProcess = secondProcessDao.getBySecondProcessId(secondProcessId);
		if (secondProcess == null)
			return secondProcessDao.getAll();

		String secondProcessName = map.get("secondProcessName");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
		String date = map.get("secondProcessTarihi");
		Date secondProcessTarihi = formatter.parse(date);

		String hastaId = map.get("hastaId");
		String nurseId = map.get("nurseId");

		Nurse nurse = nurseDao.getByNurseId(Long.valueOf(nurseId));
		Hasta hasta = hastaDao.getByHastaId(Long.valueOf(hastaId));
		if (secondProcessName != null)
			secondProcess.setSecondProcessName(secondProcessName);
		if (secondProcessTarihi != null)
			secondProcess.setSecondProcessTarihi(secondProcessTarihi);
		if (hasta != null)
			secondProcess.setHasta(hasta);
		if (nurse != null)
			secondProcess.setNurse(nurse);

		secondProcessDao.update(secondProcess);

		return secondProcessDao.getAll();
	}

}
