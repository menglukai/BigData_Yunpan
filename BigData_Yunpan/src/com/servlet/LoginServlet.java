package com.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hadoop.fs.FileStatus;

import com.entity.User;
import com.hdfs.hdfsDao;
import com.util.JDBCUtil;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符编码集
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取前台输入的帐号密码
		String uname = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		//创建jdbc对象
		JDBCUtil jd = new JDBCUtil();
		//查询
		ResultSet rs = jd.checkUser("select * from tb_login where name=? and pwd=?", uname, pwd);
		try {
			if(rs.next()) {
				//创建session
				HttpSession session = request.getSession();
				User user = new User(uname,pwd);
				//将对象设置到session
				session.setAttribute("User", user);
				//创建hdfs对象
				hdfsDao hdfs = new hdfsDao();
				//调用查询的方法
				FileStatus[] list = hdfs.ListFile();
				//保存到属性中
				request.setAttribute("List", list);
				//跳转到首页
				request.getRequestDispatcher("home.jsp").forward(request,response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
