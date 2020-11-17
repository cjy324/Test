package JDBCtest.service;

import java.util.ArrayList;
import java.util.List;

import JDBCtest.container.Container;
import JDBCtest.dao.ArticleDao;
import JDBCtest.dao.MemberDao;
import JDBCtest.dto.Article;
import JDBCtest.dto.Board;
import JDBCtest.dto.Reply;

public class ArticleService {
	
	ArticleDao articleDao;
	
	public ArticleService() {
		
		articleDao = Container.articleDao;
	}

	public int add(int boardId, String title, String body, int memberId) {
		
		return articleDao.add( boardId,  title,  body,  memberId);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public void modifyArticle(int inputedId, String title, String body) {
		articleDao.modifyArticle(inputedId, title,  body);
		
	}

	public void deleteArticle(int inputedId) {
		articleDao.deleteArticle(inputedId);
		
	}

	public Article getArticle(int inputedId) {
		for(Article article : articleDao.getArticles()) {
			if(article.id == inputedId) {
				return article;
			}
		}
		
		return null;
	}

	public Article detailArticle(int inputedId) {
		for(Article article : articleDao.getArticles()) {
			if(article.id == inputedId) {
				return article;
			}
		}
		return null;
		
	}

	public int boardAdd(String boardName) {
		return articleDao.boardAdd(boardName);
	}

	public Board getBoard(int inputedId) {
		return articleDao.getBoard(inputedId);
	}

	public int getDefultBoardId(int i) {
		Board board = articleDao.getBoard(i);
		return board.boardId;
	}

	public List<Article> getBoardArticlesByBoardId(int boardId) {
		
		List<Article> boardArticles = new ArrayList<>();
		for(Article article : articleDao.getArticles()) {
			if(article.boardId == boardId) {
				boardArticles.add(article);
			}
		}
		return boardArticles;
	}

	public int addReply(int articleId, String replyBody, int replyWriterId) {
		return articleDao.addReply(articleId, replyBody, replyWriterId);
	}

	public List<Reply> getArticleReplies(int inputedId) {
		List<Reply> getArticleReplies = new ArrayList<>();
		for(Reply reply : articleDao.getReplies()) {
			if(reply.replyArticleId == inputedId) {
				getArticleReplies.add(reply);
			}
		}
				
				
		return getArticleReplies;
	}

}
