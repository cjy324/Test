package JDBCtest.service;

import java.util.ArrayList;
import java.util.List;

import JDBCtest.container.Container;
import JDBCtest.dao.ArticleDao;
import JDBCtest.dto.Article;
import JDBCtest.dto.Board;
import JDBCtest.dto.Reply;

public class ArticleService {

	ArticleDao articleDao;

	public ArticleService() {

		articleDao = Container.articleDao;
	}

	public int add(int boardId, String title, String body, int memberId) {

		return articleDao.add(boardId, title, body, memberId);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public void modifyArticle(int inputedId, String title, String body) {
		articleDao.modifyArticle(inputedId, title, body);

	}

	public void deleteArticle(int inputedId) {
		articleDao.deleteArticle(inputedId);

	}

	public Article getArticle(int inputedId) {
		for (Article article : articleDao.getArticles()) {
			if (article.id == inputedId) {
				return article;
			}
		}

		return null;
	}

	public Article detailArticle(int inputedId) {
		for (Article article : articleDao.getArticles()) {
			if (article.id == inputedId) {
				return article;
			}
		}
		return null;

	}

	public List<Article> getBoardArticlesByBoardId(int boardId) {

		List<Article> boardArticles = new ArrayList<>();
		for (Article article : articleDao.getArticles()) {
			if (article.boardId == boardId) {
				boardArticles.add(article);
			}
		}
		return boardArticles;
	}

	public int addBoard(String boardName) {
		return articleDao.addBoard(boardName);
	}

	public Board getBoard(int inputedId) {
		return articleDao.getBoard(inputedId);
	}

	public int getDefultBoardId(int i) {
		Board defultBoard = articleDao.getBoard(i);
		return defultBoard.boardId;
	}

	public int addReply(int ArticleId, String replyBody, int memberId) {
		return articleDao.addReply(ArticleId, replyBody, memberId);
	}

	public List<Reply> getRepliesByArticleId(int id) {
		List<Reply> articleReplies = new ArrayList<>();
		for (Reply reply : articleDao.getReplies()) {
			if (reply.replyArticleId == id) {
				articleReplies.add(reply);
			}
		}

		return articleReplies;
	}

	public Reply getReply(int inputedId) {
		return articleDao.getReply(inputedId);
	}

	public void replyModify(int inputedId, String replyBody) {
		articleDao.replyModify(inputedId, replyBody);
	}

	public void replyDelete(int inputedId) {
		articleDao.replyDelete(inputedId);
	}

	public List<Article> getBoardArticlesForPrintByBoardId(int boardId) {
		List<Article> boardArticles = new ArrayList<>();
		for (Article article : articleDao.getArticlesForPrint()) {
			if (article.boardId == boardId) {
				boardArticles.add(article);
			}
		}
		return boardArticles;
	}

}
