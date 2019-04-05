import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
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
//    在计算完成后，依次处理进位 未完成
    public String multiply(String num1, String num2) {
//        int len1 = num1.length();
//        int len2 = num2.length();
//        if(len1 == 0 || len2 == 0) {
//        	return "";
//        }
//        int[] sum = new int[len1 + len2 + 2];
//        for (int i = 0; i < len1; i++) {
//        	for (int j = 0; j < len2; j++) {
//				sum[i + j] += (num1.charAt(i)-'0') * (num2.charAt(i) - '0');	//倒序存放
//			}
//		}
////        先处理进位
//        int i = sum.length-1,count = 0;
//        while(i>0 && sum[i] >= 0 ) {	//
//        	count++;
//        	sum[i] %= 10;
//        	sum[i-1] += sum[i]/10;
//        	i--;
//        }
//        if(sum[count] > 0) {	//判断最后一位。有效长度为count
//        	count++;
//        }
//        for (int j = 0; j < count; j++) {
//			sum[j] += '0';
//		}
//        String res = new String(sum, 0, count);
//        return res;
    	return "";
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
    public ArrayList<ArrayList<Integer>> permute(int[] nums) {
    	ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    	
    	
    	return res;
    }



}