package JDBCtest.service;

import java.util.List;

import JDBCtest.container.Container;
import JDBCtest.dao.ArticleDao;
import JDBCtest.dto.Article;
import JDBCtest.dto.Board;
import JDBCtest.dto.Recommand;
import JDBCtest.dto.Reply;
import JDBCtest.dto.View;

public class ArticleSerivice {

	ArticleDao articleDao;

	public ArticleSerivice() {

		articleDao = Container.articleDao;

	}

	public int boardAdd(String name, String code) {
		return articleDao.boardAdd(name, code);
	}

	public Board getBoardById(int inputedId) {
		return articleDao.getBoardById(inputedId);
	}

	public int add(int boardId, String title, String body, int memberId) {
		return articleDao.add(boardId, title, body, memberId);
	}

	public List<Article> getBoardArticlesForPrint(int boardId) {
		return articleDao.getBoardArticlesForPrint(boardId);
	}

	public Article getArticleById(int inputedId) {
		return articleDao.getArticleById(inputedId);
	}

	public void articleModify(int id, String title, String body) {
		articleDao.articleModify(id, title, body);

	}

	public void articleDelete(int id) {
		articleDao.articleDelete(id);

	}

	public int replyAdd(int articleId, String replyBody, int replyMemberId) {
		return articleDao.replyAdd(articleId, replyBody, replyMemberId);
	}

	public Reply getReply(int inputedId) {
		return articleDao.getReply(inputedId);
	}

	public void replyModify(int id, String replyBody) {
		articleDao.replyModify(id, replyBody);

	}

	public void replyDelete(int id) {
		articleDao.replyDelete(id);

	}

	public List<Reply> getRepliesForPrint(int articleId) {
		return articleDao.getRepliesForPrint(articleId);
	}

	public int recommandAdd(int articleId, int recommandMemberId) {
		return articleDao.recommandAdd(articleId, recommandMemberId);
	}

	public Recommand getRecommand(int articleId, int recommandMemberId) {
		return articleDao.getRecommand(articleId, recommandMemberId);
	}

	public void recomandDelete(int articleId, int recommandMemberId) {
		articleDao.recomandDelete(articleId, recommandMemberId);

	}

	public List<Recommand> getRecommands(int articleId) {
		return articleDao.getRecommands(articleId);
	}

	public void addView(int articleId) {
		articleDao.addView(articleId);

	}

	public List<View> getViews(int articleId) {
		return articleDao.getViews(articleId);
	}

	public List<Board> getBoards() {
		return articleDao.getBoards();
	}

	public Board getBoardByCode(String code) {
		return articleDao.getBoardByCode(code);
	}

}
