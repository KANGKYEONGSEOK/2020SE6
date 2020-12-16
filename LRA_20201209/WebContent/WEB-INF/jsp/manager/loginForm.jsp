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
 	$("#loginButton").click(loginFunc);
});
function loginFunc(){
	$.ajax({
		url:"<%=request.getContextPath()%>/manager/login.do",
		data:{"id":$("#txt1").val(), "pw":$("#txt2").val()},
		dataType:"json",
		success:function(obj){
		
			switch(obj.errorCode){
			case 0 : 
				location.href="<%=request.getContextPath()%>/lecture/addKeywordForm.html"; 
				break;
			case -1 :
				$("#errorMsgContainer").html(obj.errorMsg);
				break;
			}
		},
		error:function(a){
			console.log(a);
			alert(a); 
		}
	});	
}
</script>
</head>
<body>


<div style="width: 1000px; height: 100%; text-align: center;">
	<img border="0" src="../algorithm.png"
        alt="Pulpit rock" width="30" height="30" style="display: inline;">
	<h1 style="font-family: 'Song Myung', serif; display: inline;">유튜브 강의 추천 알고리즘</h1>
		<div  style="width:800px; margin-bottom: 30px;">
			<a style="float:right; font-size: 16px" href="<%=request.getContextPath()%>/menu.html">&lt;&lt;메인화면</a>
		</div>
	</div>
	<div style="border:2px solid gray; width:1000px; height:500px; margin-top: 20px" >
		<div style="margin-top: 20px; margin-left: 20px; text-align: center;">

 			<h3>관리자 로그인화면</h3>
				<div style="align-items: center;">
				<div>
				<div style="width:20%; height: 30px; display: inline; margin-right: 11px;"> ID  </div> <input style="width:50%; height: 30px"  type="text" id="txt1"><br/>
				</div>
				<div style="margin-top: 10px">
				<div style="width:20%; height: 30px; display: inline; margin-right: 5px;"> PW  </div> <input style="width:50%; height: 30px" type="password" id="txt2"><br/><br/>
				</div>
				<input type="button" id="loginButton" style="width: 51%; height: 40px; margin-left: 38px" value="로그인">
				<div id="errorMsgContainer" style="margin-top: 13px; color: red"></div>
			</div>
		</div>
	</div>
	

</body>
</html>
