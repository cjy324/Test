package test.service;

import java.util.ArrayList;
import java.util.Collections;
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

	public int addBoard(String bName) {
		return articleDao.addBoard(bName);
	}

	public Board getBoardByBNum(int selectedBoardNum) {
		return articleDao.getBoardByBNum(selectedBoardNum);
	}

	public int getBoardDefultNum() {
		return articleDao.getBoards().get(0).bNum;
	}

	public int add(int boardNum, String title, String body, int writerNum) {
		return articleDao.add(boardNum, title, body, writerNum);
	}

	public List<Article> getArticlesBySelectedBoard(int selectedBoard) {
		List<Article> boardArticles = new ArrayList<>();
		for (Article article : articleDao.getArticles()) {
			if (article.boardNum == selectedBoard) {
				boardArticles.add(article);
			}
		}
		Collections.reverse(boardArticles);
		return boardArticles;
	}

}
