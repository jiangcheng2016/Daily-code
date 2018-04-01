package cn.jiangrzc.dao;

import java.sql.ResultSet;

import cn.jiangrzc.domain.User;

public class testDAO extends JDBC_conn {

	public User findUserByuserNumber(String userNumber) {
		String sql = "select * from Users where userNumber=?";
		Object[] param = {userNumber};
		User user = new User();
		try {
			//rs = executeQuery(sql);
			ResultSet rs = executeQuery(sql,param);
			//System.out.println(rs.getString("userName"));
			//System.out.println(rs.getString("userNumber"));
			while (rs.next()) {
				String userNum = rs.getString("userNumber");			
				String password = rs.getString("userPassword");
				user.userNumber = userNum;
				user.userPassword = password;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return user;
		
	}
}
