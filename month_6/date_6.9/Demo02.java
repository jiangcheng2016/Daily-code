package com.ipsa.demo;

import java.util.Scanner;

public class Demo02 {
	public static void main(String[] args) {
		
		//定义数组 长度为10
		int array_test[] = new int[10];
		int sum = 0;	//定义存和变量
		
		System.out.println("请输入10个数:");
		Scanner scanner = new Scanner(System.in);
		//循环将键盘的数赋给数组array_test
		for(int i = 0;i < array_test.length;i ++) {
			
			array_test[i] = scanner.nextInt();
		}
		
		for (int i = 0; i < array_test.length; i++) {
			if (i % 2 == 0) {
				sum += array_test[i];
			}	
		}
		System.out.println("下标为偶数的和的值为:" + sum);
	}
}
