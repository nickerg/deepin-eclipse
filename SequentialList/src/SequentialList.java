import java.util.Scanner;

/**
 * 顺序表练习
 * @author dnd
 *
 */
/*
 * 定义顺序表数据元素 结点
 */
class DATA {
	String key;			//结点的关键字
	String name;
	int age;
}
/*
 * 定义顺序表结构
 */
class SLType{
	static final int MAXLEN = 100;	//定义顺序表的最大长度
	DATA[] ListData = new DATA[MAXLEN+1];	//保存顺序表的结构数组，为了容易理解，下标0的位置不用
	int ListLen;		//顺序表已存结点的数量
	/*
	 * 初始化顺序表
	 */
	void SLInit(SLType SL) {
		SL.ListLen = 0;	//初始化为空表，并没有清空原始数据
		System.out.println("初始化顺序表");
	}
	/*
	 * 计算数据表长度
	 */
	int SLLength(SLType SL) {
		return SL.ListLen;	//返回顺序表的元素数量
	}
	/**
	 * 插入结点，其后的结点编号依次加1
	 * 线性表长度加1
	 * @param SL	预插入的线性表
	 * @param n		插入的位置
	 * @param data	插入的结点
	 * @return	返回1成功，返回0失败
	 */
	int SLInsert(SLType SL,int n,DATA data) {
//		int i;
		if (SL.ListLen >=MAXLEN) {	//顺序表结点数量已达到最大，不能插入新值
			System.out.println("顺序表已满，不能插入结点\n");
			return 0;
		}
		if (n<1 || n>SL.ListLen-1) {		// -1??? 插入结点序号不正确
			System.out.println("插入元素序号错误，不能插入元素\n");
			return 0;
		}
		for(int i=SL.ListLen;i>=n;i--) {		//将顺序表中的数据向后移动
			SL.ListData[i+1] = SL.ListData[i];		//数据从下标1开始存储，正常应该是 i = i-1;
		}
		SL.ListData[n] = data;		//插入结点		同理，正常应该是第 n-1
		SL.ListLen++;				//顺序表结点数量加1
		return 1;
	}
	/**
	 * 追加结点，不必进行数据移动，单独给出函数
	 * @param SL	要追加到的顺序表
	 * @param data	要追加的数据
	 * @return	返回1成功，返回0失败
	 */
	int SLAdd(SLType SL,DATA data) {
		if (SL.ListLen >=MAXLEN) {	//顺序表结点数量已达到最大，不能插入新值
			System.out.println("顺序表已满，不能插入结点\n");
			return 0;
		}
		SL.ListData[++SL.ListLen] = data;
		return 1;
	}
	/**
	 * 删除一个结点
	 * @param SL	预删除结点所在顺序表
	 * @param n		删除的结点序号
	 * @return		返回1成功，返回0失败
	 */
	int SLDelete(SLType SL,int n) {
		if (n<1 || n>SL.ListLen+1) {		// 从序号1开始存储，判断结点序号不正确
			System.out.println("结点序号错误，不能删除结点\n");
			return 0;
		}
		for (int i = n; i < SL.ListLen; i++) {
			SL.ListData[i]=SL.ListData[i+1];	//依次左移覆盖，
		}
		SL.ListLen--;
		return 1;
	}
	/**
	 * 根据序号返回结点
	 * @param SL	查找的顺序表
	 * @param n		查找的序号
	 * @return		返回的结点，失败返回null
	 */
	DATA SLFindByNum(SLType SL,int n) {
		if (n<1 || n>SL.ListLen) {		// 从序号1开始存储，判断结点序号不正确
			System.out.println("结点序号错误，不能返回结点\n");
			return null;
		}
		return SL.ListData[n];
	}
	/**
	 * 根据关键字查找结点
	 * @param SL	预查找的结点
	 * @param key	查找的关键字
	 * @return	查找到结点的序号，失败返回0
	 */
	int SLFindByCont(SLType SL,String key) {
		for (int i = 1; i <= SL.ListLen; i++) {		//下标从1开始
			if (SL.ListData[i].key.equals(key)) {	//也可以用compareto，两者的区别？？
				return i;
			}
		}
		return 0;
	}
	/*
	 * 显示所有结点,
	 * 返回结点数量
	 */
	int SLAll(SLType SL) {
		if (SL.ListLen == 0) {
			System.out.println("顺序表为空");
			return 0;
		}
		for (int i = 1; i <= SL.ListLen; i++) {
			System.out.printf("%s,%s,%d\n", SL.ListData[i].key, SL.ListData[i].name, SL.ListData[i].age);
		}
		return SL.ListLen;
	}
}
public class SequentialList {
	public static void main(String[] args) {
		SLType SL = new SLType();	//定义顺序表变量
		DATA pData;			// 定义结点保存引用变量
		String key;			// 保存关键字
		System.out.println("顺序表操作演示！\n");
		SL.SLInit(SL);		//初始化顺序表
		
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("输入添加的结点（学号 姓名 年龄）");
			DATA data = new DATA();
			data.key = scanner.next();
			data.name = scanner.next();
			data.age = scanner.nextInt();
			
			if(data.age > 0) {	//通过年龄来判断数据有效性
				if(SL.SLAdd(SL, data) == 0) {	//如果添加失败则跳出循环
					break;
				}
			}else {		//	当输入年龄为负时，则退出输入
				break;
			}
		} while (true);
		System.out.println("\n顺序表中的结点顺序为：");
		SL.SLAll(SL);		// 	显示所有结点数据
		
		System.out.print("\n输入要查找的关键字：");
		key = scanner.next();
		pData = SL.SLFindByNum(SL, SL.SLFindByCont(SL, key));	//查找关键字返回的是序号
		if(pData != null) {
			System.out.printf("%s,%s,%d\n", pData.key, pData.name, pData.age);
		}
		scanner.close();
	}
	

}
