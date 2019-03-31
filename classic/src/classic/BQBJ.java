package classic;

public class BQBJ {

	static void bqbj(int m, int n) { //m钱买n鸡
		int k;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - i; j++) {
				k = n - i - j;
				if(k > 0 && k % 3 == 0 && 5*i+3*j+k/3 == m) {
					System.out.printf("公鸡：%d只，母鸡：%d只，小鸡：%d只\n",i,j,k);
				}else {
//					System.out.println();
				}
				
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		bqbj(100, 100);
	}

}
