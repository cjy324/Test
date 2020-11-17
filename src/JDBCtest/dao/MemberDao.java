package JDBCtest.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import JDBCtest.dto.Member;
import JDBCtest.mysqlutil.MysqlUtil;
import JDBCtest.mysqlutil.SecSql;


public class MemberDao {

	List<Member> members;
	
	public MemberDao() {

		members = new ArrayList<>();
		
	}
	
	// 회원가입
	public int join(String joinId, String joinPw, String name) {
		
		SecSql sql = new SecSql();

		sql.append("INSERT INTO member ");
		sql.append("SET ");
		sql.append("loginId = ?, ", joinId);
		sql.append("loginPw = ?, ", joinPw);
		sql.append("name = ?", name);

		return MysqlUtil.insert(sql);
	}
	
	// 로그인 아이디 확인
	public Member getMemberByloginId(String loginId) {

		SecSql sql = new SecSql();

		sql.append("SELECT * FROM member WHERE loginId = ?", loginId);
		
		Map<String, Object> memberMap = MysqlUtil.selectRow(sql);
		
		if(memberMap.isEmpty()) {
			return null;
		}
		
		return new Member(memberMap);
	}

	// 아이디 중복 확인
	public Member getMemberByMemberId(int memberId) {
		
			SecSql sql = new SecSql();

			sql.append("SELECT * FROM member WHERE memberId = ?", memberId);
			
			Map<String, Object> memberMap = MysqlUtil.selectRow(sql);
			
			if(memberMap.isEmpty()) {
				return null;
			}
			
			return new Member(memberMap);

	}

}
