package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.fs.FileStatus;

import com.hdfs.hdfsDao;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码字符集
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取filePath
		String delPath = request.getParameter("filePath");
		//通过hdfs工具类执行删除方法
		hdfsDao hdfs = new hdfsDao();
		try {
			hdfs.deleteFile(delPath);
			//再次查询
		    FileStatus[] list=hdfs.ListFile();
			//查询到数据设置到属性中
		    request.setAttribute("List", list);
		    
		    //请求转发到首页
		    request.getRequestDispatcher("home.jsp").forward(request, response);
			
		} catch (IllegalArgumentException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
