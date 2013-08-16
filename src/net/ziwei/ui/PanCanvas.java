package net.ziwei.ui;

import java.util.ArrayList;

import net.ziwei.algorithm.BasicInfo;
import net.ziwei.algorithm.Pan;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class PanCanvas extends Canvas{
	Display _display;
	Pan _pan;
	private static int _blockWidth;
	private static int _blockHeight;
	private static final int BLOCKWIDHT = 200;
	private static final int BLOCKHEIGHT= 150;
	ArrayList<Rectangle> _blockArea = new ArrayList<Rectangle>();
	Rectangle basicArea;
	Font font12 = new Font(_display, "Microsoft YaHei", 12, SWT.BOLD);
	Font font10 = new Font(_display, "Microsoft YaHei", 10, SWT.NORMAL);
	Font font8 = new Font(_display, "Microsoft YaHei", 8, SWT.NORMAL);
	 
	public PanCanvas(Composite parent, Pan pan) {
		super(parent, SWT.NONE);		
		_display =  getDisplay();		
		_pan = pan;		
		setBackground(_display.getSystemColor(SWT.COLOR_WHITE));
	}
	
	public void paint(PaintEvent e)
	{
		 final GC gc = e.gc;
		 adjustWidthAndHeight();
		 paintBlockArea(gc);
		 paintBasicInfo(gc);
	}

	private void adjustWidthAndHeight(){		
		Rectangle clientArea = getClientArea();

		if(clientArea.width + 20 < BLOCKWIDHT*4){
			_blockWidth = clientArea.width/4;
		}else{
			_blockWidth = BLOCKWIDHT;
		}
		if(clientArea.height + 20  < BLOCKHEIGHT*4){
			_blockHeight = clientArea.height/4;
		}else{
			_blockHeight = BLOCKHEIGHT;
		}
	}
	
	private void paintBlockArea(GC gc){
		 Rectangle clientArea = getClientArea();
		 int centerX = clientArea.x + clientArea.width/2;
		 int centerY = clientArea.y + clientArea.height/2;
		 int x = centerX - 2*_blockWidth, y = centerY - 2*_blockHeight;
		 _blockArea.clear();
		 Rectangle block1 = new Rectangle(x+2*_blockWidth, y+3*_blockHeight, _blockWidth, _blockHeight);
		 Rectangle block2 = new Rectangle(x+_blockWidth, y+3*_blockHeight,  _blockWidth, _blockHeight);
		 Rectangle block3 = new Rectangle(x, y+3*_blockHeight,  _blockWidth, _blockHeight);
		 Rectangle block4 = new Rectangle(x, y+2*_blockHeight,  _blockWidth, _blockHeight);
		 
		 Rectangle block5 = new Rectangle(x, y+_blockHeight,  _blockWidth, _blockHeight);
		 Rectangle block6 = new Rectangle(x, y, _blockWidth, _blockHeight);
		 Rectangle block7 = new Rectangle(x+_blockWidth, y,  _blockWidth, _blockHeight);
		 Rectangle block8 = new Rectangle(x+2*_blockWidth, y,  _blockWidth, _blockHeight);	 
		 
		 Rectangle block9 = new Rectangle(x+3*_blockWidth, y,  _blockWidth, _blockHeight);
		 Rectangle block10 = new Rectangle(x+3*_blockWidth, y+_blockHeight,  _blockWidth, _blockHeight);
		 Rectangle block11 = new Rectangle(x+3*_blockWidth, y+2*_blockHeight,  _blockWidth, _blockHeight);
		 Rectangle block12 = new Rectangle(x+3*_blockWidth, y+3*_blockHeight,  _blockWidth, _blockHeight);
		 _blockArea.add(block1);
		 _blockArea.add(block2);
		 _blockArea.add(block3);
		 _blockArea.add(block4);
		 _blockArea.add(block5);
		 _blockArea.add(block6);
		 _blockArea.add(block7);
		 _blockArea.add(block8);
		 _blockArea.add(block9);
		 _blockArea.add(block10);
		 _blockArea.add(block11);
		 _blockArea.add(block12);
		 for(int i=0; i<_blockArea.size(); i++){
			 Rectangle block = _blockArea.get(i);
			 gc.drawLine(block.x, block.y, block.x+block.width, block.y);
			 gc.drawLine(block.x+block.width, block.y, block.x+block.width, block.y + block.height);
			 gc.drawLine(block.x+block.width, block.y + block.height, block.x, block.y + block.height);
			 gc.drawLine(block.x, block.y + block.height, block.x, block.y);			 
		 }
		 
		 basicArea = new Rectangle(x+_blockWidth, y+_blockHeight, 2*_blockWidth, 2*_blockHeight);
	}
	
	private void paintBasicInfo(GC gc){
		gc.setFont(font10);
		BasicInfo basicInfo = _pan.basicInfo;
		int width = basicArea.x + basicArea.width;
		gc.drawString(basicInfo.year + "年", width - 80, basicArea.y + 50);
		gc.drawString(basicInfo.getYinYang(), width - 40, basicArea.y + 150);
		gc.drawString(basicInfo.getSex(), width - 40, basicArea.y + 170);
		gc.drawString(basicInfo.getTianGan(), width - 70, basicArea.y + 80);
		gc.drawString(basicInfo.getDiZhi(), width - 70, basicArea.y + 100);
		gc.drawString("年", width - 70, basicArea.y + 120);
		gc.drawString((basicInfo.month + 1)+"", width - 70, basicArea.y + 140);
		gc.drawString("月"+"", width - 70, basicArea.y + 160);
		gc.drawString(basicInfo.day+"", width - 70, basicArea.y + 180);
		gc.drawString("日", width - 70, basicArea.y + 200);
		gc.drawString(basicInfo.getShiChen(), width - 70, basicArea.y + 220);
		gc.drawString("时", width - 70, basicArea.y + 240);
		gc.drawString(basicInfo.wuXing, width - 120, basicArea.y + 200);
		gc.drawString(basicInfo.getWuXingJuNum(), width - 120, basicArea.y + 220);
		gc.drawString("局", width - 120, basicArea.y + 240);
		
		gc.drawString("命主", width - 180, basicArea.y + 80);
		gc.drawString(basicInfo.mingZhu, width - 180, basicArea.y + 100);
		gc.drawString("身主", width - 180, basicArea.y + 140);
		gc.drawString(basicInfo.shenZhu, width - 180, basicArea.y + 160);
		width = basicArea.x;
		gc.drawString(basicInfo.getNowTianGan(), width + 80, basicArea.y + 80);
		gc.drawString(basicInfo.getNowDiZhi(), width + 80, basicArea.y + 100);
		gc.drawString("年", width + 80, basicArea.y + 120);
		gc.drawString(basicInfo.age+"", width + 80, basicArea.y + 140);
		gc.drawString("岁", width + 80, basicArea.y + 160);
		
		gc.drawString("现", width + 40, basicArea.y + 80);
		gc.drawString("行", width + 40, basicArea.y + 100);
		gc.drawString(_pan.getDaXianGongGan(), width + 40, basicArea.y + 120);
		gc.drawString(_pan.getDaXianGongZhi(), width + 40, basicArea.y + 140);
		gc.drawString("大", width + 40, basicArea.y + 160);
		gc.drawString("限", width + 40, basicArea.y + 180);
	}
}
