package www.walk.NextData;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 将某个时间以固定格式转化成字符串
 * @author dnd
 *
 */
public class DateFormat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date now = new Date();
		System.out.println(date2FormatStr(now));
		
	}
	public static String date2FormatStr(Date date) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("y-M-d H:m:s");
		//一个与语言环境有关的方式来格式化和解析日期的具体类
		
		String string = sDateFormat.format(date);
		return string;
	}

}
