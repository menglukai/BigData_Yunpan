package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.JDBCUtil;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
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
		//添加
		int i = jd.update("insert into tb_login values(?,?)", uname, pwd);
		//判断
		if(i!=0) {
			response.sendRedirect("login.jsp");;
		}else {
			response.sendRedirect("reg.jsp");
		}
	}

}
