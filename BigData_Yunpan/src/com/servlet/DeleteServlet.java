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
		//���ñ����ַ���
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡfilePath
		String delPath = request.getParameter("filePath");
		//ͨ��hdfs������ִ��ɾ������
		hdfsDao hdfs = new hdfsDao();
		try {
			hdfs.deleteFile(delPath);
			//�ٴβ�ѯ
		    FileStatus[] list=hdfs.ListFile();
			//��ѯ���������õ�������
		    request.setAttribute("List", list);
		    
		    //����ת������ҳ
		    request.getRequestDispatcher("home.jsp").forward(request, response);
			
		} catch (IllegalArgumentException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
