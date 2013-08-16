package net.ziwei.algorithm;

import java.util.Calendar;
import java.util.Date;

import net.ziwei.algorithm.biao.LiuShiJiaZiBiao;
import net.ziwei.algorithm.biao.MingShenZhuBiao;
import net.ziwei.algorithm.biao.ShiChenBiao;

//基本信息
public class BasicInfo {
	public Date yangLi;      //阳历生日，暂时不可用
	public Date yinLi;       //阴历生日
	public int year;		 //年
	public int month;		 //月
	public int day;			 //日
	public int hour;		 //时
	public int tianGan;		 //生年天干
	public int diZhi;		 //生年地支
	public int shiChen;      //时辰
	public int age;			 //阴历年龄
	public int sex;          //性别，0是男，1是女
	public int yinYang;      //阴阳,0是阳，1是阴
	public String wuXing;    //五行局
	public int wuXingNum;    //五行对应的起始年
	public int daXian;       //大限宫支
	public String mingZhu;   //命主
	public String shenZhu;   //身主
	public int nowTianGan;   //当年天干
	public int nowDiZhi;     //当年地支
	
	public BasicInfo(Date birthday, int s, int curYear){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(birthday);
		yinLi = birthday;
		year = calendar.get(Calendar.YEAR);  
		month = calendar.get(Calendar.MONTH);  
		day = calendar.get(Calendar.DAY_OF_MONTH);  
		hour = calendar.get(Calendar.HOUR_OF_DAY); 
		sex = s;

		age = curYear - year + 1;

		shiChen = ShiChenBiao.getShiChenByHour(hour);
		tianGan = LiuShiJiaZiBiao.getTianGanByYear(year);
		diZhi = LiuShiJiaZiBiao.getDiZhiByYear(year);
		yinYang = LiuShiJiaZiBiao.getYinYangByYear(year);
		
		mingZhu = MingShenZhuBiao.mingZhuBiao[diZhi];
		shenZhu = MingShenZhuBiao.shenZhuBiao[diZhi];
		nowTianGan = LiuShiJiaZiBiao.getTianGanByYear(curYear);
		nowDiZhi = LiuShiJiaZiBiao.getDiZhiByYear(curYear);
	}
	
	public String toString(){
		String str = "年:"+year+"\n" +
					 "月（显示时加1）:"+(month+1)+"\n" +
					 "日:"+day+"\n" +
					 "时:"+hour+"\n" +
					 "年龄:"+age+"\n" + 
					 "天干:"+getTianGan()+"\n" + 
					 "地支:"+getDiZhi()+"\n" + 
					 "时辰:"+getShiChen()+"\n" + 
					 "性别:"+getSex()+"\n" + 
		 			 "阴阳:"+getYinYang()+"\n" +
					 "五行局:"+wuXing+wuXingNum+"局\n" + 
					 "命主:"+mingZhu+"\n" + 
					 "身主:"+shenZhu+"\n" +
					 "大限:"+daXian+"\n";
		return str;
	}
	
	public String getTianGan(){
		return Pan.tianGan[tianGan];
	}
	
	public String getDiZhi(){
		return Pan.diZhi[diZhi];
	}
	
	public String getNowTianGan(){
		return Pan.tianGan[nowTianGan];
	}
	
	public String getNowDiZhi(){
		return Pan.diZhi[nowDiZhi];
	}
	
	public String getShiChen(){
		return Pan.diZhi[shiChen];
	}
	
	public String getSex(){
		return sex == 0?"男":"女";
	}
	
	public String getYinYang(){
		return yinYang == 0?"阳":"阴";
	}
	
	public String getWuXingJuNum(){
		String num = "";
		if(wuXingNum == 2) num = "二";
		else if(wuXingNum == 3) num = "三";
		else if(wuXingNum == 4) num = "四";
		else if(wuXingNum == 5) num = "五";
		else if(wuXingNum == 6) num = "六";
		return num;
	}
}
