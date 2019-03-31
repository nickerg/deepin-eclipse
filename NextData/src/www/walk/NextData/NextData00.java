package www.walk.NextData;

import java.util.Date;

//import java.util.Date;

public class NextData00 {
/**
 * 根据当前时间，获取下一天时间。不判断是否是闰年，是否月底等
 * 通过距1970年1月1日，0时0分0秒的毫秒数
 * @param args
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date now = new Date();
		System.out.println("now:"+now);
		System.out.println("next:" +getNextDate(now));
	}
	public static Date getNextDate(Date now) {
		long addTime = 1;	//基数
		addTime *= 1;		//1天
		addTime *= 24;		//24小时
		addTime *= 60;		//60分钟
		addTime *= 60;		//60秒
		addTime *= 1000;	//1000毫秒
		Date next = new Date(now.getTime() + addTime);
		return next;
	}

}
