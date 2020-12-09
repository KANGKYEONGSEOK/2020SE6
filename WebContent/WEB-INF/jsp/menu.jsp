<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Song+Myung&display=swap" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.7.js">
</script>
<script>

$(document).ready(function(){
 	listFunc();
});
function listFunc(){
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
		});
	}

	function bSubmit() {
		var arry = $("#listContainer").html().split("<br>")
		var keyword = $("#lectureKeyword").val();
		var flag = false;
		for(i in  arry){
			if(arry[i]===keyword){
				flag = true;
			}
		}
		if(flag===false){
			//$("#errorMsgContainer").html("검색가능한 검색어가 아닙니다. 검색어를 확인해주세요")
			alert("검색가능한 검색어가 아닙니다. 검색어를 확인해주세요")
		}
		return flag;

    }
<%-- 	$(document).ready(function(){
	 	$("#searchButton").click(listFunc);
	});
	function listFunc(){
		alert($("#lectureKeyword").val());
		$.ajax({
			url:"<%=request.getContextPath()%>/lecture/info.do",
				dataType : "json",
				method : "GET", 
				data : {'lectureKeyword':$("#lectureKeyword").val()},
				success : function(obj) {
					alert(JSON.stringify(obj));
					switch (obj.errorCode) {
					case 0:
						var buffer = [];
						obj.memberlist.forEach(function(member) {
							buffer.push(member.id + "<br/>");
						});
						$("#listContainer").html(buffer.join(""));
						break;
					case -1:
						$("#listContainer").html("오류:" + obj.errorMsg);
						break;
					}
				}
			});
		} --%>
</script>
</head>
<body>
	<%-- <h1>:: 메뉴 ::</h1>
<a href="<%=request.getContextPath()%>/member/loginForm.html">로그인</a><br/>
<a href="<%=request.getContextPath()%>/member/joinForm.html">회원가입</a><br/>
<a href="<%=request.getContextPath()%>/member/list.do">회원리스트</a><br/>
<a href="<%=request.getContextPath()%>/member/jqGridlist.html">회원리스트(jqGrid)</a><br/>
<a href="<%=request.getContextPath()%>/member/agGridlist.html">회원리스트(agGrid)</a><br/>
<a href="#" id="listButton">회원리스트(ajax)</a><br/>
<div id="listContainer"></div> --%>
	<div style="width: 800px; height: 100%; text-align: center;">
	<img border="0" src="algorithm.png"
        alt="Pulpit rock" width="30" height="30" style="display: inline;">
		<h1 style="font-family: 'Song Myung', serif; display: inline;">유튜브 강의 추천 알고리즘</h1>
		<div  style="width:800px; margin-bottom: 30px;">
			<a style="float:right;" href="<%=request.getContextPath()%>/manager/loginForm.html">시스템계정</a>
		</div>
	</div>
	<div style="border:2px solid gray; width:800px; height:700px; margin-top: 20px" >
		<div style="margin-top: 20px; margin-left: 20px">
<h3>강의검색</h3>
 			<form action="<%=request.getContextPath()%>/lecture/info.do" style="margin-bottom: 20px;" onsubmit="return bSubmit();"> 
				<span style="font-size: 16px">검색어 입력</span>  
				<input type="text" style="height:30px; width:550px; box-sizing: border-box;" id="lectureKeyword" name="lectureKeyword">
				<input type="submit" id="searchButton" value="검색" style="width:70px; height:30px"><br>
			<div id="errorMsgContainer" style="margin-top: 13px; color: red"></div>
		</form>
		
		
	
		
		<span style="font-size: 20px;position: absolute;background-color: white; position: relative; top: 20px; z-index: 99">검색어목록</span>
		<div id="listContainer"  style=" width: 730px;   margin-top: 10px; border : 1px solid navy; padding: 10px;position: relative;">
			
		</div>
		</div>
	</div>
</body>
</html>


