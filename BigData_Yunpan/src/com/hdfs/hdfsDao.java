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
	//����Ŀ¼�ķ���
	public void mkdir() throws IllegalArgumentException, IOException, InterruptedException, URISyntaxException {
		Configuration conf = new Configuration();
		//����ϵͳ�ļ�����
		FileSystem fs = FileSystem.get(new URI(hdfsPATH), conf, "lisi");
		//����Ŀ¼
		fs.mkdirs(new Path("/YunPan"));
		System.out.println("Ŀ¼�����ɹ�");
		}
	//�����ļ��ķ���
	public void createfile() throws IllegalArgumentException, IOException, InterruptedException, URISyntaxException {
		Configuration conf = new Configuration();
		//����ϵͳ�ļ�����
		FileSystem fs = FileSystem.get(new URI(hdfsPATH), conf, "lisi");
		FSDataOutputStream out = fs.create(new Path("/YunPan/aa.txt"));
		out.write("okok".getBytes());
		out.flush();
		out.close();
		}
	//�ӱ����ϴ��ļ��ķ���
	public void upLoad(String local) throws IOException, InterruptedException, URISyntaxException {
		Configuration conf = new Configuration();
		//����ϵͳ�ļ�����
		FileSystem fs = FileSystem.get(new URI(hdfsPATH), conf, "lisi");
		//���������ش��·��
		//�����ϴ��ķ���
		fs.copyFromLocalFile(new Path(local),new Path(hdfsPATH));
		//����
		fs.close();
	}
	@Test
	//��hdfs�����ص�����defangf
	public void downLoad(String remote,String local) throws IOException, InterruptedException {
		//���������ļ�
		Configuration conf = new Configuration();
		//����ϵͳ�ļ�����
		FileSystem fs = FileSystem.get(URI.create(hdfsPATH), conf, "lisi");
		//���������ļ�����·��
		//String local="d:/temp/data/"
		//�������صķ���
		fs.copyToLocalFile(false, new Path(remote), new Path(local),true);
		//����
		fs.close();
	}
	//hdfs��ѯ�ķ�����������
	public FileStatus[] ListFile() throws FileNotFoundException, IllegalArgumentException, IOException, InterruptedException {
		//���������ļ�
		Configuration conf = new Configuration();
		//����ϵͳ�ļ�����
		FileSystem fs = FileSystem.get(URI.create(hdfsPATH), conf, "lisi");
		FileStatus[] list = fs.listStatus(new Path(hdfsPATH));
		//����
		for (int i=0;i<list.length;i++) {
			//printy ���и�ʽ���Ĵ�ӡ�����   %s �ַ�����ʾ     %d ʮ���Ƶ�����       %f ʮ���Ƹ�������
			System.out.printf("name:%s,fileType:%s,Size:%d",list[i].getPath().getName(),(list[i].isDir()?"Ŀ¼":"�ļ�"),(list[i].getLen()%1024));
		}
		return list;
	}
	//ɾ��
	public void deleteFile(String Path) throws IllegalArgumentException, IOException, InterruptedException {
		//���������ļ�
		Configuration conf = new Configuration();
		//����ϵͳ�ļ�����
		FileSystem fs = FileSystem.get(URI.create(hdfsPATH), conf, "lisi");
		fs.deleteOnExit(new Path(Path));
		fs.close();
	}
}
