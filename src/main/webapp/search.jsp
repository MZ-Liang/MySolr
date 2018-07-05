<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${root}/resources/jquery.min.js"></script>
<style type="text/css">
	.show{
		margin-top: 30px;
	}
</style>
<script type="text/javascript">
</script>
</head>
<body>
	<div>
		<form action="${root}/search" method="post">
			<table rules="all" frame="border">
				<tbody>
					<tr>
						<td>关键字</td>
						<td><input type="text" name="keywordList" /></td>
						<td><input type="text" name="keywordList" /></td>
						<td><input type="text" name="keywordList" /></td>
					</tr>
					<tr>
						<td>筛选条件</td>
						<td>
							<select name="status">
								<option value="-1">请选择状态</option>
								<option value="1">上架</option>
								<option value="0">下架</option>
							</select>
						</td>
						<td>
							<select name="orderType">
								<option value="-1">请选择排序</option>
								<option value="0">按发版时间升序</option>
								<option value="1">按发版时间降序</option>
							</select>
						</td>
						<td>
							<select name="ifHL">
								<option value="true">高亮</option>
								<option value="false">不高亮</option>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
			<div>
				<input type="submit" value="search"/>
			</div>
		</form>
	</div>
	<c:if test="${not empty page}">
		<div class="show">
			<table rules="all" frame="border">
				<tr>
					<th>序号</th>
					<th>id</th>
					<th>bookName</th>
					<th>status</th>
					<th>content</th>
					<th>publishDate</th>
					<th>author_id</th>
					<th>author_name</th>
					<th>author_nickname</th>
				</tr>
				<c:forEach items="${page.list}" var="l" varStatus="i">
					<tr>
						<td>${i.index}</td>
						<td>${l.id}</td>
						<td>${l.bookName}</td>
						<td>${l.status}</td>
						<td>${l.content}</td>
						<td>${l.publishDate}</td>
						<td>${l.author.id}</td>
						<td>${l.author.name}</td>
						<td>${l.author.nickname}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div>
			<span>共${page.count}条记录 </span> <span>第${page.pageNo}页 </span> <span>共${page.totalPage}页
			</span>
		</div>
	</c:if>
</body>
</html>