package com.java2019.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java2019.pojo.Loanapply;
import com.java2019.service.LoanapplyService;
import com.java2019.utils.FileUploadAndDownloandUtils;


@Controller
public class LoanapplyController {
	// ����ע��
	@Autowired
	private LoanapplyService loanapplyService;
	
	/**
	 * �����ݿ��ȡ���������ݵ������
	 */
	//���Map�ۺϴ洢��Ҫ��ҳ��Ķ�������
	private Map<String,Object> result = new HashMap<String,Object>();
	@RequestMapping(value = "/findPersonalAndConloaner.action")
	@ResponseBody
	public Loanapply findPersonalAndConloaner(String user_idcard) {
		System.out.println(user_idcard);
		Loanapply loanapply = loanapplyService.findPersonalAndConloaner(user_idcard);
		String nowyear = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
		int beginyear = Integer.parseInt(nowyear);
		int endyear = beginyear + 1;
		loanapply.setAyear(beginyear + "��" + endyear + "ѧ��");
		return loanapply;

	}
/**
 * ������������
 */
	@RequestMapping(value = "/insertLoanapply.action")
	@ResponseBody
	public Map<String, Object> insertLoanapply(Loanapply loanapply,List<MultipartFile> agrade,
			HttpServletRequest request) {
		
		String pname=loanapply.getPname();
		String pidcard=loanapply.getPidcard();		
		String ayear=loanapply.getAyear();
		FileUploadAndDownloandUtils fileUploadAndDownloandUtils=new FileUploadAndDownloandUtils();		
		Loanapply loanapply1=loanapplyService.findPersonalByPidcardAndAyear(pidcard, ayear);		
		try {
			if(loanapply1==null) {
			fileUploadAndDownloandUtils.handlerFormUpload(pname, agrade, ayear,pidcard, request);
			loanapplyService.insertLoanapply(loanapply);
			result.put("success",true);
			}else {
				result.put("success",false);
				result.put("msg", "��������������ѧ��������ظ�����");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	/**
	 * ��ѯ�������ݣ���ҳ�淵��json��ʽ����
	 * easyui��datagrid�������Ҫչʾ���ṩjson���ݣ� [ {id:1,name:xxx},{id:1,name:xxx} ]
	 */
	@RequestMapping("/loanrecordfindAll.action")
	@ResponseBody  // ����ת������Ϊjson
	public List<Loanapply> loanrecordfindAll(String pidcard){
		//��ѯ����
		List<Loanapply> list =loanapplyService.findAll(pidcard);		
		return list;
	}
}
