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
			<form action="create" method="post">
			<div>
		          	商品类别
		        	 <form:select path="merchandise.catagory.id">
		        	 	<form:option label="-- Please Select --" value=""></form:option>
		        	 	<form:options items="${catagories }" itemLabel="name" itemValue="value" />
		        	 </form:select>
			</div>
			<br />
			
			<div>
		          	大客户
		        	 <form:select cssStyle="width:160px;" path="merchandise.description" multiple="true" >
		        	 	<form:options items="${orgs }" itemLabel="name" itemValue="value" />
		        	 </form:select>
			</div>	
			<br />
			
			<div>
		          	商品名称
		        	 <form:input path="merchandise.name" /> <font color="red"><form:errors path="merchandise.name"></form:errors></font>
			</div>	
			 <br />
			 <div>
		          	商品图片
			</div>	
			<br />
			 
			<div>
					商品描述 
				<form:textarea cols="40" path="merchandise.description" />
			</div>
			<br />
			<div class="submit">
				<input type="button" value="上传图片" />
		        <input id="proceed" type="submit" value="提交" />
		        <input id="reset" type="reset" value="重置" /> 
		     </div>
		</form>
</div>