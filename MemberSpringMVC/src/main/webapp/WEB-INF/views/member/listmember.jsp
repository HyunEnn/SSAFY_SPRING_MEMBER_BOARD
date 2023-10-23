<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="root" value="${ pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 조회</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
mark.sky {
	background: linear-gradient(to top, #54fff9 20%, transparent 30%);
}
</style>
<script type="text/javascript">
	function pageMove(pg) {
		document.getElementById("pg").value = pg;
		document.getElementById("pageform").action = "${root}/mem/delete";
		document.getElementById("pageform").submit();
	}
	function dels() {
		document.getElementById("dels").action = "${root}/mem/update";
		document.getElementById("dels").submit();
	}
</script>
</head>
<body>

	<div class="container text-center mt-3">
		<div class="col-lg-8 mx-auto">
			<jsp:include page="/WEB-INF/views/member/loginheader.jsp"></jsp:include>
			<form name="pageform" id="pageform" method="get" action="">
				<input type="hidden" name="pg" id="pg" value="">
			</form>

			<h2 class="p-3 mb-3 shadow bg-light">
				<mark class="sky">회원조회</mark>
				<button type="button" class="btn btn-danger " onclick="dels()">선택삭제</button>
			</h2>
			
			<form action="#" method="post" id="dels">
				<table class="table table-active text-left" border="1">
					<tr class="table-info">
						<td>번호</td>
						<td>id</td>
						<td>pw</td>
						<td>name</td>
						<td>addr</td>
						<td>age</td>
						<td>선택</td>
					</tr>

					<c:forEach items="${list }" var="m" varStatus="cnt">
						<tr class="table-danger">
							<td>${cnt.count }</td>
							<td><a
								href="${root }/mem/view?id=${m.id }">${m.id }</a></td>
							<td>${m.pw }</td>
							<td>${m.name }</td>
							<td>${m.addr }</td>
							<td>${m.age }</td>
							<td><input type="checkbox" name="id" value="${m.id }"></td>
						</tr>

					</c:forEach>

				</table>
			</form>
			<center>
				<table class="text-center">
					<tr>
						<%-- <td>${navigation.navigator }</td> --%>
					</tr>
				</table>
			</center>
			<a href="${root }">초기화면</a>
			
		</div>
	</div>
</body>
</html>