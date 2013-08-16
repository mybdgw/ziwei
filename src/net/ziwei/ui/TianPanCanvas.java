package net.ziwei.ui;

import java.util.List;

import net.ziwei.algorithm.Block;
import net.ziwei.algorithm.Pan;
import net.ziwei.algorithm.Yao;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;

public class TianPanCanvas extends PanCanvas{

	public TianPanCanvas(Composite parent, Pan pan) {
		super(parent, pan);
		// TODO Auto-generated constructor stub
		
		addPaintListener(new PaintListener() {
		     public void paintControl(PaintEvent e) {
		    	 paint(e);
		     }
		});
	}
	
	public void paint(PaintEvent e)
	{
		 final GC gc = e.gc;
		 super.paint(e);
		 paintBlock(gc);
	}
	
	private void paintBlock(GC gc){
		 for(int i=0; i<_blockArea.size(); i++){
			 Rectangle blockArea = _blockArea.get(i);
			 Block block = _pan.blockList.get(i);
			 //画正曜
			 List<String> zhengYaoList = block.zhengYaoList;
			 int x = blockArea.x + blockArea.width/2 - 10;
			 int y = blockArea.y + 5;
			 gc.setFont(font12);
			 for(int j=0; j<zhengYaoList.size(); j++){
				 String zhengYao = zhengYaoList.get(j);				
				 gc.setBackground(_display.getSystemColor(SWT.COLOR_WHITE));
				 gc.drawString(zhengYao, x, y);
				 Yao yao = _pan.xingMap.get(zhengYao);
				 if(yao.siHua >= 0)
				 {
					 String sihua = Pan.siHua[yao.siHua];
					 gc.setBackground(_display.getSystemColor(SWT.COLOR_GREEN));
					 gc.drawString(sihua, x + 40, y);
				 }
				 y += 20;
			 }
			 //画辅曜
			 x = blockArea.x + blockArea.width - 30;
			 y = blockArea.y + 5;
			 gc.setFont(font10);
			 gc.setBackground(_display.getSystemColor(SWT.COLOR_WHITE));
			 List<String> fuYaoList = block.fuYaoList;
			 for(int j=0; j<fuYaoList.size(); j++){
				 String fuYao = fuYaoList.get(j);				
				 Yao yao = _pan.xingMap.get(fuYao);
				 if(yao.siSha >= 0)
				 {					 
					 gc.setForeground(_display.getSystemColor(SWT.COLOR_RED));					 
				 }else{
					 gc.setForeground(_display.getSystemColor(SWT.COLOR_BLACK));
				 }
				 gc.drawString(fuYao, x, y);
				 y += 20;
			 }
			 gc.setForeground(_display.getSystemColor(SWT.COLOR_BLACK));
			 //画宫干支
			 x = blockArea.x + blockArea.width - 30;
			 y = blockArea.y + blockArea.height - 20;
			 gc.drawString( block.getGongGan()+ block.getGongZhi(), x, y);
			 //画长生十二宫
			 x = blockArea.x + blockArea.width - 30;
			 y = blockArea.y + blockArea.height - 40;
			 gc.drawString(block.changShengShen, x, y);
			 //画十二宫
			 x = blockArea.x + blockArea.width/2 - 15;
			 y = blockArea.y + blockArea.height - 20;
			 gc.drawString(block.shiErGong, x, y);
			 //画博士十二宫
			 x = blockArea.x + 5;
			 y = blockArea.y + blockArea.height - 60;
			 gc.drawString(block.boShiShen, x, y);
			 //画太岁十二宫
			 y = blockArea.y + blockArea.height - 40;
			 gc.drawString(block.taiSuiShen, x, y);
			 //画流年将前诸星
			 y = blockArea.y + blockArea.height - 20;
			 gc.drawString(block.jiangQianShen, x, y);
			 //画杂曜
			 gc.setFont(font8);
			 x = blockArea.x + 5;
			 y = blockArea.y + 5;
			 List<String> zaYaoList = block.zaYaoList;
			 for(int j=0; j<zaYaoList.size(); j++){
				 String zaYao = zaYaoList.get(j);				
				 gc.drawString(zaYao, x, y);				
				 if(j % 2 ==0){
					 x += 30;					 
				 }else{
					 x -= 30;
					 y += 15;
				 }
			 }
			 //画大限
			 x = blockArea.x + blockArea.width/2 - 10;
			 y = blockArea.y + blockArea.height - 40;
			 gc.drawString(block.startDaXian + "-" + block.endDaXian, x, y);
			
		 }
	}
}
