package cn.jiangrzc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConn {
	
	protected PreparedStatement pstm;	//预编译sql
	public Connection conn;				//声明Connection 对象的实例
	public Statement stmt;				//声明Statement 对象的实例
	public ResultSet rs;				//声明ResultSet 对象的实例
	private static String dbClassName; 	//定义保存数据库驱动的变量
	private static String dbUrl;
	private static String dbUser;
	private static String dbPwd;
	
	public DBConn() {
			dbClassName = "com.mysql.jdbc.Driver";	//获取数据库驱动
			dbUrl = "jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf-8";	//获取URL
			dbUser = "root";	//获取登录用户
			dbPwd = "root123";	//获取密码
	}
	
	

	//获取数据库连接
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(dbClassName).newInstance();
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		if (conn == null) {
			System.err.println("警告：DBConnectionManager.getConnection() 获取数据库连接失败.\r\n\r\n链接类型:" + dbClassName + "\r\n链接位置:"
								+ dbUrl + "\r\n用户/密码" + dbUser + "/" + dbPwd);
		}
		
		return conn;
	}
	
	/*
	 *功能:关闭数据库连接 
	 */
	protected void closeAll() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			if (conn != null && conn.isClosed() == false) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 *执行sql 语句 
	 **/
	protected int executeSQL(String sql,Object[] param) throws Exception{
		int rows = 0;
		try {
			conn = getConnection();
			if (param != null && param.length > 0) {
				pstm = conn.prepareStatement(sql);
				for(int i = 0;i < param.length; i ++) {
					pstm.setString(i+1,param.toString());
				}
				rows = pstm.executeUpdate();
			} else {
				stmt = conn.createStatement();
				rows = stmt.executeUpdate(sql);
			}
		} finally {
			this.closeAll();
		}
		System.out.println("处理成功！");
		
		return rows;
	}
	
	/*
	 * 功能：执行查询语句 
	 */
	public ResultSet executeQuery(String sql) {
		try {//捕捉异常
			conn = getConnection();		//调用getConnection()方法构造Connection对象的一个实例conn
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());	//输出异常信息
		}
		
		return rs;  	//返回结果集对象
	}
	/*
	 *执行查询语句 
	 */
	public ResultSet executeQuery(String sql,Object[] params) {
		try {
			conn = getConnection();	//调用getConnection()方法构造Connection对象的一个实例conn
			if (params != null && params.length > 0) {
				pstm = conn.prepareStatement(sql);
				for(int i = 0;i < params.length; i ++) {
					pstm.setString(i + 1, params[i].toString());
				}
				rs = pstm.executeQuery();
			} else {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		
		return rs;
	}
}











