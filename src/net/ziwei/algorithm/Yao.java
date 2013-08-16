package net.ziwei.algorithm;

public class Yao {
	public int gongZhi;
	public int siHua = -1;	//四化，0:禄,1:权,2:科,3:忌
	public int daYunSiHua = -1; //大运四化.0:禄,1:权,2:科,3:忌
	public int liuNianSiHua = -1; //流年四化.0:禄,1:权,2:科,3:忌
	public int siSha = -1;  //四煞,0:火星,1:铃星,2:擎羊,3:驼罗
	public int miaoXian = -1; //5:庙,4:旺,3:平,2:闲,1:地,0:陷,-1:不存在
	public Yao(int zhi){
		gongZhi = zhi;
	}
}
