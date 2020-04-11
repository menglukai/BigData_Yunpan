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
		//�����ַ����뼯
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡǰ̨������ʺ�����
		String uname = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		//����jdbc����
		JDBCUtil jd = new JDBCUtil();
		//��ѯ
		ResultSet rs = jd.checkUser("select * from tb_login where name=? and pwd=?", uname, pwd);
		try {
			if(rs.next()) {
				//����session
				HttpSession session = request.getSession();
				User user = new User(uname,pwd);
				//���������õ�session
				session.setAttribute("User", user);
				//����hdfs����
				hdfsDao hdfs = new hdfsDao();
				//���ò�ѯ�ķ���
				FileStatus[] list = hdfs.ListFile();
				//���浽������
				request.setAttribute("List", list);
				//��ת����ҳ
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
