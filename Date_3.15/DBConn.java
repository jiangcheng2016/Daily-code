package cn.jiangrzc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConn {
	
	protected PreparedStatement pstm;	//Ԥ����sql
	public Connection conn;				//����Connection �����ʵ��
	public Statement stmt;				//����Statement �����ʵ��
	public ResultSet rs;				//����ResultSet �����ʵ��
	private static String dbClassName; 	//���屣�����ݿ������ı���
	private static String dbUrl;
	private static String dbUser;
	private static String dbPwd;
	
	public DBConn() {
			dbClassName = "com.mysql.jdbc.Driver";	//��ȡ���ݿ�����
			dbUrl = "jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf-8";	//��ȡURL
			dbUser = "root";	//��ȡ��¼�û�
			dbPwd = "root123";	//��ȡ����
	}
	
	

	//��ȡ���ݿ�����
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(dbClassName).newInstance();
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		if (conn == null) {
			System.err.println("���棺DBConnectionManager.getConnection() ��ȡ���ݿ�����ʧ��.\r\n\r\n��������:" + dbClassName + "\r\n����λ��:"
								+ dbUrl + "\r\n�û�/����" + dbUser + "/" + dbPwd);
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
		System.out.println("����ɹ���");
		
		return rows;
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
	/*
	 *ִ�в�ѯ��� 
	 */
	public ResultSet executeQuery(String sql,Object[] params) {
		try {
			conn = getConnection();	//����getConnection()��������Connection�����һ��ʵ��conn
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











