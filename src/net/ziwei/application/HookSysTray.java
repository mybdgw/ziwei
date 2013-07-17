package net.ziwei.application;

import net.ziwei.ui.UI;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class HookSysTray {
	private TrayItem trayItem;
	private Image trayImage;
	public HookSysTray(){}
	
	public void createSysTray(final IWorkbenchWindow window){
		trayItem = initTrayItem(window);
		if(trayItem != null){
			trayPopupMenu(window);
		}
	}
	
	private void trayPopupMenu(final IWorkbenchWindow window){
		trayItem.addListener(SWT.MenuDetect, new Listener(){
			public void handleEvent(Event event){
				MenuManager trayMenu = new MenuManager();
				Menu menu = trayMenu.createContextMenu(window.getShell());
				fillTrayItem(trayMenu, window);
				menu.setVisible(true);
			}
		});
	}
	
	private void fillTrayItem(IMenuManager trayItem, final IWorkbenchWindow window){
		Action exitSystem = new Action("退出系统"){
			public void run(){
				PlatformUI.getWorkbench().close();
			}
		};		
		trayItem.add(exitSystem);
	}
	
	private TrayItem initTrayItem(final IWorkbenchWindow window){
		final Tray tray = window.getShell().getDisplay().getSystemTray();
		
		if(tray == null)
			return null;
		trayItem = new TrayItem(tray, SWT.NONE);
		trayImage = UI.IMAGE_REGISTRY.get(UI.APPLICATION_IMAGE);
		trayItem.setImage(trayImage);			
		return trayItem;
	}
	
	public void Dispose(){
		if(trayItem != null) trayItem.dispose();
		if(trayImage != null) trayImage.dispose();
	}
}
