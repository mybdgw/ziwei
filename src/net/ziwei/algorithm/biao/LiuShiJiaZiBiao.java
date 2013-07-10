package net.ziwei.algorithm.biao;
//六十甲子表
public class LiuShiJiaZiBiao {
	static String[] tianGan = {"甲","乙","丙","丁","戊","己","庚","辛","壬","癸",
						"甲","乙","丙","丁","戊","己","庚","辛","壬","癸",
						"甲","乙","丙","丁","戊","己","庚","辛","壬","癸",
						"甲","乙","丙","丁","戊","己","庚","辛","壬","癸",
						"甲","乙","丙","丁","戊","己","庚","辛","壬","癸",
						"甲","乙","丙","丁","戊","己","庚","辛","壬","癸"};
	static String[] diZhi = {"子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥",
					  "子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥",
					  "子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥",
					  "子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥",
					  "子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥"};
	/*返回该年的天干*/
	static public String getTianGanByYear(int year){
		int index = (year-4)%60;
		return tianGan[index];
	}
	/*返回该年的地支*/
	static public String getDiZhiByYear(int year){
		int index = (year-4)%60;
		return diZhi[index];
	}
	/*根据年返回该年是阴年还是阳年，0是阳，1是阴*/
	static public int getYinYangByYear(int year){
		int index = (year-4)%60;
		return index%2;
	}	
}
