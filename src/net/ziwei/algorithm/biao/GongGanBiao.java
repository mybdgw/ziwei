package net.ziwei.algorithm.biao;
/*宫干表,横轴是宫支，纵轴是天支/2*/
public class GongGanBiao {
	public static int[][] gongGanBiao = {{2,3,2,3,4,5,6,7,8,9,0,1},
										 {4,5,4,5,6,7,8,9,0,1,2,3},
										 {6,7,6,7,8,9,0,1,2,3,4,5},
										 {8,9,8,9,0,1,2,3,4,5,6,7},
										 {0,1,0,1,2,3,4,5,6,7,8,9},};
	/*返回天干和宫支对应的宫干*/
	public static int getGongGanByTianZhiAndGongZhi(int gongzhi, int tiangan){
		return gongGanBiao[tiangan][gongzhi];
	}
}
