package net.ziwei.algorithm.biao;

//时辰表
public class ShiChenBiao {
	public static String[] shiChen = {"子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥"};
	public static int[] startHour = {23,1,3,5,7,9,11,13,15,17,19,21};
	public static int[] endHour   = {1,3,5,7,9,11,13,15,17,19,21,23};
	/*返回小时对应的时辰*/
	public static String getShiChenByHour(int hour){			
		if(hour==23 || hour == 24 || hour == 0)
			return shiChen[0];
		for(int i=1; i<shiChen.length; i++){
			if( hour >= startHour[i] && hour < endHour[i]){
				return shiChen[i];
			}
		}
		return "";
	}
}
