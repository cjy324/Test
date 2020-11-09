package test.service;

import java.util.ArrayList;
import java.util.List;

import test.container.Container;
import test.dao.ArticleDao;
import test.dto.Article;
import test.dto.Board;

public class ArticleService {

	ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public int add(int boardNum, String title, String body, int writerNum) {
		return articleDao.add(boardNum, title, body, writerNum);
	}

	public List<Article> getArticles() {
		return articleDao.articles();
	}

	public Article getArticleByNum(int inputedNum) {
		return articleDao.getArticleByNum(inputedNum);
	}

	public List<Article> getSearchedArticlesByKeyword(String inputedKeyword) {

		List<Article> searchedArticle = new ArrayList<>();

		for (Article article : getArticles()) {
			if (article.title.contains(inputedKeyword)) {
				searchedArticle.add(article);
			}
		}
		return searchedArticle;

	}

	public int makeBoard(String bTitle) {
		return articleDao.makeBoard(bTitle);
	}

	public Board getBoardByNum(int inputedNum) {
		return articleDao.getBoardByNum(inputedNum);
	}

	public List<Article> getArticlesByBoardNum(int selectedBoardNum) {
		List<Article> selectedArticles = new ArrayList<>();
		for (Article article : getArticles()) {
			if (article.boardNum == selectedBoardNum) {
				selectedArticles.add(article);
			}
		}
		return selectedArticles;

	}

	public int getDefultBoardNum() {
		List<Board> boards = articleDao.getBoards();
		return boards.get(1).bNum;
	}

}
