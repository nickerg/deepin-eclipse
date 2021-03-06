import java.util.Scanner;

/*
 * 链表结构
 */
// 数据结点类型
class DATA{
	String key;
	String name;
	int age;
}
// 定义链表结构
class CLType{
	DATA nodeData = new DATA();
	CLType nextNode;
	/**
	 * 在链表末尾追加结点
	 * 分配内存存储新增结点;将此结点的地址部分设为null;
	 * 从head开始查找，将原表的表尾结点地址设为此新增结点地址
	 * @param head	链表头
	 * @param nodeData	追加的结点
	 * @return	追加后的链表头，如果失败返回空指针
	 */
	@SuppressWarnings("unused")
	CLType CLAddEnd(CLType head,DATA nodeData) {
		CLType htemp;
		CLType node= new CLType();
		if(node == null) {	//申请内存
			System.out.println("申请内在失败");
			return null;
		}else {
			node.nodeData = nodeData;	//保存数据
			node.nextNode = null;		//可以将这两个属性放在DATA中？？
			if(head == null) {
				head = node;
				return head;
			}else {
				htemp = head;
				while (htemp.nextNode != null) {		//查找表尾
					htemp = htemp.nextNode;
				}
				htemp.nextNode = node;
				return head;
			}
			
		}
	}
	/**
	 * 在链表开头添加结点
	 * 分配内存存储新增结点;将此结点的地址部分指向原head;
	 * 改变原head的引用地址
	 * @param head	链表头
	 * @param nodeData	追加的结点
	 * @return	添加后的链表头，如果失败返回空指针
	 */
	@SuppressWarnings("unused")
	CLType CLAddStart(CLType head,DATA nodeData) {
		CLType node= new CLType();
		if(node == null) {	//申请内存
			System.out.println("申请内在失败");
			return null;
		}else {
			node.nodeData = nodeData;
			node.nextNode = head;
			head = node;		//head传入的是引用，其指向可以改变
			return head;		//不能直接返回node，需要将传入的head指向新的头引用
		}
	}
	//查找结点
	CLType CLFindNode(CLType head, String key) {
		CLType htemp = head;
		while (htemp != null) {
			if(htemp.nodeData.key.equals(key)) {
				return htemp;
			}
			htemp = htemp.nextNode;		//查找下一个节点
		}
		return null;
	}
	/**
	 * 在findkey 之后 插入节点
	 * @param head	预插入链表的头引用
	 * @param findkey	
	 * @param nodeData
	 * @return
	 */
	CLType CLInsertNode(CLType head,String findkey,DATA nodeData) {
		CLType node = new CLType(),nodetemp;
		if(node == null) {
			System.out.println("申请内存失败");
			return null;
		}
		node.nodeData = nodeData;		//保存节点数据
		nodetemp = CLFindNode(head, findkey);	//找到预插入节点的位置
		if(nodetemp != null) {
			node.nextNode = nodetemp.nextNode;	//将关键节点的下一节点引用保留
			nodetemp.nextNode = node;		//关键节点的下一节点指向插入的节点
		}else {
			System.out.println("未找到正确的插入位置");
		}
		return head;		//返回链表的头引用
	}
	/**
	 * 删除关键字key所在的节点
	 * 此方法不能删除第一个节点（已修改）
	 * @param head
	 * @param key
	 * @return	返回0失败
	 */
	CLType CLDeleteNode(CLType head,String key) {
		CLType node,htemp;
		if(head.nodeData.key.equals(key)) {
			head = head.nextNode;
			return head;
		}
		node = head;		//指向删除节点的前一节点
		htemp = head;		//指向预删除节点
		while(htemp != null) {
			if(htemp.nodeData.key.equals(key)) {
				node.nextNode = htemp.nextNode;
//				htemp.nodeData = null;
//				htemp = null;	//释放内存
//				free(htemp);
				
				System.out.println("删除成功");
				return head;
			}else {
				node = htemp;
				htemp = htemp.nextNode;
			}
		}
		System.out.println("删除失败");
		return head;	//删除失败
	}
	//计算链表长度
	int CLLength(CLType head) {
		CLType htemp = head;
		int len = 0;
		while (htemp != null) {
			len++;
			htemp= htemp.nextNode;
		}
		return len;
	}
	//显示所有结点
	void CLAllNode(CLType head) {
		DATA data = new DATA();
		CLType htemp = head;
		System.out.println("当前列表共有"+CLLength(head)+"个节点：");
		while(htemp != null) {
			data = htemp.nodeData;
			System.out.println("结点：\t"+data.key+"\t"+data.name+"\t"+data.age);
			htemp=htemp.nextNode;
		}
	}
}
public class ChainList {

	public static void main(String[] args) {
		CLType node,head = null;
		CLType CL = new CLType();
		@SuppressWarnings("unused")
		String key ,findkey;
		Scanner scanner = new Scanner(System.in);
		System.out.println("链表测试。输入链表数据：关键字 姓名 年龄");
		do {
			DATA nodeData = new DATA();		//存放预添加的节点
			nodeData.key = scanner.next();
			if(nodeData.key.equals("0")) {
				break;		//如果输入0,则结束输入
			}else {
				nodeData.name = scanner.next();
				nodeData.age = scanner.nextInt();
				head = CL.CLAddEnd(head, nodeData);
			}
		} while (true);
		CL.CLAllNode(head); 		//显示所有结点
//		System.out.println("插入结点，请输入插入位置的关键字");
//		findkey = scanner.next();
//		System.out.println("输入插入的结点： 关键字 姓名 年龄");
//		DATA nodeData = new DATA();
//		nodeData.key = scanner.next();
//		nodeData.name = scanner.next();
//		nodeData.age = scanner.nextInt();
//		head = CL.CLInsertNode(head, findkey, nodeData);	//进行节点插入
		CL.CLAllNode(head); 
		System.out.println("演示删除节点");
		key = scanner.next();		//删除节点关键字
		head = CL.CLDeleteNode(head, key);
		CL.CLAllNode(head);
		System.out.println("演示查找节点");
		key = scanner.next();
		node = CL.CLFindNode(head, key);
		if(node != null) {
			System.out.println("关键字为："+key+"\t对应的结点为："+node.nodeData.key+"\t"+node.nodeData.name+"\t"+node.nodeData.age);
		}else {
			System.out.println("未在链表中找到"+key+"对应的结点");
		}
		scanner.close();
	}

}
