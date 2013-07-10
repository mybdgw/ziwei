package net.ziwei.algorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import net.ziwei.algorithm.biao.GongGanBiao;
import net.ziwei.algorithm.biao.MingShenGongBiao;
import net.ziwei.algorithm.biao.WuXingJuBiao;


//命盘
public class Pan {
	public BasicInfo basicInfo;
	public ArrayList<Block> blockList = new ArrayList<Block>();	
	
	public HashMap<String, Integer> tianGanMap = new HashMap<String, Integer>();
	static public String[] tianGanTable = {"甲","乙","丙","丁","戊","己","庚","辛","壬","癸"};
	public HashMap<String, Integer> diZhiMap = new HashMap<String, Integer>();
	static public String[] diZhiTable = {"子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥"};
	
	public Pan(Date birthday, int sex){
		for(int i=0; i<tianGanTable.length; i++){
			tianGanMap.put(tianGanTable[i], i);
		}
		for(int i=0; i<diZhiTable.length; i++){
			diZhiMap.put(diZhiTable[i], i);
		}
		
		basicInfo = new BasicInfo(birthday, sex);
		
		//排盘准备，设各宫时辰
		for(int i=0; i<diZhiTable.length; i++){
			Block block = new Block();
			block.gongZhi = diZhiTable[i];
			blockList.add(block);
		}
		//安命身宫
		int hour = diZhiMap.get(basicInfo.shiChen);				
		String mingGong = MingShenGongBiao.getMingGongByMonthAndHour(basicInfo.month, hour);
		int mingGongIndex = diZhiMap.get(mingGong);
		blockList.get(mingGongIndex).shiErGong = "命宫";
		//安十二宫
		String[] shiErGong = {"父母","福德","田宅","事业","交友","迁移","疾厄","财帛","子女","夫妻","兄弟"};
		for(int i=0; i<11; i++){
			int index = (mingGongIndex+i+1)%12;
			blockList.get(index).shiErGong = shiErGong[i];
		}
		
		//定十二宫天干
		int tianGanIndex = tianGanMap.get(basicInfo.tianGan)%5;
		for(int i=0; i<blockList.size(); i++){
			blockList.get(i).gongGan = GongGanBiao.getGongGanByTianZhiAndGongZhi(i, tianGanIndex);
		}
		//定五行局
		int gonggan = tianGanMap.get(blockList.get(mingGongIndex).gongGan)%5;
		int gongzhi = diZhiMap.get(blockList.get(mingGongIndex).gongZhi)%6;
		basicInfo.wuXingJu = WuXingJuBiao.getWuXingJuByGongGanZhi(gongzhi, gonggan);
		
		System.out.println(basicInfo);
		for(int i=0; i<blockList.size(); i++){
			System.out.println(blockList.get(i));
		}
	}
	
	public static void main(String[] args){
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		try {
			Date birthday = formatter.parse("1985:12:11 05:00:00");			
			Pan pan = new Pan(birthday, 1);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	
	}
}
