<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
  .box{
       width: 500px;
       height: 300px;
       border: 1px black solid;
       background: gray;
       margin: auto;
       background-color: rgba(94,94,94,0.8);
     }
   body{
	 background: url("img/timg (4).jpg");
	}
</style>
</head>
<body>
<center>
  <div class="box" align="center">
  <h2>登录</h2>
   <table>
   <form action="LoginServlet" method="post">
     <tr>
      <td><input type="text" name="name" size="30" value="用户名"></td>
     </tr>
     <tr>
      <td><input type="password" name="pwd" size="30" value="密码"></td>
     </tr>
     <tr>
      <td><input type="submit" value="登录"></td>
     </tr>
     <tr>
      <td><input type="button" value="还有没有帐号"></td>
     </tr>
     </form>
    </table>
  </div>
</center>
</body>
</html>