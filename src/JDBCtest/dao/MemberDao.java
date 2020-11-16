package JDBCtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBCtest.dto.Member;
import JDBCtest.mysqlutil.MysqlUtil;

public class MemberDao {

	String driver;
	Connection conn;
	String url;
	String userName;
	String userPw;
	String sql;
	List<Member> members;
	
	
	public MemberDao() {

		driver = "com.mysql.cj.jdbc.Driver";
		url = "jdbc:mysql://127.0.0.1:3306/textBoard?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull&connectTimeout=60000&socketTimeout=60000";
		userName = "sbsst";
		userPw = "sbs123414";
		members = new ArrayList<>();
		
	}
	
	// 회원가입
	public int join(String joinId, String joinPw, String name) {
		int memberId = 0;
		
		try {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(url, userName, userPw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sql = "INSERT INTO member ";
		sql += "SET ";
		sql += "loginId = ?, ";
		sql += "loginPw = ?, ";
		sql += "name = ?";
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, joinId);
			pstmt.setString(2, joinPw);
			pstmt.setString(3, name);
			
			pstmt.executeUpdate();
			
			ResultSet addedMemberId = pstmt.getGeneratedKeys();
			addedMemberId.next();
			memberId = addedMemberId.getInt(1);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		finally {
			try {
				if (conn != null) {
					conn.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return memberId;
	}
	
	// 로그인 아이디 확인
	public Member getMemberByloginId(String loginId) {

		try {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(url, userName, userPw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sql = "SELECT * FROM member ";
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				if(rs.getString("loginId").equals(loginId) == true) {
					int memberId = rs.getInt("memberId");
					String memberloginId = rs.getString("loginId");
					String loginPw = rs.getString("loginPw");
					String name = rs.getString("name");
					
					Member member = new Member(memberId, memberloginId, loginPw, name);
					
					return member;
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		finally {
			try {
				if (conn != null) {
					conn.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return null;
	}

	// 아이디 중복 확인
	public Member getMemberByMemberId(int memberId) {
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				conn = DriverManager.getConnection(url, userName, userPw);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			sql = "SELECT * FROM member ";
			
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql);
				
				ResultSet rs = pstmt.executeQuery();

				while(rs.next()) {
					
					if(rs.getInt("memberId") == memberId) {
						memberId = rs.getInt("memberId");
						String loginId = rs.getString("loginId");
						String loginPw = rs.getString("loginPw");
						String name = rs.getString("name");
						
						Member member = new Member(memberId, loginId, loginPw, name);
						
						return member;
					}
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			}
			finally {
				try {
					if (conn != null) {
						conn.close();

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			return null;
		}

}
