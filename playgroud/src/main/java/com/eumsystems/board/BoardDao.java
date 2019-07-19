package com.eumsystems.board;

import java.util.List;

public interface BoardDao {
	
	
	public List<BoardDTO> getList() throws Exception;

	public BoardDTO getArticle(String num) throws Exception;

	public void doCreated(BoardDTO dto);
}
