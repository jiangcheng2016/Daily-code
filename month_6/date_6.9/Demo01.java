package com.ipsa.demo;

import java.util.Scanner;



public class Demo01 {

	public static void main(String[] args) {
		//定义工资变量
		int salary;			
		System.out.println("请输入第一个月工资:");
		//将键盘输入的值赋给salary
		Scanner scanner = new Scanner(System.in);
		salary = scanner.nextInt();
		
		//根据salary 判断到底买什么
		if (salary > 8000) {
			System.out.println("给父亲买一台液晶电视机");
		}else if (salary >4000 && salary < 8000) {
			System.out.println("给父亲买一台电动车");
		}else if (salary > 2000 && salary < 4000) {
			System.out.println("给父亲买一件新毛衣");
		}else {
			System.out.println("什么都不买");
		}
	}
	
	
}
