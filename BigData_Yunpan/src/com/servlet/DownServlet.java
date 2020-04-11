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
 * Servlet implementation class DownServlet
 */
@WebServlet("/DownServlet")
public class DownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取超链接中的路径信息
		String remote = request.getParameter(new String("filePath".getBytes("ISO8859-1"),"UTF-8"));
		//真正删除通过hdfs删除
		hdfsDao hdfs = new hdfsDao();
		//创建本地路径
		String local="D://down//";
		try {
			hdfs.downLoad(remote, local);
			//查询
			FileStatus[] list = hdfs.ListFile();
			request.setAttribute("List", list);
			//请求
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
