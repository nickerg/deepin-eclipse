//快速排序
public class QuickSort {
	static void quickSort(int[] arr, int left, int right) {
		if(left >= right) {
			return;
		}
		int ltemp,rtemp,temp;
		ltemp = left;
		rtemp = right;
		temp = arr[ltemp];
		while(ltemp < rtemp) {
			while(ltemp < rtemp && arr[rtemp] > temp) {
				rtemp--;
			}
			if(ltemp < rtemp) {
				arr[ltemp++] = arr[rtemp];
			}
			while(ltemp < rtemp && arr[ltemp] < temp) {
				ltemp++;
			}
			if(ltemp < rtemp) {
				arr[rtemp--] = arr[ltemp];
			}
		}
		arr[ltemp] = temp;	//ltemp = rtemp
//		System.out.println(arr[ltemp]+"\t"+arr[rtemp]);
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i]+"\t");
//		}
//		System.out.println();
		quickSort(arr, left, ltemp-1);	//递归调用
		quickSort(arr, rtemp+1, right);
	}
	public static void main(String[] args) {
		final int SIZE = 20;
		int[] shuzu = new int[SIZE];
		for (int i = 0; i < shuzu.length; i++) {
			shuzu[i] = (int)(Math.random()*(100));
		}
		System.out.println("排序前的数组为：");
		for (int i = 0; i < shuzu.length; i++) {
			System.out.print(shuzu[i]+"\t");
		}
		System.out.println();
		quickSort(shuzu, 0, SIZE-1);
		System.out.println("排序后的数组为：");
		for (int i = 0; i < shuzu.length; i++) {
			System.out.print(shuzu[i]+"\t");
		}
		
	}
}
