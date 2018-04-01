package cn.jiangrzc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBC_conn {

	protected static PreparedStatement pstm;	//预编译sql
	public static Connection conn;				//声明Connection 对象的实例
	public static Statement stmt;				//声明Statement 对象的实例
	public static ResultSet rs;				//声明ResultSet 对象的实例
	
	public static void main(String[] args) {
		/*System.out.println("start");
		String sql = "select * from Users where userNumber = ?";
		Object[] param = {"201590514134"};
		try {
			conn = getConnection();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, param[0].toString());
			rs = pstm.executeQuery();
			if(rs.next()) {
				System.out.println("success");
				System.out.println(rs.getString("userName"));
			}else {
				System.out.println("失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	
	private static Connection getConnection() {
		
		//获取数据库连接			
		try {
			//1.加载驱动包
			Class.forName("com.mysql.jdbc.Driver");	//获取数据驱动
			
			//2.连接数据库
			conn =	DriverManager.getConnection("jdbc:mysql://47.94.150.238:3306/tsc?useUnicode=true&amp;characterEncoding=utf-8", "tsc", "root123");
			System.out.println(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
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
					pstm.setString(i+1,param[i].toString());
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
	 * 防sql注入，带参查询
	 */
	public ResultSet executeQuery(String sql,Object[] param) throws Exception {
		try {
			conn = getConnection();
			if (param != null && param.length > 0) {
				pstm = conn.prepareStatement(sql);		//预编译sql
				for(int i = 0;i < param.length;i ++) {	//for循环把参数数组的参数赋给对应的sql里面的 ?
					pstm.setString(i+1, param[i].toString());
				}
				rs = pstm.executeQuery();
 			}else {
 				stmt = conn.createStatement();
 				rs = stmt.executeQuery(sql); 
 			}
		} finally {
			
			//this.closeAll();
		} 		
		return rs;
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
		

}
