<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${ pageContext.request.contextPath}" />
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
			
			</script>
<title>회원 가입</title>
</head>

<body>

	<div class="container text-center mt-3">
		<div class="col-lg-4 mx-auto">
			<form action="${root }/book/insert" class="text-left mb-3"
				method="post" id="rform" enctype="multipart/form-data">
				<h2 class="p-3 mb-3 text-center shadow bg-light">
					<mark class="sky">도서 등록</mark>
				</h2>
				<div class="form-group">
					<label for="isbn">ISBN</label> <input type="text"
						class="form-control" name="isbn" id="isbn" />
				</div>
				<div class="form-group">
					<label for="publisher">작가</label> <input type="text"
						class="form-control" name="author" id="author" />
				</div>
				<div class="form-group">
					<label for="title">도서 제목</label> <input type="text" name="title"
						class="form-control" id="title" />
				</div>
				<div class="form-group">
					<label for="price">가격</label> <input type="text"
						class="form-control" name="price" id="price" />
				</div>
				<div class="form-group">
					도서 이미지 : <input type="file" name="upfile" >
				</div>
				<div class="form-group">
					<input type="reset" class="btn btn-outline-danger" value="취소">
					<input type="submit" class="btn btn-outline-primary" value="등록">
				</div>
			</form>

			<a href="${root }">초기화면</a>
		</div>
	</div>
	<script type="text/javascript">
				document.getElementById("check").addEventListener("click", function () {
					let uid = document.getElementById("id").value;
					fetch("${root}/mem/idcheck/"+uid)
						.then(response => response.json())
						.then(data => alert(data.result))
						.catch(error => alert(error));
				});


			</script>
</body>

</html>