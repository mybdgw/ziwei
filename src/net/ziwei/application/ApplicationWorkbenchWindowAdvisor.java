package net.ziwei.application;

import net.ziwei.Messages;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {
	private ApplicationActionBarAdvisor			_applicationActionBarAdvisor;
	private String								_appTitle;
	private HookSysTray hookSysTray;
	
    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
		_appTitle = Messages.App_Title + " - "
				+ ApplicationVersion.getVersionSimple()
				+ ApplicationVersion.getDevelopmentId();
    }
    
    private void createSystemTray(){
    	hookSysTray = new HookSysTray();
    	hookSysTray.createSysTray(getWindowConfigurer().getWindow());
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
    	_applicationActionBarAdvisor = new ApplicationActionBarAdvisor(configurer);
    	return _applicationActionBarAdvisor;
    }
    public void postWindowOpen() {
    	Shell shell = getWindowConfigurer().getWindow().getShell();
    	Rectangle screenSize = Display.getDefault().getClientArea();
    	Rectangle frameSize = shell.getBounds();
    	shell.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
    	shell.setMaximized(true);  
    	createSystemTray();
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setShowPerspectiveBar(true);
		configurer.setShowCoolBar(true);
		configurer.setShowProgressIndicator(true);
        configurer.setTitle(_appTitle);
    }
    
    public void dispose(){
    	hookSysTray.Dispose();
    }
}
