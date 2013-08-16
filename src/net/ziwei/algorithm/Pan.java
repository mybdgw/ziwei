package net.ziwei.algorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import net.ziwei.algorithm.biao.ChangQuBiao;
import net.ziwei.algorithm.biao.GanXiZhuXingBiao;
import net.ziwei.algorithm.biao.GongGanBiao;
import net.ziwei.algorithm.biao.MiaoXianBiao;
import net.ziwei.algorithm.biao.MingShenGongBiao;
import net.ziwei.algorithm.biao.ShiXiZhuXingBiao;
import net.ziwei.algorithm.biao.SiHuaXingBiao;
import net.ziwei.algorithm.biao.WuXingJuBiao;
import net.ziwei.algorithm.biao.XunKongXingBiao;
import net.ziwei.algorithm.biao.YueXiZhuXingBiao;
import net.ziwei.algorithm.biao.ZhengXingBiao;
import net.ziwei.algorithm.biao.ZhiXiZhuXingBiao;
import net.ziwei.algorithm.biao.ZiWeiBiao;


//命盘，输入为生辰八字，和性别
public class Pan {
	public BasicInfo basicInfo;
	public ArrayList<Block> blockList = new ArrayList<Block>();	

	public static final String[] tianGan = {"甲","乙","丙","丁","戊","己","庚","辛","壬","癸"};
									       // 0,  1,   2,  3,  4,  5,  6,  7,   8,  9
	public static final String[] diZhi = {"子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥"};
								         //0,  1,  2,   3,  4,   5,  6,  7,  8,  9,   10,  11
	public static final String[] shiErGong = {"父母宫","福德宫","田宅宫","事业宫","交友宫","迁移宫","疾厄宫","财帛宫","子女宫","夫妻宫","兄弟宫"};
	public static final String[] changSheng = {"长生","沐浴","冠带","临官","帝旺","衰","病","死","墓","绝","胎","养"};
	public static final String[] boShi = {"博士","力士","青龙","小耗","将军","奏书","蜚廉","喜神","病符","大耗","伏兵","官符"};
	public static final String[] taiSui = {"太岁","晦气","丧门","贯索","官符","小耗","岁破","龙德","白虎","天德","吊客","病符"};
	public static final String[] jiangQian = {"将星","攀鞍","岁驿","息神","华盖","劫煞","灾煞","天煞","指背","咸池","月煞","亡神"};
	
	public static final String[] siHua = {"禄","权","科","忌"};
										 //0    1   2   3
	public HashMap<String, Yao> xingMap = new HashMap<String, Yao>();  //星曜名称与对应宫位

	public int getIndexByDiZhi(String dizhi){
		for(int i=0; i<diZhi.length; i++){
			if(diZhi[i].equals(diZhi)) return i;
		}
		return -1;
	}
	
	public Pan(Date birthday, int sex, int curYear){		
		basicInfo = new BasicInfo(birthday, sex, curYear);
		//安十二宫地支
		for(int i=0; i<diZhi.length; i++){
			Block block = new Block();
			block.gongZhi = i;
			blockList.add(block);
		}
		//定十二宫天干
		int tianGanIndex = basicInfo.tianGan%5;
		for(int i=0; i<blockList.size(); i++){
			blockList.get(i).gongGan = GongGanBiao.getGongGanByTianZhiAndGongZhi(i, tianGanIndex);
		}	
		//安命身宫		
		int mingGong = MingShenGongBiao.getMingGongByMonthAndHour(basicInfo.month, basicInfo.shiChen);
		//安十二宫		
		for(int i=0; i<11; i++){
			int index = (mingGong+i+1)%12;
			blockList.get(index).shiErGong = shiErGong[i];
		}
		//定五行局
		basicInfo.wuXing = WuXingJuBiao.getWuXingJuByGongGanZhi(blockList.get(mingGong).gongGan, blockList.get(mingGong).gongZhi);
		basicInfo.wuXingNum = WuXingJuBiao.getWuXingNumByWuXing(basicInfo.wuXing);
		blockList.get(mingGong).shiErGong = "命宫";
		basicInfo.daXian = (1 + mingGong)%12;
		//起大限
		for(int i=0; i<blockList.size(); i++){
			int index;
			if(basicInfo.sex == 1)
			  index = (mingGong + i)%12;
			else
			  index = (mingGong - i + 12)%12;
			blockList.get(index).startDaXian = basicInfo.wuXingNum + i*10;
			blockList.get(index).endDaXian = basicInfo.wuXingNum + (i+1)*10 - 1;
			int age = basicInfo.age;		
			if(blockList.get(index).startDaXian <= age && blockList.get(index).endDaXian >= age){
				basicInfo.daXian = index;
			}
		}
		//安大运十二宫
		blockList.get(basicInfo.daXian).daYunShiErGong = "命宫";
		for(int i=0; i<blockList.size()-1; i++){
			int index = (basicInfo.daXian + i + 1)%12;
			blockList.get(index).daYunShiErGong = shiErGong[i];
		}

		//安紫微星
		int ziWei = ZiWeiBiao.ziWeiBiao[basicInfo.day-1][basicInfo.wuXingNum-2];
		blockList.get(ziWei).zhengYaoList.add("紫微");
		Yao ziWeiYao = new Yao(ziWei);
		ziWeiYao.miaoXian = MiaoXianBiao.ziWeiBiao[ziWei];
		xingMap.put("紫微", ziWeiYao);
		//安其他正星
		int tianJi = ZhengXingBiao.tianJiBiao[ziWei];
		blockList.get(tianJi).zhengYaoList.add("天机");
		Yao tianJiYao = new Yao(tianJi);
		tianJiYao.miaoXian = MiaoXianBiao.tianJiBiao[tianJi];
		xingMap.put("天机", tianJiYao);
		
		int taiYang = ZhengXingBiao.taiYangBiao[ziWei];
		blockList.get(taiYang).zhengYaoList.add("太阳");
		Yao taiYangYao = new Yao(taiYang);
		taiYangYao.miaoXian = MiaoXianBiao.taiYangBiao[taiYang];
		xingMap.put("太阳", taiYangYao);
		
		int wuQu = ZhengXingBiao.wuQuBiao[ziWei];
		blockList.get(wuQu).zhengYaoList.add("武曲");
		Yao wuQuYao = new Yao(wuQu);
		wuQuYao.miaoXian = MiaoXianBiao.wuQuBiao[wuQu];
		xingMap.put("武曲", wuQuYao);
		
		int tianTong = ZhengXingBiao.tianTongBiao[ziWei];
		blockList.get(tianTong).zhengYaoList.add("天同");
		Yao tianTongYao = new Yao(tianTong);
		tianTongYao.miaoXian = MiaoXianBiao.tianTongBiao[tianTong];
		xingMap.put("天同", tianTongYao);
		
		int lianZhen = ZhengXingBiao.lianZhenBiao[ziWei];
		blockList.get(lianZhen).zhengYaoList.add("廉贞");
		Yao lianZhenYao = new Yao(lianZhen);
		lianZhenYao.miaoXian = MiaoXianBiao.lianZhenBiao[lianZhen];
		xingMap.put("廉贞",lianZhenYao);
		
		int tianFu = ZhengXingBiao.tianFuBiao[ziWei];
		blockList.get(tianFu).zhengYaoList.add("天府");
		Yao tianFuYao = new Yao(tianFu);
		tianFuYao.miaoXian = MiaoXianBiao.tianFuBiao[tianFu];
		xingMap.put("天府", tianFuYao);
		
		int taiYin = ZhengXingBiao.taiYinBiao[ziWei];
		blockList.get(taiYin).zhengYaoList.add("太阴");
		Yao taiYinYao = new Yao(taiYin);
		taiYinYao.miaoXian = MiaoXianBiao.taiYinBiao[taiYin];
		xingMap.put("太阴", taiYinYao);
		
		int tanLang = ZhengXingBiao.tanLangBiao[ziWei];
		blockList.get(tanLang).zhengYaoList.add("贪狼");
		Yao tanLangYao = new Yao(tanLang);
		tanLangYao.miaoXian = MiaoXianBiao.tanLangBiao[tanLang];
		xingMap.put("贪狼", tanLangYao);
		
		int juMen = ZhengXingBiao.juMenBiao[ziWei];
		blockList.get(juMen).zhengYaoList.add("巨门");
		Yao juMenYao = new Yao(juMen);
		juMenYao.miaoXian = MiaoXianBiao.juMenBiao[juMen];
		xingMap.put("巨门", juMenYao);
		
		int tianXiang = ZhengXingBiao.tianXiangBiao[ziWei];
		blockList.get(tianXiang).zhengYaoList.add("天相");
		Yao tianXiangYao = new Yao(tianXiang);
		tianXiangYao.miaoXian = MiaoXianBiao.tianXiangBiao[tianXiang];
		xingMap.put("天相", tianXiangYao);
		
		int tianLiang = ZhengXingBiao.tianLiangBiao[ziWei];
		blockList.get(tianLiang).zhengYaoList.add("天梁");
		Yao tianLiangYao = new Yao(tianLiang);
		tianLiangYao.miaoXian = MiaoXianBiao.tianLiangBiao[tianLiang];
		xingMap.put("天梁", tianLiangYao);
		
		int qiSha = ZhengXingBiao.qiShaBiao[ziWei];
		blockList.get(qiSha).zhengYaoList.add("七杀");
		Yao qiShaYao = new Yao(qiSha);
		qiShaYao.miaoXian = MiaoXianBiao.qiShaBiao[qiSha];
		xingMap.put("七杀", qiShaYao);
		
		int poJun = ZhengXingBiao.poJunBiao[ziWei];
		blockList.get(poJun).zhengYaoList.add("破军");
		Yao poJunYao = new Yao(poJun);
		poJunYao.miaoXian = MiaoXianBiao.poJunBiao[poJun];
		xingMap.put("破军", poJunYao);		
		//安干系诸星
		int luCun = GanXiZhuXingBiao.luCunBiao[basicInfo.tianGan];
		blockList.get(luCun).fuYaoList.add("禄存");
		Yao luCunYao = new Yao(luCun);
		luCunYao.miaoXian = MiaoXianBiao.luCunBiao[luCun];
		xingMap.put("禄存", luCunYao);
		
		int qingYang = GanXiZhuXingBiao.qingYangBiao[basicInfo.tianGan];
		blockList.get(qingYang).fuYaoList.add("擎羊");
		Yao qingYangYao = new Yao(qingYang);
		qingYangYao.siSha = 2;
		qingYangYao.miaoXian = MiaoXianBiao.qingYangBiao[qingYang];
		xingMap.put("擎羊", qingYangYao);
		
		int tuoLuo = GanXiZhuXingBiao.tuoLuoBiao[basicInfo.tianGan];
		blockList.get(tuoLuo).fuYaoList.add("陀罗");
		Yao tuoLuoYao = new Yao(tuoLuo);
		tuoLuoYao.siSha = 3;
		tuoLuoYao.miaoXian = MiaoXianBiao.tuoLuoBiao[tuoLuo];
		xingMap.put("陀罗", tuoLuoYao);
		
		int tianKui = GanXiZhuXingBiao.tianKuiBiao[basicInfo.tianGan];
		blockList.get(tianKui).fuYaoList.add("天魁");
		Yao tianKuiYao = new Yao(tianKui);
		tianKuiYao.miaoXian = MiaoXianBiao.tianKuiBiao[tianKui];
		xingMap.put("天魁", tianKuiYao);	
		
		int tianYue = GanXiZhuXingBiao.tianYueBiao[basicInfo.tianGan];
		blockList.get(tianYue).fuYaoList.add("天钺");
		Yao tianYueYao = new Yao(tianYue);
		tianYueYao.miaoXian = MiaoXianBiao.tianYueBiao[tianYue];
		xingMap.put("天钺", tianYueYao);	
		
		int tianGuan = GanXiZhuXingBiao.tianGuanBiao[basicInfo.tianGan];
		blockList.get(tianGuan).zaYaoList.add("天官");
		int tianFu2 = GanXiZhuXingBiao.tianFuBiao[basicInfo.tianGan];
		blockList.get(tianFu2).zaYaoList.add("天福");
		int tianChu = GanXiZhuXingBiao.tianChuBiao[basicInfo.tianGan];
		blockList.get(tianChu).zaYaoList.add("天厨");
		int jieKong1 = GanXiZhuXingBiao.jieKongBiao1[basicInfo.tianGan];
		blockList.get(jieKong1).zaYaoList.add("截空");
		int jieKong2 = GanXiZhuXingBiao.jieKongBiao2[basicInfo.tianGan];
		blockList.get(jieKong2).zaYaoList.add("截空");

		//安支系诸星
		int tianMa = ZhiXiZhuXingBiao.tianMaBiao[basicInfo.diZhi];
		blockList.get(tianMa).fuYaoList.add("天马");
		Yao tianMaYao = new Yao(tianMa);
		tianMaYao.miaoXian = MiaoXianBiao.tianMaBiao[tianMa];
		xingMap.put("天马", tianMaYao);
		
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
		Yao zuoFuYao = new Yao(zuoFu);
		zuoFuYao.miaoXian = MiaoXianBiao.zuoFuBiao[zuoFu];
		xingMap.put("左辅", zuoFuYao);
		
		int youBi = YueXiZhuXingBiao.youBiBiao[basicInfo.month];
		blockList.get(youBi).fuYaoList.add("右弼");
		Yao youBiYao = new Yao(youBi);
		youBiYao.miaoXian = MiaoXianBiao.youBiBiao[youBi];
		xingMap.put("右弼", youBiYao);
		
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
//		int tianDe = ZhiXiZhuXingBiao.tianDeBiao[basicInfo.diZhi];
//		blockList.get(tianDe).zaYaoList.add("天德");
		int nianJie = ZhiXiZhuXingBiao.nianJieBiao[basicInfo.diZhi];
		blockList.get(nianJie).zaYaoList.add("年解");
		int jieSha = ZhiXiZhuXingBiao.jieShaBiao[basicInfo.diZhi];
		blockList.get(jieSha).zaYaoList.add("劫煞");
		int daHao = ZhiXiZhuXingBiao.daHaoBiao[basicInfo.diZhi];
		blockList.get(daHao).zaYaoList.add("大耗");
		int xianChi = ZhiXiZhuXingBiao.xianChiBiao[basicInfo.diZhi];
		blockList.get(xianChi).zaYaoList.add("咸池");	
//		int huaGai = ZhiXiZhuXingBiao.huaGaiBiao[basicInfo.diZhi];
//		blockList.get(huaGai).zaYaoList.add("华盖");
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
		Yao wenChangYao = new Yao(wenChang);
		wenChangYao.miaoXian = MiaoXianBiao.wenChangBiao[wenChang];
		xingMap.put("文昌", wenChangYao);
		
		int wenQu = ShiXiZhuXingBiao.wenQuBiao[basicInfo.shiChen];
		blockList.get(wenQu).fuYaoList.add("文曲");
		Yao wenQuYao = new Yao(wenQu);
		wenQuYao.miaoXian = MiaoXianBiao.wenQuBiao[wenQu];
		xingMap.put("文曲", wenQuYao);
		
		int diJie = ShiXiZhuXingBiao.diJieBiao[basicInfo.shiChen];
		blockList.get(diJie).zaYaoList.add("地劫");
		Yao diJieYao = new Yao(diJie);
		diJieYao.miaoXian = MiaoXianBiao.diJieBiao[diJie];
		xingMap.put("地劫", diJieYao);
		
		int diKong = ShiXiZhuXingBiao.diKongBiao[basicInfo.shiChen];
		blockList.get(diKong).zaYaoList.add("地空");
		Yao diKongYao = new Yao(diKong);
		diKongYao.miaoXian = MiaoXianBiao.diKongBiao[diKong];
		xingMap.put("地空", diKongYao);
		
		int taiFu = ShiXiZhuXingBiao.taiFuBiao[basicInfo.shiChen];
		blockList.get(taiFu).zaYaoList.add("台辅");
		int fengGao = ShiXiZhuXingBiao.fengGaoBiao[basicInfo.shiChen];
		blockList.get(fengGao).zaYaoList.add("封诰");

		//安火星铃星
		int huoXing = ShiXiZhuXingBiao.huoXingBiao[basicInfo.diZhi%4][basicInfo.shiChen];
		blockList.get(huoXing).fuYaoList.add("火星");
		int lingXing = ShiXiZhuXingBiao.lingXingBiao[basicInfo.diZhi%4][basicInfo.shiChen];
		blockList.get(lingXing).fuYaoList.add("铃星");
		Yao huoXingYao = new Yao(huoXing);
		huoXingYao.siSha = 0;
		huoXingYao.miaoXian = MiaoXianBiao.huoXingBiao[huoXing];
		xingMap.put("火星", huoXingYao);
		Yao lingXingYao = new Yao(lingXing);
		lingXingYao.siSha = 1;
		lingXingYao.miaoXian = MiaoXianBiao.lingXingBiao[lingXing];
		xingMap.put("铃星", lingXingYao);
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
		//安旬空星
		int xunKong1 = XunKongXingBiao.xunKongXingBiao1[(basicInfo.year - 4)%60/10];
		int xunKong2 = XunKongXingBiao.xunKongXingBiao2[(basicInfo.year - 4)%60/10];
		blockList.get(xunKong1).zaYaoList.add("旬空");
		blockList.get(xunKong2).zaYaoList.add("旬空");
		//安四化星
		anSiHuaXing(basicInfo.tianGan);
		//安长生十二神
		int changShen = 0;
		String wuXing = basicInfo.wuXing;
		if(wuXing.endsWith("金")) 
			changShen = 5;
		else if(wuXing.endsWith("木")) 
			changShen = 11;
		else if(wuXing.endsWith("火")) 
			changShen = 2;
		else 
			changShen = 8;
		for(int i=0; i<changSheng.length; i++){
			int index = 0;
			if(basicInfo.sex == 0){
				index = (changShen - i + 12)%12;
			}else{				
				index = (i + changShen)%12;
			}
			blockList.get(index).changShengShen = changSheng[i];
		}
		//安博士十二神		
		for(int i=0; i<boShi.length; i++){
			int index = 0;
			if((basicInfo.sex == 0 && basicInfo.yinYang == 0) || (basicInfo.sex == 1 && basicInfo.yinYang == 1)){
				index = (i + luCun)%12;				
			}else{				
				index = (luCun - i + 12)%12;
			}
			blockList.get(index).boShiShen = boShi[i];
		}
		//安太岁十二神	
		for(int i=0; i<taiSui.length; i++){
			int index  = (i + basicInfo.diZhi)%12;
			blockList.get(index).taiSuiShen = taiSui[i];
		}
		//安将前十二神
		int jiangXing = -1;
		int temp = basicInfo.diZhi%4;
		if(temp==0)jiangXing=0;
		else if(temp==1)jiangXing=9;
		else if(temp==2)jiangXing=6;
		else if(temp==3)jiangXing=3;
		for(int i=0; i<jiangQian.length; i++){
			int index  = (i + jiangXing)%12;
			blockList.get(index).jiangQianShen = jiangQian[i];
		}
		
		
		//安大运魁钺羊陀禄存昌曲
		int gongGan = blockList.get(basicInfo.daXian).gongGan;
		int yunKui = GanXiZhuXingBiao.tianKuiBiao[gongGan];
		blockList.get(yunKui).yunYaoList.add("运魁");
		int yunYue = GanXiZhuXingBiao.tianYueBiao[gongGan];
		blockList.get(yunYue).yunYaoList.add("运钺");
		int yunYang = GanXiZhuXingBiao.qingYangBiao[gongGan];
		blockList.get(yunYang).yunYaoList.add("运羊");
		int yunTuo = GanXiZhuXingBiao.tuoLuoBiao[gongGan];
		blockList.get(yunTuo).yunYaoList.add("运驼");
		int yunLu = GanXiZhuXingBiao.luCunBiao[gongGan];
		blockList.get(yunLu).yunYaoList.add("运禄");		
		int yunChang = ChangQuBiao.wenChangBiao[gongGan];
		blockList.get(yunChang).yunYaoList.add("运昌");
		int yunQu = ChangQuBiao.wenQuBiao[gongGan];
		blockList.get(yunQu).yunYaoList.add("运曲");
		//安大运四化
		anDaYunSiHuaXing(gongGan);


		//安流年四化
		anLiuNianSiHuaXing(basicInfo.nowTianGan);
	}	
	
	private void anSiHuaXing(int tianGan){
		//安四化星
		Yao huaLuYao = xingMap.get(SiHuaXingBiao.huaLuBiao[tianGan]);
		huaLuYao.siHua = 0;
		huaLuYao.miaoXian = MiaoXianBiao.huaLuBiao[huaLuYao.gongZhi];
		
		Yao huaQuanYao = xingMap.get(SiHuaXingBiao.huaQuanBiao[tianGan]);
		huaQuanYao.siHua = 1;
		huaQuanYao.miaoXian = MiaoXianBiao.huaQuanBiao[huaQuanYao.gongZhi];
		
		Yao huaKeYao = xingMap.get(SiHuaXingBiao.huaKeBiao[tianGan]);
		huaKeYao.siHua = 2;
		huaKeYao.miaoXian = MiaoXianBiao.huaQuanBiao[huaKeYao.gongZhi];
		
		Yao huaJiYao = xingMap.get(SiHuaXingBiao.huaJiBiao[tianGan]);
		huaJiYao.siHua = 3;
		huaJiYao.miaoXian = MiaoXianBiao.huaJiBiao[huaJiYao.gongZhi];
	}
	
	private void anDaYunSiHuaXing(int tianGan){
		//安流四化星
		Yao huaLuYao = xingMap.get(SiHuaXingBiao.huaLuBiao[tianGan]);
		huaLuYao.daYunSiHua = 0;
		huaLuYao.miaoXian = MiaoXianBiao.huaLuBiao[huaLuYao.gongZhi];
		
		Yao huaQuanYao = xingMap.get(SiHuaXingBiao.huaQuanBiao[tianGan]);
		huaQuanYao.daYunSiHua = 1;
		huaQuanYao.miaoXian = MiaoXianBiao.huaQuanBiao[huaQuanYao.gongZhi];
		
		Yao huaKeYao = xingMap.get(SiHuaXingBiao.huaKeBiao[tianGan]);
		huaKeYao.daYunSiHua = 2;
		huaKeYao.miaoXian = MiaoXianBiao.huaQuanBiao[huaKeYao.gongZhi];
		
		Yao huaJiYao = xingMap.get(SiHuaXingBiao.huaJiBiao[tianGan]);
		huaJiYao.daYunSiHua = 3;
		huaJiYao.miaoXian = MiaoXianBiao.huaJiBiao[huaJiYao.gongZhi];
	}
	
	private void anLiuNianSiHuaXing(int tianGan){
		//安流四化星
		Yao huaLuYao = xingMap.get(SiHuaXingBiao.huaLuBiao[tianGan]);
		huaLuYao.liuNianSiHua = 0;
		huaLuYao.miaoXian = MiaoXianBiao.huaLuBiao[huaLuYao.gongZhi];
		
		Yao huaQuanYao = xingMap.get(SiHuaXingBiao.huaQuanBiao[tianGan]);
		huaQuanYao.liuNianSiHua = 1;
		huaQuanYao.miaoXian = MiaoXianBiao.huaQuanBiao[huaQuanYao.gongZhi];
		
		Yao huaKeYao = xingMap.get(SiHuaXingBiao.huaKeBiao[tianGan]);
		huaKeYao.liuNianSiHua = 2;
		huaKeYao.miaoXian = MiaoXianBiao.huaQuanBiao[huaKeYao.gongZhi];
		
		Yao huaJiYao = xingMap.get(SiHuaXingBiao.huaJiBiao[tianGan]);
		huaJiYao.liuNianSiHua = 3;
		huaJiYao.miaoXian = MiaoXianBiao.huaJiBiao[huaJiYao.gongZhi];
	}
	
	public String getDaXianGongGan(){
		return Pan.tianGan[blockList.get(basicInfo.daXian).gongGan];
	}
	
	public String getDaXianGongZhi(){
		return Pan.diZhi[blockList.get(basicInfo.daXian).gongZhi];
	}
	
	public static void main(String[] args){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		try {
			Date birthday = formatter.parse("1985:12:11 05:00:00");	
			//Date birthday = formatter.parse("1987:9:15 05:00:00");
			Pan pan = new Pan(birthday, 1, 2013);
			System.out.println(pan.basicInfo);
			for(int i=0; i<pan.blockList.size(); i++){
				System.out.println(pan.blockList.get(i));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	
	}
}
