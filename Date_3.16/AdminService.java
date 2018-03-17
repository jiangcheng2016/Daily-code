package cn.jiangrzc.service;

import cn.jiangrzc.dao.AdminDAO;

public class AdminService {

	/*
	 * �û���¼�ӿڣ���¼�ɹ�����0
	 * -1:���������������û���������
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
