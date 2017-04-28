<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/validation.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/cookieControl.js"></script>
<script type="text/javascript">
$(function(){
	if(eval('${!empty param.message}')){
		alert('${param.message}');
	}
	
	$('form[name=loginForm]').submit(function(){
		if(!$('input[name=mem_id]').val().validationID()){
			alert('<spring:message code="fail.common.id"/>');
			return false;
		}
		if(!$('input[name=mem_pass]').val().validationPWD()){
			alert('<spring:message code="cop.password.msg"/>');
			return false;
		}
		
		$(this).attr('action', '${pageContext.request.contextPath}/join/loginCheck.do');

		return;
	});
	
	$('input[value=회원가입]').click(function(){
		var $frm = $('<form action="${pageContext.request.contextPath}/member/memberForm.do"></form>');
		$frm.attr('name', 'memberForm');
		$frm.attr('method', 'get');
		
		$frm.append('<input type="text" name="amugerna" value="아무거나"/>');
		
		$(document.body).append($frm);
		
		$frm.submit();
	});
});

</script>
</head>
<body>
<form action="" name="loginForm" method="post">
	<table>
		<tr>
			<td>아이디</td>
			<td>
				<input type="text" name="mem_id"/>
			</td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td><input type="text" name="mem_pass"/></td>
		</tr>
		<tr>
			<td colspan="2">
				아이디 저장 :<input type="checkbox" id='saveID'>
				<input type="submit" value="로그인">
				<input type="button" value="회원가입" />
			</td>
		</tr>
	</table>
</form>
</body>
</html>