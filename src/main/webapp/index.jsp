<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${root}/resources/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#del").click(function(){
			if(confirm('确定删除全部索引？')){
				var url='${root}/del';
				$.post(url,{},function(res){
					alert(res);
				});
			}
		});
		
	});
</script>
</head>
<body>
	<a href="${root}/add.jsp">添加索引</a><br/>
	<a href="${root}/search.jsp">搜索</a><br/>
	<a href="javascript:void(0)" id='del'>删除全部索引</a><br/>
	<p>${msg}</p>
</body>
</html>