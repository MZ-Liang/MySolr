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
		$("#btn").click(function(){
			var url='${root}/addDocument';
			var text=$("#text");
			$.post(url,{},function(res){
				alert(res);
			});
		});
		
		$("#home").click(function(){
			var url='${root}/home';
			$.post(url,{},function(res){
				text.text(res);
			})
		});
	})
</script>
</head>
<body>
	<button id="btn">addDocument</button>
	<button id="home">home</button>
	<p id="text"></p>
</body>
</html>