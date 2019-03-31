
public class ShellSort {
	void shellSort(int[] a) {
		int j;		//欲插入的位置
		int r;		//分组间隔
		int temp;	//中间变量
		for(r = a.length/2; r>=1; r/=2) {	//以一半为间隔
			for(int i=r; i<a.length; i++) {
				temp = a[i];
				j = i-r;
				while(j>=0 && temp < a[j]){
					a[j+r] = a[j];
					j-=r;
				}
				a[j+r] = temp;
			}
		}
	}
	
}
