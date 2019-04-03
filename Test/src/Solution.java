import java.util.HashMap;
import java.util.Map;

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
	
	

}