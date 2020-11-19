package JDBCtest.dto;

import java.util.Map;

public class Member {

	
	public int memberId;
	public String loginId;
	public String loginPw;
	public String name;
	
	public Member(Map<String, Object> memberMap) {
		this.memberId = (int)memberMap.get("memberId");
		this.loginId = (String)memberMap.get("loginId");
		this.loginPw = (String)memberMap.get("loginPw");
		this.name = (String)memberMap.get("name");
	}

	public boolean isAdmin() {			//관리자 아이디 지정
		return loginId.equals("asd");
	}
	
	public String getType() {      //isAdmin()를 통한 회원 유형 확인
		return isAdmin() ? "관리자" : "일반회원";
	}
	
	
}
