package com.eumsystems.util;

import org.springframework.stereotype.Service;

@Service
public class MyUtil {
	
	//전체 페이지수 구하기
	//numPerPage : 한화면에 표시할 데이터의 갯수
	//dataCount : 전체 데이터의 갯수
	public int getPageCount(int numPerPage, int dataCount){
		
		int pageCount = 0;
		pageCount = dataCount / numPerPage;
		
		if(dataCount % numPerPage != 0)
			pageCount++;
		
		return pageCount;	
		
	}
	
	//페이징 처리 메소드
	//currentPage :현재 표시할 페이지
	//totalPage : 전체 페이지수
	//listUrl : 링크를 설정할 url
	public String pageIndexList(int currentPage, int totalPage, String listUrl){
		
		int numPerBlock = 5; //1◀이전 6 7 8 9 10 다음▶11(6-10까지 표시되는 페이지 갯수)
		int currentPageSetup; //표시할 첫 페이지(6)의 – 1 해준 값(5,10,15,20...)
		int page;
		
		StringBuffer sb = new StringBuffer();
		
		if(currentPage==0 || totalPage==0)	//데이터가 없을 경우
			return "";
		
		//abc.jsp?a=1
		if(listUrl.indexOf("?") != -1)  //주소줄에 ?표가 있다면
			listUrl = listUrl + "&";
		else
			listUrl = listUrl + "?";
		
		//표시할 첫 페이지의 – 1 해준 값
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		
		if(currentPage % numPerBlock == 0)
			currentPageSetup = currentPageSetup - numPerBlock;
		
		//◀이전
		if(totalPage > numPerBlock && currentPageSetup > 0){
						
			sb.append("<a href=\"" + listUrl + "pageNum=" 
					+ currentPageSetup + "\">◀이전</a>&nbsp;");
			
			
		}
		
		//바로가기 페이지
		page = currentPageSetup + 1;
		
		while(page <= totalPage && page <= (currentPageSetup + numPerBlock)){
			
			if(page == currentPage){
				
				sb.append("<font color=\"#A3C838\">" + page + "</font>&nbsp;");				
			
			}else{
				
				sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">"
						+ page + "</a>&nbsp;");
				
			}
			
			page++;
			
		}		
		
		//다음▶
		if(totalPage - currentPageSetup > numPerBlock){
						
			sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">다음▶</a>&nbsp;");
			
		}
		
		
		return sb.toString();
		
	}
	
	public String pageIndexList_r(int currentPage, int totalPage, String replyUrl) {
		//currentPage : 현재 표시할 페이지
		//totalPage : 전체 페이지 수
		//replyUrl : 링크를 설정할 URL (list.jsp)
		//#section3 : 댓글을 표시할  div 영역
		
	
		
		int numPerBlock = 5; //numPerBlock : 보여줄 page 갯수(◀1 2 3 4 5▶ = 5개)
		
		//CurrentPageSetup : 이전 버튼을 눌렀을때 표시할(선택할) 페이지 (numPerBlock의 제일 왼쪽 page값 - 1)
		int currentPageSetup;
		
		int page; //numPerBlock안에있는 5개의 값
		
		StringBuffer sb = new StringBuffer();
		
		//현재선택한 페이지가 0페이지이거나 전체 페이지수가 0페이지일 경우
		if (currentPage == 0 || totalPage == 0) {
			return ""; //numPerBlock를 표시하지 않는다.
		}
		
		//indexOf() : 찾고자 하는 값이 있을경우 그 값의 위치(인덱스번호)를 반환하고 없으면 -1을 반환
		if (replyUrl.indexOf("?") != -1) { //검색을 한 상태라면(?가 -1이 아닐경우 = ?가 있을경우 = 검색을 한 경우)
			
			//검색한 상태의 주소뒤에 &를 붙여 검색된 결과를 반환하는 페이지를 페이징 처리 하기 위함(&뒤에는 pageNum이 붙을 예정)
			replyUrl = replyUrl + "&";
		} else { //검색을 하지 않은 상태라면
			
			//?를 붙여 pageNum을 get방식으로 넘기기 위함
			replyUrl = replyUrl + "?";
		}
		
		//◀이전을 눌렀을경우 표시할(선택될) 페이지 (numPerBlock의 제일 왼쪽 page값 - 1)		
		currentPageSetup = (currentPage / numPerBlock) * numPerBlock;
		
		if (currentPage % numPerBlock == 0) {
			currentPageSetup = currentPageSetup - numPerBlock;
		}
		
		//◀이전 버튼 만들기
		//전체 페이지수가 보여줄 페이지 갯수보다 크고 ◀이전을 눌렀을경우 표시할(선택될) 페이지가 0보다 클 경우
		if (totalPage > numPerBlock && currentPageSetup > 0) {
			//sb에 문자열을 누적시킨다.
			sb.append("<a href=\"" + replyUrl + "replyPageNum=" + currentPageSetup + "\">◀이전</a>&nbsp;");
		}                //    \"는 '라고 생각하면 된다.
		
		//바로가기 페이지(◀이전 과 다음▶의 사이에 있는 숫자들)
		page = currentPageSetup + 1;
		
		while (page <= totalPage && page <= (currentPageSetup + numPerBlock)) {
			
			if (page == currentPage) {
				
				sb.append("<font color=\"black\" size=\"16px\">" + page + "</font>&nbsp;");
				
			} else {
				sb.append("<a href=\"" + replyUrl + "replyPageNum=" + page + "\">" + page  + "</a>&nbsp;");
			}					
			
			page++;
			
		}
		
		//다음▶
		if (totalPage - currentPageSetup > numPerBlock) {
			
			sb.append("<a href=\"" + replyUrl + "replyPageNum=" + page + "\">다음▶</a>&nbsp;");
			
		}
		
		return sb.toString();
		
	}
	
}
