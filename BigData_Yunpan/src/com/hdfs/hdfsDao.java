package com.hdfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

public class hdfsDao {
	String hdfsPATH="hdfs://192.168.56.2:8020/YunPan";
	//创建目录的方法
	public void mkdir() throws IllegalArgumentException, IOException, InterruptedException, URISyntaxException {
		Configuration conf = new Configuration();
		//创建系统文件对象
		FileSystem fs = FileSystem.get(new URI(hdfsPATH), conf, "lisi");
		//创建目录
		fs.mkdirs(new Path("/YunPan"));
		System.out.println("目录创建成功");
		}
	//创建文件的方法
	public void createfile() throws IllegalArgumentException, IOException, InterruptedException, URISyntaxException {
		Configuration conf = new Configuration();
		//创建系统文件对象
		FileSystem fs = FileSystem.get(new URI(hdfsPATH), conf, "lisi");
		FSDataOutputStream out = fs.create(new Path("/YunPan/aa.txt"));
		out.write("okok".getBytes());
		out.flush();
		out.close();
		}
	//从本地上传文件的方法
	public void upLoad(String local) throws IOException, InterruptedException, URISyntaxException {
		Configuration conf = new Configuration();
		//创建系统文件对象
		FileSystem fs = FileSystem.get(new URI(hdfsPATH), conf, "lisi");
		//声明出本地存放路径
		//调用上传的方法
		fs.copyFromLocalFile(new Path(local),new Path(hdfsPATH));
		//关流
		fs.close();
	}
	@Test
	//从hdfs上下载到本地defangf
	public void downLoad(String remote,String local) throws IOException, InterruptedException {
		//加载配置文件
		Configuration conf = new Configuration();
		//创建系统文件对象
		FileSystem fs = FileSystem.get(URI.create(hdfsPATH), conf, "lisi");
		//创建本地文件保存路径
		//String local="d:/temp/data/"
		//调用下载的方法
		fs.copyToLocalFile(false, new Path(remote), new Path(local),true);
		//关流
		fs.close();
	}
	//hdfs查询的方法（遍历）
	public FileStatus[] ListFile() throws FileNotFoundException, IllegalArgumentException, IOException, InterruptedException {
		//加载配置文件
		Configuration conf = new Configuration();
		//创建系统文件对象
		FileSystem fs = FileSystem.get(URI.create(hdfsPATH), conf, "lisi");
		FileStatus[] list = fs.listStatus(new Path(hdfsPATH));
		//遍历
		for (int i=0;i<list.length;i++) {
			//printy 带有格式化的打印输出，   %s 字符串显示     %d 十进制的整数       %f 十进制浮点类型
			System.out.printf("name:%s,fileType:%s,Size:%d",list[i].getPath().getName(),(list[i].isDir()?"目录":"文件"),(list[i].getLen()%1024));
		}
		return list;
	}
	//删除
	public void deleteFile(String Path) throws IllegalArgumentException, IOException, InterruptedException {
		//加载配置文件
		Configuration conf = new Configuration();
		//创建系统文件对象
		FileSystem fs = FileSystem.get(URI.create(hdfsPATH), conf, "lisi");
		fs.deleteOnExit(new Path(Path));
		fs.close();
	}
}
