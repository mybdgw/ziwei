package net.ziwei.views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.ziwei.algorithm.Pan;
import net.ziwei.ui.TianPanCanvas;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;


public class TianPanView extends ViewPart {

	public static final String ID = "net.ziwei.views.TianPanView"; //$NON-NLS-1$
	TianPanCanvas tianpan;

	public TianPanView() {
		
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		try {
			Date birthday = formatter.parse("1985:12:11 05:00:00");
			
			Date curDate = new Date(System.currentTimeMillis());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(curDate);
			Pan pan = new Pan(birthday, 1, calendar.get(Calendar.YEAR));
			tianpan = new TianPanCanvas(parent, pan);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
		

	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
