package cn.jiangrzc.service;

import cn.jiangrzc.dao.AdminDAO;

public class AdminService {

	/*
	 * 用户登录接口，登录成功返回0
	 * -1:参数错误，请输入用户名或密码
	 */
	public Integer login(String adminName,String password) {
		if(adminName == null || adminName.equals("")) return -1;
		if(password == null || password.equals("")) return -1;
		
		AdminDAO dao = AdminDAO.getInstance();
		String admin = dao.findAdminByAdminName(adminName);
		if (admin == null) return -2;
		
		return 0;
	}
}
