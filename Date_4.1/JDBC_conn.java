package cn.jiangrzc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBC_conn {

	protected static PreparedStatement pstm;	//Ԥ����sql
	public static Connection conn;				//����Connection �����ʵ��
	public static Statement stmt;				//����Statement �����ʵ��
	public static ResultSet rs;				//����ResultSet �����ʵ��
	
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
				System.out.println("ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	
	private static Connection getConnection() {
		
		//��ȡ���ݿ�����			
		try {
			//1.����������
			Class.forName("com.mysql.jdbc.Driver");	//��ȡ��������
			
			//2.�������ݿ�
			conn =	DriverManager.getConnection("jdbc:mysql://47.94.150.238:3306/tsc?useUnicode=true&amp;characterEncoding=utf-8", "tsc", "root123");
			System.out.println(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
		
	}

	/*
	 *����:�ر����ݿ����� 
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
	 *ִ��sql ��� 
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
		System.out.println("����ɹ���");
		return rows;
	}
	/*
	 * ��sqlע�룬���β�ѯ
	 */
	public ResultSet executeQuery(String sql,Object[] param) throws Exception {
		try {
			conn = getConnection();
			if (param != null && param.length > 0) {
				pstm = conn.prepareStatement(sql);		//Ԥ����sql
				for(int i = 0;i < param.length;i ++) {	//forѭ���Ѳ�������Ĳ���������Ӧ��sql����� ?
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
	 * ���ܣ�ִ�в�ѯ��� 
	 */
	public ResultSet executeQuery(String sql) {
		try {//��׽�쳣
			conn = getConnection();		//����getConnection()��������Connection�����һ��ʵ��conn
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());	//����쳣��Ϣ
		}
		return rs;  	//���ؽ��������
	}
		

}
