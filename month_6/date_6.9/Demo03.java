package com.ipsa.demo;

import java.util.Scanner;

public class Demo03 {
	public static void main(String[] args) {
		//�������� �Զ���5��Ԫ��
		int[] nums = {5,12,94,3,43};
		int key = 0;	//����key
		//�Ӽ�������һ����
		System.out.println("������һ������");
		Scanner scanner  = new Scanner(System.in);
		//�������������num
		int num = scanner.nextInt(); 
		
		for(int i = 0;i < nums.length;i ++) {
			if (num == nums[i]) {
				key = 1;
				break;
			}
		}
		if (key == 1) {
			System.out.println("�¶��ˣ�");
		}else {
			System.out.println("Sorry!");
		}
		System.out.println("test");
	}
}
