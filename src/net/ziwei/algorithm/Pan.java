package net.ziwei.algorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import net.ziwei.algorithm.biao.GanXiZhuXingBiao;
import net.ziwei.algorithm.biao.GongGanBiao;
import net.ziwei.algorithm.biao.MingShenGongBiao;
import net.ziwei.algorithm.biao.ShiXiZhuXingBiao;
import net.ziwei.algorithm.biao.WuXingJuBiao;
import net.ziwei.algorithm.biao.YueXiZhuXingBiao;
import net.ziwei.algorithm.biao.ZhengXingBiao;
import net.ziwei.algorithm.biao.ZhiXiZhuXingBiao;
import net.ziwei.algorithm.biao.ZiWeiBiao;


//命盘
public class Pan {
	public BasicInfo basicInfo;
	public ArrayList<Block> blockList = new ArrayList<Block>();	

	public static String[] tianGanTable = {"甲","乙","丙","丁","戊","己","庚","辛","壬","癸"};
										  // 0,  1,   2,  3,  4,  5,  6,  7,   8,  9
	public static String[] diZhiTable = {"子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥"};
										//0,  1,  2,   3,  4,   5,  6,  7,  8,  9,   10,  11
	
	public int getIndexByDiZhi(String diZhi){
		for(int i=0; i<diZhiTable.length; i++){
			if(diZhiTable[i].equals(diZhi)) return i;
		}
		return -1;
	}
	
	public Pan(Date birthday, int sex){		
		basicInfo = new BasicInfo(birthday, sex);
		
		//排盘准备，按子,丑……的顺序开始设宫支
		for(int i=0; i<diZhiTable.length; i++){
			Block block = new Block();
			block.gongZhi = i;
			blockList.add(block);
		}
		//安命身宫		
		int mingGong = MingShenGongBiao.getMingGongByMonthAndHour(basicInfo.month, basicInfo.shiChen);
		blockList.get(mingGong).shiErGong = "命宫";
		//安十二宫
		String[] shiErGong = {"父母","福德","田宅","事业","交友","迁移","疾厄","财帛","子女","夫妻","兄弟"};
		for(int i=0; i<11; i++){
			int index = (mingGong+i+1)%12;
			blockList.get(index).shiErGong = shiErGong[i];
		}
		
		//定十二宫天干
		int tianGanIndex = basicInfo.tianGan%5;
		for(int i=0; i<blockList.size(); i++){
			blockList.get(i).gongGan = GongGanBiao.getGongGanByTianZhiAndGongZhi(i, tianGanIndex);
		}
		//定五行局
		basicInfo.wuXing = WuXingJuBiao.getWuXingJuByGongGanZhi(blockList.get(mingGong).gongGan, blockList.get(mingGong).gongZhi);
		basicInfo.wuXingNum = WuXingJuBiao.getWuXingNumByWuXing(basicInfo.wuXing);
		//起大限
		for(int i=0; i<blockList.size(); i++){
			int index;
			if(basicInfo.sex == 1)
			  index = (mingGong + i)%12;
			else
			  index = (mingGong - i + 12)%12;
			blockList.get(index).startDaXian = basicInfo.wuXingNum + i*10;
			blockList.get(index).endDaXian = basicInfo.wuXingNum + (i+1)*10 - 1;
		}
		//安紫微星
		int ziwei = ZiWeiBiao.getZiWeiByWuXingAndDay(basicInfo.wuXingNum, basicInfo.day);
		blockList.get(ziwei).zhengYaoList.add("紫微");
		//安其他正星
		int tianJi = ZhengXingBiao.tianJiBiao[ziwei];
		blockList.get(tianJi).zhengYaoList.add("天机");
		int taiYang = ZhengXingBiao.taiYangBiao[ziwei];
		blockList.get(taiYang).zhengYaoList.add("太阳");
		int wuQu = ZhengXingBiao.wuQuBiao[ziwei];
		blockList.get(wuQu).zhengYaoList.add("武曲");
		int tianTong = ZhengXingBiao.tianTongBiao[ziwei];
		blockList.get(tianTong).zhengYaoList.add("天同");
		int lianZhen = ZhengXingBiao.lianZhenBiao[ziwei];
		blockList.get(lianZhen).zhengYaoList.add("廉贞");
		int tianFu = ZhengXingBiao.tianFuBiao[ziwei];
		blockList.get(tianFu).zhengYaoList.add("天府");
		int taiYin = ZhengXingBiao.taiYinBiao[ziwei];
		blockList.get(taiYin).zhengYaoList.add("太阴");
		int tanLang = ZhengXingBiao.tanLangBiao[ziwei];
		blockList.get(tanLang).zhengYaoList.add("贪狼");
		int juMen = ZhengXingBiao.juMenBiao[ziwei];
		blockList.get(juMen).zhengYaoList.add("巨门");
		int tianXiang = ZhengXingBiao.tianXiangBiao[ziwei];
		blockList.get(tianXiang).zhengYaoList.add("天相");
		int tianLiang = ZhengXingBiao.tianLiangBiao[ziwei];
		blockList.get(tianLiang).zhengYaoList.add("天梁");
		int qiSha = ZhengXingBiao.qiShaBiao[ziwei];
		blockList.get(qiSha).zhengYaoList.add("七杀");
		int poJun = ZhengXingBiao.poJunBiao[ziwei];
		blockList.get(poJun).zhengYaoList.add("破军");
		//安干系诸星
		int luCun = GanXiZhuXingBiao.luCunBiao[basicInfo.tianGan];
		blockList.get(luCun).fuYaoList.add("禄存");
		int qingYang = GanXiZhuXingBiao.qingYangBiao[basicInfo.tianGan];
		blockList.get(qingYang).fuYaoList.add("擎羊");
		int tuoLuo = GanXiZhuXingBiao.tuoLuoBiao[basicInfo.tianGan];
		blockList.get(tuoLuo).fuYaoList.add("陀罗");
		int tianKui = GanXiZhuXingBiao.tianKuiBiao[basicInfo.tianGan];
		blockList.get(tianKui).fuYaoList.add("天魁");
		int tianYue = GanXiZhuXingBiao.tianYueBiao[basicInfo.tianGan];
		blockList.get(tianYue).fuYaoList.add("天钺");
		int tianGuan = GanXiZhuXingBiao.tianGuanBiao[basicInfo.tianGan];
		blockList.get(tianGuan).zaYaoList.add("天官");
		int tianChu = GanXiZhuXingBiao.tianChuBiao[basicInfo.tianGan];
		blockList.get(tianChu).zaYaoList.add("天厨");
		int jieKong1 = GanXiZhuXingBiao.jieKongBiao1[basicInfo.tianGan];
		blockList.get(jieKong1).zaYaoList.add("截空");
		int jieKong2 = GanXiZhuXingBiao.jieKongBiao2[basicInfo.tianGan];
		blockList.get(jieKong2).zaYaoList.add("截空");
		//安支系诸星
		int tianMa = ZhiXiZhuXingBiao.tianMaBiao[basicInfo.diZhi];
		blockList.get(tianMa).fuYaoList.add("天马");
		int tianKong = ZhiXiZhuXingBiao.tianKongBiao[basicInfo.diZhi];
		blockList.get(tianKong).zaYaoList.add("天空");
		int tianKu = ZhiXiZhuXingBiao.tianKuBiao[basicInfo.diZhi];
		blockList.get(tianKu).zaYaoList.add("天哭");
		int tianXu = ZhiXiZhuXingBiao.tianXuBiao[basicInfo.diZhi];
		blockList.get(tianXu).zaYaoList.add("天虚");
		int longChi = ZhiXiZhuXingBiao.longChiBiao[basicInfo.diZhi];
		blockList.get(longChi).zaYaoList.add("龙池");
		int fengGe = ZhiXiZhuXingBiao.fengGeBiao[basicInfo.diZhi];
		blockList.get(fengGe).zaYaoList.add("凤阁");
		int hongLuan = ZhiXiZhuXingBiao.hongLuanBiao[basicInfo.diZhi];
		blockList.get(hongLuan).zaYaoList.add("红鸾");
		int tianXi = ZhiXiZhuXingBiao.tianXiBiao[basicInfo.diZhi];
		blockList.get(tianXi).zaYaoList.add("天喜");
		int guChen = ZhiXiZhuXingBiao.guChenBiao[basicInfo.diZhi];
		blockList.get(guChen).zaYaoList.add("孤辰");
		int guaSu = ZhiXiZhuXingBiao.guaSuBiao[basicInfo.diZhi];
		blockList.get(guaSu).zaYaoList.add("寡宿");
		//安月系诸星
		int zuoFu = YueXiZhuXingBiao.zuoFuBiao[basicInfo.month];
		blockList.get(zuoFu).fuYaoList.add("左辅");
		int youBi = YueXiZhuXingBiao.youBiBiao[basicInfo.month];
		blockList.get(youBi).fuYaoList.add("右弼");
		int tianXing = YueXiZhuXingBiao.tianXingBiao[basicInfo.month];
		blockList.get(tianXing).zaYaoList.add("天刑");
		int tianYao = YueXiZhuXingBiao.tianYaoBiao[basicInfo.month];
		blockList.get(tianYao).zaYaoList.add("天姚");
		int jieShen = YueXiZhuXingBiao.jieShenBiao[basicInfo.month];
		blockList.get(jieShen).zaYaoList.add("解神");
		int tianWu = YueXiZhuXingBiao.tianWuBiao[basicInfo.month];
		blockList.get(tianWu).zaYaoList.add("天巫");
		int tianMoon = YueXiZhuXingBiao.tianYueBiao[basicInfo.month];
		blockList.get(tianMoon).zaYaoList.add("天月");
		int yinSha = YueXiZhuXingBiao.yinShaBiao[basicInfo.month];
		blockList.get(yinSha).zaYaoList.add("阴煞");
		//安年月系诸星
		int yueDe = ZhiXiZhuXingBiao.yueDeBiao[basicInfo.diZhi];
		blockList.get(yueDe).zaYaoList.add("月德");
		int tianDe = ZhiXiZhuXingBiao.tianDeBiao[basicInfo.diZhi];
		blockList.get(tianDe).zaYaoList.add("天德");
		int nianJie = ZhiXiZhuXingBiao.nianJieBiao[basicInfo.diZhi];
		blockList.get(nianJie).zaYaoList.add("年解");
		int jieSha = ZhiXiZhuXingBiao.jieShaBiao[basicInfo.diZhi];
		blockList.get(jieSha).zaYaoList.add("劫煞");
		int daHao = ZhiXiZhuXingBiao.daHaoBiao[basicInfo.diZhi];
		blockList.get(daHao).zaYaoList.add("大耗");
		int xianChi = ZhiXiZhuXingBiao.xianChiBiao[basicInfo.diZhi];
		blockList.get(xianChi).zaYaoList.add("咸池");	
		int huaGai = ZhiXiZhuXingBiao.huaGaiBiao[basicInfo.diZhi];
		blockList.get(huaGai).zaYaoList.add("华盖");
		int poSui = ZhiXiZhuXingBiao.poSuiBiao[basicInfo.diZhi];
		blockList.get(poSui).zaYaoList.add("破碎");
		int feiLian = ZhiXiZhuXingBiao.feiLianBiao[basicInfo.diZhi];
		blockList.get(feiLian).zaYaoList.add("蜚廉");	
		//安天寿星
		int shenGong = MingShenGongBiao.getShenGongByMonthAndHour(basicInfo.month, basicInfo.shiChen);
		int tianShou = (shenGong + basicInfo.diZhi)%12;
		blockList.get(tianShou).zaYaoList.add("天寿");
		//安天才星
		int tianCai = (mingGong + basicInfo.diZhi)%12;
		blockList.get(tianCai).zaYaoList.add("天才");
		//安时系诸星
		int wenChang = ShiXiZhuXingBiao.wenChangBiao[basicInfo.shiChen];
		blockList.get(wenChang).fuYaoList.add("文昌");
		int wenQu = ShiXiZhuXingBiao.wenQuBiao[basicInfo.shiChen];
		blockList.get(wenQu).fuYaoList.add("文曲");
		int diJie = ShiXiZhuXingBiao.diJieBiao[basicInfo.shiChen];
		blockList.get(diJie).zaYaoList.add("地劫");
		int diKong = ShiXiZhuXingBiao.diKongBiao[basicInfo.shiChen];
		blockList.get(diKong).zaYaoList.add("地空");
		int taiFu = ShiXiZhuXingBiao.taiFuBiao[basicInfo.shiChen];
		blockList.get(taiFu).zaYaoList.add("台辅");
		int fengGao = ShiXiZhuXingBiao.fengGaoBiao[basicInfo.shiChen];
		blockList.get(fengGao).zaYaoList.add("封诰");
		//安火星铃星
		int huoXing = ShiXiZhuXingBiao.huoXingBiao[basicInfo.diZhi%4][basicInfo.shiChen];
		blockList.get(huoXing).fuYaoList.add("火星");
		int lingXing = ShiXiZhuXingBiao.lingXingBiao[basicInfo.diZhi%4][basicInfo.shiChen];
		blockList.get(lingXing).fuYaoList.add("铃星");
		//安三台八座
		int sanTai = (zuoFu + basicInfo.day - 1)%12;
		blockList.get(sanTai).zaYaoList.add("三台");
		int baZuo = (youBi - basicInfo.day + 1 + 36)%12;
		blockList.get(baZuo).zaYaoList.add("八座");
		//安恩光天贵
		int enGuang = (wenChang + basicInfo.day - 2)%12;
		blockList.get(enGuang).zaYaoList.add("恩光");
		int tianGui = (wenQu + basicInfo.day - 2)%12;
		blockList.get(tianGui).zaYaoList.add("天贵");	
		//安天伤天使
		int tianShang = 0, tianShi = 0;
		if((basicInfo.yinYang == 0 && basicInfo.sex == 0) || (basicInfo.yinYang == 1 && basicInfo.sex == 1)){
			tianShang = (mingGong + 5)%12;
			tianShi = (mingGong + 7)%12;
		}else{
			tianShang = (mingGong + 7)%12;
			tianShi = (mingGong + 5)%12;
		}
		blockList.get(tianShang).zaYaoList.add("天伤");
		blockList.get(tianShi).zaYaoList.add("天使");
	}
	
	public static void main(String[] args){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		try {
			Date birthday = formatter.parse("1985:12:11 05:00:00");	
			//Date birthday = formatter.parse("1987:9:15 05:00:00");
			Pan pan = new Pan(birthday, 1);
			System.out.println(pan.basicInfo);
			for(int i=0; i<pan.blockList.size(); i++){
				System.out.println(pan.blockList.get(i));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	
	}
}
