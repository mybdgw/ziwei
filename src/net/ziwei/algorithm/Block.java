package net.ziwei.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Block{
	public int gongGan;	       //宫干
	public int gongZhi;        //宫支
	public String shiErGong;   //十二宫
	public int startDaXian;    //大限起始年
	public int endDaXian;      //大限结束年
	public List<String> zhengYaoList = new ArrayList<String>();    //正星
	public List<String> fuYaoList = new ArrayList<String>(); //辅星
	public List<String> zaYaoList = new ArrayList<String>(); //杂星
	public String changShengShen;  //长生十二神
	public String boShiShen;       //博士十二神
	public String taiSuiShen;      //太岁十二神
	
	
	public String toString(){
		 String str = "宫干:"+Pan.tianGan[gongGan]+"\n" +
				      "宫支:"+Pan.diZhi[gongZhi]+"\n" +
			          "十二宫:"+shiErGong+"\n" +
			          "大限:"+startDaXian+"-"+endDaXian +"\n" +
			          "正曜:";
		 for(int i=0; i<zhengYaoList.size(); i++){
			 str += zhengYaoList.get(i)+" ";
		 }
		 str += "\n辅曜:";
		 for(int i=0; i<fuYaoList.size(); i++){
			 str += fuYaoList.get(i)+" ";
		 }
		 str += "\n杂曜:";
		 for(int i=0; i<zaYaoList.size(); i++){
			 str += zaYaoList.get(i)+" ";
		 }
		 str += "\n长生十二神:" +changShengShen +"\n" +
				 "博士十二神:" +boShiShen +"\n" +
				 "太岁十二神:" +taiSuiShen +"\n";
		 return str;
	}
}
