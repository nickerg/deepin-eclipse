import java.util.Scanner;

/**
 * 字符移动 将字符串中的字符“*”移到字符串的前部，前面的非"*"字符后移
 * 不改变非"*"字符的先后顺序
 * 返回串中字符“*"的数量
 * @author dnd
 *
 */
public class MoveStr {

	public static void main(String[] args) {
		System.out.println("输入字符串");
		Scanner scanner = new Scanner(System.in);
		String str = scanner.next();
		scanner.close();
		System.out.println("移动前的字符串为："+str);
		char[] ch = str.toCharArray();
		int sum = beginMove(ch);
		// 此处copyValueOf 和valueof方法都可以
		System.out.println("移动后的字符串为："+String.copyValueOf(ch)+"\t*字符数为："+sum);
		
	}
	/**
	 * 对传入的ch数组进行处理
	 * @param ch 预处理的数组
	 * @return	ch中的'*'数量
	 */
	public static int beginMove(char[] ch) {
		//从数组最后开始判断
		// num用来找需要移动的'*'
		// length用来找可交换字符，及循环整个数组
		int num,length=ch.length-1;
		for (num = length; length >= 0; length--) {
			//如果不需要移动，向左移动，寻找下一下
			if(ch[num] != '*') {
				num--;
			// 当需要移动时，判断是否可移动，如果length字符是'*'则继续左移寻找下一个
			}else if (ch[length] != '*') {
				ch[num] = ch[length];
				ch[length] = '*';
//				System.out.print("执行了一次交换\t" +String.copyValueOf(ch));
//				System.out.println();
				num--;
			}
		}
		return num+1;
	}

}
