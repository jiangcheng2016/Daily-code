package com.ipsa.demo;

import java.util.Scanner;



public class Demo01 {

	public static void main(String[] args) {
		//���幤�ʱ���
		int salary;			
		System.out.println("�������һ���¹���:");
		//�����������ֵ����salary
		Scanner scanner = new Scanner(System.in);
		salary = scanner.nextInt();
		
		//����salary �жϵ�����ʲô
		if (salary > 8000) {
			System.out.println("��������һ̨Һ�����ӻ�");
		}else if (salary >4000 && salary < 8000) {
			System.out.println("��������һ̨�綯��");
		}else if (salary > 2000 && salary < 4000) {
			System.out.println("��������һ����ë��");
		}else {
			System.out.println("ʲô������");
		}
	}
	
	
}
