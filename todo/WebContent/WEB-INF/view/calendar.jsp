<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>calendar</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<style>
	.spring{
		background-image:url('${pageContext.request.contextPath}/image/background-spring.png');
		background-size: cover;
	}
	.summer{
		background-image:url('${pageContext.request.contextPath}/image/background-summer.jpg');
		background-size: cover;
	}
	.autumn{
		background-image:url('${pageContext.request.contextPath}/image/background-autumn.jpg');
		background-size: cover;
	}
	.winter{
		background-image:url('${pageContext.request.contextPath}/image/background-winter.jpg');
		background-size: cover;
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
			<div class="text-center">
				<h1><span class="text-primary">${loginMember.memberId}</span>님 반갑습니다.</h1>
				<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
				<a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/member/removeMember">회원탈퇴</a>
			</div>
			<br>
		
			<!-- 달력 + todo -->
			<div class="text-center">
				<h1>
					<span><a class="btn" href="${pageContext.request.contextPath}/member/calendar?currentYear=${targetYear}&currentMonth=${targetMonth}&option=pre"><img src="${pageContext.request.contextPath}/image/left.png" width="40" height="40"></a></span>
					${targetYear}년 ${targetMonth}월		
					<span><a class="btn" href="${pageContext.request.contextPath}/member/calendar?currentYear=${targetYear}&currentMonth=${targetMonth}&option=next"><img src="${pageContext.request.contextPath}/image/right.png" width="40" height="40"></a></span>
				</h1>
			</div>
			
			<table class="table table-bordered color-border">
				<tr class="text-center font-weight-bold h3">
					<td class="text-danger" style="width: 14.2%">일</td>
					<td style="width: 14.2%">월</td>
					<td style="width: 14.2%">화</td>
					<td style="width: 14.2%">수</td>
					<td style="width: 14.2%">목</td>
					<td style="width: 14.2%">금</td>
					<td class="text-primary" style="width: 14.2%">토</td>
				</tr>
				<tr>			
					<c:forEach var="i" begin="1" end="${startBlank + endDay + endBlank}" step="1">
						<c:if test="${i-startBlank >= 1 && 	i-startBlank<=endDay}">
							<td width="100px" height="100px">
								<a class="font-weight-bold h5" href="${pageContext.request.contextPath}/member/todoList?y=${targetYear}&m=${targetMonth}&d=${i-startBlank}">${i-startBlank}</a>
								
								<div>
									<!-- 날짜별 일정 -->
									<c:forEach var="todo" items="${todoList}">
										<c:if test="${(i-startBlank) == todo.todoDate.substring(8)}">
											<c:if test="${todo.todoComplete eq 'N'}">
												<div style="color:${todo.fontColor}">${todo.todoContent}...</div>
											</c:if>
										</c:if>
										<c:if test="${(i-startBlank) == todo.todoDate.substring(8)}">
											<c:if test="${todo.todoComplete eq 'Y'}">
												<div><del style="color:${todo.fontColor}">${todo.todoContent}</del>...</div>
											</c:if>
										</c:if>
									</c:forEach>						
								</div>					
							</td>					
						</c:if>
						<c:if test="${i-startBlank < 1 || i-startBlank>endDay}">
							<td>&nbsp;</td>
						</c:if>
						
						<c:if test="${i%7 == 0}">
							</tr><tr>					
						</c:if>
					</c:forEach>
				</tr>
			</table>
		
			<h1 class="text-center">${targetMonth}월 todoList : ${todoList.size()}개</h1>
		</section>
	</div>
</body>
</html>