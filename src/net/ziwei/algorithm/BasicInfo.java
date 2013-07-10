package net.ziwei.algorithm;

import java.util.Calendar;
import java.util.Date;

import net.ziwei.algorithm.biao.LiuShiJiaZiBiao;
import net.ziwei.algorithm.biao.ShiChenBiao;

//基本信息
public class BasicInfo {
	public Date yangLi;      //阳历生日，暂时不可用
	public Date yinLi;       //阴历生日
	int year;				 //年
	int month;				 //月
	int day;				 //日
	int hour;				 //时
	String tianGan;			 //年份天干
	String diZhi;			 //年份地支
	String shiChen;          //时辰
	int age;				 //阴历年龄
	public int sex;          //性别，0是男，1是女
	public int yinYang;      //阴阳,0是阳，1是阴
	public String wuXingJu;  //五行局
	public int daXian;       //大限
	
	public BasicInfo(Date birthday, int s){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(birthday);
		yinLi = birthday;
		year = calendar.get(Calendar.YEAR);  
		month = calendar.get(Calendar.MONTH);  
		day = calendar.get(Calendar.DAY_OF_MONTH);  
		hour = calendar.get(Calendar.HOUR_OF_DAY); 
		sex = s;
		
		Date curDate = new Date(System.currentTimeMillis());
		calendar.setTime(curDate);
		int curYear = calendar.get(Calendar.YEAR);
		age = curYear - year + 1;
		
		shiChen = ShiChenBiao.getShiChenByHour(hour);
		tianGan = LiuShiJiaZiBiao.getTianGanByYear(year);
		diZhi = LiuShiJiaZiBiao.getDiZhiByYear(year);
		yinYang = LiuShiJiaZiBiao.getYinYangByYear(year);
	}
	
	public String toString(){
		String str = "年:"+year+"\n" +
					 "月（显示时加1）:"+month+"\n" +
					 "日:"+day+"\n" +
					 "时:"+hour+"\n" +
					 "年龄:"+age+"\n" + 
					 "天干:"+tianGan+"\n" + 
					 "地支:"+diZhi+"\n" + 
					 "时辰:"+shiChen+"\n" + 
					 "性别:"+(sex == 0?"男":"女")+"\n" + 
		 			 "阴阳:"+(yinYang == 0?"阳":"阴")+"\n" +
					 "五行局:"+wuXingJu+"\n";
		return str;
	}
}
