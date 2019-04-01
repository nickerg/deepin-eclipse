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
}