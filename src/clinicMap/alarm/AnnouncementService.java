package clinicMap.alarm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementService implements IAnnouncementService {

	@Autowired
	private AnnouncementDAO aDao;
	
	@Override
	public List<Announcement1> forAnnouncement() {
		return aDao.forAnnouncement();
	}

}
