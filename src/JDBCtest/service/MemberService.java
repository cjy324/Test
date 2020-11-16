package JDBCtest.service;

import JDBCtest.container.Container;
import JDBCtest.dao.MemberDao;
import JDBCtest.dto.Member;

public class MemberService {
	
	MemberDao memberDao;
	
	public MemberService() {
		memberDao = Container.memberDao;
		
	}

	public int join(String joinId, String joinPw, String name) {
		return memberDao.join( joinId,  joinPw,  name);
	}

	public Member getMemberByloginId(String loginId) {
		return memberDao.getMemberByloginId(loginId);
	}

	public boolean checkUsableLoginIdBy(String joinId) {
		Member member = memberDao.getMemberByloginId(joinId);
		if(member == null) {
			return false;
		}
		
		return true;
	}

	public Member getMemberByMemberId(int memberId) {
		return memberDao.getMemberByMemberId(memberId);
	}

	public int getDefultMemberId(int i) {
		Member member = memberDao.getMemberByMemberId(i);
		return member.memberId;
	}

}
