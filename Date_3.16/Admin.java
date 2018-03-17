package cn.jiangrzc.domain;

public class Admin {
	public String adminNumber; 		//管理员编号
	public String adminName;		//管理员姓名
	public String password;			//密码
	public String adminNickname;	//管理员昵称
	
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
