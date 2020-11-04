package test3.service;

import test3.container.Container;
import test3.dao.MemberDao;
import test3.dto.Member;

public class MemberService {
	private MemberDao memberDao;

	public MemberService() {
		memberDao = Container.memberDao;
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId, loginPw, name);
	}

	public Member getMemberByLoginId(String inputedLoginId) {
		return memberDao.getMemberByLoginId(inputedLoginId);
	}

	public boolean checkJoinableLoginIdBy(String loginId) {
		if(memberDao.getEqualsLoginIdBy(loginId) == true) {
			return false;
		}
		return true;
	}

	public Member getMemberByLoginedMemberNum(int isLoginedMemberNum) {
		return memberDao.getEqualsLoginMemberBy(isLoginedMemberNum);
	}

}
