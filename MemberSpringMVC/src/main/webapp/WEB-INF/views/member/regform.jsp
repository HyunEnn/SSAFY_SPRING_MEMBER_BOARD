<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<c:set var="root" value="${ pageContext.request.contextPath}" />
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
			<style type="text/css">
				mark.sky {
					background: linear-gradient(to top, #54fff9 20%, transparent 30%);
				}
			</style>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
			<script type="text/javascript">
				/*
				$(function () {
					$('#check').on('click', function (evt) {


						$.ajax({
							url: '${root}/mem/idcheck',
							method: 'POST',
							data: {
								id: $('#id').val()
							},
							dataType: 'json',
							success: function (data, status, xhr) {
								alert(data.result);
							},
							error: function (err) {
								console.log(err);
							}
						});

					});
				});
				*/
			</script>
			<title>회원 가입</title>
		</head>

		<body>

			<div class="container text-center mt-3">
				<div class="col-lg-4 mx-auto">
					<jsp:include page="/WEB-INF/views/member/loginheader.jsp"></jsp:include>
					<form action="regmember" class="text-left mb-3" method="post" id="rform">
						<h2 class="p-3 mb-3 text-center shadow bg-light">
							<mark class="sky">회원가입</mark>
						</h2>
						<div class="form-group">
							<label for="id">아이디</label> <input type="text" class="form-control" name="id" id="id" />
							<input type="button" value="아이디체크" id="check" />
						</div>
						<div class="form-group">
							<label for="pw">비밀번호</label> <input type="password" class="form-control" name="pw"
								id="pw" />
						</div>
						<div class="form-group">
							<label for="name">이름</label> <input type="text" name="name" class="form-control"
								id="name" />
						</div>
						<div class="form-group">
							<label for="addr">주소</label> <input type="text" class="form-control" name="addr"
								id="addr" />
						</div>
						<div class="form-group">
							<label for="age">나이</label> <input type="text" class="form-control" name="age" id="age" />
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