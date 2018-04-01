package cn.jiangrzc.service;

import cn.jiangrzc.dao.testDAO;
import cn.jiangrzc.domain.User;

public class testService {
	testDAO testDAO = new testDAO();
	
	public Integer login(String userNumber,String password) {
		if(userNumber == null || userNumber.equals("")) return -1;
		if(password == null || password.equals("")) return -2;
		
		User user = testDAO.findUserByuserNumber(userNumber);
		if(user.userNumber == null) return -3;
		if(!user.userPassword.equals(password)) return -4;
	
		return 0;
	}
}
