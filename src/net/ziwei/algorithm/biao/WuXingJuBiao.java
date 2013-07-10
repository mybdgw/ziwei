package net.ziwei.algorithm.biao;
/*五行局表，横轴命宫宫支，纵轴命宫宫干*/
public class WuXingJuBiao {
	public static String[][] wuXingJuBiao = {{"金:4","水:2","火:6","金:4","水:2","火:6"},
											 {"水:2","火:6","土:5","水:2","火:6","土:5"},
											 {"火:6","土:5","木:3","火:6","土:5","木:3"},
											 {"土:5","木:3","金:4","土:5","木:3","金:4"},
											 {"木:3","金:4","水:2","木:3","金:4","水:2"}};
	/*根据命宫宫干和宫支，返回五行局*/
	public static String getWuXingJuByGongGanZhi(int gongzhi, int gonggan){
		return wuXingJuBiao[gonggan][gongzhi];
	}
}
