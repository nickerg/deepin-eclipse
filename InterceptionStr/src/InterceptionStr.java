import java.util.Scanner;

public class InterceptionStr {
/**
 * 解析截取字符串
 * @param args
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str;
		int nu;
		System.out.println("请输入字符串");
		Scanner scanner = new Scanner(System.in);	//从键盘获取字符串
		str = scanner.next();
		System.out.println("输入截取的字节数");
		nu = scanner.nextInt();
		System.out.println(str + "\t" + nu);
//		System.out.println("\u4e00"+ "\t"+ "\u9fa5");
//		System.out.println("[\u4e00-\u9fa5]");
		interceptionString(getArray(str), nu);
	}
	private static String[] getArray(String str) {
		String string[] = new String[str.length()];
		for (int i = 0; i < string.length; i++) {
			string[i] = str.substring(i, i+1);	//将字符串的第i个字符取出放入数组中
		}
		return string;		//返回转换后的数组
	}
	public static void interceptionString(String string[],int num) {
		int count = 0;
		String ch = "[\u4e00-\u9fa5]";
		System.out.println("以每"+ num+"字节划分的字符串为：");
		for (int i = 0; i < string.length; i++) {
			if(string[i].matches(ch)) {
				count += 2;	//汉字占两个字节
			}else {
				count += 1;//其他占一个字节
			}
			if (count < num) {
				System.out.print(string[i]);
			}else if (count == num) {
				System.out.print(string[i]);
				count = 0;
				System.out.println();//换行
			}else {
				count = 0;	//丢失半个汉字
				System.out.println();
			}
			
		}
		
	}

}
