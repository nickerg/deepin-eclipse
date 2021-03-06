import java.util.ArrayList;
import java.util.Random;

public class InsertionSort {
/**
 * 实现插入排序
 * @param args
 */
	public static void main(String[] args) {
		InsertionSort iSort = new InsertionSort(20, 1000);//构造一个对象
		iSort.sortIt();	//调用对象的方法
	}
	
	// 用来存储需要排序的数据
	ArrayList<Integer> list = null;
	/**
	 * 产生一个序列
	 * @param num	数据的个数
	 * @param mod	产生随机数的范围在1～mod之间
	 */
	public InsertionSort(int num, int mod) {
		list = new ArrayList<Integer>(num);
		Random random = new Random();
		System.out.println("排序之前的数组为：");
//		System.out.println(list.size());	//此时list中的元素为0
		
		//获取随机数并添加到列表中
		for (int i = 0; i < num; i++) {
			list.add(Math.abs(random.nextInt()) % mod + 1);	//自动装箱
			System.out.print("list[" + i + "]=" + list.get(i) + "\t");
		}
		System.out.println();
	}
	/**
	 * 对此类中的list进行插入排序
	 */
	public void sortIt() {
		if(list.size() == 0) {
			System.out.println("列表为空");
			return ;
		}
//		int maxsize = 1;	//标记已经排序好的数据数，实际和循环中的i值相同
		Integer tempInt ;		//定义一个中间变量
		for (int i = 1; i < list.size(); i++) {
			tempInt = list.remove(i);
			//和第i-1个元素进行比较，如果大于，则不用排序，插入到i-1元素后面即可
			if(tempInt.intValue() > list.get(i-1).intValue()) {
				list.add(i, tempInt);
//				maxsize++;
			}else {
				/**
				 * 插入排序是将L[i]与L[i-1]进行调换，然后继续比较L[i-1](temp)和L[i-2]，直到满足L[j-1](temp) <= L[j]
				 * 实际为从第一个数进行比较，如果遇到一个大于此数的A，就将此数插入到A的位置，原有元素自动后移
				 */
				for (int j = 0; j < i; j++) {
					if(list.get(j).intValue() >= tempInt.intValue()) {
						list.add(j, tempInt); 	
//						maxsize++;
						break;
					}
				}
			}
		}
		System.out.println("排序之后的数组为：");
		for (int i = 0; i < list.size(); i++) {
			System.out.print("list[" + i + "]=" + list.get(i) + "\t");
		}
		System.out.println();
	}
	
	// 用数组进行排序
	void insertionSort(int[] a) {
		int j;	//用来存放需要插入的位置
		int t;	//用来保存需要插入的数据
		for (int i = 1; i < a.length; i++) {
			t = a[i];	//保存要插入的数据
			j = i-1; 	//从前一个数据开始判断
			while(j>=0 && t < a[j]) {	//由于前面的数据已经排序好，所以是 依次后移
				a[j+1] = a[j];		//数据后移
				j--;				//准备判断下一个
			}
			a[j+1] = t;				//在需要插入的位置插入原数据a[i]
		}
	}

}
