<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="LOGIN" />
<%@ include file="../common/head.jspf"%>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3 text-center">
		<form method="post" action="../member/doLogin">
		<div>
			로그인 아이디 : <input class="w-96" name="loginId" placeholder="아이디를 입력해주세요" type="text" />
		</div>
		<div>
			로그인 비밀번호 : <input class="w-96" name="loginPw" placeholder="비밀번호를 입력해주세요" type="text" />
		</div>
		<div class="btns">
		<button class="btn-text-link" type="button" onclick="history.back();">뒤로가기</button>
		<button class="btn-text-link" type="submit">로그인</button>
		</div>
	
		</form>
	</div>
</section>



<%@ include file="../common/foot.jspf"%>