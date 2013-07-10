package net.ziwei.application;

import net.ziwei.Messages;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.StatusLineContributionItem;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	 // File menu
        register(ActionFactory.QUIT.create(window));

        // Windows menu
        register(ActionFactory.OPEN_NEW_WINDOW.create(window));
        register(ActionFactory.EDIT_ACTION_SETS.create(window));
        register(ActionFactory.PREFERENCES.create(window));        
        register(ActionFactory.SAVE_PERSPECTIVE.create(window));
        register(ActionFactory.RESET_PERSPECTIVE.create(window));
        register(ActionFactory.CLOSE_PERSPECTIVE.create(window));
        register(ActionFactory.CLOSE_ALL_PERSPECTIVES.create(window));
        
        // Help menu
        IWorkbenchAction help = ActionFactory.DYNAMIC_HELP.create(window);
        help.setText(Messages.App_Action_Help);
        register(help);
        IWorkbenchAction intro = ActionFactory.INTRO.create(window);
        intro.setText(Messages.App_Action_Intro);
        register(intro);
        IWorkbenchAction about = ActionFactory.ABOUT.create(window);
        about.setText(Messages.App_Action_About);
        register(about);
    }
    @Override
	protected void fillCoolBar(final ICoolBarManager coolBar) {
    	coolBar.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    }
    
    @Override
	protected void fillMenuBar(final IMenuManager menuBar) {
    	 menuBar.add(createFileMenu());
         menuBar.add(createWindowMenu());
         menuBar.add(createHelpMenu());
	}
    
    private MenuManager createFileMenu() {
    	MenuManager menu = new MenuManager(Messages.App_Action_Menu_File, IWorkbenchActionConstants.M_FILE);
	    menu.add(new GroupMarker(IWorkbenchActionConstants.FILE_START));	    
	    menu.add(new GroupMarker(IWorkbenchActionConstants.FILE_END)); 
	    menu.add(getAction(ActionFactory.QUIT.getId()));
		return menu;
	}

	 private MenuManager createWindowMenu() {
	    MenuManager menu = new MenuManager(Messages.App_Action_Menu_Window, 
	                IWorkbenchActionConstants.M_WINDOW);

	    menu.add(new Separator());
	    MenuManager perspectiveMenu = new MenuManager(
                "Open_Perspective", "openPerspective"); //$NON-NLS-1$ //$NON-NLS-2$
        IContributionItem perspectiveList = ContributionItemFactory.PERSPECTIVES_SHORTLIST
                .create(getActionBarConfigurer().getWindowConfigurer().getWindow());
        perspectiveMenu.add(perspectiveList);
        menu.add(perspectiveMenu);

	    MenuManager viewMenu = new MenuManager("Show_View");
        IContributionItem viewList = ContributionItemFactory.VIEWS_SHORTLIST
                .create(getActionBarConfigurer().getWindowConfigurer().getWindow());
        viewMenu.add(viewList);
        menu.add(viewMenu);

	    menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	        
	    menu.add(getAction(ActionFactory.PREFERENCES.getId()));		   
	    menu.add(new Separator());		 
	    menu.add(getAction(ActionFactory.SAVE_PERSPECTIVE.getId()));
	    menu.add(getAction(ActionFactory.RESET_PERSPECTIVE.getId()));
	    menu.add(getAction(ActionFactory.CLOSE_PERSPECTIVE.getId()));
	    menu.add(getAction(ActionFactory.CLOSE_ALL_PERSPECTIVES.getId()));
	    
	    menu.add(new Separator());
	    menu.add(getAction(ActionFactory.OPEN_NEW_WINDOW.getId()));     
	    menu.add(ContributionItemFactory.OPEN_WINDOWS.create(getActionBarConfigurer().getWindowConfigurer().getWindow()));
	   
	    return menu;
	 }

	    
	private MenuManager createHelpMenu() {
		MenuManager menu = new MenuManager(Messages.App_Action_Menu_Help, IWorkbenchActionConstants.M_HELP); //$NON-NLS-1$
     
        menu.add(getAction(ActionFactory.DYNAMIC_HELP.getId()));  
        menu.add(getAction(ActionFactory.INTRO.getId()));

        menu.add(new GroupMarker(IWorkbenchActionConstants.HELP_START));
        menu.add(new GroupMarker(IWorkbenchActionConstants.HELP_END));

        menu.add(new Separator());
        menu.add(getAction(ActionFactory.ABOUT.getId()));

        return menu;
	}
	
    @Override
	protected void fillStatusLine(IStatusLineManager statusLine) {
		super.fillStatusLine(statusLine);
		StatusLineContributionItem statusItem = new StatusLineContributionItem("");	
		statusItem.setText(Messages.App_Title);	
		statusLine.add(statusItem);
	}
}
