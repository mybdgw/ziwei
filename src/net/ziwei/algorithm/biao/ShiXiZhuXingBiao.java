package net.ziwei.algorithm.biao;
/*根据出生时4，安时系诸星宫位*/
public class ShiXiZhuXingBiao {
	public static int[] wenChangBiao = {10,9,8,7,6,5,4,3,2,1,0,11};
	public static int[] wenQuBiao = {4,5,6,7,8,9,10,11,0,1,2,3};
	public static int[] diJieBiao = {11,0,1,2,3,4,5,6,7,8,9,10};
	public static int[] diKongBiao = {11,10,9,8,7,6,5,4,3,2,1,0};
	public static int[] taiFuBiao = {6,7,8,9,10,11,0,1,2,3,4,5};
	public static int[] fengGaoBiao = {2,3,4,5,6,7,8,9,10,11,0,1};
	public static int[][] huoXingBiao = {{2,3,4,5,6,7,8,9,10,11,0,1},
										 {3,4,5,6,7,8,9,10,11,0,1,2},
										 {1,2,3,4,5,6,7,8,9,10,11,0},
										 {9,10,11,0,1,2,3,4,5,6,7,8}};
	public static int[][] lingXingBiao = {{10,11,0,1,2,3,4,5,6,7,8,9},
										  {10,11,0,1,2,3,4,5,6,7,8,9},
										  {3,4,5,6,7,8,9,10,11,0,1,2},
										  {10,11,0,1,2,3,4,5,6,7,8,9}};
}
