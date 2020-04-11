<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<style type="text/css">
  .box{
       width: 500px;
       height: 300px;
       border: 1px black solid;
       background: gray;
       
     }
</style>
</head>
<body>
<center>
  <div class="box" align="center">
  <h2>注册</h2>
   <table>
   <form action="RegServlet" method="post">
     <tr>
      <td><input type="text" name="name" size="30" value="用户名"></td>
     </tr>
     <tr>
      <td><input type="password" name="pwd" size="30" value="密码"></td>
     </tr>
     <tr>
      <td><input type="password" name="pwd2" size="30" value="再次输入密码"></td>
     </tr>
     <tr>
      <td><input type="submit" value="注册"></td>
     </tr>
     <tr>
      <td><a href="login.jsp"><input type="button" value="已经有帐号"></a></td>
     </tr>
     </form>
    </table>
  </div>
</center>
</body>

</body>
</html>