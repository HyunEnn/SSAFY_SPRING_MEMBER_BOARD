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
<title>Insert title here</title>
<script type="text/javascript">
	function del() {
		document.getElementById("rform").action = "${root}/book/delete";
		document.getElementById("rform").submit();
	}
	function update() {
		document.getElementById("rform").action = "${root}/book/update";
		document.getElementById("rform").submit();
	}
</script>
</head>
<body>
<%--
	<%
		String id = (String) request.getAttribute("id");

		String pw = (String) request.getAttribute("pw");
		String name = (String) request.getAttribute("name");
		String addr = (String) request.getAttribute("addr");
		String age = (String) request.getAttribute("age");
	%>
 --%>

	<div class="container text-center mt-3">
	 	<jsp:include page="/WEB-INF/views/member/loginheader.jsp"></jsp:include>
		<div class="col-lg-4 mx-auto">
			<form action="" method="post" id="rform" class="text-left mb-3">
				<h2 class="p-3 mb-3 text-center shadow bg-light"><mark class="sky">도서정보</mark></h2>
				<div class="form-group" >
					<label for="isbn">ISBN</label> 
					<input readonly type="text" class="form-control" value="${book.isbn }" name="isbn" id="isbn" />
				</div>
				<div class="form-group" >
					<label for="publisher">작가</label> 
					<input type="text" class="form-control" value="${book.publisher }" name="publisher" id="publisher" />
				</div>
				<div class="form-group" >
					<label for="title">제목</label> 
					<input type="text" name="title" class="form-control" value="${book.title }" id="title" />
				</div>
				<div class="form-group" >
					<label for="price">가격</label> 
					<input type="text" class="form-control" name="price" id="price" value="${book.price }"/>
				</div>
				<div class="form-group" >
					<label for="img">이미지</label> 
					<input type="text" class="form-control" name="img" id="img" value="${book.img }"/>
				</div>
				<div class="form-group" >
					<input type="button" class="btn btn-outline-danger" value="삭제" onclick="del()">
					<input type="button" class="btn btn-outline-primary" value="수정" onclick="update()">
				</div>
			</form>
			<a href="${root }">초기화면</a>
		</div>
	</div>
</body>
</html>