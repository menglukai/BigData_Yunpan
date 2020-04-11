<%@page import="com.entity.User"%>
<%@page import="org.apache.hadoop.fs.FileStatus"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<title>无标题文档</title>
<style type="text/css">
  .box{
     width: 900px;
     height: 700px;
     border: 1px solid black;
     margin:auto;
	 background:#CCC;
  }
  .box11{
	  width: 900px;
      height: 70px;
	  }
  .box1{
     width:500px;
     height: 70px;
	 float: left;
  }
  .box2{
     width:200px;
     height: 70px;
     float: right;
     
  }
  .box3{
	  width:850px;
	  height:600px;
	  background:#FFF;
	  }
  .aa td{
	  border-bottom: 1px solid #000000;
	  padding-left:50px;
	  padding-right:50px;
	  }
	  a{
	  text-decoration: none;
	  }
</style>
</head>
<body>
<div class="box">
  <div class="box11">
   <div class="box1">
   <h3 align="center">网盘</h3>
   </div>
   <div class="box2">
    <table>
     <tr>
      <%User user=(User)session.getAttribute("User"); %>
       <td><%=user.getName() %></td>
       <td><a href="login.jsp"><input type="button" name="tuichu" value="退出"></a></td>
      </tr>
    </table>
   </div>
  </div>
   <form action="UploadServlet" method="post" enctype="multipart/form-data">
     <input type="submit" value="上传文件">
     <input type="file" name="file" value="浏览">
   </form>
 <div class="box3">
   <table class="aa" align="center">
     <tr>
       <th>文件名</th>
       <th>属性</th>
       <th>大小</th>
       <th>操作</th>
       <th>操作</th>
     </tr>
     <%  FileStatus[] list=(FileStatus[])request.getAttribute("List");
          if(list!=null){
        	  for(int i=0;i<list.length;i++){
     %>
     <tr>
     <%
       if(list[i].isDir()){	   
     %>
      <td><a href="UploadServlet?filePath=<%=list[i].getPath().toString() %>"><%=list[i].getPath().getName() %></a></td>
     <%
       }else{ 
     %>
       <td><%=list[i].getPath().getName() %></td>
     <%
       }
     %>
       
       <td><%=list[i].isDir()?"目录":"文件"%></td>
       <td><%=list[i].getLen()%1024 %></td>
       <td><a href="DeleteServlet?filePath=<%=list[i].getPath().toString()%>">删除</a></td>
       <td><a href="DownServlet?filePath=<%=list[i].getPath().toString()%>">下载</a></td>
     </tr>
     <%
        	  }
          }
     %>
   </table>
 </div>
</div>
</body>
</html>