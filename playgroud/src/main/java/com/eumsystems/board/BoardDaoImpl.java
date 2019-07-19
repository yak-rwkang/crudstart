package com.eumsystems.board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImpl  implements BoardDao {

	@Autowired
	private SqlSessionTemplate st;
	
	
	public SqlSessionTemplate getSt() {
		return st;
	}

	public void setSt(SqlSessionTemplate st) throws SQLException {
		this.st = st;
	}

	public static String boardMapper = "com.eumsystems.mybatis.boardMapper";

	
	//°Ô½ÃÆÇ 
	@Override
	public List<BoardDTO> getList() throws Exception {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		return  list = st.selectList(boardMapper + ".boardList");
	}

	@Override
	public BoardDTO getArticle(String num) throws Exception {
		BoardDTO dto = st.selectOne(boardMapper + ".boardArticle",num);
		return dto;
	}

	@Override
	public void doCreated(BoardDTO dto) {
		st.insert(boardMapper + ".boardInsert",dto);
	}

}
