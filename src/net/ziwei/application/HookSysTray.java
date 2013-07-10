package net.ziwei.application;

import net.ziwei.ui.UI;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
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
			trayMinimize(window);
		}
	}
	
	public void windowMinimized(final Shell shell){
		shell.setMinimized(true);
		shell.setVisible(false);
	}
	
	private void trayMinimize(final IWorkbenchWindow window){
		window.getShell().addShellListener(new ShellAdapter(){
			public void shellIconified(ShellEvent e){
				window.getShell().setVisible(false);
			}			
		});
		trayItem.addListener(SWT.Selection, new Listener(){
			public void handleEvent(Event event){
				Shell shell = window.getShell();
				if(!shell.isVisible()){
					shell.setVisible(true);
					window.getShell().setMinimized(false);
				}
			}
		});
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
		Action exitSystem = new Action("退出系统[&E]"){
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
//		Timer timer = new Timer();
//		timer.schedule(new TimerTask(){
//			public void run(){
//				window.getShell().getDisplay().asyncExec(new Runnable(){
//
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						ToolTip tip = new ToolTip(window.getShell(), SWT.BALLOON | SWT.ICON_INFORMATION);
//						tip.setAutoHide(true);
//						tip.setText(Messages.App_Title);
//						tip.setMessage(Messages.HookSysTray_Tooltip);
//						tip.setVisible(true);
//						trayItem.setToolTip(tip);	
//					}
//					
//				});
//				
//			}
//		}, 0, 100);		
			
		return trayItem;
	}
	
	public void Dispose(){
		if(trayItem != null) trayItem.dispose();
		if(trayImage != null) trayImage.dispose();
	}
}
