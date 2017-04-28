<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.member.service.IMemberServiceImpl"%>
<%@page import="kr.or.ddit.member.service.IMemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
$(function(){
	$('table tr').click(function(){
		var index = $(this).index()+1;
		var mem_id = $('table tr:eq(' + index + ') td:eq(0)').text();
		
		$(location).attr('href', '${pageContext.request.contextPath}/member/memberView.do?mem_id='+mem_id);
	});
});
</script>
</head>
<body>
<table>
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>생일</th>
			<th>직업</th>
			<th>취미</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${memberList }" var="memberInfo">
		<tr>
			<td>${memberInfo.mem_id }</td>
			<td>${memberInfo.mem_name }</td>
			<td>${memberInfo.mem_bir }</td>
			<td>${memberInfo.mem_job }</td>
			<td>${memberInfo.mem_like }</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<input type="button" value="로그아웃" onclick="javascript:location.href='<c:url value="/join/logout.do"/>';"/>
</body>
</html>







