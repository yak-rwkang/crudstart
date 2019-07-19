<%@ page contentType="text/html; charset=UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
	String cp = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>게 시 판</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<link rel="stylesheet" href="<%=cp%>/resources/data/css/style.css">
<link rel="stylesheet" href="<%=cp%>/resources/data/css/article.css">

</head>

<body>

<div id="bbs">
	<div id="bbs_title">
	게 시 판(spring)
	</div>
	<div id="bbsArticle">
		<div id="bbsArticle_header">
			${dto.subject}
		</div>
		<div class="bbsArticle_bottomLine">
			<dl>
				<dt>작성자</dt>
				<dd>${dto.name}</dd>
			</dl>
		</div>
		<div class="bbsArticle_bottomLine">
			<dl>
				<dt>등록일</dt>
<%-- 				<dd>${dto.created}</dd>
 --%>				<dt>조회수</dt>
				<dd>${dto.hitcount}</dd>
			</dl>
		</div>
		<div id="bbsArticle_content">
			<table width="600" border="0">
			<tr><td style="padding: 20px 80px 20px 62px;" valign="top" height="200">
			${dto.content}
			</td></tr>
			</table>
		</div>
		<div class="bbsArticle_bottomLine">
			이전글 : 작업중
		</div>
		<div class="bbsArticle_noLine">
			다음글 : 작업중
		</div>
	</div>
	<div class="bbsArticle_noLine" style="text-align:right">
		    ${dto.ipaddr }
	</div>
	

        <input type="hidden" name="num" value="${dto.num }">
      <%--   <input type="hidden" name="pageNum" value="${pageNum }"> 	
		<input type="hidden" name="searchKey" value="${searchKey }">
		<input type="hidden" name="searchValue" value="${searchValue }"> --%>
	
	
	
	<div id="bbsArticle_footer">
		<div id="leftFooter">
               <input type="button" value=" 수정 " class="btn2"  onclick="javascript:location.href='<%=cp%>/boardUpdate.do?&num=${dto.num}';">
               <input type="button" value=" 삭제 " class="btn2"  onclick="javascript:location.href='<%=cp%>/boardDelete.do?&num=${dto.num}';">
		</div>
		<%-- <div id="rightFooter">
               <input type="button" value=" 리스트 " class="btn2" onclick="javascript:location.href='<%=cp%>/board.do?method=list&pageNum=${pageNum }'"/>
		</div> --%>
	</div>

</div>

<br/>&nbsp;<br/>
</body>

</html>