package net.ziwei.algorithm.biao;
/*五行局表，横轴命宫宫支，纵轴命宫宫干*/
public class WuXingJuBiao {
	public static String[][] wuXingJuBiao = {{"金","水","火","金","水","火"},
											 {"水","火","土","水","火","土"},
											 {"火","土","木","火","土","木"},
											 {"土","木","金","土","木","金"},
											 {"木","金","水","木","金","水"}};
	
	/*根据命宫宫干和宫支，返回五行*/
	public static String getWuXingJuByGongGanZhi(int gonggan, int gongzhi){
	//	3,7
		int row = gonggan/2;
		int col = gongzhi/2;
		return wuXingJuBiao[row][col];
	}
	/*根据五行，返回起始年*/
	public static int getWuXingNumByWuXing(String wuxing){
		if(wuxing.endsWith("金")) 
			return 4;
		else if(wuxing.endsWith("木")) 
			return 3;
		else if(wuxing.endsWith("水")) 
			return 2;
		else if(wuxing.endsWith("火")) 
			return 6;
		else if(wuxing.endsWith("土")) 
			return 5;
		return -1;
	}
}
