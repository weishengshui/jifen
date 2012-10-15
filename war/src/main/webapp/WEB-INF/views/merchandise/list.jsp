<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript" src="<c:url value='/js/jquery/jquery-latest.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/js/jquery/jquery.pagination.withoutAjax.js' />" charset="UTF-8"></script>
	<script type="text/javascript" src="<c:url value='/js/jquery/jquery-ui-1.8.2.custom.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/js/jquery/jquery-ui-i18n.js' />"></script>
	<script type="text/javascript" src="<c:url value='/js/jquery/ui/jquery.ui.autocomplete.selectFirst.js' />"></script>
	<script type="text/javascript" src="<c:url value='/js/jquery/jquery.form.js' />"></script>
<div>
		<form id="searchMerchandise" action="list" method="get">
			<table>
				<tr>
					<td>商品名称</td>
					<td>
						<input type="hidden" name="currentPage" id ="startPage"/>
						<form:input path="criteria.name" />
					</td>
				</tr>
			</table>
			<div class="submit">
				<input type="submit" value="查询"> 
			</div>
			<table>
				<tr>
					<td>名称</td>
					<td>描述</td>
					<td>类型</td>
					<td>创建人</td>
					<td>创建时间</td>
					<td>修改人</td>
					<td>修改时间</td>
				</tr>
				
				<c:forEach items="${merchandiseList }" var="list" >
					<tr>
						<td><c:out value="${list.name }"></c:out> </td>
						<td><c:out value="${list.description }"></c:out></td>
						<td><c:out value="${list.catagory.name }"></c:out></td>
						<td><c:out value="${list.createdBy }"></c:out></td>
						<td><c:out value="${list.createdAt }"></c:out></td>
						<td><c:out value="${list.lastModifiedBy }"></c:out></td>
						<td><c:out value="${list.lastModifiedAt }"></c:out></td>
					</tr>
				</c:forEach>
			</table>
			<div id="Pagination_" class="pagination">
			</div>
			
			<!-- <div>
				<input type="button" value="修改"> &nbsp;&nbsp; 
				<input type="button" value="删除"> 
			</div> -->
		</form>
		<script type="text/javascript">
			
			var totalCount = parseInt('<c:out value="${totalCount }"></c:out>');
			var perpage = parseInt('<c:out value="${perpage }"></c:out>');
			var currentPage = parseInt('<c:out value="${currentPage }"></c:out>');
			
			$(document).ready(function(){
				if(totalCount >  perpage){
					$("#Pagination_").pagination(totalCount, 
						{items_per_page:perpage,
							num_display_entries:8,
							num_edge_entries: 2,
							current_page:currentPage-1,
							prev_text:"上一页",
							next_text:"下一页",
							dataprocess: function(page_id) {
							$('#startPage').val(page_id+1);
							$('#searchMerchandise').submit();
						}
					   }
				)}
			});
		</script>
</div>