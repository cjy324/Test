package JDBCtest.dto;

import java.util.Map;

public class Member {

	
	public int memberId;
	public String loginId;
	public String loginPw;
	public String name;
	
	/*
	 * public Member(int memberId, String loginId, String loginPw, String name) {
	 * this.memberId = memberId; this.loginId = loginId; this.loginPw = loginPw;
	 * this.name = name;
	 * 
	 * 
	 * }
	 */

	public Member(Map<String, Object> memberMap) {
		this.memberId = (int)memberMap.get("memberId");
		this.loginId = (String)memberMap.get("loginId");
		this.loginPw = (String)memberMap.get("loginPw");
		this.name = (String)memberMap.get("name");
	}
	
	
}
