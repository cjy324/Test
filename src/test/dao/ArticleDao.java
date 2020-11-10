package test.dao;

import java.util.ArrayList;
import java.util.List;

import test.dto.Article;
import test.dto.Board;

public class ArticleDao {

	private List<Board> boards;
	private int lastBoardNum;
	private List<Article> articles;
	private int lastArticleNum;
	

	public ArticleDao() {
		boards = new ArrayList<>();
		lastBoardNum = 0;
		articles = new ArrayList<>();
		lastArticleNum = 0;
	}

	public int addBoard(String bName) {

		Board board = new Board();

		board.bNum = lastBoardNum + 1;
		board.bName = bName;
		boards.add(board);
		lastBoardNum = board.bNum;

		return board.bNum;
	}

	public Board getBoardByBNum(int selectedBoardNum) {
		for (Board board : boards) {
			if (board.bNum == selectedBoardNum) {
				return board;
			}
		}
		return null;
	}

	public List<Board> getBoards() {
		return boards;
	}

	public int add(int boardNum, String title, String body, int writerNum) {
		
		Article article = new Article();
		
		article.boardNum = boardNum;
		article.aNum = lastArticleNum + 1;
		article.title = title;
		article.body = body;
		article.writerNum = writerNum;
		articles.add(article);
		lastArticleNum = article.aNum; 
		
		return article.aNum;
	}

	public List<Article> getArticles() {
		return articles;
	}

}
