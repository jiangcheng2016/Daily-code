package cn.jiangrzc.domain;

public class Admin {
	public String adminNumber; 		//����Ա���
	public String adminName;		//����Ա����
	public String password;			//����
	public String adminNickname;	//����Ա�ǳ�
	
	public Admin(String adminNumber,String adminName,String password,String adminNickname) {
		super();
		this.adminNumber = adminNumber;
		this.adminName = adminName;
		this.password = password;
		this.adminNickname = adminNickname;
	}
	
	public Admin() {
		super();
	}
	
}
