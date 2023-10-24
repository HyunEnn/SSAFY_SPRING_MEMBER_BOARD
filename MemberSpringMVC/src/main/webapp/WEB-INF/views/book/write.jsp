<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<%@ include file="/WEB-INF/views/common/confirm.jsp" %>
      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="sky">책 목록</mark>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="row align-self-center mb-2">
            <div class="col-md-2 text-start">
              <button type="button" id="btn-mv-register" class="btn btn-outline-primary btn-sm">
                책 쓰기
              </button>
            </div>
            <div class="col-md-7 offset-3">
              <form class="d-flex" id="form-search" action="">
                <input type="hidden" name="pgno" value="1"/>
                <select
                  name="key"
                  id="key"
                  class="form-select form-select-sm ms-5 me-1 w-50"
                  aria-label="검색조건"
                >
                  <option selected>검색조건</option>
                  <option value="subject">제목</option>
                  <option value="authorId">저자</option>
                </select>
                <div class="input-group input-group-sm">
                  <input type="text" name="word" id="word" class="form-control" placeholder="검색어..." />
                  <button id="btn-search" class="btn btn-dark" type="button">검색</button>
                </div>
              </form>
            </div>
          </div>
          <table class="table table-hover">
            <thead>
              <tr class="text-center">
                <th scope="col">책 번호</th>
                <th scope="col">저자</th>
                <th scope="col">제목</th>
                <th scope="col">가격</th>
              </tr>
            </thead>
            <tbody>    
				<c:forEach var="book" items="${books}">    
	              <tr class="text-center">
	                <th scope="row">${book.isbn}</th>
	                <td class="text-start">
	                  <a
	                    href="#"
	                    class="book-isbn link-dark"
	                    data-no="${book.isbn}"
	                    style="text-decoration: none"
	                  >
	                    ${book.author}
	                  </a>
	                </td>
	                <td>${book.title}</td>
	                <td>${book.price}</td>
	              </tr>            
				</c:forEach>   
            </tbody>
          </table>
        </div>
        <div class="row">
          ${navigation.navigator}
        </div>
      </div>
    </div>
    <form id="form-param" method="get" action="">
      <input type="hidden" name="pgno" id="pgno" value="${pgno}">
      <input type="hidden" name="key" value="${key}">
      <input type="hidden" name="word" value="${word}">
    </form>
    <form id="form-no-param" method="get" action="${root}/book/list/view">
      <input type="hidden" name="pgno" value="${pgno}">
      <input type="hidden" name="key" value="${key}">
      <input type="hidden" name="word" value="${word}">
      <input type="hidden" id="bookisbn" name="bookisbn" value="">
    </form>
    <script>
      let titles = document.querySelectorAll(".book-isbn");
      titles.forEach(function (title) {
        title.addEventListener("click", function () {
          document.querySelector("#bookisbn").value = this.getAttribute("data-no");
          document.querySelector("#form-no-param").submit();
        });
      });

      document.querySelector("#btn-mv-register").addEventListener("click", function () {
    	  let form = document.querySelector("#form-param");
          form.setAttribute("action", "${root}/book/write");
          form.submit();
      });
      
      document.querySelector("#btn-search").addEventListener("click", function () {
    	  let form = document.querySelector("#form-search");
          form.setAttribute("action", "${root}/book/list");
          form.submit();
      });
      
      let pages = document.querySelectorAll(".page-link");
      pages.forEach(function (page) {
        page.addEventListener("click", function () {
       	  document.querySelector("#pgno").value = this.parentNode.getAttribute("data-pg");
          let form = document.querySelector("#form-param");
          form.setAttribute("action", "${root}/book/list");
          form.submit();
        });
      });
    </script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
