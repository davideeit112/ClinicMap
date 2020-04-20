package tw.tingyen.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService implements IAppointmentService {
	
	@Autowired
	private AppointmentDao aDao;
	
	@Override
	public Appointment saveAppoint(String appointmentID, Appointment appointment) {		
		return aDao.saveAppoint(appointmentID, appointment);
	}

	@Override
	public List<Appointment> queryAllAppointment(String clinicID,String today) {
		List<Appointment> list = aDao.queryAllAppointment(clinicID,today);
		return list;
	}

	@Override
	public Appointment querySingleAppointment(String appointmentID) {
		return aDao.querySingleAppointment(appointmentID);
	}

	@Override
	public Appointment updateAppointmentStatus(String appointmentID, String appointmentStatus) {
		return aDao.updateAppointmentStatus(appointmentID, appointmentStatus);
	}

	@Override
	public Appointment updateAppointmentData(String appointmentID, String appointmentType, String sickDescription,
			int appointmentNumber) {
		return aDao.updateAppointmentData(appointmentID, appointmentType, sickDescription, appointmentNumber);
	}

	@Override
	public boolean deleteAppointment(String appointmentID) {
		return aDao.deleteAppointment(appointmentID);
	}

	@Override
	public Map<String, Integer> analyzeAppointmentData(String clinicID, String dateForm) {
		return aDao.analyzeAppointmentData(clinicID, dateForm);
	}
	
}
