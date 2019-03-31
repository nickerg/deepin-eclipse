import java.io.IOException;
import java.util.Scanner;

public class ExchangeDemo00 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		exchangeNum(readNum());
		
	}
	public static int[] readNum() {
//		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		Scanner scanner = new Scanner(System.in);
		int temp = 0;
		int n;	//开关量和中间量
		/**
		 * 获取要排列的元素数
		 */
		do {
			n = 1;
			System.out.println("输入要互换的元素数，需为偶数：");
			try {
//				temp = Integer.parseInt( bReader.readLine());
				temp = scanner.nextInt();
				if((temp % 2) != 0) {
					throw new IOException();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("数据异常，请重新输入");
				n = 0;
			} 
		} while (n == 0);
//		System.out.println(temp);
		int array[] = new int[temp];
		for (int i = 0; i < array.length; i++) {
			array[i] = scanner.nextInt();
		}
//		for (int i = 0; i < array.length; i++) {
//			System.out.print(array[i] + "\t");
//		}
//		System.out.println();
		scanner.close();
		
		return array;
	}

	public static void exchangeNum(int[] array) {
		
		int temp;	//交换的中间变量
		
		System.out.println("原数组为：");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + "\t");
		}
		System.out.println();
		
		for (int i = 0; i < array.length/2; i++) {
			temp = array[i];
 			array[i] = array[array.length - 1 - i];
 			array[array.length - 1 - i] = temp;
		}
		
		System.out.println("交换后的数组为：");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + "\t");
		}
		System.out.println();
	}
}
