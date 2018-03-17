package cn.jiangrzc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;


import cn.jiangrzc.domain.Admin;

public class AdminDAO extends DBConn{

	private static AdminDAO dao = new AdminDAO();
	public static AdminDAO getInstance() {
		return dao;
	}
	
	
	public boolean adminLogin(String adminName,String password) {
		String sql = "SELECT * FROM 'Admins' WHERE 'adminName'=? AND 'password'=?";
		Object[] params = {adminName,password};
		ResultSet rs = super.executeQuery(sql, params);
		try {
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();
		return false;
	}
	
	/**
	 * 查询所有管理员信息
	 */
	public List<Admin> getAdminList(){
		String sql = "select * from Admins";
		ResultSet rs = super.executeQuery(sql);
		
		List<Admin> list = new ArrayList<Admin>();
		
		try {
			while(rs.next()) {
				String adminNum = rs.getString("adminNumber");
				String adminName = rs.getString("adminName");
				String password = rs.getString("password");
				String adminNick = rs.getString("adminNickname");

				Admin admin = new Admin(adminNum, adminName, password, adminNick);
				list.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 根据用户名从数据库中取出用户信息
	 */
	public String findAdminByAdminName(String adminName) {
		String sql = "select *(adminName,password) from Admins where adminName=?";
		ResultSet rs = super.executeQuery(sql);
		List<Admin> list = new ArrayList<Admin>();
		
		try {
			while(rs.next()) {
				
				String adminNum = rs.getString("adminNumber");
				String adminName1 = rs.getString("adminName");
				String password = rs.getString("password");
				String adminNick = rs.getString("adminNickname");	
				Admin admin = new Admin(adminNum, adminName1, password, adminNick);
				list.add(admin);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list.get(0).adminName;
		
	}
	
	
	
	
	
	
	
	
	
}
