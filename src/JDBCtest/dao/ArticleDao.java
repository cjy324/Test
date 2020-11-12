package JDBCtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBCtest.dto.Article;

public class ArticleDao {

	private List<Article> articles;
	private Connection conn;
	private Statement state;
	private String driver;
	private String url;
	private String sql;

	public ArticleDao() {
		articles = new ArrayList<>();
		conn = null;
		state = null;
		driver = "com.mysql.cj.jdbc.Driver";   
		url = "jdbc:mysql://localhost/a2?serverTimezone=UTC";
		sql = "";
	}

	// 리스팅
	public List<Article> getArticles() {
		try {
			Class.forName(driver); //driver를 깨우는 것?
			//이전에는 반드시 사전 실행되었어야 했지만
			//최근에는 자동으로 실행되서 굳이 작성하지 않아도 됨

			conn = DriverManager.getConnection(url, "sbsst", "sbs123414");

			state = conn.createStatement();

			sql = "SELECT * FROM article";
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String regDate = rs.getString("regDate");
				String title = rs.getString("title");
				String body = rs.getString("body");
				String nickname = rs.getString("nickname");
				int hit = rs.getInt("hit");

				Article article = new Article();

				article.id = id;
				article.regDate = regDate;
				article.title = title;
				article.body = body;
				article.nickname = nickname;
				article.hit = hit;
				articles.add(article);

			}
			rs.close();
			state.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		}

		return articles;

	}

	// 추가
	public int add(String title, String body) {
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, "sbsst", "sbs123414");

			state = conn.createStatement();

			sql = "INSERT INTO article " + "set " + "regDate = NOW()," + "title = " + "'" + title + "'," + "body = "
					+ "'" + body + "'," + "nickname = 'nickname'," + "hit = 10;";

			int cnt = state.executeUpdate(sql);
			System.out.println(cnt > 0 ? "등록성공" : "등록실패");

			conn.close();
			state.close();
		}

		catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		}

		return 0;
	}

	// 삭제
	public void deleteArticleById(int inputedId) {
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, "sbsst", "sbs123414");

			state = conn.createStatement();

			sql = "DELETE FROM article " + "WHERE id = " + inputedId + ";";

			int cnt = state.executeUpdate(sql);
			System.out.println(cnt > 0 ? "삭제성공" : "삭제실패");

			conn.close();
			state.close();
		}

		catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		}
	}

	// 수정
	public void modifyArticleById(int inputedId, String modifyTitle, String modifyBody) {
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, "sbsst", "sbs123414");

			state = conn.createStatement();

			sql = "UPDATE article " + "SET title = " + "'" + modifyTitle + "'" + "," + "`body` = " + "'" + modifyBody
					+ "'" + "WHERE id = " + inputedId + ";";
			
			state.executeUpdate(sql);
			int cnt = state.executeUpdate(sql);
			System.out.println(cnt > 0 ? "수정성공" : "수정실패");

			conn.close();
			state.close();
		}

		catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		}
	}

}
