<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
	body{
		background-image:url('${pageContext.request.contextPath}/image/calendar.jpg');
		background-size: cover;
	}
	.position {
		position:absolute;
		height:300px;
		width:450px;
		margin:-150px 0px 0px -225px;
		top:50%;
		left:50%;
	}
	.section-calendar{
		background-color:rgba(255,255,255,0.95);
		padding: 50px;
	}
	.table{
		border-color: grey;		
	}
	.color-border td{
		border-color: grey;
	}
</style>
</head>
<body>
	<div class="position section-calendar">
		<h1>Login</h1>		<!-- 관리자 로그인 추가 -->
							<!-- 1) /adminLogin, AdminLoginController.doGet(), adminLogin.jsp -->
							<!-- 2) /adminLogin, dminLoginController.doPost(), /admin/adminIndex, AdminIndexController.doGet(), adminIndex.jsp -->
		
			<form class="text-center" method="post" action="${pageContext.request.contextPath}/login">
				<table class="table table-bordered color-border">
					<tr>
						<td>아이디 : </td>
						<td><input type="text" name="memberId" value="member"></td>
					</tr>
					<tr>
						<td>패스워드 : </td>
						<td><input type="text" name="memberPw" value="1234"></td>
					</tr>			
				</table>
				<div>
					<button class="btn btn-outline-dark" type="submit">로그인</button>
					<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/insertMember">회원가입</a>
				</div>
			</form>			
	</div>
</body>
</html>