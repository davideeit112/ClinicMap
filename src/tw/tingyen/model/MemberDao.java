package tw.tingyen.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao implements IMemberDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public Member queryMemberById(int memberID) {
		Session session = sessionFactory.getCurrentSession();
		Member mBean = session.get(Member.class,memberID);
		return mBean;
	}

}
