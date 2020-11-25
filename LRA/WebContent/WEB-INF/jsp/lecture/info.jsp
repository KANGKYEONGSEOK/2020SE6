<%@page import="kr.ac.gnu.lecture.to.LectureBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Song+Myung&display=swap" rel="stylesheet">
<style type="text/css">
	td {border: 1px solid navy; padding : 5px } 
</style>
<script src="http://code.jquery.com/jquery-1.7.js">
</script>
<script>

$(document).ready(function(){
 	$(".c_cancel_button").click(listFunc);
 	
});
function listFunc(){
	//alert($(this).attr('id'));
	var result = confirm("선택한 강의를 취소하시겠습니까? 한번 추천 취소된 강의는 더 이상 추천되지 않습니다.");
	if(result){
		$(this).parent().parent().remove();
	   
	}else{

	//window.open("../lecture/popup.do", "a", "width=400, height=300, left=100, top=50"); 
	}
	
}
</script>
</head>
<body>
<div style="width: 800px; height: 100%; text-align: center;">
	<img border="0" src="../algorithm.png"
        alt="Pulpit rock" width="30" height="30" style="display: inline;">
		<h1 style="font-family: 'Song Myung', serif; display: inline;">유튜브 강의 추천 알고리즘</h1>
		<div  style="width:800px; margin-bottom: 30px;">
			<a style="float:right; color:blue; font-size:16px" href="<%=request.getContextPath()%>/menu.html">&lt;&lt;강의검색화면</a>
		</div>
	</div>
	<div style="border:2px solid gray; width:800px; height:700px; margin-top: 20px" >
		<div style="margin-top: 20px; margin-left: 20px">

 			<h3>추천강의 목록</h3>
 			<div style="margin-bottom: 15px">
 			<span style="font-size: 16px">검색어 입력</span>  
				<input type="text" style="height:30px; width:500px; box-sizing: border-box;" value="<%=request.getAttribute("Keyword")%>" id="lectureKeyword" name="lectureKeyword">
			</div>
	
<%
List<LectureBean> list=(List<LectureBean>)request.getAttribute("lectureList");

out.print("<table>");
out.print("<tr>");
out.print("<td>강의순위</td>");
out.print("<td>강의이름</td>");
out.print("<td>강의링크</td>");
out.print("<td>추천취소</td>");
out.print("</tr>");
for(LectureBean bean:list){
	String s_id=bean.getId();
	out.print("<tr style='border: 1px solid navy'>");
	   //for(int a=0; a>6; a++){
		   out.print("<td>"+bean.getRank()+"</td>");
		   out.print("<td>"+bean.getName()+"</td>");
		   out.print("<td><a href="+bean.getLink()+">"+bean.getLink()+"</a></td>");
		   out.print("<td><input type=button value=추천취소 class=c_cancel_button id=cancel_"+bean.getId()+"></td>");
		   
	  // }
	out.print("</tr>");
}
out.print("</table>");
%>
</div>
	</div>
</body>
</html>