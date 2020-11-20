package JDBCtest.dao;

import java.util.Map;

import JDBCtest.dto.Member;
import JDBCtest.mysqlutil.MysqlUtil;
import JDBCtest.mysqlutil.SecSql;

public class MemberDao {

	public MemberDao() {

	}

	public int join(String loginId, String loginPw, String name) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO member");
		sql.append("SET loginId = ?,", loginId);
		sql.append("loginPw = ?,", loginPw);
		sql.append("name = ?", name);

		return MysqlUtil.insert(sql);
	}

	public Member getMemberByLoginId(String loginId) {
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM member");
		sql.append("WHERE loginId = ?", loginId);
		
		Map<String, Object> memberMap = MysqlUtil.selectRow(sql);
		
		if(memberMap.isEmpty()) {
			return null;
		}
		
		return new Member(memberMap);
	}

	public Member getMemberById(int loginMemberId) {
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM member");
		sql.append("WHERE id = ?", loginMemberId);
		
		Map<String, Object> memberMap = MysqlUtil.selectRow(sql);
		
		if(memberMap.isEmpty()) {
			return null;
		}
		
		return new Member(memberMap);
	}

}
