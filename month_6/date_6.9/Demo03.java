package com.ipsa.demo;

import java.util.Scanner;

public class Demo03 {
	public static void main(String[] args) {
		//定义数组 自定义5个元素
		int[] nums = {5,12,94,3,43};
		int key = 0;	//定义key
		//从键盘输入一个数
		System.out.println("请输入一个数：");
		Scanner scanner  = new Scanner(System.in);
		//将输入的数赋给num
		int num = scanner.nextInt(); 
		
		for(int i = 0;i < nums.length;i ++) {
			if (num == nums[i]) {
				key = 1;
				break;
			}
		}
		if (key == 1) {
			System.out.println("猜对了！");
		}else {
			System.out.println("Sorry!");
		}
		System.out.println("test");
	}
}
