<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
mark.sky {
	background: linear-gradient(to top, #54fff9 20%, transparent 30%);
}
</style>
<title>파일 업 다운로드</title>
</head>
<body>
	<!-- 
		1. 전송하고자 하는  입력폼을 만든다
		2. action 을 처리할 controller 구현
		3. method는  꼭 post 로
		4. enctype mutipart/form-data
		5. inputtype file 해서 submit 하세요
	-->
	<div class="container text-center mt-3">
		<form action="${root }/fileupload" id="fileupload" method="post"
			enctype="multipart/form-data">
			파일 : <input type="file" name="upfile" multiple="multiple"> <input
				type="submit" value="업로드" id="upfile">

		</form>
		<!-- 다운로드 -->
		<hr />
		<table border="1" align="center">
			<c:if test="${not empty files }">
				<tr>
					<th>fid</th>
					<th>name</th>
					<th>path</th>
				</tr>

				<c:forEach items="${files }" var="file">
					<tr>
						<td><a
							href="<c:url value='/filedownload?fid=${file.fid }' />">${file.fid }</a></td>
						<td>${file.name }</td>
						<td>${file.path }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty files }">
				<h5>다운로드 파일 없음</h5>
			</c:if>
		</table>
		<a href="${root }/">초기화면</a>
	</div>
</body>
</html>