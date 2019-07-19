package com.eumsystems.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BoardController {
	
	Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardService boardService;
	
	
	@RequestMapping(value ="/home.do")
	public ModelAndView practice() {
		
		ModelAndView mav = new ModelAndView();
		
		String msg = "Hello Spring";
		log.debug(msg);
		mav.setViewName("home");
		mav.addObject("msg", msg);
		
		return mav;
	}
	@RequestMapping(value = "/boardList.do")
	public ModelAndView list() throws Exception {
		
		
		ModelAndView mav = new ModelAndView();
	
		List<BoardDTO> list = boardService.getList();
		mav.addObject("list", list);
		mav.setViewName("board/list");
		
		return mav;
	}
	@RequestMapping(value = "/boardArticle.do")
	public ModelAndView article(HttpServletRequest req) throws Exception{
		
		String num = req.getParameter("num");
		
		BoardDTO dto = boardService.getArticle(num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/article");
		mav.addObject("dto", dto);
		return mav;
	}@RequestMapping(value = "/boardCreate.do")
	public ModelAndView create(HttpServletRequest req, BoardDTO dto) throws Exception{
		log.debug("사용자 입력값" + dto);
		
		
		boardService.doCreate(dto);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/created");
		
		return mav;
	}
}
