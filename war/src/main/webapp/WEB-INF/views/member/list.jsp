<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style>
.member_query_table {
	border: 0px;
	margin-bottom: 5px;
}
.member_query_table tr td {
	border: 0px;
}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<c:url value='/js/jquery/jquery.pagination.withoutAjax.js' />" charset="UTF-8"></script>
<script>
	var selects = '' ;
	function checkSelect(){
		var n = 0;
		selects = '';
		var objs = $("input[name='checkbox']");
		for(var i=0; i<objs.length; i++){
			if(objs[i].checked == true){
				n++;
				selects += "," + objs[i].value;
			}
		}
		return n;
	}
	function update(){
		var n = checkSelect();
		if(n == 0){alert("请选择一条记录!");return;}
		if(n > 1){alert("只能选择一条记录!");return;}
		window.location.href = parseInt(selects.split(",")[1]);
	}

	function disable(status){
		var n = checkSelect();
		if(n == 0){alert("请选择一条记录!");return;}
		window.location.href = "updateStatus?status="+status+"&ids="+selects.substring(1, selects.length);
	}
</script>
</head>
<body>
<div>
<div>
	<div>
		<form id="aa" action="list" method="post" style="float: left; margin-right: 10px;">
			<table class="member_query_table">
				<tr>
					<td>会员姓名：</td>
					<td><input type="text" name="name" style="width: 80px; height: 15px;" value="${member.name }" /></td>
					<td>状态：</td>
					<td><select name="status">
							<option></option>
							<option value="0">正常</option>
							<option value="1">禁用</option>
						</select>
					</td>
					<td><input type="submit" value="查询" /></td>
					<td><a href="createPage">新增会员</a></td>
				</tr>
			</table>
		</form>
	</div>
	<br />
	<table>
		<thead>
			<tr>
				<td></td>
				<td>会员姓名</td>
				<td>性别</td>
				<td>手机号码</td>
				<td>电子邮箱</td>
				<td>地址</td>
				<td>状态</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="member">
				<tr>
					<td><input type="checkbox" value="${member.id }" name="checkbox"/></td>
					<td>${member.name }</td>
					<td>${member.sex==1?'男':'女' }</td>
					<td>${member.phone }</td>
					<td>${member.email }</td>
					<td>${member.address }</td>
					<td>${member.status==0?'正常':'禁用' }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<table class="member_query_table" style="margin-top: 5px;">
		<tr>
			<td>
				<a href="javascript:void(0)" onclick="update()">修改</a>&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="disable(0)">启用</a>&nbsp;/&nbsp;
				<a href="javascript:void(0)" onclick="disable(1)">禁用</a>&nbsp;&nbsp;
			</td>
		</tr>
	</table>
	<div id="Pagination_" class="pagination"></div>
	<script type="text/javascript">
			var totalCount = parseInt('${page.totalRows}');
			var perpage = parseInt('${page.perpage}');
			var currentPage = parseInt('${perpage}');
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
							$('#aa').submit();
						}
					   }
				)}
			});
		</script>
</div>
</div>
</body>
</html>