package basic;
/*
 * 判断闰年 
 */
public class LeapYear {

	private static boolean leapYear(int year) {
		if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count=0;
		for(int i= 2000;i<3000; i++) {
			if(LeapYear.leapYear(i)) {
				System.out.print(i+" ");
				count++;
				if(count > 7) {
					count = 0;
					System.out.println();
				}
			}
		}
	}

}
