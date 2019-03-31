/*
 * 选择排序
 */
public class SelecionSort {
	void selectSort1(int[] a) {
//		int temp;
		for (int i = 0; i < a.length-1; i++) {
			for (int j = i+1 ; j < a.length; j++) {
				if(a[i] > a[j]) {
					int temp = a[i];
					a[i] = a[j];
					a[j] = a[i];
				}
			}
		}
	}
	void selectSort2(int[] a) {
		int index;
		for (int i = 0; i < a.length-1; i++) {
			index = i;
			for (int j = i+1 ; j < a.length; j++) {
				if(a[j] < a[index]) {	//可以直接找到最小的数，只交换一次
					index = j;
				}
			}
			if(index != i) {
				int temp = a[i];
				a[i] = a[index];
				a[index] = a[i];
			}
		}
	}
}
