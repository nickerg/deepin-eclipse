package com.walk.multi;

public class MultiTable00 {
/**
 * 实现打印九九乘法表
 * @param args
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		print1();
		System.out.println();
		print2();
	}
	public static void print1() {
		for (int i = 1; i <= 9; i++) {		//遍历行
			for (int j = 1; j < i+1; j++) {		//遍历列，不能大于行的i值
				System.out.print(j+"*"+i+"="+i*j+"\t");	//打印
			}
			System.out.println();		//换行
		}
	}
	public static void print2() {
		for (int i = 1,j = 1; i < 10; j++) {	//i控制行循环，j控制列
			System.out.print(j+"*"+i+"="+i*j+"\t");
			if(i == j) {	//说明该行已经打印完成
				i++;		//行数加1
				j=0;		//j不能为1,重新循环的时候需要执行for中的`j++`
				System.out.println();
			}
		}
	}

}
