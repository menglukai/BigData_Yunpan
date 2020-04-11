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
	/*思路：先使用uploadjar包的类实现本地到服务器的上传
	 *    第二步使用hdfs将服务器的文件传到hdfs的YunPan下边
	 * 
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建文件项工厂类
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//创建servlet的文件上传类
		ServletFileUpload fileupload = new ServletFileUpload(factory);
		String parPath="D://fuwuqi//";
		try {
			//解析出所有的文件项
			List<FileItem> list = fileupload.parseRequest(request);
			//遍历得到的filetItem
			for (FileItem fi : list) {
				if(!fi.isFormField()) {
				//获取上传的文件信息
				String fname=fi.getName();  //name的值
				File fw = new File(parPath,fname);
				//获取上传文件路径
				fi.write(fw);
				System.out.println("Tomcat服务器上传完毕。。。上传hdfs开始");
				//创建hdfsDao
				hdfsDao hdfsDao = new hdfsDao();
				//指定上传的文件名
				String localName=parPath+fname;
				hdfsDao.upLoad(localName);
				//查询
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
