package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.hadoop.fs.FileStatus;

import com.hdfs.hdfsDao;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private File file;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*˼·����ʹ��uploadjar������ʵ�ֱ��ص����������ϴ�
	 *    �ڶ���ʹ��hdfs�����������ļ�����hdfs��YunPan�±�
	 * 
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ļ������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//����servlet���ļ��ϴ���
		ServletFileUpload fileupload = new ServletFileUpload(factory);
		String parPath="D://fuwuqi//";
		try {
			//���������е��ļ���
			List<FileItem> list = fileupload.parseRequest(request);
			//�����õ���filetItem
			for (FileItem fi : list) {
				if(!fi.isFormField()) {
				//��ȡ�ϴ����ļ���Ϣ
				String fname=fi.getName();  //name��ֵ
				File fw = new File(parPath,fname);
				//��ȡ�ϴ��ļ�·��
				fi.write(fw);
				System.out.println("Tomcat�������ϴ���ϡ������ϴ�hdfs��ʼ");
				//����hdfsDao
				hdfsDao hdfsDao = new hdfsDao();
				//ָ���ϴ����ļ���
				String localName=parPath+fname;
				hdfsDao.upLoad(localName);
				//��ѯ
				FileStatus[] list1 = hdfsDao.ListFile();
				request.setAttribute("List", list1);
				request.getRequestDispatcher("home.jsp").forward(request, response);
				
			}
		  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
