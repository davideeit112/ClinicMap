package clinicMap.tingyen.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements IMemberService {
	
	@Autowired
	private MemberDao mDao;
	
	@Override
	public Member queryMemberById(int memberID) {		
		return mDao.queryMemberById(memberID);
	}

}
