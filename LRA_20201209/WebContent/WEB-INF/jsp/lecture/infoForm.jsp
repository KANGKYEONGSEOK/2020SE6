<%@page import="kr.ac.gnu.lecture.to.LectureBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="updateForm.do">
<%
List<LectureBean> list=(List<LectureBean>)request.getAttribute("lectureList");
for(LectureBean bean:list){
	String s_id=bean.getId();
out.print("<input type=radio name=id value="+s_id+">");
out.print("<a href=info.do?id="+s_id+">");	
out.print(s_id);
out.print("</a><br/>");
}
%>
<input type="submit" value="수정">
</form>
</body>
</html>