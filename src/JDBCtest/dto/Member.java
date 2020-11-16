package JDBCtest.dto;

public class Member {

	
	public int memberId;
	public String loginId;
	public String loginPw;
	public String name;
	
	public Member(int memberId, String loginId, String loginPw, String name) {
		this.memberId = memberId;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		
		
	}
	
	
}
