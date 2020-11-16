package JDBCtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBCtest.dto.Article;

public class ArticleDao {
	
	String driver;
	Connection conn;
	String url;
	String userName;
	String userPw;
	String sql;
	
	
	public ArticleDao() {

		driver = "com.mysql.cj.jdbc.Driver";
		url = "jdbc:mysql://127.0.0.1:3306/textBoard?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull&connectTimeout=60000&socketTimeout=60000";
		userName = "sbsst";
		userPw = "sbs123414";

		
	}
	
	// 게시물 생성
	public int add(int boardId, String title, String body, int memberId) {
		int id = 0;

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

		sql = "INSERT INTO article ";
		sql += "SET ";
		sql += "regDate = NOW(), ";
		sql += "updateDate = NOW(), ";
		sql += "title = ?, ";
		sql += "body = ?, ";
		sql += "memberId = ?,";
		sql += "boardId = ?";
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, title);
			pstmt.setString(2, body);
			pstmt.setInt(3, memberId);
			pstmt.setInt(4, boardId);
			
			pstmt.executeUpdate();
			
			ResultSet addedArticleId = pstmt.getGeneratedKeys();
			addedArticleId.next();
			id = addedArticleId.getInt(1);
			
			
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

		return id;
	}

	// 게시물 리스팅
	public List<Article> getArticles() {
		List<Article> articles = new ArrayList<>();
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

			sql = "SELECT * FROM article";

			
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int id = rs.getInt("id");
					String regDate = rs.getString("regDate");
					String updateDate = rs.getString("updateDate");
					String title = rs.getString("title");
					String body = rs.getString("body");
					int boardId = rs.getInt("boardId");
					int memberId = rs.getInt("memberId");
					
					Article article = new Article(id,regDate,updateDate,title,body,boardId,memberId);
					
					articles.add(article);

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
		
		
		return articles;
	}

	// 게시물 수정
	public void modifyArticle(int inputedId, String title, String body) {
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

			sql = "UPDATE article ";
			sql += "SET ";
			sql += "updateDate = NOW(), ";
			sql += "title = ?, ";
			sql += "body = ? ";
			sql += "WHERE id = ? ";

			
			PreparedStatement pstmt;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, body);
				pstmt.setInt(3, inputedId);
				
				
				pstmt.executeUpdate();
				
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
	}

	// 게시물 삭제
	public void deleteArticle(int inputedId) {
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

			sql = "DELETE FROM article ";
			sql += "WHERE id = ? ";

			
			PreparedStatement pstmt;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, inputedId);
				
				
				pstmt.executeUpdate();
				
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
	}

}
