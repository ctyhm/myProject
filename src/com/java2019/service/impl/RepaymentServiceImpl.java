package com.java2019.service.impl;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java2019.dao.LoanManageDao;
import com.java2019.dao.RepaymentDao;
import com.java2019.pojo.Contract;
import com.java2019.pojo.Loanapply;
import com.java2019.pojo.Overdue;
import com.java2019.pojo.PageBean;
import com.java2019.pojo.Repayment;
import com.java2019.service.RepaymentService;
@Service("repaymentService")
public class RepaymentServiceImpl implements RepaymentService {
      @Autowired
     private RepaymentDao repaymentDao;
     //ע��LoanapplyDao
		@Autowired
		private LoanManageDao  loanManageDao;
	@Override
	public List<Repayment> findRepaymentInformation(String pidcard) {
		List<Repayment> list=repaymentDao.findRepaymentInformation(pidcard);
		return list;
	}
	@Override
	/*
	 * ��ӻ�������
	 *
	 */
	public void addRepayment(Repayment repayment) {
		// TODO �Զ����ɵķ������
		repaymentDao.addRepayment(repayment);
	}
	@Override
	public List<Repayment> findAllRepaymentInformation(PageBean pageBean) {
		List<Repayment> list=repaymentDao.findAllRepaymentInformation(pageBean);
		return list;
	}
	@Override
	public int findRepaymentInformationCount() {
		int total=repaymentDao.findRepaymentInformationCount();
		return total;
	}
	/*
	 * ��ӿۿ�����
	 */
	@Override
	public void updateRactualdate(Repayment repayment) {
		// TODO �Զ����ɵķ������
		Loanapply loanapply=new Loanapply();//ʵ����loanapply��������������״̬
		int loanapplyId=Integer.parseInt(repayment.getConumber().substring(repayment.getConumber().length()-6,repayment.getConumber().length()));	
		loanapply.setId(loanapplyId);
		DecimalFormat df=new DecimalFormat("0.00");
		float total=Float.parseFloat(repayment.getTotal());//����Ӧ�û����
		float ramount=Float.parseFloat(repayment.getRamount()) ;//���λ�����			
		String nowarrears=df.format(total-ramount) +"";
		if((total-ramount)==0) {			
			loanapply.setAstate("�ѻ���");
			loanManageDao.updateAstate(loanapply);			
		}else {
			loanapply.setAstate("���ֻ���");
			loanManageDao.updateAstate(loanapply);			
		}
		repayment.setNowarrears(nowarrears);		
		repaymentDao.updateRactualdate(repayment);
	}
	@Override
	public int findPersonalRepaymentRecordCount(PageBean pageBean) {
		int total=repaymentDao.findPersonalRepaymentRecordCount(pageBean);
		return total;
	}
	@Override
	public List<Repayment> findRepaymentRecord(PageBean pageBean) {	
		List<Repayment> list=repaymentDao.findRepaymentRecord(pageBean);
		return list;
	}
	/*
	 * ��ѯ����ĳһ��Ĵ��Ƿ����
	 */
	@Override
	public Repayment findMinNowarrearsByYearAndPidcard(String ayear, String conumber) {
		Repayment minRepayment=repaymentDao.findMinNowarrearsByYearAndPidcard(ayear,conumber);
		return minRepayment;
	}
	@Override
	public List<Overdue> findAllOverdueInformation(PageBean pageBean) {
		// TODO �Զ����ɵķ������
		List<Overdue> list=repaymentDao.findAllOverdueInformation(pageBean);
		return list;
	}
	

}
