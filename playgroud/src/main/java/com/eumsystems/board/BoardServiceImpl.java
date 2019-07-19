package com.eumsystems.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl  implements BoardService{

	@Autowired
	BoardDao boardDao;
	
	@Override
	public List<BoardDTO> getList() throws Exception {
		
		List<BoardDTO> list =  boardDao.getList();
		return list;
	}

	@Override
	public BoardDTO getArticle(String num) throws Exception {
		BoardDTO dto = boardDao.getArticle(num);
		return dto;
	}

	@Override
	public void doCreate(BoardDTO dto) {
		
		boardDao.doCreated(dto);
	}

	

}
