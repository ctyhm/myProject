package com.java2019.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java2019.dao.LoanManageDao;
import com.java2019.pojo.Auditing;
import com.java2019.pojo.Contract;
import com.java2019.pojo.Loanapply;
import com.java2019.pojo.PageBean;
import com.java2019.service.LoanManageService;
@Service("loanManageService")
public class LoanManageServiceImpl implements LoanManageService {
	//ע��LoanapplyDao
		@Autowired
		private LoanManageDao  loanManageDao;
	@Override
	public List<Loanapply> findAllLoanApply(Auditing auditing) {
		List<Loanapply> list=loanManageDao.findAllLoanApply(auditing);
		return list;
	}
	@Override
	public int findLoanApplyCount() {
		int total=loanManageDao.findLoanApplyCount();
		return total;
	}
	@Override
	public void updateAstate(Loanapply loanapply) {
		// TODO �Զ����ɵķ������
		loanManageDao.updateAstate(loanapply);
	}
	@Override
	public Loanapply findPersonalAndConloanerById(String id) {
		// TODO �Զ����ɵķ������
		Loanapply loanapply=loanManageDao.findPersonalAndConloanerById(id);
		return loanapply;
	}
	/*	 
	 * ��������ͬ����	 
	 */
	@Override
	public void addContract(Contract contract) {
		loanManageDao.addContract(contract);
		
	}
	/*
	 * ��ѯ���д����ͬ��¼
	 */
	@Override
	public List<Contract> findAllLoanRecord(PageBean pageBean) {
		// TODO �Զ����ɵķ������
		List<Contract> list=loanManageDao.findAllLoanRecord(pageBean);
		return list;
	}
	@Override
	public int findContractCount() {
		// TODO �Զ����ɵķ������
		int total=loanManageDao.findContractCount();
		return total;
	}
	@Override
	public void deleteContract(Integer[] id) {
		// TODO �Զ����ɵķ������
		loanManageDao.deleteContract(id);
	}

}
