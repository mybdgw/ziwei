package ziwei.application;


import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import ziwei.Messages;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {
	private ApplicationActionBarAdvisor			_applicationActionBarAdvisor;
	private String								_appTitle;
	
    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
		_appTitle = Messages.App_Title + " - " //$NON-NLS-1$
				+ ApplicationVersion.getVersionSimple()
				+ ApplicationVersion.getDevelopmentId();
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
    	_applicationActionBarAdvisor =  new ApplicationActionBarAdvisor(configurer);
    	return _applicationActionBarAdvisor;
    }
    
    public void postWindowOpen() {
    	Shell shell = getWindowConfigurer().getWindow().getShell();
    	Rectangle screenSize = Display.getDefault().getClientArea();
    	Rectangle frameSize = shell.getBounds();
    	shell.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
    	IStatusLineManager statusLine = getWindowConfigurer().getActionBarConfigurer().getStatusLineManager();
    	statusLine.setMessage(null, _appTitle);
    }
    
    public void preWindowOpen() {
    	   IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
           configurer.setInitialSize(new Point(400, 300));
           configurer.setShowPerspectiveBar(true);
           configurer.setShowStatusLine(true);
   		   configurer.setShowCoolBar(true);
   		   configurer.setShowProgressIndicator(true);
           configurer.setTitle(_appTitle);
    }
}
