package com.java2019.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
public class FileUploadAndDownloandUtils {
public String handlerFormUpload(String name,List<MultipartFile> uploadfile,
		String ayear,String idcard,HttpServletRequest request) {
		if(!uploadfile.isEmpty() && uploadfile.size()>0) {
		for (MultipartFile file : uploadfile) {
			//��ȡ�ϴ��ļ���ԭʼ����
			String originalFilename=file.getOriginalFilename();
			//�����ϴ��ļ��ı����ַĿ¼	String dirpath=request.getServletContext().getRealPath("/gradeFile/");
			String dirpath="C:/Users/23132/Desktop/���ſ��Գɼ���/";	
			File filepath=new File(dirpath);
			if(!filepath.exists()) {
				filepath.mkdirs();
			}
			//�������ϴ����ļ�����
			String newFilename = name+"_"+idcard+"_"+ayear+"���ſ��Գɼ���.png";
			//ʹ��multipartfile�ӿڵķ�������ļ��ϴ���ָ��λ��
			try {
				file.transferTo(new File(dirpath+newFilename));
				//��ӡ·��
				//System.out.println(dirpath+newFilename);
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				return "error";
			}
		}
		return "success";
	}else {
		return "success";
	}
	
}
	/*
	 * �ļ�����
	 */
	public ResponseEntity<byte[]> dowbLoad(String filename,HttpServletRequest request) throws IOException {
		//ָ�����ص��ļ�����·��request.getContextPath()
		String path="C:/Users/23132/Desktop/���ſ��Գɼ���/";		
		//�����ļ�����
		File file=new File(path+File.separator+filename);
		//���ļ������롣��ֹ��������
		filename=this.getFilename(request,filename);
		//������Ӧͷ
		HttpHeaders headers=new HttpHeaders();
	    // ֪ͨ����������صķ�ʽ���ļ�
	    headers.setContentDispositionFormData("attachment", filename);
	    // ������������ʽ���ط����ļ�����
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    // ʹ��Sring MVC��ܵ�ResponseEntity�����װ������������
   return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
	                                                       headers,HttpStatus.OK);
	
	}
	/**
	 * ����������Ĳ�ͬ���б������ã����ر������ļ���
	 * @throws UnsupportedEncodingException 
	 */
	private String getFilename(HttpServletRequest request, String filename) throws UnsupportedEncodingException {
		  // IE��ͬ�汾User-Agent�г��ֵĹؼ���
	    String[] IEBrowserKeyWords = {"MSIE", "Trident", "Edge"};  
	    // ��ȡ����ͷ������Ϣ
	    String userAgent = request.getHeader("User-Agent");  
	    for (String keyWord : IEBrowserKeyWords) { 
	         if (userAgent.contains(keyWord)) { 
	              //IE�ں��������ͳһΪUTF-8������ʾ
	              return URLEncoder.encode(filename, "UTF-8");
	         }
	    }  
	    //��������������ͳһΪISO-8859-1������ʾ
	    return new String(filename.getBytes("UTF-8"), "ISO-8859-1");  
	}  
	
}
