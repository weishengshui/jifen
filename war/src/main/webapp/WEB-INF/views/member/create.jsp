<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
<div>
  <div id="title" title="${empty member.id ? '添加' : '修改' }会员信息">
    <form action="create" method="POST">
      <input type="hidden" value="${member.id }" name="id" />
      <table border="0">
      	<tr>
      		<td width="20"><label for="name">姓名</label></td>
      		<td><input id="name" type='text' name='name' style="width:150px" value="${member.name }" maxlength="20"/></td>
      	</tr>
      	<tr>
      		<td>性别</td>
      		<td>
      			<label style="width: 50px;">男<input type='radio' name='sex' value="1" style="margin:0;" checked="checked"/></label>
        		<label>女<input type='radio' name='sex' value="0" style="margin:0;" ${member.sex==0?'checked':'' } /></label>
        	</td>
      	</tr>
      	<tr>
      		<td><label for="phone">手机号</label></td>
      		<td><input id="phone" type='text' name='phone' style="width:150px" value="${member.phone }" maxlength="11"/></td>
      	</tr>
      	<tr>
      		<td><label for="email">电子邮箱</label></td>
      		<td><input id="email" type='text' name='email' style="width:150px" value="${member.email }" maxlength="50"/></td>
      	</tr>
      	<tr>
      		<td><label for="addre">地址</label></td>
      		<td><input id="address" type='text' name='address' style="width:150px" value="${member.address}" maxlength="200"/></td>
      	</tr>
      	<tr>
      		<td colspan="2">
      			<div class="submit">
		        <input id="proceed" type="submit" value="提交" />
		        <input id="reset" type="reset" value="重置" />
		      	</div>
      		</td>
      	</tr>
      </table>
    </form>
  </div>
</div>
</body>
</html>
