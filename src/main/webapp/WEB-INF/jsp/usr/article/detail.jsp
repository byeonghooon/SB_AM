<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE DETAIL" />
<%@ include file="../common/head.jspf"%>

<script>
	const params = {};
	params.id = parseInt('${param.id}');
</script>

<script>
	function ArticleDetail__increaseHitCount() {
		const localStorageKey = 'article__' + params.id + '__alreadyView';

		if (localStorage.getItem(localStorageKey)) {
			return;
		}
		localStorage.setItem(localStorageKey, true);

		$.get('../article/doIncreaseHitCountRd', {
			id : params.id,
			ajaxMode : 'Y'
		}, function(data) {
			$('.article-detail__hit-count').empty().html(data.data1);
		}, 'json');
	}
	$(function() {
		// 실전코드
		//ArticleDetail__increaseHitCount();
		// 연습코드
		setTimeout(ArticleDetail__increaseHitCount, 2000);
	})
</script>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type-1">
			<table>
				<colgroup>
					<col width="200" />
				</colgroup>

				<tbody>
					<tr>
						<th bgcolor="gray">번호</th>
						<td>${article.id }</td>
					</tr>
					<tr>
						<th bgcolor="gray">작성날짜</th>
						<td>${article.regDate }</td>
					</tr>
					<tr>
						<th bgcolor="gray">수정날짜</th>
						<td>${article.updateDate }</td>
					</tr>
					<tr>
						<th bgcolor="gray">작성자</th>
						<td>${article.extra__writerName }</td>
					</tr>
					<tr>
						<th bgcolor="gray">조회수</th>
						<td><span class="badge article-detail__hit-count">${article.hitCount }</span></td>
					</tr>
					<tr>
						<th bgcolor="gray">추천</th>
						<td>
						<span class="badge">${article.goodReactionPoint }</span>
							<c:if test="${actorCanMakeReaction}">
								<span>&nbsp;</span>
								<button class="btn btn-outline btn-success btn-xs">좋아요👍</button>
								<span>&nbsp;</span>
								<button class="btn btn-outline btn-error btn-xs">싫어요👎</button>
							</c:if>
						</td>
					</tr>
					<tr>
						<th bgcolor="gray">제목</th>
						<td>${article.title }</td>
					</tr>
					<tr>
						<th bgcolor="gray">내용</th>
						<td>${article.body }</td>
					</tr>
				</tbody>

			</table>
		</div>
		<div class="btns">
			<button class="btn-text-link" type="button" onclick="history.back();">뒤로가기</button>
			<c:if test="${article.extra__actorCanModify }">
				<a class="btn-text-link" href="../article/modify?id=${article.id }">수정</a>
			</c:if>
			<c:if test="${article.extra__actorCanDelete }">
				<a class="btn-text-link"
					onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;"
					href="../article/doDelete?id=${article.id }">삭제</a>
			</c:if>
		</div>
	</div>
</section>

<%@ include file="../common/foot.jspf"%>