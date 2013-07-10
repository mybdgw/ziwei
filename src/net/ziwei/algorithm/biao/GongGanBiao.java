package net.ziwei.algorithm.biao;
/*宫干表,横轴是宫支，纵轴是天支/2*/
public class GongGanBiao {
	public static String[][] gongGanBiao = {{"丙","丁","丙","丁","戊","己","庚","辛","壬","癸","甲","乙"},
											{"戊","己","戊","己","庚","辛","壬","癸","甲","乙","丙","丁"},
											{"庚","辛","庚","辛","壬","癸","甲","乙","丙","丁","戊","己"},
											{"壬","癸","壬","癸","甲","乙","丙","丁","戊","己","庚","辛"},
											{"甲","乙","甲","乙","丙","丁","戊","己","庚","辛","壬","癸"},};
	/*返回天干和宫支对应的宫干*/
	public static String getGongGanByTianZhiAndGongZhi(int gongzhi, int tiangan){
		return gongGanBiao[tiangan][gongzhi];
	}
}
