package net.ziwei.algorithm.biao;
//六十甲子表
public class LiuShiJiaZiBiao {
	/*返回该年的天干*/
	static public int getTianGanByYear(int year){
		return (year-4)%60%10;
	}
	/*返回该年的地支*/
	static public int getDiZhiByYear(int year){
		return (year-4)%60%12;
	}
	/*根据年返回该年是阴年还是阳年，0是阳，1是阴*/
	static public int getYinYangByYear(int year){
		return (year-4)%60%2;
	}	
}
