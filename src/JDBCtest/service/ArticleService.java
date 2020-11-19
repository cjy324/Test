package JDBCtest.service;

import java.util.ArrayList;
import java.util.List;

import JDBCtest.container.Container;
import JDBCtest.dao.ArticleDao;
import JDBCtest.dto.Article;
import JDBCtest.dto.Board;
import JDBCtest.dto.Recommand;
import JDBCtest.dto.Reply;
import JDBCtest.dto.View;

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

	public int addRecommand(int id, int recommandMemberId) {
		return articleDao.addRecommand(id, recommandMemberId);
	}

	public boolean getRecommand(int articleId, int recommandMemberId) {
		Recommand recommand = articleDao.getRecommand(articleId,recommandMemberId);
		if (recommand == null) {
			return true;
		}
		return false;
	}

	public void cancelRecommand(int articleId, int recommandMemberId) {
		articleDao.cancelRecommand(articleId, recommandMemberId);

	}

	public Recommand getRecommandByArticleId(int articleId, int recommandMemberId) {
		return articleDao.getRecommandByArticleId(articleId, recommandMemberId);
	}

	public List<Recommand> getRecommands(int inputedId) {
		List<Recommand> articleRecommands = new ArrayList<>();
		for (Recommand recommand : articleDao.getRecommands()) {
			if (recommand.recommandArticleId == inputedId) {
				articleRecommands.add(recommand);
			}

		}
		return articleRecommands;

	}

	public void addView(int inputedId) {
		articleDao.addView(inputedId);
	}

	public List<View> getViews(int inputedId) {

		List<View> articleViews = new ArrayList<>();
		for (View view : articleDao.getViews()) {
			if (view.viewArticleId == inputedId) {
				articleViews.add(view);
			}

		}
		return articleViews;
	}

}
