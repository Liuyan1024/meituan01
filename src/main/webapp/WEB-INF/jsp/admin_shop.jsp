<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>用户管理</title>
	<link href="../bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="../admin/css/admin_index.css" type="text/css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<script src="../js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="../bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="admin/js/manage.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#addUser").click(function(){
				var pa = $(top.document);
				pa.find("#addUser table tr td:eq(1) input").val("");
				pa.find("#addUser table tr td:eq(3) input").val("");
				pa.find("#addUser table tr td:eq(5) select").val("");
				pa.find("#addUser table tr td:eq(7) input").val("");
				pa.find("#addUser table tr td:eq(9) input").val("");
				pa.find("#addUserButton").click();
			});
		});
	</script>
  </head>
  
  <body>
  	<div class="content">
	    <div class="btnDiv">
			
		</div>
		<table>
			<tr class="head" align="center">
				<td width="120px;">商家id</td>
				<td width="150px;">商家名</td>
				<td width="150px;">商家手机</td>
				<td width="160px;">商家地址</td>
				
				<td width="90px">操作</td>
			</tr>
			
				<c:forEach var="mlist" items="${mlist}">
					<tr align="center">
						<td>${mlist.cid }</td>
						<td>${mlist.name }</td>
						<td>${mlist.phone}</td>
						<td>${mlist.address }</td>
						<td>
						<a href="/adm/mer/update/${mlist.cid }">修改</a>
							<a href="/adm/mer/del/${mlist.cid }">删除</a>
						</td>
					</tr>
				</c:forEach>
			
		</table>
	</div>
	<!--  <c:if test="${ucount != 0 }">
		<div class="page">
			<ul class="pagination">
				<c:if test="${currentPage == 1 }">
				  <li class="disabled"><a href="javascript:void(0)">&laquo;</a></li>
				</c:if>
				<c:if test="${currentPage != 1 }">
				  <li><a href="">&laquo;</a></li>
				</c:if>
				<c:if test="${totalPage<10}">
					<c:forEach begin="1" end="${totalPage }" varStatus="c">
						<c:if test="${currentPage == c.index }">
					  	 	<li class="active"><a href="">${c.index }</a></li>
					  	</c:if>
					  	<c:if test="${currentPage != c.index }">
					  	 	<li><a href="">${c.index }</a></li>
					  	</c:if>
					</c:forEach>
		    	</c:if>
				<c:if test="${totalPage>=10&&currentPage>=10&&currentPage<totalPage-10 }">
					<c:forEach begin="${(currentPage/10)*10 }" end="${((currentPage/10)+1)*10 }" varStatus="c">
						<c:if test="${currentPage == c.index }">
					  	 	<li class="active"><a href="">${c.index }</a></li>
					  	</c:if>
					  	<c:if test="${currentPage != c.index }">
					  	 	<li><a href="">${c.index }</a></li>
					  	</c:if>
					</c:forEach>
		    	</c:if>
		    	<c:if test="${totalPage>=10&&currentPage>=10&&currentPage>=totalPage-10 }">
					<c:forEach begin="${currentPage }" end="${totalPage }" varStatus="c">
						<c:if test="${currentPage == c.index }">
					  	 	<li class="active"><a href="">${c.index }</a></li>
					  	</c:if>
					  	<c:if test="${currentPage != c.index }">
					  	 	<li><a href="">${c.index }</a></li>
					  	</c:if>
					</c:forEach>
		    	</c:if>
		    	<c:if test="${totalPage>=10&&currentPage<10 }">
					<c:forEach begin="1" end="10" varStatus="c">
						<c:if test="${currentPage == c.index }">
					  	 	<li class="active"><a href="">${c.index }</a></li>
					  	</c:if>
					  	<c:if test="${currentPage != c.index }">
					  	 	<li><a href="">${c.index }</a></li>
					  	</c:if>
					</c:forEach>
		    	</c:if>
			  <c:if test="${currentPage == totalPage }">
			  	<li class="disabled"><a href="javascript:;">&raquo;</a></li>
			  </c:if>
			  <c:if test="${currentPage != totalPage }">
			  	<li><a href="">&raquo;</a></li>
			  </c:if>
			</ul>
		</div>
	</c:if>-->
  </body>
</html>
