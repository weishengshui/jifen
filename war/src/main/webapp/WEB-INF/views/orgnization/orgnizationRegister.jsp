<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Operation</title>
<script type="text/javascript"
	src="<c:url value='/js/jquery/jquery-latest.min.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/js/jquery/jquery.pagination.withoutAjax.js' />"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="<c:url value='/js/jquery/jquery-ui-1.8.2.custom.min.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/js/jquery/jquery-ui-i18n.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/js/jquery/ui/jquery.ui.autocomplete.selectFirst.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/js/jquery/jquery.form.js' />"></script>

<style type="text/css">
body {
	font: 15px/1 "宋体", SimSun, serif;
	background: #fff;
	color: #000;
}

ul,li,dl,dt,dd {
	margin: 0;
	padding: 0; /* 清除浏览器默认的margin和padding值 */
}

ul,li {
	list-style: none outside; /* 清除浏览器中列表项默认的占位 */
}

a {
	text-decoration: none;
}

img {
	border: 0;
}

/* 选项卡整体外观定义 */
.tabList {
	width: 810px;
	height: 630px;
	overflow: hidden;
}

.tabList .tabBox {
	height: 100%;
	position: relative;
	background: #09c;
}

.tabList .tabBox li {
	float: left;
	width: 15%;
	text-align: center;
}

.tabList h4 {
	margin: 0;
	height: 26px;
	font-size: 1em;
	line-height: 22px;
}

.tabList h4 a {
	color: #039;
	font-weight: normal;
	display: block;
	background: #bfeafd url(http://www.webjx.com/files/090217/1_222040.png)
		no-repeat;
	padding-left: 5px;
}

.tabList h4 a span {
	display: block;
	background: url(http://www.webjx.com/files/090217/1_222040.png)
		no-repeat top right;
	padding-right: 5px;
}

.tabList h4 a:hover,.tabList .tabOn h4 a {
	background-position: 0 -50px;
}

.tabList h4 a:hover span,.tabList .tabOn h4 a span {
	background-position: right -50px;
}

.tabList .tabContentBox li {
	width: auto;
	margin-top: 0;
}

.tabList .tabContentBox {
	width: 800px;
	height: 600px;
	border: 1px solid #069;
	text-align: left;
	overflow: hidden;
	background: #fff;
	position: absolute;
	top: 22px;
	left: 0;
	display: none;
	margin: 4px;
}

.tabList li:hover .tabContentBox,.tabList .tabOn .tabContentBox {
	display: block;
}

/* 以下是对各项内容的CSS定义 */
.tabList .tabContentBox .blog,.tabList .tabContentBox .group {
	color: #999;
	line-height: 2em;
	margin: 5px;
}

.tabList .tabContentBox .blog dt,.tabList .tabContentBox .group dt,.tabList .tabContentBox .blog dd,.tabList .tabContentBox .group dd
	{
	float: left;
	border-bottom: 1px dotted #9cf;
}

.tabList .tabContentBox .blog dt,.tabList .tabContentBox .group dt {
	width: 60%;
	white-space: nowrap;
	overflow: hidden; 
	clear: left;
	font-size: 1.1em;
}

.tabList .tabContentBox .blog dt a,.tabList .tabContentBox .group dt a {
	color: #039;
	display: block;
	margin-left: 5px;
	padding-left: 12px;
	background: url(http://www.webjx.com/files/090217/1_222128.png)
		no-repeat 5px 11px;
}

.tabList .tabContentBox .blog dt a:hover,.tabList .tabContentBox .group dt a:hover
	{
	color: #f90;
	background-position: 5px -19px;
}

.tabList .tabContentBox .blog dd,.tabList .tabContentBox .group dd {
	color: #0cf;
	width: 20%;
	float: left;
	white-space: nowrap;
	overflow: hidden; 
}

.tabList .tabContentBox .blog dd a,.tabList .tabContentBox .group dd a {
	color: #069;
}

.tabList .tabContentBox .blog dd a:hover,.tabList .tabContentBox .group dd a:hover
	{
	color: #f90;
}

.tabList .tabContentBox .album li,.tabList .tabContentBox .share li {
	width: 24.9%;
	float: left;
	padding-top: 15px;
	text-align: center;
}
</style>
<script type="text/javascript" language="javascript">
		window.onload = tabEffect;
		function tabEffect()
		{
			// getCardsByOrgnizationId();
			//  getCardLevelsByCardId();
		/* var allElements = document.getElementsByTagName('*');
		for (var i=0; i<allElements.length; i++)
		{
		if (allElements[i].className.indexOf('tabOption') >= 0)
		{
		// allElements[i].onmouseover = mouseOver;
			  
		}
		} */
		}
		function mouseOver()
		{
		tabList = this.parentNode;
		tabOptions = tabList.getElementsByTagName("li");
		for (var i=0; i<tabOptions.length; i++)
		{
		if (tabOptions[i].className.indexOf('tabOption') >= 0)
		{
		tabOptions[i].className = "tabOption";
		}
		}
		this.className += " tabOn";
		} 

		function display(object){
			tabList = object.parentNode;
			tabOptions = tabList.getElementsByTagName("li");
			style="display:none";
			for (var i=0; i<tabOptions.length; i++)	{
				if (tabOptions[i].className.indexOf('tabOption') >= 0)	{
					tabOptions[i].className = "tabOption";
					tabOptions[i].getElementsByTagName("div")[0].style.display = "";
				}
			}
			object.className += " tabOn";
			object.getElementsByTagName("div")[0].style.display = "";
		}
		/*
		function checkDefault(obj){
			var pu = document.getElementById("pointUnit");
			var pup = document.getElementById("pointUnitPrice");
			if(obj.checked){
				pu.value="缤纷";
				pu.setAttribute("readOnly",true);
				pup.value="0.75";
				pup.setAttribute("readOnly",true);
			}else{
				pu.value=null;
				pu.removeAttribute("readOnly");
				pup.value=null;
				pup.removeAttribute("readOnly");
			}
			
		}
		*/
		/*
		function getCardsByOrgnizationId(){
			displayCardDiv();
			var content = "";
			
			var cardDiv = document.getElementById("cardDiv");
			
			var url = "<c:url value='/or/getCardsByOrgnizationId' />";
			if(document.getElementById("orgnizationId") == null){
				content = "当前还没有大客户！";
				cardDiv.innerHTML = content;
				return;
			}
			var orgnizationId = document.getElementById("orgnizationId").value;
			
			if(orgnizationId == null || orgnizationId.Trim().length==0){
				content = "当前还没有大客户！";
				cardDiv.innerHTML = content;
				return;
			}
			var params = {
					orgnizationId:orgnizationId
			};
			jQuery.post(url, params, function(data){

				alert("sssssssssssssssssss");
				alert(data);
				var button = "<br><br><button onclick='displayAddCardDiv()'>添加卡</button>";
				if(data==null){
					content = "当前大客户还没有卡！";
					content += button;
					cardDiv.innerHTML = content;
					return;
				}else{
					var count = data.length;
					if(count==0){
						content = "当前大客户还没有卡！";
						content += button;
						cardDiv.innerHTML = content;
						return;
					}else{
						content = "<table border='1'>";
						content += "<tr><td>卡名称</td><td>操作</td></tr>";
						for(var i=0;i<count;i++){
							var id = data[i].id;
							var name = data[i].name;
							var updateCardButton = "<button onclick='beforeUpdateCard(\""+id+"\",\"" + name+"\")'>修改</button>";
							var deleteCardButton = "<button onclick='deleteCard(\""+id+"\")'>删除</button>";
							content += "<tr><td>"+name+"</td><td>"+updateCardButton + " | " + deleteCardButton+"</td></tr>";
						}
						content += "</table>";
					}
				}
				content += button;
				cardDiv.innerHTML = content;
			}); 
			
		}
		*/
		// beforeUpdateCard()
		/* function beforeUpdateCard(id, name){
		
			document.getElementById("ucardId").value = id;
			document.getElementById("cardName1").value = name;
			
			displayUpdateCardDiv();
		}
		*/ 
		// updateCard()
		/* function updateCard(){
			
			var cardId = document.getElementById("ucardId").value;
			var cardName = document.getElementById("cardName1").value;
			if(cardId == null || cardId.Trim().length == 0){
				return;
			}
			if(cardName == null || cardName.Trim().length == 0){
				alert("请输入卡名称！");
				return;
			}
			if(!confirmSomething("确认修改卡？")){
				return;
			}
			var url = "<c:url value='/or/updateCard' />";			
			var params = {
					cardId:cardId,
					cardName:cardName
					};
			jQuery.post(url, params, function(data){
					getCardsByOrgnizationId();
				});
			
		}
		*/
		// deleteCard()
		/* function deleteCard( id ){
			if(id==null || id.Trim().length == 0){
				return;
			}
			if(!confirmSomething("确认删除卡？")){
				return;
			}
			var url = "<c:url value='/or/deleteCardByCardId' />";
			var params = {
					cardId:id,
					};
			jQuery.post(url, params, function(data){
					getCardsByOrgnizationId();
				});
		}*/ 
		//addCard()
		/* function addCard(){
			
			var url = "<c:url value='/or/addCard' />";
			var cardName = document.getElementById("cardName").value;
			if(cardName == null || cardName.Trim().length == 0){
				alert("请输入卡名称！");
				return;
			}
			if(!confirmSomething("确认添加卡？")){
				return;
			}
			var orgnizationId = document.getElementById("orgnizationId").value;
			var params = {
					cardName:cardName,
					orgnizationId:orgnizationId
					};
			
			jQuery.post(url, params, function(data){
				  getCardsByOrgnizationId();
				}); 
			
		} 
		*/
		
		// getCardLevelsByCardId()
		/*function getCardLevelsByCardId(){
			displayCardLevelDiv();
			var cardLevelDiv = document.getElementById("cardLevelDiv");
			var content = "";
			var button = "<br><br><button onclick='displayAddCardLevelDiv()'>添加卡级别</button>";
				content = "当前卡还没有卡级别！";
				content += button;
				cardLevelDiv.innerHTML = button; */
			/*
			var url = "<s:url namespace='/view/crm' action='getCardLevelsByCardId.action' />";
			var cardId = document.getElementById("cardId").value;
			var cardLevelDiv = document.getElementById("cardLevelDiv");
			var content = "";
			var button = "<br><br><button onclick='displayAddCardLevelDiv()'>添加卡级别</button>";
			if(cardId == null || cardId.Trim().length==0){
				content = "当前卡还没有卡级别！";
				content += button;
				cardLevelDiv.innerHTML = button;
				return;
			}
			var params = {
					cardId:cardId
			};
			jQuery.post(url, params, function(data){
		
				if(data.cardLevels==null){
					content = "当前卡还没有卡级别！";
				}else{
					var cardLevels = data.cardLevels;
					var count = cardLevels.length;
					if(count==0){
						content = "当前卡还没有卡级别！";	
					}else{
						content = "<table border='1'>";
						content += "<tr><td>级别名称</td><td>级别有效期</td><td>升级所需积分</td><td>级别序号</td><td>操作</td></tr>";
						for(var i=0;i<count;i++){
							var id = cardLevels[i].id;
							var levelName = cardLevels[i].levelName;
							var effectiveCycle = cardLevels[i].effectiveCycle;
							var upgradeStandard = cardLevels[i].upgradeStandard;
							var levelNumber = cardLevels[i].levelNumber;
							var updateCardLevelButton = "<button onclick='beforeUpdateCardLevel(\""+id+"\",\"" + levelName+"\",\""+effectiveCycle +"\",\""+upgradeStandard +"\",\""+levelNumber +"\")'>修改</button>";
							var deleteCardLevelButton = "<button onclick='deleteCardLevel(\""+id+"\")'>删除</button>";
							if(effectiveCycle == -1){
								effectiveCycle = "永久有效";
							}else{
								effectiveCycle = effectiveCycle + "个月";
							}
							content += "<tr><td>"+levelName+"</td><td>"+effectiveCycle+"</td><td>"+upgradeStandard+"</td><td>"+levelNumber+"</td><td>"+updateCardLevelButton +" | " + deleteCardLevelButton+"</td></tr>";
						}
						
					}
						content += "</table>";
				}
				content += button;
				cardLevelDiv.innerHTML = content;
			}); */
		/* }*/
		// addCardLevel()
		/* function addCardLevel(){
			var url = "<s:url namespace='/view/crm' action='addCardLevel.action' />";
			var levelName = document.getElementById("levelName").value;
			var effectiveCycle = document.getElementById("effectiveCycle").value;
			var upgradeStandard = document.getElementById("upgradeStandard").value;
			var levelNumber = document.getElementById("levelNumber").value;
			if(levelName == null || levelName.Trim().length == 0){
				alert("请输入级别名称！");
				return;
			}
			if(effectiveCycle == null || effectiveCycle.Trim().length == 0){
				alert("请输入级别有效期！");
				return;
			}	else if(effectiveCycle == "永久有效"){
				effectiveCycle = -1;
			} else if(effectiveCycle!=parseInt(effectiveCycle) || effectiveCycle < 0){
				alert("请输入正确的级别有效期！");
				return;
			} 
			if(upgradeStandard == null || upgradeStandard.Trim().length == 0){
				alert("请输入升级所需积分！");
				return;
			}else if(upgradeStandard!=parseInt(upgradeStandard) || upgradeStandard < 0){
				alert("请输入正确的升级所需积分！");
				return;
			}
			if(levelNumber == null || levelNumber.Trim().length == 0){
				alert("请输入级别序号！");
				return;
			}else if(levelNumber !=parseInt(levelNumber)){
				alert("请输入正确的级别序号(级别序号为一整数)！");
				return;
			}
			if(!confirmSomething("确认添加卡级别？")){
				return;
			}
			var cardId = document.getElementById("cardId").value;
			var params = {
					cardId:cardId,
					levelName:levelName,
					effectiveCycle:effectiveCycle,
					upgradeStandard:upgradeStandard,
					levelNumber:levelNumber
					};
			
			jQuery.post(url, params, function(data){
				getCardLevelsByCardId();
				}); 
		}
		function beforeUpdateCardLevel(id, levelName, effectiveCycle, upgradeStandard, levelNumber){
			
			document.getElementById("ucardLevelId").value = id;
			document.getElementById("levelName1").value = levelName;
			if(effectiveCycle=="-1"){
				document.getElementById("effectiveCycle1").value = "永久有效";	
			}else{
				document.getElementById("effectiveCycle1").value = effectiveCycle;
			}
			document.getElementById("upgradeStandard1").value = upgradeStandard;
			document.getElementById("levelNumber1").value = levelNumber;
		
			displayUpdateCardLevelDiv()
			
		}
		function deleteCardLevel(id){
		
			var cardLevelId = id;
			if(confirmDeleteCardLevel()){
				var url = "<s:url namespace='/view/crm' action='deleteCardLevel.action' />";
				var params = {
						cardLevelId:cardLevelId
						};
				jQuery.post(url, params, function(data){
					req_data();
					});
			}
		}
		function updateCardLevel(){
			if(validateUpdateCardLevel()==true){
				var url = "<s:url namespace='/view/crm' action='updateCardLevel.action' />";
				
				var levelName = document.getElementById("levelName1").value;
				var effectiveCycle = document.getElementById("effectiveCycle1").value;
				var upgradeStandard = document.getElementById("upgradeStandard1").value;
				var levelNumber = document.getElementById("levelNumber1").value;
				var cardLevelId = document.getElementById("ucardLevelId").value;
		
				if(levelName == null || levelName.Trim().length == 0){
					alert("请输入级别名称！");
					return;
				}
				if(effectiveCycle == null || effectiveCycle.Trim().length == 0){
					alert("请输入级别有效期！");
					return;
				}	else if(effectiveCycle == "永久有效"){
					effectiveCycle = -1;
				} else if(effectiveCycle!=parseInt(effectiveCycle) || effectiveCycle < 0){
					alert("请输入正确的级别有效期！");
					return;
				} 
				if(upgradeStandard == null || upgradeStandard.Trim().length == 0){
					alert("请输入升级所需积分！");
					return;
				}else if(upgradeStandard!=parseInt(upgradeStandard) || upgradeStandard < 0){
					alert("请输入正确的升级所需积分！");
					return;
				}
				if(levelNumber == null || levelNumber.Trim().length == 0){
					alert("请输入级别序号！");
					return;
				}else if(levelNumber !=parseInt(levelNumber)){
					alert("请输入正确的级别序号(级别序号为一整数)！");
					return;
				}
				if(!confirmSomething("确认修改卡级别？")){
					return;
				}
				var params = {
						cardLevelId:cardLevelId,
						levelName:levelName,
						effectiveCycle:effectiveCycle,
						upgradeStandard:upgradeStandard,
						levelNumber:levelNumber
						};
				
				jQuery.post(url, params, function(data){
					getCardLevelsByCardId();
					}); 
			}
		}
		*/
		
		//***************************************************************************************
		// displayAddCardLevelDiv()
		/* function displayAddCardLevelDiv(){
			style="display:none";
			setCardLevelNull();
			document.getElementById("addCardLevelDiv").style.display = "";
			document.getElementById("cardLevelDiv").style.display = "none";
			document.getElementById("updateCardLevelDiv").style.display = "none";
		}
		function displayCardLevelDiv(){
			style="display:none";
			setCardLevelNull();
			document.getElementById("cardLevelDiv").style.display = "";
			document.getElementById("addCardLevelDiv").style.display = "none";
			document.getElementById("updateCardLevelDiv").style.display = "none";
		}
		function displayUpdateCardLevelDiv(){
			style="display:none";
			document.getElementById("cardLevelDiv").style.display = "none";
			document.getElementById("addCardLevelDiv").style.display = "none";
			document.getElementById("updateCardLevelDiv").style.display = "";
		}
		function setCardLevelNull(){
			document.getElementById("levelName").value = ""; 
			document.getElementById("effectiveCycle").value = "";
			document.getElementById("upgradeStandard").value = "";
			document.getElementById("levelNumber").value = "";
		
			document.getElementById("levelName1").value = ""; 
			document.getElementById("effectiveCycle1").value = "";
			document.getElementById("upgradeStandard1").value = "";
			document.getElementById("levelNumber1").value = "";
		}
		*/
		
		//***************************************************************************************
		/* function displayAddCardDiv(){
			style="display:none";
			setCardNull();
			document.getElementById("addCardDiv").style.display = "";
			document.getElementById("cardDiv").style.display = "none";
			document.getElementById("updateCardDiv").style.display = "none";
		}
		function displayCardDiv(){
			style="display:none";
			setCardNull();
			document.getElementById("cardDiv").style.display = "";
			document.getElementById("addCardDiv").style.display = "none";
			document.getElementById("updateCardDiv").style.display = "none";
		}
		function displayUpdateCardDiv(){
			style="display:none";
			document.getElementById("cardDiv").style.display = "none";
			document.getElementById("addCardDiv").style.display = "none";
			document.getElementById("updateCardDiv").style.display = "";
		}
		function setCardNull(){
			document.getElementById("cardName").value = "";
			document.getElementById("cardName1").value = "";
		}
		function confirmAddCardLevel(){
			if(confirm("确认添加卡级别？")){
				return true;
			}
			return false;	
		}
		*/
		
			
		//去除字符串左右的空格
		String.prototype.Trim   =   function()
		{
		return   this.replace(/(^\s*)|(\s*$)/g,   "");
		}
		//去除字符串左边的空格
		String.prototype.LTrim   =   function()
		{
		return   this.replace(/(^\s*)/g,   " ");
		}
		//去除字符串右边的空格
		String.prototype.Rtrim   =   function()
		{
		return   this.replace(/(\s*$)/g,   " ");
		} 
		
		
		function confirmSomething(content){
			if(confirm(content)){
				return true;
			}
			return false;
		}

		function createOrgnization(){
			var name = document.getElementById("addOrgnName").value;
			var forShort = document.getElementById("addOrgnForShort").value;
			var businessLicenseNumber = document.getElementById("addOrgnBusinessLicenseNumber").value;
			var params = {
					name:name,
					forShort:forShort,
					businessLicenseNumber:businessLicenseNumber
					};
			var url='<c:url value="/orgnization/create" />';
			jQuery.post(url, params, function(data){
				document.getElementById("aName").innerHTML = "";
				document.getElementById("aForShort").innerHTML = "";
				document.getElementById("aBusinessLicenseNumber").innerHTML = "";
					if(data==null || data.length==0){
						alert("添加成功！");
						display(document.getElementById("orgnListId"));		
						resetOrgnizationCreate();
					}else{
						var count = data.length;
						for(var i = 0; i < count; i++){
							switch(data[i].code){
							case "name":
								document.getElementById("aName").innerHTML = data[i].defaultMessage;
								break;
							case "forShort":
								document.getElementById("aForShort").innerHTML = data[i].defaultMessage;
								break;
							case "businessLicenseNumber":
								document.getElementById("aBusinessLicenseNumber").innerHTML = data[i].defaultMessage;
								break;
							}
						}
						
					} 
				});
		}
		
		function resetOrgnizationCreate(){
			document.getElementById("addOrgnName").value = "";
			document.getElementById("addOrgnForShort").value = "";
			document.getElementById("addOrgnBusinessLicenseNumber").value = "";
		}
		
		</script>

</head>
<body>

<h2>大客户基本信息管理</h2>
<div class="tabList" id="tabList1">
<ul class="tabBox">

<li class="tabOption tabOn" onclick="display(this)" id="orgnListId"><h4><a><span>大客户列表</span></a></h4>
	<div class="tabContentBox">
		<center>
			<c:choose>
				<c:when test="${!empty orgnizations}">
					<table>
						<c:forEach items="${orgnizations }" var="orgn" varStatus="s">
							<c:choose>
								<c:when test="${s.first == true }">
									<tr>
										<td>大客户名称</td>
										<td>大客户简称</td>
										<td>营业执照</td>
										<td>操作</td>
									</tr>
								</c:when>
							</c:choose>
							<tr>
								<td><c:out value="${orgn.name }" /></td>
								<td><c:out value="${orgn.forShort }" /></td>
								<td><c:out value="${orgn.businessLicenseNumber }" /></td>
								<td></td>
							</tr>
						</c:forEach>
					</table>					 
				</c:when>
				<c:otherwise>
					<br><br><br>
					<h3>当前没有大客户</h3>
				</c:otherwise>
			</c:choose>
		</center>
	</div>
</li>
<li class="tabOption" id="addOrgn" onclick="display(this)"><h4><a><span>添加大客户</span></a></h4>
	<div class="tabContentBox">
		<center>
			
<!--			<form:form method="post" action='create'  modelAttribute="orgnization">-->
<!--				<table>-->
<!--					<tr>-->
<!--						<td align="right" width="auto">大客户名称</td>-->
<!--						<td>-->
<!--							<form:input path="name" />-->
<!--							<font color="red"><form:errors path="name" id="aName"/></font>-->
<!--						</td>-->
<!--					</tr>-->
<!--					<tr>-->
<!--						<td align="right" width="auto">大客户简称</td>-->
<!--						<td>-->
<!--							<form:input path="forShort" />-->
<!--							<font color="red"><form:errors path="forShort" id="aForShort"/></font>-->
<!--						</td>-->
<!--					</tr>-->
<!--					<tr>-->
<!--						<td align="right" width="auto">营业执照</td>-->
<!--						<td>-->
<!--							<form:input path="businessLicenseNumber" />-->
<!--							<font color="red"><form:errors path="businessLicenseNumber" id="aBusinessLicenseNumber"/></font>-->
<!--						</td>-->
<!--					</tr>-->
<!--					<tr>-->
<!--						<td align="right" width="auto">-->
<!--							<input type="submit" value="添加">-->
<!--						</td>-->
<!--						<td>-->
<!--							<input type="reset" value="重置"/>-->
<!--						</td>-->
<!--					</tr>-->
<!--				</table>-->
<!--			</form:form>-->
			
				<table>
					<tr>
						<td align="right" width="auto">大客户名称</td>
						<td width="auto">
							<input type="text" name="name"  id="addOrgnName">
							<font color="red">
								<span id="aName"></span>
							</font>
						</td>
					</tr>
					<tr>
						<td align="right" width="auto">大客户简称</td>
						<td>
							<input type="text" name="forShort" id="addOrgnForShort">
							<font color="red"><span id="aForShort"></span></font>
						</td>
					</tr>
					<tr>
						<td align="right" width="auto">营业执照</td>
						<td width="auto">
							<input type="text" name="businessLicenseNumber" id="addOrgnBusinessLicenseNumber">
							<font color="red"><span id="aBusinessLicenseNumber"></span></font>
						</td>
					</tr>
					<tr>
						<td align="right" width="auto">
							<button onclick="createOrgnization()">添加</button>
						</td>
						<td width="auto">
							<button onclick="resetOrgnizationCreate()">重置</button>
						</td>
					</tr>
				</table>
		</center>
	</div>
</li>
<li class="tabOption" id="queryOrgn" onclick="display(this)"><h4><a><span>查询大客户</span></a></h4>
	<div class="tabContentBox">
		<center>
			
		</center>
	</div>
</li>
		
</ul>
</div>
	
</body>
</html>