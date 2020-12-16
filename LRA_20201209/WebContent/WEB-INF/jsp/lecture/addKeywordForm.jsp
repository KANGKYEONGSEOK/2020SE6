<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Song+Myung&display=swap" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.7.js">
</script>
<script>

$(document).ready(function(){
 	$("#addKeyword").click(listFunc);
});
function listFunc(){
	window.open("./popup.html", "a", "width=400, height=300, left=100, top=50"); 
	<%-- 
	$.ajax({
		url:"<%=request.getContextPath()%>/lecture/keyword/ajaxlist.do",
			dataType : "json",
			success : function(obj) {
				switch (obj.errorCode) {
				case 0:
					var buffer = [];
					obj.keywordlist.forEach(function(member) {
						buffer.push(member.lectureKeyword + "<br/>");
					});
					$("#listContainer").html(buffer.join(""));
					break;
				case -1:
					$("#listContainer").html("오류:" + obj.errorMsg);
					break;
				}
			}
		}); --%>
	}
</script>	
</head>
<body>

<div style="width: 1000px; height: 100%; text-align: center;">
	<img border="0" src="../algorithm.png"
        alt="Pulpit rock" width="30" height="30" style="display: inline;">
		<h1 style="font-family: 'Song Myung', serif; display: inline;">유튜브 강의 추천 알고리즘</h1>
		<div  style="width:1000px; margin-bottom: 30px;">
			<a style="float:right;" href="<%=request.getContextPath()%>/manager/loginForm.html">메인화면</a>
		</div>
	</div>
	<div style="border:2px solid gray; width:1000px; height:500px; margin-top: 20px" >
		<div style="margin-top: 20px; margin-left: 20px">

 			<h3>관리자 검색어 추가 화면</h3>
				<form>
				<span>검색어 입력  </span><input type="text" style="height: 30px; width: 550px">
				<input type="button" value="검색어 추가" id="addKeyword" style="height: 36px; width: 100px;">
				</form>
				<div id="listContainer">
					
				</div>
		</div>
	</div>
	
	

</body>
</html>