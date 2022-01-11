<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>todoList</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<style>
	.spring{
		background-image:url('${pageContext.request.contextPath}/image/background-spring.png');
		background-size: 1600px 1300px;
	}
	.summer{
		background-image:url('${pageContext.request.contextPath}/image/background-summer.jpg');
		background-size: 1600px 1300px;
	}
	.autumn{
		background-image:url('${pageContext.request.contextPath}/image/background-autumn.jpg');
		background-size: 1600px 1300px;
	}
	.winter{
		background-image:url('${pageContext.request.contextPath}/image/background-winter.jpg');
		background-size: 1600px 1300px;
	}
	.section-calendar{
		background-color:rgba(255,255,255,0.75);
		padding: 50px;
	}
	.section-calendar tr{
		color: black;
	}
	.section-calendar a{
		color: black;
	}
	.table{
		border-color: grey;		
	}
	.color-border td{
		border-color: grey;
	}
</style>

<body class="${season}">
	<div class="container" style="margin-top:100px; margin-bottom:100px">
		<section class="section-calendar">	
			<h1 class="text-center">${todoDate} todo list</h1>
			<table class="table table-hover text-center color-border">
				<tr class="font-weight-bold h4">
					<td width="10%">날짜</td>
					<td width="40%">할일</td>
					<td width="7%">완료</td>		
					<td width="7%">삭제</td>		
					<td width="20%">작성일</td>
				</tr>
				<c:forEach var="t" items="${todoList}">
					<tr>
						<td>${todoDate}</td>
						
						<c:choose>
							<c:when test="${t.todoComplete eq 'N'}">
								<td class="text-left" style="color:${t.fontColor}">${t.todoContent}</td>
							</c:when>
							<c:when test="${t.todoComplete eq 'Y'}">							
								<td class="text-left"><strong>[완료]</strong> <del style="color:${t.fontColor}">${t.todoContent}</del></td>
							</c:when>
						</c:choose>					
						
						<c:choose>
							<c:when test="${t.todoComplete eq 'N'}">
								<td><a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/member/updateComplete?todoDate=${todoDate}&todoNo=${t.todoNo}">완료</a></td>
							</c:when>
							<c:when test="${t.todoComplete eq 'Y'}">
								<td></td>
							</c:when>
						</c:choose>
						<td><a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/member/deleteTodo?todoDate=${todoDate}&todoNo=${t.todoNo}">삭제</a></td>
						<td>${t.createDate}</td>
					</tr>
				</c:forEach>		
			</table>
			
			<br><br>
			
			<!-- List 추가 폼 -->
			<h1 class="text-center">리스트 추가</h1>
			
			<form class="text-center" method="post" action="${pageContext.request.contextPath}/member/todoList">
			
				<!-- 계절정보 -->
				<input type="hidden" name="season" value="${season}">
				
				<!-- todo 내용 입력 -->
				<table class="table table-bordered color-border">
					<tr>
						<td class="h4">날짜</td> 
						<td><input type="hidden" name="todoDate" value="${todoDate}">${todoDate}</td>
					</tr>
					<tr>
						<td class="h4">내용</td> 
						<td><textarea class="md-textarea form-control" rows="1" cols="50" name="todoContent"></textarea></td>
					</tr>
					<tr>
						<td colspan="2">폰트 색상 : <input type="color" name="fontColor"></td>				
					</tr>
				</table>			
				<button class="btn btn-outline-dark" type="submit">추가</button>
				<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/member/calendar?currentYear=${y}&currentMonth=${m}&option=no">돌아가기</a>
			</form>
		</section>
	</div>
</body>
</html>