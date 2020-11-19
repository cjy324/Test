package JDBCtest.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import JDBCtest.dto.Article;
import JDBCtest.dto.Board;
import JDBCtest.dto.Recommand;
import JDBCtest.dto.Reply;
import JDBCtest.dto.View;
import JDBCtest.mysqlutil.MysqlUtil;
import JDBCtest.mysqlutil.SecSql;

public class ArticleDao {

	public ArticleDao() {

	}

	// 게시물 생성
	public int add(int boardId, String title, String body, int memberId) {

		SecSql sql = new SecSql();

		sql.append("INSERT INTO article ").append("SET ").append("regDate = NOW(), ").append("updateDate = NOW(), ")
				.append("title = ?, ", title).append("body = ?, ", body).append("memberId = ?, ", memberId)
				.append("boardId = ?", boardId);

		return MysqlUtil.insert(sql);
	}

	// 게시물 리스팅x => 게시물s 가져오기만 수행
	public List<Article> getArticles() {

		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM article ");

		List<Article> articles = new ArrayList<Article>();
		List<Map<String, Object>> articleListMap = MysqlUtil.selectRows(sql); // sql을 입력받고 명령어 수행 후 출력되는 값을 돌려받아
																				// articleListMap에 넣어주는 코드

		for (Map<String, Object> articleMap : articleListMap) {
			Article article = new Article(articleMap);

			articles.add(article);
		}
		return articles;
	}

	// 게시물 수정
	public void modifyArticle(int inputedId, String title, String body) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append("SET updateDate = NOW(),");
		sql.append("title = ?,", title);
		sql.append("body = ?", body);
		sql.append("WHERE id = ?", inputedId);

		MysqlUtil.update(sql);
	}

	// 게시물 삭제
	public void deleteArticle(int inputedId) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM article ").append("WHERE id = ?", inputedId);

		MysqlUtil.delete(sql);
	}

	// 게시판 추가
	public int addBoard(String boardName) {

		SecSql sql = new SecSql();
		sql.append("INSERT INTO board ");
		sql.append("SET boardName = ?", boardName);

		return MysqlUtil.insert(sql);
	}

	// 게시판 가져오기
	public Board getBoard(int inputedId) {
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM board WHERE boardId = ?", inputedId);

		Map<String, Object> boardMap = MysqlUtil.selectRow(sql);

		if (boardMap.isEmpty()) {
			return null;
		}

		return new Board(boardMap);
	}

	// 댓글 추가
	public int addReply(int ArticleId, String replyBody, int memberId) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO reply ");
		sql.append("SET replyBody = ?, ", replyBody);
		sql.append("replyArticleId = ?, ", ArticleId);
		sql.append("replyWriterId = ? ", memberId);

		return MysqlUtil.insert(sql);
	}

	// 댓글s 가져오기
	public List<Reply> getReplies() {
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM reply");

		List<Reply> replies = new ArrayList<>();
		List<Map<String, Object>> replyMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> replyMap : replyMapList) {
			Reply reply = new Reply(replyMap);

			replies.add(reply);
		}

		return replies;
	}

	// 댓글 가져오기
	public Reply getReply(int inputedId) {
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM reply WHERE replyId = ?", inputedId);

		Map<String, Object> replyMap = MysqlUtil.selectRow(sql);

		if (replyMap.isEmpty()) {
			return null;
		}

		return new Reply(replyMap);
	}

	// 댓글 수정
	public void replyModify(int inputedId, String replyBody) {
		SecSql sql = new SecSql();

		sql.append("UPDATE reply ");
		sql.append("SET replyBody = ? ", replyBody);
		sql.append("WHERE replyId = ? ", inputedId);

		MysqlUtil.update(sql);

	}

	// 댓글 삭제
	public void replyDelete(int inputedId) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM reply ");
		sql.append("WHERE replyId = ? ", inputedId);

		MysqlUtil.delete(sql);

	}

	// 출력용 게시물 리스트 가져오기(쿼리 한개만 수행)
	public List<Article> getArticlesForPrint() { // 오직 리스트 출력용으로만 사용될 함수
		SecSql sql = new SecSql();

		sql.append("SELECT article.*, member.name AS extra_memberName"); // inner join을 통해 쿼리를 한번만 실행해도
		sql.append("FROM article"); // 멤버의 이름까지 가져올 수 있도록 함
		sql.append("INNER JOIN member"); // 단, 이 퀴리 실행시 article 클래스 안에 member.name이라는 변수가 존재하지 않으므로
		sql.append("ON article.memberId = member.memberId"); // member.name를 extra_memberName로 명명후 article 클래스에
																// extra_memberName 변수 선언

		List<Article> articles = new ArrayList<Article>();
		List<Map<String, Object>> articleListMap = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articleMap : articleListMap) {
			Article article = new Article(articleMap);

			articles.add(article);
		}
		return articles;
	}

	// 추천 추가
	public int addRecommand(int id, int recommandMemberId) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO recommand");
		sql.append("SET");
		sql.append("recommandArticleId = ?, ", id);
		sql.append("recommandMemberId = ? ", recommandMemberId);

		return MysqlUtil.insert(sql);
	}

	// 추천 중복 확인
	public Recommand getRecommand(int articleId, int recommandMemberId) {
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM recommand");
		sql.append("WHERE recommandArticleId = ?", articleId);
		sql.append("AND");
		sql.append("recommandMemberId = ?", recommandMemberId);

		Map<String, Object> recommandMap = MysqlUtil.selectRow(sql);

		if (recommandMap.isEmpty()) {
			return null;
		}

		return new Recommand(recommandMap);
	}
	
	//추천 취소
	public void cancelRecommand(int articleId, int recommandMemberId) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM recommand");
		sql.append("WHERE recommandArticleId = ?", articleId);
		sql.append("AND");
		sql.append("recommandMemberId = ?", recommandMemberId);

		MysqlUtil.delete(sql);

	}

	//추천 가져오기
	public Recommand getRecommandByArticleId(int articleId, int recommandMemberId) {
		
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM recommand");
		sql.append("WHERE recommandArticleId = ?", articleId);
		sql.append("AND");
		sql.append("recommandMemberId = ?", recommandMemberId);

		Map<String, Object> recommandMap = MysqlUtil.selectRow(sql);

		if (recommandMap.isEmpty()) {
			return null;
		}

		return new Recommand(recommandMap);
	}
	
	//추천s 가져오기
	public List<Recommand> getRecommands() {
		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM recommand");

		List<Recommand> recommands = new ArrayList<>();
		List<Map<String, Object>> recommandsListMap = MysqlUtil.selectRows(sql); 

		for (Map<String, Object> recommandMap : recommandsListMap) {
			Recommand recommnad = new Recommand(recommandMap);

			recommands.add(recommnad);
		}
		return recommands;
	}

	//조회수 추가
	public void addView(int inputedId) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO view");
		sql.append("SET");
		sql.append("viewArticleId = ?", inputedId);

		MysqlUtil.insert(sql);
	}
	
	//조회수s 가져오기
	public List<View> getViews() {
		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM view");

		List<View> views = new ArrayList<>();
		List<Map<String, Object>> viewsListMap = MysqlUtil.selectRows(sql); 

		for (Map<String, Object> viewMap : viewsListMap) {
			View view = new View(viewMap);

			views.add(view);
		}
		return views;
	}

}
