package shulun;
/*
 * 数论
 */
public class PerfectNum {
	static void perfectNum(long range) {
		long num,sum;
		int count;	//计数因子
		long count2 = 0; //计数完全数
		long[] p = new long[Character.MAX_VALUE];
//		for(long i=0; i<range; i++) {	//循环所有range内的数
		long i = 0;
		while(count2 < range) {
			num = i;
			sum = i;
			count = 0;
			for (int j = 1; j < num/2 + 1; j++) {
				if(num % j == 0) {
					p[count++] = j;		// 找到一个因数
					sum -= j;			//从该数中减去
				}
//				System.out.println(num);
			}
			if(sum == 0) {
				count2++;
				System.out.print(num + "是一个完全数，其因子是：");
				for (int j = 0; j < count; j++) {
					System.out.print(p[j] + " ");
				}
				System.out.println();
			}
//			System.out.println(i);
			i++;
		}
		System.out.println(i);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		long range = 10000l;
		long range = 6;
		System.out.println("查找"+range+"以内的完全数：");
		perfectNum(range);
	}

}
