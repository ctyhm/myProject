package com.java2019.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java2019.dao.UserDao;
import com.java2019.pojo.User;
import com.java2019.service.UserService;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
//ע��userdao
	@Autowired
	private UserDao userDao;
	//�û���¼
	@Override
	public User findUser(String username, String password) {
		User user=this.userDao.findUser(username, password);
		return user;
	}
	//�û�ע��
	@Override
	public void registerUser(User user) {
		// TODO �Զ����ɵķ������
		this.userDao.registerUser(user);
	}
	@Override
	public void updateUserState(String user_idcard, String user_state) {
		// TODO �Զ����ɵķ������
		this.userDao.updateUserState(user_idcard,user_state);
	}
	@Override
	public void updatePersonal(String user_name, String user_email, String user_password, String user_idcard) {
		// TODO �Զ����ɵķ������
		this.userDao.updatePersonal(user_name,user_email, user_password, user_idcard); 
			
	}
	@Override
	public User findUserName(String username) {
		User user=userDao.findUserName(username);
		return user;				
	}
	@Override
	public List<User> findtUserIdCardList() {
		// TODO �Զ����ɵķ������
		List<User> userList=userDao.findtUserIdCardList();
		return userList;
	}
	@Override
	public User findUserIdCard(String useridcard) {
		User user=userDao.findUserIdCard(useridcard);
		return user;
	}

}
