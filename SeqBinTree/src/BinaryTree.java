import java.util.Scanner;

/*
 * 二叉树
 */
class CBTType{
	String data;		//元素数据
	CBTType left;		//左子树结点引用
	CBTType right;		//右子树结点引用
//	CBTType parent;
}
public class BinaryTree {
	static final int MAXLEN = 100;
	Scanner scanner = new Scanner(System.in);
	// 初始化二叉树
	CBTType InitTree() {
		CBTType node = new CBTType();
		if(node != null) {	//内存申请成功
			System.out.println("请输入一个结点数据");
			node.data = scanner.next();
			node.left = null;
			node.right = null;
			return node;
		}
		return null;
	}
	// 添加结点
	void AddTreeNode(CBTType treeNode) {
		CBTType pnode,parent;
		String data;
		int menusel;
		
		if((pnode = new CBTType()) != null) {
			System.out.println("输入二叉树结点数据");
			pnode.data = scanner.next();
			pnode.left = null;
			pnode.right = null;
			System.out.println("输入该结点的父结点数据");
			data = scanner.next();
			parent = TreeFindNode(treeNode,data);	//查找指定数据的结点
			if (parent == null) {
				System.out.println("未找到该父结点");
				pnode = null;	//释放创建的结点内存
				return;
			}
			System.out.println("1.添加该结点到左子树\t2.添加该结点到右子树");
			do {
				menusel = scanner.nextInt();
				if (menusel ==1 || menusel == 2) {
					switch (menusel) {
					case 1:
						if (parent.left != null) {
							System.out.println("左子树结点不为空");
						}else {
							parent.left = pnode;
						}
						break;
					case 2:
						if (parent.right != null) {
							System.out.println("右子树结点不为空");
						}else {
							parent.right = pnode;
						}
						break;

					default:
						System.out.println("无效参数");
					}
				}
			} while (menusel != 1 && menusel != 2);	//当输入有效的时候退出循环
		}
	}
	//查找结点
	CBTType TreeFindNode(CBTType treeNode, String data) {
		CBTType ptr;
		if (treeNode == null) {
			return null;
		}else {
			if(treeNode.data.equals(data)) {
				return treeNode;
			}else {	//分别向左右子树递归查找
				if ((ptr = TreeFindNode(treeNode.left, data)) != null) {
					return ptr;
				}else if ((ptr = TreeFindNode(treeNode.right, data)) != null) {
					return ptr;
				}else {
					return null;
				}
			}
		}
	}
	//获取左子树
	CBTType TreeLeftNode(CBTType treeNode) {
		if (treeNode != null) {
			return treeNode.left;
		}else {
			return null;
		}
	}
	//获取右子树
	CBTType TreeRightNode(CBTType treeNode) {
		if (treeNode != null) {
			return treeNode.right;
		}else {
			return null;
		}
	}
	//判断空树
	boolean TreeIsEmpty(CBTType treeNode) {
		if (treeNode != null) {
			return false;
		}else {
			return true;
		}
	}
	//计算二叉树深度
	int TreeDepth(CBTType treeNode) {
		int depLeft,depRight;
		if (treeNode == null) {
			return 0;
		}else {
			depLeft = TreeDepth(treeNode.left);		//递归调用，查询左子树深度
			depRight = TreeDepth(treeNode.right);	//递归调用，查询右子树尝试
			if (depLeft >depRight) {
				return depLeft+1;
			}else {
				return depRight+1;
			}
		}
	}
	//清空二叉树
	void ClearTree(CBTType treeNode) {
		if (treeNode != null) {
			ClearTree(treeNode.left);
			ClearTree(treeNode.right);
			treeNode = null;
		}
	}
	//显示结点数据
	void TreeNodeData(CBTType treeNode) {
		System.out.println(treeNode.data);
	}
	//先序遍历
	void DLRTree(CBTType treeNode) {
		if (treeNode != null) {
			TreeNodeData(treeNode);
			DLRTree(treeNode.left);
			DLRTree(treeNode.right);
		}
	}
	//中序遍历
	void LDRTree(CBTType treeNode) {
		if (treeNode != null) {
			LDRTree(treeNode.left);
			TreeNodeData(treeNode);
			LDRTree(treeNode.right);
		}
	}
	//后序遍历
	void LRDTree(CBTType treeNode) {
		if (treeNode != null) {
			LRDTree(treeNode.left);
			LRDTree(treeNode.right);
			TreeNodeData(treeNode);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
