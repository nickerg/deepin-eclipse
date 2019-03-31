import java.util.Scanner;

/**
 * 判断是否是回文数字
 * 通过将该数字倒置来判断
 * @author dnd
 *
 */
public class PalindRome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("输入一个整数");
		num = scanner.nextInt();
		if(isPalindRome(num)){
			System.out.println(num + "是一个回文数字。");
		}else {
			System.out.println(num + "不是一个回文数字。");
		}
		scanner.close();
	}
	/**
	 * 判断输入的数字是否为回文
	 * @param num	输入的数字
	 * @return	真或假
	 */
	public static boolean isPalindRome(int num) {
		int m = reverse(num);
		if(m == num) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 将num倒置
	 * 通过将num的个位依次取出，然后依次乘10相加进行倒置
	 * @param num 预倒置的数字
	 * @return	倒置后的数字
	 */
	public static int reverse(int num) {
		int res = 0;	//倒置后的数
		while (num != 0) {
			res = res * 10 + num % 10;//原来的位左移一位，将传入数的个位添加过来
			num /= 10;
		}
		return res;
	}
}
