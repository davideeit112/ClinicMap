package clinicMap.tingyen.model;

import java.util.List;
import java.util.Map;

public interface IAppointmentService {
	public Appointment saveAppoint(String appointmentID, Appointment appointment);
	public List<Appointment> queryAllAppointment(String clinicID,String today);
	public Appointment querySingleAppointment(String appointmentID);
	public Appointment updateAppointmentStatus(String appointmentID,String appointmentStatus);
	public Appointment updateAppointmentData(String appointmentID,String appointmentType,String sickDescription,int appointmentNumber);
	public  boolean deleteAppointment(String appointmentID);
	public Map<String,Integer> analyzeAppointmentData(String clinicID,String dateForm);
}
