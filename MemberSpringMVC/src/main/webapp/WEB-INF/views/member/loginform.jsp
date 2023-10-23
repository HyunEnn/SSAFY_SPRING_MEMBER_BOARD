<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="root" value="${ pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style type="text/css">
mark.sky {
	background: linear-gradient(to top, #54fff9 20%, transparent 30%);
}
</style>
<title>Login</title>

<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="container text-center mt-3"">
		<h2 class="p-3 mb-3 text-center shadow bg-light">
			<mark class="sky">로그인</mark>
		</h2>
		
		<form action="${root }/mem/login" method="post" id="rform">
			<div class="col-lg-4 mx-auto">
				<div class="form-group">
					<label for="id">아 이 디 : </label> <input type="text" name="id"
						id="id" />
				</div>
				<div class="form-group">
					<label for="pw">비밀번호 : </label> <input type="password" name="pw"
						id="pw" />
				</div>
				<div class="form-group">
					<input type="submit" value="로그인"> <input type="reset"
						value="취소">
				</div>
			</div>
		</form>
		<a href="${root }">회원 등록</a>
	</div>
</body>
</html>