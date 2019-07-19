package com.eumsystems.board;

import java.util.List;

public interface BoardService {
	
	public List<BoardDTO> getList() throws Exception;

	public BoardDTO getArticle(String num)throws Exception ;

	public void doCreate(BoardDTO dto);

}
