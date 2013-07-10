package net.ziwei.algorithm;

public class Block{
	public String gongGan;	  //宫干
	public String gongZhi;    //宫支
	public String shiErGong;  //十二宫
	
	public String toString(){
		return "宫干:"+gongGan+"\n"+
			   "宫支:"+gongZhi+"\n"+
			   "十二宫:"+shiErGong+"\n";
	}
}
