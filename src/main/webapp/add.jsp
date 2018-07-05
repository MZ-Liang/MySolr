<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${root}/resources/jquery.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<div align="center">
		<form action="${root}/add" method="post">
			<table rules="all" frame="border">
				<tbody>
					<tr>
						<td><label>书名</label></td>
						<td><input type="text" name="bookName"/></td>
					</tr>
					<tr>
						<td><label>作者</label></td>
						<td><input type="text" name="author.name"/></td>
					</tr>
					<tr>
						<td><label>作者昵称</label></td>
						<td><input type="text" name="author.nickname"/></td>
					</tr>
					<tr>
						<td><label>内容</label></td>
						<td><textarea name="content" cols="45" rows="10"></textarea></td>
					</tr>
					<tr>
						<td><label>上下架</label></td>
						<td>
							<select name="status">
								<option value="1">上架</option>
								<option value="0">下架</option>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
			<input type="submit" value="添加索引"/>
		</form>
	</div>
</body>
</html>