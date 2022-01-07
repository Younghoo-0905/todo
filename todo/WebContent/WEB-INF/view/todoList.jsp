<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>todoList</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
	<h1 class="text-center">${todoDate} todo list</h1>
		<table class="table table-hover">
			<tr class="font-weight-bold h4">
				<td width="10%">날짜</td>
				<td width="50%">할일</td>
				<td width="20%">작성일</td>
				<td width="20%">수정일</td>		
			</tr>
			<c:forEach var="t" items="${todoList}">
				<tr>
					<td>${todoDate}</td>
					<td style="color:${t.fontColor}">${t.todoContent}</td>
					<td>${t.createDate}</td>
					<td>${t.updateDate}</td>
				</tr>
			</c:forEach>		
		</table>
		
		<br><br>
		
		<h1 class="text-center">리스트 추가</h1>
		
		<form class="text-center" method="post" action="${pageContext.request.contextPath}/member/todoList">
			<table class="table table-bordered">
				<tr>
					<td class="h4">날짜</td> 
					<td><input type="hidden" name="todoDate" value="${todoDate}">${todoDate}</td>
				</tr>
				<tr>
					<th class="h4">내용</th> 
					<td><textarea class="md-textarea form-control" rows="3" cols="50" name="todoContent"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">폰트 색상 : <input type="color" name="fontColor"></td>				
				</tr>
			</table>			
			<button class="btn btn-outline-dark" type="submit">추가</button>
		</form>
	</div>
</body>
</html>