<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div style="width: 800px; height: 100%; text-align: center;">
	<img border="0" src="algorithm.png"
        alt="Pulpit rock" width="30" height="30" style="display: inline;">
		<h1 style="display: inline;">유튜브 강의 추천 알고리즘</h1>
		<div  style="width:800px; margin-bottom: 30px;">
			<a style="float:right;" href="<%=request.getContextPath()%>/menu.html">강의검색</a>
		</div>
	</div>
	<div style="border:2px solid gray; width:800px; height:700px; margin-top: 20px" >
		<div style="margin-top: 20px; margin-left: 20px">
<h3>에러페이지</h3>
 			<%=request.getAttribute("errorMsg")%>
		</div>
	</div>
	
</body>	
</html>