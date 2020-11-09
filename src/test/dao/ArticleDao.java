package test.dao;

import java.util.ArrayList;
import java.util.List;

import test.dto.Article;
import test.dto.Board;

public class ArticleDao {

	private List<Article> articles;
	private int lastArticleNum;
	private int lastArticleBoardNum;
	private List<Board> boards;


	public ArticleDao() {
		articles = new ArrayList<>();
		boards = new ArrayList<>();
		lastArticleNum = 0;
		lastArticleBoardNum = 0;

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

	public List<Article> articles() {
		return articles;
	}

	public Article getArticleByNum(int inputedNum) {
		for(Article article : articles) {
			if(article.aNum == inputedNum) {
				return article;
			}
		}
		return null;
	}

	public int makeBoard(String bTitle) {
		Board board = new Board();
		
		board.bNum = lastArticleBoardNum + 1;
		board.bTitle = bTitle;
		boards.add(board);
		lastArticleBoardNum = board.bNum;
		
		return board.bNum;
		
	}

	public Board getBoardByNum(int inputedNum) {
		for(Board board : boards) {
			if(board.bNum == inputedNum) {
				return board;
			}
		}
		return null;
	}



	public List<Board> getBoards() {
		return boards;
	}

}
