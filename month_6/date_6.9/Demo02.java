package com.ipsa.demo;

import java.util.Scanner;

public class Demo02 {
	public static void main(String[] args) {
		
		//�������� ����Ϊ10
		int array_test[] = new int[10];
		int sum = 0;	//�����ͱ���
		
		System.out.println("������10����:");
		Scanner scanner = new Scanner(System.in);
		//ѭ�������̵�����������array_test
		for(int i = 0;i < array_test.length;i ++) {
			
			array_test[i] = scanner.nextInt();
		}
		
		for (int i = 0; i < array_test.length; i++) {
			if (i % 2 == 0) {
				sum += array_test[i];
			}	
		}
		System.out.println("�±�Ϊż���ĺ͵�ֵΪ:" + sum);
	}
}
