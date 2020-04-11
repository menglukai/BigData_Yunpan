<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="UploadServlet" method="post" enctype="multipart/form-data">
用户名：<input type="text" name="username"><br>
密&nbsp;&nbsp;码：<input type="password" name="pwd"><br>
头&nbsp;&nbsp;像：<input type="file" name="photo"><br>
    <input type="submit" value="提交"><br>
</form>
</body>
</html>