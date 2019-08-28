package com.java2019.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java2019.pojo.User;
import com.java2019.service.UserService;
import com.java2019.utils.VerifyCode;

/*
 * �û���������
 */
@Controller
public class UserController {
//����ע��
	@Autowired
	private UserService userService;
	//���Map�ۺϴ洢��Ҫ��ҳ��Ķ�������
		private Map<String,String> result = new HashMap<String,String>();
	/*
	 * �û�ע����֤
	 */	
	//�û�����֤
	@RequestMapping(value="/checkUserName.action",method=RequestMethod.POST)
	public void checkUserName(String username,HttpServletResponse response) throws IOException {
		System.out.println(username);
		User user=userService.findUserName(username);
		response.setContentType("text/html);charset=utf-8");
		if(user!=null) {
			response.getWriter().println("1");
		}else {
			response.getWriter().println("0");
		}
		}
	//���֤����֤
		@RequestMapping(value="/checkUserIdCard.action",method=RequestMethod.POST)
		public void checkUserIdCard(String useridcard,HttpServletResponse response) throws IOException {
			System.out.println(useridcard);
			User user=userService.findUserIdCard(useridcard);
			response.setContentType("text/html);charset=utf-8");
			if(user!=null) {
				response.getWriter().println("1");
			}else {
				response.getWriter().println("0");
			}
			}
	
	/*
	 * �û�ע��	
	 */
		@RequestMapping(value="/register.action",method=RequestMethod.POST)
		public String register(User user,Model model) {
			userService.registerUser(user);
			model.addAttribute("msg", "ע��ɹ�");
	         // ���ص���¼ҳ��
			return "login";
		}	
	/*
	 * �û���¼
	 */
	@RequestMapping(value="/login.action")
	public String login(String username,String password,String userCode ,Model model,HttpServletRequest request,HttpSession session) {
		//ͨ���˺ź������ѯ�û�
		User user=userService.findUser(username, password);
		String serverCode=(String) session.getAttribute("checkcode_session");
		if(user== null){
			model.addAttribute("msg", "�˺Ż�����������������룡");
	         // ���ص���¼ҳ��
			return "login";
		
		}if(!serverCode.equalsIgnoreCase(userCode)) {
			model.addAttribute("msg", "��֤�����");
			return "login";
		}else {					
			// ���û�������ӵ�Session
			session.setAttribute("usersession", user);
			// ��ת����ҳ��
			if(user.getUser_state().equals("ѧ��")) {
			return "main";
			}
			if (user.getUser_state().equals("����Ա")){
				return "loanmanage";
			}
			if (user.getUser_state().equals("����Ա")){
				return "bankmanage";
			}else {
				return "main";
			}
		}
	}
	
	/**
	 * �˳���¼
	 */
	@RequestMapping(value = "/logout.action")
	public String logout(HttpSession session) {
	    // ���Session
	    session.invalidate();
	    // �ض��򵽵�¼ҳ�����ת����
	    return "redirect:login.action";
	}
	/*
	 * ��Ӧajax������û��������֤�Ż���
	 */
	@ResponseBody
	@RequestMapping("/findUserIdCardList.action")
	public List<User> findtUserIdCardList() {
		List<User> userList=userService.findtUserIdCardList();							
		int user_id=1;
		for (User user : userList) {			
			user.setUser_id(user_id);
			user_id+=1;
		}
		
		return  userList;	  
	}
	/*
	 * ����Ȩ��
	 */
	@RequestMapping(value = "/authoritySet.action",method=RequestMethod.POST)
	public String authority(String user_idcard,String user_state) {		
		userService.updateUserState(user_idcard,user_state);
	    return "success";
	}
	/*
	 * �޸�����
	 */
	@RequestMapping(value = "/systemSet.action",method=RequestMethod.POST)
	public String systemSet(HttpSession session,String user_name,String user_email,String user_password,String user_idcard) {		
		userService.updatePersonal(user_name,user_email,user_password,user_idcard);
		// ���Session
	   
	    return "success";
	}
	/**
	 * ��֤�������
	 * @throws IOException 
	 */
	@RequestMapping("imageCode.action")
	public void imageCode(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		response.setContentType("image/jpeg;utf-8");
		VerifyCode code = new VerifyCode();
		BufferedImage image = code.createImage();
		String str=code.getText();
		// ����֤�����ݱ���session
		session.setAttribute("checkcode_session", str);			
		ImageIO.write(image,"jpg",response.getOutputStream());
		
		}
		
}
