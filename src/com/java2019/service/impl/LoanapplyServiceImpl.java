package com.java2019.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java2019.dao.LoanapplyDao;
import com.java2019.pojo.Loanapply;
import com.java2019.service.LoanapplyService;
@Service("loanapplyService")
public class LoanapplyServiceImpl implements LoanapplyService {
	//ע��LoanapplyDao
	@Autowired
	private LoanapplyDao loanapplyDao;
	
/*
 * ��ѯ�����Ը��˺͹�ͬ����˵�����ҳ��
 */
	@Override
	public Loanapply findPersonalAndConloaner(String user_idcard) {
		Loanapply loanapply=loanapplyDao.findPersonalAndConloaner(user_idcard);
		return loanapply;
	}
	@Override
	public void insertLoanapply(Loanapply loanapply) {
		
		loanapplyDao.insertLoanapply(loanapply);
		
	}
	@Override
	public Loanapply findPersonalByPidcardAndAyear(String pidcard, String ayear) {
		Loanapply loanapply=loanapplyDao.findPersonalByPidcardAndAyear(pidcard, ayear);
		return loanapply;
	}
	/*��ô����¼
	 */
	@Override
	public List<Loanapply> findAll(String pidcard) {
		List<Loanapply> list=loanapplyDao.findAll(pidcard);
		return list;
	}

}
