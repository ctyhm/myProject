package com.java2019.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.java2019.pojo.Auditing;
import com.java2019.pojo.Conloaner;
import com.java2019.pojo.Contract;
import com.java2019.pojo.Loanapply;
import com.java2019.pojo.PageBean;
import com.java2019.pojo.Personal;
import com.java2019.service.ConloanerService;
import com.java2019.service.LoanManageService;
import com.java2019.service.PersonalService;
import com.java2019.utils.FileUploadAndDownloandUtils;

@Controller
public class LoanManageController {
	// ����ע��
		@Autowired
		private LoanManageService loanManageService;
		@Autowired
		private PersonalService personalService;
		@Autowired
		private ConloanerService conloanService;
		//���Map�ۺϴ洢��Ҫ��ҳ��Ķ�������
		private Map<String,Object> result = new HashMap<String,Object>();
		
		@RequestMapping("/findAllLoanApply.action")
		@ResponseBody  // ����ת������Ϊjson
		public Map<String, Object> findAllLoanApply(Integer page,Integer rows,Auditing auditing){
			
			//��װ���ݵ�auditingBean 
			auditing.setPage(page);
			auditing.setRows(rows);			
			//��ѯ����
			List<Loanapply> list =loanManageService.findAllLoanApply(auditing);
			int total=loanManageService.findLoanApplyCount();			
			result.put("total", total);
			result.put("rows", list);
			return result;
		}
		/*
		 * ���س��ſ��Գɼ���
		 */
		@RequestMapping("/findGradeFile.action")
	public ResponseEntity<byte[]> findGradeFile(String pname,String pidcard,String ayear,HttpServletRequest request) throws IOException {
			String filename=pname+"_"+pidcard+"_"+ayear+"���ſ��Գɼ���.png";
			ResponseEntity<byte[]> f;
			FileUploadAndDownloandUtils fileUploadAndDownloandUtils=new FileUploadAndDownloandUtils();
			try {
			 f=fileUploadAndDownloandUtils.dowbLoad(filename, request);
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
				 f=fileUploadAndDownloandUtils.dowbLoad("���ſ��Գɼ���.png", request);
			}
			return 	f;			
		}
	/*
	 * ���������Ϣ������ȷ����Ϣ
	 */
	@RequestMapping("/updateAstate.action")
public Map<String, Object> updateAstate(Loanapply loanapply) {
		
		try {
			loanManageService.updateAstate(loanapply);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	/**
	 * �����ݿ��ȡ���������ݵ������ͬ
	 */
	
	@RequestMapping(value = "/findPersonalAndConloanerById.action")
	@ResponseBody
	public Loanapply findPersonalAndConloanerById(String id) {
		Loanapply loanapply = loanManageService.findPersonalAndConloanerById(id);
		/*String nowyear = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
		int beginyear = Integer.parseInt(nowyear);
		int endyear = beginyear + 1;
		loanapply.setAyear(beginyear + "��" + endyear + "ѧ��");*/
		
		return loanapply;

	}
	/**
	 * ������ͬ
	 */
		@RequestMapping(value = "/addContract.action")
		@ResponseBody
		public Map<String, Object> addContract(Contract contract,int id) {
			String coendyear= contract.getCoendyear()+"-09-20";
			contract.setCoendyear(coendyear);
			Loanapply loanapply=new Loanapply();
			contract.setId(id);
			try {
				loanapply.setId(id);
			loanapply.setAstate("��ͬ��ǩ");			
			loanManageService.updateAstate(loanapply);
				loanManageService.addContract(contract);
				result.put("success",true);									
			} catch (Exception e) {
				e.printStackTrace();
				result.put("success", false);
				result.put("msg", e.getMessage());
			}							
			return result;
		}
		/*
		 * ��ѯ���д����¼
		 */
		
		@RequestMapping("/findAllLoanRecord.action")
		@ResponseBody  // ����ת������Ϊjson
		public Map<String, Object> findAllLoanRecord(Integer page,Integer rows,PageBean pageBean){
			
			//��װ���ݵ�auditingBean 
			pageBean.setPage(page);
			pageBean.setRows(rows);			
			//��ѯ����
			List<Contract> list =loanManageService.findAllLoanRecord(pageBean);
			int total=loanManageService.findContractCount();			
			result.put("total", total);
			result.put("rows", list);
			return result;
		}
		
		/**
		 * ɾ������
		 */
		@RequestMapping("/deleteContract.action")
		@ResponseBody
		public Map<String,Object> deleteContract(Integer[] id){
			try {
				loanManageService.deleteContract(id);
				result.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				result.put("success", false);
				result.put("msg", e.getMessage());
			}
			return result;
		}
		@RequestMapping(value="/loanmanage/findPersonalByPIdCard.action")
		@ResponseBody
		public Personal findPersonalByPIdCard(String user_idcard) {
			Personal personal=this.personalService.findPersonalByPIdCard(user_idcard);	
			return personal;			
		}
		
	@RequestMapping(value="/loanmanage/findConloanerByuser_idcard.action")
	@ResponseBody
	public Conloaner findConloanerByuser_idcard(String user_idcard) {		
		Conloaner conloaner=this.conloanService.findConloanerByuser_idcard(user_idcard);		
		return conloaner;
		
	}
}
