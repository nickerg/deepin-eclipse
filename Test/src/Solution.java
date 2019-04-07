import java.awt.Label;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

public class Solution {
	
	//找到最长回文子串
	// 可以解决，但是会超出时间限制
    public String longestPalindrome1(String s) {
        int max = 0;
        String out;
        if(s.length() >0){
            out = s.substring(0,1);
        }else{
            out = "";
        }
        for(int i=0;i<s.length()-1;i++){
            for(int j=s.length()-1;j> i;j--){
                int m=i,n=j;
                while(n-m >=1){
                    if(s.charAt(m) == s.charAt(n)){
                        // max = Math.max(max,n-m);
                        m++;
                        n--;
                        continue;
                    }
                    break;
                }
                if(n-m <1 && max < j-i+1){
                    
                    max = j-i+1;
                    // System.out.println(max);
                    out = s.substring(i,j+1);
                }
            }
        }
        // System.out.println(out);
        return out;
    }
    
    // 添加了判断，确保查询的都是大于max长度的
    public String longestPalindrome2(String s) {
        int max = 0;
        String out;
        if(s.length() >0){
            out = s.substring(0,1);
        }else{
            out = "";
        }
        for(int i=0;i<s.length()-1;i++){
            for(int j=s.length()-1;j>=i+max;j--){ //    判断可能长度大于max的，否则没有必要
                int m=i,n=j;
                
                while(n-m >=1){
                    if(s.charAt(m) == s.charAt(n)){
                        // max = Math.max(max,n-m);
                        m++;
                        n--;
                        continue;
                    }
                    break;
                }
                if(n-m <1 && max < j-i+1){
                    
                    max = j-i+1;
                    // System.out.println(max);
                    out = s.substring(i,j+1);
                    // i = j;
                }
            }
        }
        // System.out.println(out);
        return out;
    }
  
    //z字形变换
    //需考虑 输入为1行，除数为0
    public String convert(String s, int numRows) {

        int len = s.length();
        int her = len;
        StringBuilder out = new StringBuilder();
        char a[][] = new char[numRows][her];
        int i=0,j=0,k=0;
        if(numRows > 1) {
        	her = (int)(numRows*len/(float)(2*numRows-2));	// 一个块存numRows + numRows -2数，len 需要x个块存，每个块是numRows列
//        	System.out.println(her);
//        	为了判断字符数组不为空
//        	for (int k2 = 0; k2 < a.length; k2++) {
//        		for (int l = 0; l < a[0].length; l++) {
//        			a[k2][l] = ' ';
//        		}
//        	}
        	while(k < len){
        		if(j % (numRows-1) == 0 || (i+j)%(numRows -1) == 0){
        			a[i][j] = s.charAt(k++);    
        		}else {
//                a[i][j] = ' ';
        		}
        		i++;
        		if(i % numRows == 0){
        			i = 0;
        			j++;
        			if(j == her) {
        				j = 0;
        			}
        		}
        	}
//        	System.out.println(a.length + "\t" + a[0].length);
        	for (int k2 = 0; k2 < a.length; k2++) {
        		for (int l = 0; l < a[0].length; l++) {
        			if(a[k2][l] != '\0') {
        				out.append(a[k2][l]);
        			}
        		}
        	}
        }else {
//			return s;
        	out.append(s);
		}
        return out.toString();
    }
//  有符号整数反转，在int范围之内
    public int reverse(int x) {
    	int rev = 0;
    	while (x != 0) {
            if(rev * 10 / 10 != rev){	// 判断溢出
                return 0;
            }
			rev = rev * 10 + x % 10;
// 			if(rev == Integer.MAX_VALUE || rev == Integer.MIN_VALUE) {
// //				rev = 0;
// 				return 0;
// 			}
			x/=10;
		}
    	return rev;
    }
//    字符串转换整数
	public int myAtoi(String str) {
		str = str.trim();		//去除空格
        if(str.isEmpty()) return 0;//为空当即返回
	     int j=0;
	     long rev = 0;
	     int com = 0;
//	     while(str.charAt(i)==' '){		// 去除字符串之前的空格 可以用trim
//              if(i+1 < str.length()) {
//                 i++;
//             }else return 0; 
//         }
//	     int j=i;
	    //判断第一个是否为符号位
        if(str.charAt(j) == '-' || str.charAt(j) == '+'){
           com=1;
           j++;
        }
	     while(j<str.length() &&str.charAt(j)>='0' && str.charAt(j) <= '9' ) {
	    	 j++;
	     }
	     if(j >com) {
	    	 try {
				rev = Integer.parseInt(str.substring(0, j));
                 return (int)rev;
			} catch (NumberFormatException e) {
				if(str.charAt(0) == '-') {
					return Integer.MIN_VALUE;
				}else {
					return Integer.MAX_VALUE;
				}
			}
	     }else
	    	 return 0;
    }
//	判断一个整数是否是回文数
//	反转一半数字。如果x小于反转后的数字，说明完成了一半。如果不相等，就不是回文数
	public boolean isPalindrome(int x) {
        int y=0;
        if(x < 0 || (x % 10 == 0 && x != 0))    //如果尾数是0，刚该数只有0满足条件，否则false。-121 不是回文数，所以负数肯定不是
            return false;
        while(x > y){   // 判断是否反转了一半
            y = y * 10 + x % 10;
            x /= 10;
        }
        if(x == y || x == y/10){	//如果是奇数，去除反转后的中间一位。
            return true;
        }
        return false;
    }
//	求盛最多水的容器	采用缩进法，两边谁的高度小谁缩进。
	public int maxArea(int[] height) {
        int left = 0,right = height.length-1;
        int maxArea =0;
        while(left < right) {
        	maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right-left));
        	if(height[left] < height[right]) {
        		left++;
        	}else {
        		right--;
        	}
        }
        return maxArea;
    }
//	整数转罗马数字
	public String intToRoman(int num) {
		String sNum = String.valueOf(num);	// 将数字转换为字符串
		StringBuilder sBuilder = new StringBuilder("");	
		sBuilder.append(sNum);
		StringBuilder res = new StringBuilder();
		while(sBuilder.length() < 4) {//确保占位4个字节 在这个方法中，很重要
			sBuilder.insert(0, 0);
		}
//		int count = 4;
//		int[] key = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		String[][] value = {
				{"  ", " ", "  ", "M"},
				{"CM", "D", "CD", "C"}, 
				{"XC", "L", "XL", "X"},
				{"IX", "V", "IV", "I"}}; 
		for (int i = 0; i < 4; i++) {
			if(sBuilder.charAt(i) == '9') {
				res.append(value[i][0]);
			}
			if(sBuilder.charAt(i) == '5') {
				res.append(value[i][1]);
			}
			if(sBuilder.charAt(i) == '4') {
				res.append(value[i][2]);
			}
			if((sBuilder.charAt(i) > '5' && sBuilder.charAt(i) < '9') || (sBuilder.charAt(i) > '0' && sBuilder.charAt(i) < '4')) {
				if(sBuilder.charAt(i) > '5' && sBuilder.charAt(i) < '9') {
					res.append(value[i][1]);
				}
				for (int j = 0; j < (Integer.parseInt(sBuilder.charAt(i) + "") % 5); j++) {
					res.append(value[i][3]);
				}
			}
		}
		return res.toString();
    }
//	罗马数字变整数	来自评论区 先加后减
	public int romanToInt(String s) {
		int n = s.length();
		int roman_int = 0;
		for(int i=0;i<n;i++)
		{
			switch(s.charAt(i)) 
			{
			case 'I' : roman_int = roman_int + 1;break;
			case 'V' : roman_int = roman_int + 5;break;
			case 'X' : roman_int = roman_int + 10;break;
			case 'L' : roman_int = roman_int + 50;break;
			case 'C' : roman_int = roman_int + 100;break;
			case 'D' : roman_int = roman_int + 500;break;
			case 'M' : roman_int = roman_int + 1000;break;
			default: System.out.println("default");break;
			}
	
			if(i!=0)
			{	// 非第一个的时候判断 由于是先加，所以应该减两次基值 900 +100+1000-100*2
				if(((s.charAt(i)=='V')||(s.charAt(i)=='X'))&&(s.charAt(i-1)=='I')) 
					roman_int = roman_int-1*2;
				if(((s.charAt(i)=='L')||(s.charAt(i)=='C'))&&(s.charAt(i-1)=='X'))
					roman_int = roman_int-10*2;
				if(((s.charAt(i)=='D')||(s.charAt(i)=='M'))&&(s.charAt(i-1)=='C'))
					roman_int = roman_int-100*2;
			}
		}
		return roman_int;	
	}
//	最长公共前缀
	public String longestCommonPrefix(String[] strs) {
		if(strs.length == 0) return "";		// 注意输入为空的时候
		StringBuilder res = new StringBuilder();
		int minLen = strs[0].length();
		for (int i = 1; i < strs.length; i++) {		// 数组的长度没有括号
			minLen = Math.min(minLen, strs[i].length());
		}
		boolean label = true;
		for(int i=0; i < minLen; i++) {		//查询 最小字符串长度次
			for (int j = 1; j < strs.length; j++) {		// 每次都依次判断每个是否相同
				if(strs[0].charAt(i) == strs[j].charAt(i)) {
					continue;
				}
				label = false;
				break;
			}
			if(label) {
				res.append(strs[0].charAt(i));	// 如果第一个和之后的每个都相同，则添加 否则跳出循环
				continue;
			}
			break;
		}
		return res.toString();
    }
//	上述方法的一种简单实现。也是一个个比较，同时如果越界或不相等就退出
	public String longestCommonPrefix1(String[] strs) {
	    if (strs == null || strs.length == 0) return "";
	    for (int i = 0; i < strs[0].length() ; i++){
	        char c = strs[0].charAt(i);
	        for (int j = 1; j < strs.length; j ++) {
	            if (i == strs[j].length() || strs[j].charAt(i) != c)
	                return strs[0].substring(0, i);             
	        }
	    }
	    return strs[0];
	}
//	三数之和
//	public List<List<Integer>> threeSum(int[] nums) {
//		List<List<Integer>> res = new ArrayList<>();
//		int temp = 0;
//		int j = 0;
////		for (int i = 0; i < nums.length; i++) {
////			System.out.print(nums[i]+" ");
////		}
//		// 插入法排序
//		for (int i = 1; i < nums.length; i++) {
//			temp = nums[i];
//			j = i;
//			while(j > 0 && temp < nums[j-1]) {
//				nums[j] = nums[j-1];
//				j--;
//			}
//			nums[j] = temp;
//		}
////		System.out.println();
////		for (int i = 0; i < nums.length; i++) {
////			System.out.print(nums[i]+" ");
////		}
//		return res;
//    }
	// 找出数组中最接近目标数的三个数之和
	public int threeSumClosest(int[] nums, int target) {
		if(nums.length < 3) return 0;
		Arrays.sort(nums);;  // 排序
		int sum = nums[0]+nums[1]+nums[2];
		int res = sum;
		int i=0,j=1,k=0;
		int min = Math.abs(nums[0]+nums[1]+nums[2]-target);
        for(i = 0;i<nums.length-2;i++) {		//遍历
        	j = i + 1;
        	k = nums.length -1;
        	while(j < k) {
        		sum = nums[i]+nums[j]+nums[k];
        		if(Math.abs(sum -target) < min) {
        			min = Math.abs(sum - target);
        			res = sum;
        		}
        		if(sum < target) {
        			j++;
        		}else if(sum > target) {
        			k--;
        		}else {//相等
        			return sum;	//即target
        		}
        	}
        }
        return res;
    }
//	删除链表的倒数第n个节点，保证输入的n是有效的 ，未考虑链表为空的情况
	
    public ListNode removeNthFromEnd(ListNode head, int n) {
    	ListNode root = head;
    	int count = 1;
    	while(root.next != null) {	//找到链表的总数
    		count++;
    		root = root.next;
    	}
    	root = head;
    	if(count == n)	return head.next;	//如果删除第一个，直接返回 未使用哑结点
    	while(count - n > 1) {	//找前一个节点
    		count--;
    		root =root.next;
    	}	//此时定位到了预删除节点的前一个
//    	ListNode temp = root.next;
    	root.next = root.next.next;
//    	temp = null;
    	return head;
    }
//	参考答案，给一个固定间隔，可以只遍历一次，且使用哑结点
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
//	合并两个有序链表	改变了原有链表，新创建了一个链表存储数据
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode temp = new ListNode(0);		//哑节点
        ListNode res = temp;		//保存头节点。
        if(l1 == null) {	//	某个链表为空，返回另一个
        	return l2;
        }else if(l2 == null) {
        	return l1;
        }else {
//        	temp.next = l1;
        	while(true) {
        		if(l1.val < l2.val) {
        			temp.next = l1;
        			temp = temp.next;
        			l1 = l1.next;
        		}else {
        			temp.next = l2;
        			temp = temp.next;
        			l2 = l2.next;
        		}
        		if(l1 == null) {
        			temp.next = l2;
        			return res.next;
        		}else if(l2 == null) {
        			temp.next = l1;
        			return res.next;
        		}
        	}
        }
        
    }
//  合并 k 个排序链表
    public ListNode mergeKLists(ListNode[] lists) {
//      采用递归的思想
     if(lists.length == 0){
         return null;
     }
     if(lists.length == 1){
         return lists[0];
     }
     ListNode res = mergeTwoLists(lists[0],lists[1]);
     // ListNode temp = res;
     for(int i = 2; i< lists.length; i++){
         // temp.next = mergeTwolists(mergeTwolists(),lists[])
         res = mergeTwoLists(res,lists[i]);
     }
     return res;
 }
//	两两交换链表中的节点		一定要注意，操作的是引用，可能导致节点丢失
    public ListNode swapPairs(ListNode head) {
    	if(head == null || head.next == null) {
    		return head;
    	}
    	ListNode temp = new ListNode(0);	// 为哑节点
    	ListNode res = temp;
    	while(head != null && head.next!= null) {	// 当节点不为空	A B C
    			temp.next = head.next;		// 将节点指向B
    			head.next = head.next.next;		//将C的引用保存到A.next中
    			temp.next.next = head;		//即此时的B.next 指向A
    			temp = temp.next.next;		//将temp向右移动两位，为下次准备
    			head = head.next;			//由于此时A.next 指向C,原head中已经丢失B，所以移动一次即可
    	}
    	return res.next;
    }
//    删除排序数组中的重复项
	public int removeDuplicates(int[] nums) {
		int count = 0;
		int j = 0;
		for (int i = 0; i < nums.length-1; i++) {
//			j = j+1;
			while(j<nums.length && nums[j] <= nums[i]) {	// 在不超出边界的情况下，找下一个比该数大的值
				j++;
//				count--;
			}
			if(j < nums.length)		// 如果没有超出边界，则进行赋值
				nums[i+1] = nums[j];
		}
		for (int i = 0; i < nums.length-1; i++) {	// 如果当前值大于后一个值，则说明已经去重完成 等于不好判断，所以直接返回的时候加一即可
			if(nums[i] < nums[i+1]) {
				count++;
				continue;
			}
			break;
		}
		return count+1;
//		switch (nums.length) {
//		case 0:
//			return 0;
//		case 1:
//			return 1;
//		case 2:
//			if(nums[0] == nums[1]) {
//				return 1;
//			}else {
//				return 2;
//			}
//		default:
//			break;
//		}
//		int j = 0;
//		int count = nums.length;
//        for (int i = 0; i < nums.length-1; i++) {
//        	j = i+1;
//			while(j < nums.length && nums[j] == nums[i]) {
//				nums[j++] = nums[0]-1;	// 这是一个确保不会重合的数
////				j++;
//			}
//        }
//        for (int k = 1; k < nums.length-1; k++) {
//        	if(nums[k] == nums[0]-1) {
//        		for (int k2 = k; k2 < nums.length-1; k2++) {
//        			nums[k2] = nums[k2 + 1];
//        		}
//        		count--;
//        	}
//        }
//        return count;
}
//	和上述方法类似，不过不用再次循环来计算返回的数值
	public int removeDuplicates1(int[] nums) {
		if(nums.length == 0) {
			return 0;
		}
		int i = 0;
		for (int j = 1; j < nums.length; j++) {
			if(nums[j] != nums[i]) {
				i++;
				nums[i] = nums[j];
			}
		}
		return i+1;
	}
//	移除元素 在数组中移除所有等val的元素， 采用左右双指针
//	参考答案是	当左数等val时，直接和最后一个数据交换并递减数组长度（通过变量）
	public int removeElement(int[] nums, int val) {
        if(nums.length == 0){
            return 0;
        }
        int i = 0,j = nums.length-1;
        int count = nums.length;
        while( i < j){
            while(i < nums.length && nums[i] != val){
                i++;
            }
            while(j > 0 && nums[j] == val){
                j--;
            }
            if(i < j){
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                // count--;
            }
        }
        for(int k = nums.length-1; k >= 0; k--){
            if(nums[k] == val){
                count--;
            }
        }
        return count;
    }
//	实现strStr方法，如果needle为空，则返回0.未找到返回-1
	public int strStr(String haystack, String needle) {
		int len = needle.length();
		if(len == 0) {
			return 0;
		}
		int j = 0;
		for (int i = 0; i <= haystack.length()-needle.length(); i++) {		// 遍历所有haystack字符 '='是因为如果两者相同，需要至少循环一次
			if( haystack.charAt(i) == needle.charAt(0)) {	// 如果首字母相同，进行后续判断
				j = 0;
				while(j < len && haystack.charAt(j + i) == needle.charAt(j)) {	// 从第一个字母开始判断，直到最后一个字母
					j++;
				}
				if(j == len) {	//如果相当，说明所有的needle字符都满足上述判断，返回此时在haystack中的位置
					return i;
				}
			}
		}
		return -1;
	}
//	下一个排列 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
//	如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）
	public void nextPermutation(int[] nums) {
        for(int i = nums.length-1; i > 0; i--) {	// 次如果没有交换，说明此时已经是最大
        	if(nums[i-1] < nums[i]) {
        		int j = nums.length-1;
        		while(nums[j--] <= nums[i-1]);	// 找到一个比要交换的数大于的数， 然后将剩下的数倒序 4 6 5 5 3 ，5 6 5 4 3, 5 3 4 5 6 
        		
        		int temp = nums[++j];		// while循环中多减了一次
        		nums[j] = nums[i-1];
        		nums[i-1] = temp; 
//        		for(j = 0; j<(nums.length-i)/2;j++) {	 //对后面的数组倒序
//           			temp = nums[j+i];
//                   	nums[j+i] = nums[nums.length-1-j];
//                   	nums[nums.length-1-j] = temp;
//           		}
        		int k = nums.length-1;	//另一种倒序
        		j = i;
        		while(j < k) {
        			temp = nums[j];
        			nums[j] = nums[k];
        			nums[k] = temp;
        			j++;
        			k--;
        		}
        		return;
        	}
        }	
//       已经是最大，需要将nums进行倒序
        int temp;
        for(int i = 0; i < nums.length/2; i++) {
        	temp = nums[i];
        	nums[i] = nums[nums.length-1-i];
        	nums[nums.length-1-i] = temp;
        }
        return;
    }
//	在排序数组中查找元素的第一个和最后一个位置 时间复杂度为O(logn)级别
//	可以成功，但是报超时
    public int[] searchRange(int[] nums, int target) {
    	int[] res = new int[] {-1,-1};
    	int mid = nums.length/2;
    	int left = 0;
    	int right = nums.length-1;
    	while(left >= 0 && right <= nums.length-1 && left<right) {
    		if(nums[mid] < target) {
    			right = mid;
//    			mid /= 2;
    			mid += (right-left)/2;
    		}
    		else if(nums[mid] > target) {
    			left = mid;
//    			mid /= 2;
    			mid += (right-left)/2;
    		}else {
				break;
			}
    	}
    	left = right = mid;
    	while(left >= 0 && nums[left] == target ) {
    		left--;
    	}
    	res[0] = left+1;
    	while(right <= nums.length-1 && nums[right] == target) {
    		right++;
    	}
    	res[1] = right-1;
//    	while(left >=0 && right <= nums.length-1) {
//    		if(nums[left] != target && nums[right] != target) {
//    			break;
//    		}
//    		if(nums[left] == target);
//    	}
    	
    	
    	return res;
    }
//    成功通过 但是时间复杂度不明白
    public int[] searchRange1(int[] nums, int target) {
    	int[] res = new int[] {-1,-1};
        if(nums.length == 0 || nums == null){
            return res;
        }
//    	int mid = nums.length/2;
    	int left = 0;
    	int right = nums.length-1;
    	while(left < right && (nums[left] != target || nums[right] != target)){
            if(nums[left]<target){
                left++;
            }
            if(nums[right]>target){
                right--;
            }
        }
        if(nums[left] == target && nums[right] == target){
            res[0] = left;
            res[1] = right;    
        }
        
    	return res;
    }
//	搜索插入位置 nums为已经排序好的数组 无二分效率不高 nums中可能已经存在，也可能需要插入
    public int searchInsert(int[] nums, int target) {
//    	int i = 0;
//        for(i = 0; i < nums.length; i++) {
//        	if(nums[i] >= target) {
////        		return i;
//        		break;
//        	}
//        }
//        return i;
//    	二分未实现		 实现
    	int left = 0,mid=0,right = nums.length-1;
    	switch (nums.length) {	// 对极端情况进行先行判断
		case 0:			//长度为0,返回0
			return 0;
		case 1:			//长度为1 ，直接判断
			if(nums[0] >= target) {
				return 0;
			}else {
				return 1;
			}
		default:		//长度至少为2,判断边界
			if(nums[0] >= target) {	// 如果target小于第0个数，则需要在第0个数插入。如果等于，则下标为第0个数
				return 0;
			}else if(nums[nums.length-1] == target) {	// 如果等于最后一个数，则返回其下标
				return nums.length-1;
			}else if(nums[nums.length-1] < target) {	// 说明不在数组中，返回长度
				return nums.length;
			}
		}
//    	此时，确定target在数组之内 且 数组长度大于1; 只需要判断其在数组内的（插入）位置
    	while(left < right - 1) {	// 使left和right不相等即跳出循环
    		mid = left + (right-left)/2;		//mid取中间值	最后的mid等于left
    		if(nums[mid] == target)
    			return mid;
    		else if(nums[mid] < target) {		//确保left< target right > target
    			left = mid;
    		}else if(nums[mid] > target) {
    			right = mid;
    		}
    	}
//    	在最后，mid是等于left的，left和right只能相差1.而未返回，说明left<target，并且right大于target。
    	return right;
    }
//   有效的数独
    public boolean isValidSudoku(char[][] board) {
        int[] ar = new int[10];
        for(int i = 0; i < 9; i++) {	//查询行
        	for(int j = 0; j < 9; j++) {
        		if(board[i][j] == '.')
        			continue;
        		ar[board[i][j]-'0']++;
        	}
        	for(int j = 0;j < 10; j++) {	// 确认是否某个数字出现超过两次
        		if(ar[j] > 1)
        			return false;
        		ar[j] = 0;
        	}
        }
        for(int i = 0; i < 9; i++) {	//查询列
        	for(int j = 0; j < 9; j++) {
        		if(board[j][i] == '.')
        			continue;
        		ar[board[j][i]-'0']++;
        	}
        	for(int j = 0;j < 10; j++) {		// 确认是否某个数字出现超过两次
        		if(ar[j] > 1)
        			return false;
        		ar[j] = 0;
        	}
        }
        int k = 0,m = 0;
        for(int i = 0; i < 9; i++) {	//查询块
        	for(int j = 0; j < 9; j++) {
        		k =3*(i/3) + (j%3);	//块的行数
        		m =3*(i%3) + j/3;	// 块的列数
        		if(board[k][m] == '.')
        			continue;
        		ar[board[k][m]-'0']++;
        	}
        	for(int j = 0;j < 10; j++) {		// 确认是否某个数字出现超过两次
        		if(ar[j] > 1)
        			return false;
        		ar[j] = 0;
        	}
        }
        return true;
    }
//    报数序列
    public String countAndSay(int n) {
    	if(n == 1) {
    		return "1";
    	}
        StringBuilder temp = new StringBuilder(countAndSay(n-1));
        StringBuilder res = new StringBuilder();
        int count = 0;
//        int k = 0;
        for (int i = 0; i < temp.length(); i++) {
			while(i+count < temp.length() && temp.charAt(i+count) == temp.charAt(i)) {
				count++;	// 获得在长度范围内时，有多少个当前字符
			}
//			for (int j = 0; j < count; j++) {	// 添加count个当前字符
//				res.append(temp.charAt(i));
//			}
			res.append(count);
			res.append(temp.charAt(i));
			i += count - 1;	// 跳过重复的字符 。 因为有i++，所以需要-1.
			count = 0;
		}
        return res.toString();
    }
//    缺失的第一个正数，数组未排序，要求时间复杂度为O(n)，且使用常数的空间
    public int firstMissingPositive(int[] nums) {
        int res = 1;
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i = 0; i < nums.length; i++) {
        	arr.add(nums[i]);	
        	while(arr.contains(res)) {	//利用一个arraylist来查询res是否在原nums中。
        		res++;
        	}
        }
        return res;
    }
//    接雨水 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
    public int trap(int[] height) {
        int sum = 0;
        int slo = 0, fas = 1;	//定义一个慢指针一个快指针 实际是左右端
        int min = 0;
        if(height.length == 0) return 0;
        while(slo < height.length && height[slo] == 0) {	// 除去开头的空数字
        	slo++;
        	fas = slo+1;	// 从左端的右边开始
        }
        while(slo < height.length && fas < height.length) {	// 两个指针都在范围内，
        		int tem = fas;		// 定义一个循环之后数字的变量
        		int max = height[fas];	// 保存找到剩余高度的最大值
        		while(tem < height.length && max < height[slo]) {	//当比左边低时，持续寻找，并保存已经找到的最大值。
        			if(max <= height[tem]) {
        				max = height[tem];
        				fas = tem;
        			}
        			tem++;
        		}
//        		max >= height[slo] 或者tem移动到数组末尾 此时fas中保存下一个最大高度。
        		min = Math.min(height[fas], height[slo++]);	// 获得两端的最小值 左端从下一个开始计入sum
        		while(slo<fas){		// 计算可以存储的容积
        			if(height[slo] - min < 0) {
        				sum = sum + min - height[slo];
        			}
        			slo++;
        		}	//对找到的进行相加	结束后slo = fas;
        		fas++;	//使fas从左端的右边开始寻找
        }
        return sum;
    }	
//    字符串相乘  乘法 低位从0开始 nums1 的j位与 nums2 的i位相乘 ，结果在i+j 及 i+j+1中。个位相乘结果最大为两位
//    在计算完成后，依次处理进位 完成
//    既然 i和j相乘的结果应该在i+j上，那为了防止丢失最高位的进位，就将结果放在i+j+1上。 这样高位或低位从0开始都可以 。高位从0开始则结果顺序排放
    public String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        if(len1 == 0 || len2 == 0) {
        	return "";
        }
        int[] sum = new int[len1 + len2];
        for (int i = 0; i < len1; i++) {		//此时没有处理进位。所以低位和高位开始循环都可以
        	for (int j = 0; j < len2; j++) {
				sum[i + j + 1] += (num1.charAt(i)-'0') * (num2.charAt(j) - '0');	//存放结果统一后移一位
			}
		}
//        先处理进位
        int i = len1-1 + len2-1 + 1;
        while(i>0) {	//
        	sum[i-1] += sum[i]/10;	//如果不先进位，求余的时候会丢失进位
        	sum[i] %= 10;
        	i--;
        }
//        不会判断int数组为空，就先加'0'，0和空 都变为'0'；然后从开头找'0'去除高位的所有0
//        int数组初始化后为0
        for (int j = 0; j < len1+len2; j++) {
			sum[j] += '0';		// 将int变为char
		}
//        去除高位的0
        int start = 0;
        while(start < sum.length && sum[start] == '0') {	//不能越界
        	start++;
        }
        if(start >= sum.length) {	// 说明有一个字符串为0
        	return "0";
        }
        String res = new String(sum, start, sum.length-start);
        return res;
    }
/*
 * 给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 */
    private int findEnd(int[] nums, int end, int[] re) {
//		if(end <= 0) {
//			return 0;
//		}
		int res = end;
		re[0]++;	// 次数加1
		for (int j = end - 1; j >= 0; j--) {
			if(nums[j] >= (end -j)) {	// 找到一个最小的，可以直接到达末尾的下标。
				res = j;
			}
		} 
		return res;
		
	}
    public int jump(int[] nums) {
        int[] res = new int[] {0};	//通过数组传递，每进一次子函数次数加一，
        int end = nums.length-1;	//尾标
        while (end > 0) {
        	end = findEnd(nums, end, res);
		}
		return res[0];
    }
//    没有重复数字的全排列
//    对于一个已经排好序的数组，相当于找下一个次大的排列。
//    注意int[] 转list 及 比较两个数组的内容应该调用Arrays类的方法
    public java.util.List<java.util.List<Integer>> permute(int[] nums) {
    	java.util.List<java.util.List<Integer>> res = new ArrayList<>();
    	int[] end = nums.clone();
    	nextPermutation(nums);	//先执行一次 后面根据重复来跳出循环，所以先执行一次
    	res.add(Arrays.stream(nums).boxed().collect(Collectors.toList())); //将执行完后的进行装入。可能是原list，或下一个list
    	while(!Arrays.equals(end, nums)) {	// 不能直接用end.equals。 因为其比较的是地址。Arrays比较的是两个数组的内容
    		nextPermutation(nums);	
    		res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));	// int[] 转list 
    	}
    	return res;
    }
//    给定一个可包含重复数字的序列，返回所有不重复的全排列。
//    思路同上。 在加入的时候判断一下是否重复。循环条件依然是返回到本身
//    由于nextPermutation保证了下一个数组是次大的，一定和当前不一样。所以实际和上述代码相同
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int self[] = nums.clone();
        nextPermutation(nums);
        res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        while(!Arrays.equals(self, nums)) {
        	nextPermutation(nums);
        	res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        }
        return res;
    }
/*
 * 给定一个 n × n 的二维矩阵表示一个图像。    
 * 将图像顺时针旋转 90 度。你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。
 * 请不要使用另一个矩阵来旋转图像。 
 * 思路。一个n×n阶乘，需要循环 n/2 次。 n = 3 处理外围1圈。n = 4 处理2圈
 * 关键是注意下标的处理
 */
    public void rotate(int[][] matrix) {
        int dimension = matrix.length;
        for(int i = 0; i < dimension/2; i++) {	// 旋转 数组维度/2 次
        	int[] temp = new int[dimension-2*i];	// 数组长度依次递减2
        	for(int j = 0; j < temp.length; j++) {	// 保存上方数据
				temp[j] = matrix[i][i+j];	// i相当于基址。j为偏移。 基址依次递增1 。
			}
        	for(int j = 0; j < temp.length; j++) {	//为顺时针旋转，转移左侧数据 确保转移时，被转移的数据从头开始读取
        		matrix[i][dimension-1-i -j] = matrix[i+j][i];	//纵坐标为基址， 横坐标为基址 且倒序
        	}
        	for(int j = 0; j < temp.length; j++) {	//转移下侧数据
        		matrix[i+j][i] = matrix[dimension-1-i][i+j];	//左从上到下 等于 下从左到右 左列不变，下行不变 
        	}
        	for(int j = 0; j < temp.length; j++) {	//转移右侧数据
        		matrix[dimension-1-i][i+j] = matrix[dimension-1-i -j][dimension-1-i]; // 下从左到右 等于 右 从下到上。基址为length-1-i
        	}
        	for(int j = 0; j < temp.length; j++) {	// 在右侧存储原上侧数据
        		matrix[dimension-1-i -j][dimension-1-i] = temp[temp.length-1-j];	//右从上到下 等于 上从左到右
        	}
        }
        return;
    }
//    给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
//    思路：定义一个ｍａｐ映射，对每个ｓｔｒｉｎｇ排序，如果已经在ｋｅｙ中，则添加ｖａｌｕｅ，如果未找到，则新增ｋｅｙ及对应ｖａｌｕｅ
//    字符串到数组，对数组排序；　添加新ｋｅｙ的时候同时ｎｅｗ一个包含内容的ｌｉｓｔ；通过一个ｌｉｓｔ新建ｌｉｓｔ．
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strs) {
        	char[] so = s.toCharArray();
        	Arrays.sort(so);
        	String tem = String.valueOf(so);
//        	map.containsKey(tem)?(map.get(tem).contains(s)? :map.put(tem, s)):map.put(tem, s);
        	if(map.containsKey(tem)) {
//        		if(!map.get(tem).contains(s))	//　题目不要求去重，所以直接添加即可．
        			map.get(tem).add(s);
        	}else
        		map.put(tem, new ArrayList<>(Arrays.asList(s)));
        }
        return new ArrayList<>(map.values());
    }
/*
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
有效字符串需满足：
左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。
 */
    public boolean isValid(String s) {
//        boolean res = true
    	if(s == "")
    		return true;
    	Stack<Character> stack = new Stack<>();
    	for(char ch : s.toCharArray()) {
    		if(ch == '(' || ch == '[' || ch == '{') {
    			stack.push(ch);
    		}else {
				if(stack.isEmpty()) {
					return false;	// 当为右括号但是没有左括号匹配，返回ｆａｌｓｅ
				}else  {
					switch (ch) {
					case ')':
						if (stack.pop() != '(') {
							return false;
						}
						break;
					case ']':
						if(stack.pop() != '[') {
							return false;
						}
						break;
					case '}':
						if(stack.pop() != '{') {
							return false;
						}
					default:
						break;
					}
				}
			}
    	}
    	if(!stack.isEmpty())
    		return false;
    	return true;
    }
/*
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 使用回遡法．通过一个函数来判断根据之前的位置，是否可以添加到当前列．如果可以就判断下一行
 */
    /**
     * 只判断列及斜线，因为算法中保证了每行只添加一个数
     * @param loc	保存各行皇后位置。1~9
     * @param row	传入当前行数。只判断当前行之前的皇后要求
     * @return	符合条件为真，
     */
    private boolean hereValid(int[] loc, int row) {
		for (int i = 0; i < row; i++) {	//　和所有之前行的数进行比较
			if(loc[row] == loc[i] || Math.abs(loc[row]-loc[i]) == row-i) {		// 判断是否在同一列及同一斜线 斜线包括左上到右下和右下到左上，所以加绝对值
				return false;
			}
		}
		return true;
	}
    /**
     * 皇后调用函数。 回遡法。
     * @param n	维度
     * @param loc	各行皇后位置
     * @param row	当前行数
     * @param res	结果的返回。是一个列表。
     */
    private void queen(int n, int[] loc, int row,List<List<String>> res) {
		if(row == n) {	// 说明找到了一个满足条件的。
//			添加当前ｌｏｃ
			List<String> list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				char[] temp = new char[n];
				for (int j = 0; j < n; j++) {
					if(loc[i] == j+1) {
						temp[j] = 'Q';
					}else
						temp[j] = '.';
				}
				list.add(String.valueOf(temp));
			}
			res.add(list);
//			return;
			
		}else {
			for (int i = 0; i < n; i++) {	// 保证了循环完所有列
				loc[row] = i+1;
				if(hereValid(loc, row)) {	//先判断，再调用。
					queen(n, loc, row+1,res);
				}else
					loc[row] = 0;
			}
		}
	}
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
//        int i = 0;
        int[] loc = new int[n];
        queen(n, loc, 0,res);
//        无法用for循环做。因为无法递归调用，当前行找到一个满足的后，找下一行，在处理完当前行的这个数后，还得继续处理当前行的剩余数。for循环无法接着处理。
//        for(int i = 0; i < n; i++) {	//遍历第一行中的所有列
//        	for(int row = 0; row < n; row++) {	//遍历所有行
//        		for(int col = 0; col < n; col++) {	//遍历每行的所有列
//        			loc[row] = col+1;
//        			if(hereValid(loc, row)) {	// 如果有效，跳出循环，
//        				break;
//        			}
//        		}
//        		if(!hereValid(loc, row)) {	//说明此行没找到合适的列，之后的行也没有必要寻找，跳出这一级的循环．
//        			loc[row] = 0;		
//        			break;
//        		}
//        	}
//        	
//        }
        return res;
    }
/*
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *  [-2,1,-3,4,-1,2,1,-5,4],	[4,-1,2,1] 
 *  思路，找每个下标开始的数组，如果下一个数之和大于当前max就使对应right变化，直到数组结束
 *  定义一个和(sum)保存到当前为止的和。如果小于0(如果是目前为止最大数，已经在上次循环的时候保存),则需要重新开始最长序列。
 *  如果大于0,说明最长序列可以继续右扩。
 *  每次循环都更新一次最大值
 */
    public int maxSubArray(int[] nums) {
//        int leftMax = 0,rightMax = nums.length-1;
//        if(nums.length == 1)
//        	return nums[0];
//        int max_all = nums[0];
//        for(int i = 0; i < nums.length; i++) {
//        	int left = i,right = i,max = nums[i],temp = nums[i];
//        	for(int j = i+1; j < nums.length; j++) {
//        		temp += nums[j];
//        		if(temp >= max) {
//        			right = j;	//	保存此时下标
//        			max = temp;	//	更新最大值
//        		}
//        	}
//        	if(max >= max_all) {	//如果当前下标开始的最大和大于之前的，进行更新
//        		leftMax = left;
//        		rightMax = right;
//        		max_all = max;
//        	}
//        }
    	int sum = nums[0];
    	int max_all = nums[0];
    	for(int i = 1; i < nums.length; i++) {
    		if(sum < 0) {	// 当sum小于0且小于当前值时，从当前值开始记 
    			sum = nums[i];
    		}else
    			sum = sum + nums[i];
    		max_all = Math.max(sum, max_all);
    	}
        return max_all;
    }
/*
 * 螺旋矩阵
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 */
    public List<Integer> spiralOrder(int[][] matrix) {
    	List<Integer> res = new ArrayList<>();
    	if(matrix.length == 0) return res;
        int m = matrix.length,n = matrix[0].length;
        for(int i = 0; i < (Math.min(m,n)+1)/2; i++) {	// 循环多少圈
        	for(int j = i; j <= n-1 -i; j++) {	// 上 循环列	
        		res.add(matrix[i][j]);		// i行j列
        	}
        	for(int j = i+1; j< m-1 -i; j++) { 		//右	循环行	少打印上下
        		res.add(matrix[j][n-1 -i]);	// j行n-1-i列
        	}
        	if (m-1-i > i) {	// 如果行重复，就不再打印
				for (int j = n - 1 - i; j >= i; j--) { //下		循环列
					res.add(matrix[m - 1 - i][j]); //	m-1-i行j列
				} 
			}
			if (i < n-1-i) {	//如果列重复，就不再打印
				for (int j = m - 1 - i - 1; j > i; j--) { //左 循环行	除去上下两个数
					res.add(matrix[j][i]);
				} 
			}
        }
        return res;
    }
/*
 * 跳跃游戏 类似于之前的最短跳跃问题
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 */
    public boolean canJump(int[] nums) {
        int right = nums.length-1;
//        int left = right;
        int i = right;
//        while(left > 0) {
//        	if(nums[i] >= right-i) {
//        		left = i;
//        	}	
//        	if(i == 0) {	// 当前right位置循环结束
//        		if(right == left) {	// 当一次结束时，right仍然等于left，则说明没有找到可以跳转的下标，返回假
//        			return false;
//        		}
//        		right = left;
//        		i = right;
//        	}
//        	i--;
//        }
        while(i >= 0) {
        	if(nums[i] >= right -i) {	//如果当前下标可以跳转到right，就更新right的值
        		right = i;
        	}
        }
        if(right != 0) return false;	//当整个循环结束时，right没有到达0,说明不可抵达
        return true;
    }
    /**
     * Definition for an interval.
     * public class Interval {
     *     int start;
     *     int end;
     *     Interval() { start = 0; end = 0; }
     *     Interval(int s, int e) { start = s; end = e; }
     * }
     */
    private class Interval {
           int start;
           int end;
           Interval() { start = 0; end = 0; }
           Interval(int s, int e) { start = s; end = e; }
        }
    /*
     * 循环所有的元素。先将当前元素的始末放入min和max变量。如果其余有重合的，就先更新min和max值，然后删除
     * 轮循完其余元素后，如果min和max变化，说明有重合，添加新元素并删除当前元素。
     */
    public List<Interval> merge(List<Interval> intervals) {
		for (int i = 0; i < intervals.size(); i++) {
			int as = intervals.get(i).start, ae = intervals.get(i).end;
			int min = as,max = ae;
			for (int j = 0; j < intervals.size(); j++) {
				if (i == j) {
					continue;
				}
				int bs = intervals.get(j).start, be = intervals.get(j).end;
//                System.out.println(as+" "+ae+"\t"+bs+" "+be+"\t"+min+" "+max);
				if (min >= bs && min <= be || max >= bs && max <= be || bs >= min && bs <=max || be >= min && be <= max) { //i 和 j 重合
					min = Math.min(min, bs);	// 保存最大值和最小值
					max = Math.max(max, be);
					intervals.remove(j);	// 移除当前j
//                    System.out.println("removed "+j+"\t"+min+" "+max);
					j--;	// 下轮需重新判断当前元素

				} 
			}
			if (as != min || ae != max) {
				intervals.remove(i);
				intervals.add(i, new Interval(min, max));
//                System.out.println("added "+i);
				i--;	// 添加后，当前元素已经改变，需要重新判断
			}
		}
		return intervals;
    }
/*
 * 插入区间
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 思路：按照起始端点排序，所以是插入在第一个开头大于end之前。原来的数组不重合，所以只需要考虑new和其的重合即可
 * 给定一个新序列，如果不重合，直接添加。如果重合，重新计算start和end值。碰到第一个开头大于end的值插入new。如果到循环结束都末插入，说明应该在最后添加
 */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        int start = newInterval.start, end = newInterval.end;
        int ins = 0;	// 用来使在序列中只插入一次new值
        for(int i = 0; i < intervals.size(); i++) {
        	int as = intervals.get(i).start, ae = intervals.get(i).end;
        	if(as > end && ins < 1) {	// 用来插入序列，且只插入一次
        		ins++;
        		res.add(new Interval(start,end));	// 如果碰到了大于的，说明重合的已经处理完毕。在当前res末尾插入即可
        	}
        	if(as > end || ae < start) {	//如果开头大于插入的结尾 或者 结尾小于插入的开头，则肯定不重合
        		res.add(intervals.get(i));
        	}else {
	        	start = Math.min(start, as);	// 获得边界
	        	end = Math.max(end, ae);
        	}
        }
        if (ins < 1) {
			res.add(new Interval(start, end));	// 如果在循环中未插入。则此时在末尾插入
		}
		return res;
    }    
/*
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 * 如果不存在最后一个单词，请返回 0 。
 * while 循环不能偷懒直接 len--
 */
    public int lengthOfLastWord(String s) {
        if(s == null || s.equals("")) return 0;
        int res = 0;
        int len = s.length()-1;
        while(len >= 0 && s.charAt(len) == ' ') {	// 先去除末尾的空格
        	len--;
        }
        while(len >= 0 && s.charAt(len--) != ' ') {	// 找字符数
        	res++;
        }
        return res;
    }
/*
 * 螺旋矩阵II
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
// * 思路是循环添加n^2次。关键是如何处理转向。
 * 需要循环(n+1)/2次。且判断下和左不能和上和右重复
 * 直接利用之前螺旋矩阵遍历的方法
 */
    public int[][] generateMatrix(int n) {
        int num = 0;
        int[][] res = new int[n][n];
        for(int i = 0; i < (n+1)/2; i++) {
        	for(int j = i; j <= n-1 -i; j++) {	// 上 循环列	
        		res[i][j] = ++num;// i行j列
        	}
        	for(int j = i+1; j< n-1 -i; j++) { 		//右	循环行	少打印上下
        		res[j][n-1 -i] = ++num;	// j行n-1-i列
        	}
        	if (n-1-i > i) {	// 如果行重复，就不再打印
				for (int j = n - 1 - i; j >= i; j--) { //下		循环列
					res[n - 1 - i][j] = ++num; //	n-1-i行j列
				} 
			}
			if (i < n-1-i) {	//如果列重复，就不再打印
				for (int j = n - 1 - i - 1; j > i; j--) { //左 循环行	除去上下两个数
					res[j][i] = ++num;
				} 
			}
        }
        return res;
    }
    
    
    
    
    
    
    
    
    
    
}