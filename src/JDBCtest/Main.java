package JDBCtest;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {

		Connection conn = null;
		Statement state = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/a2?serverTimezone=UTC";

			conn = DriverManager.getConnection(url, "sbsst", "sbs123414");
			System.out.println("=== 연결성공 ===");

			state = conn.createStatement();

			String sql;
			sql = "SELECT * FROM article";
			ResultSet rs = state.executeQuery(sql);

			System.out.println("=== 로딩결과 ===");
			while (rs.next()) {
				String id = rs.getString("id");
				String regDate = rs.getString("regDate");
				String title = rs.getString("title");
				String body = rs.getString("body");
				String nickname = rs.getString("nickname");
				String hit = rs.getString("hit");
				System.out.printf("id : %s\nregDate : %s\ntitle : %s\nbody : %s\nnickname : %s\nhit : %s\n----------\n",
						id, regDate, title, body, nickname, hit);

			}
			rs.close();
			state.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
