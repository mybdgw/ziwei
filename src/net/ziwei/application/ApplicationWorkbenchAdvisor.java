package net.ziwei.application;

import net.ziwei.perspectives.PerspectiveFactoryFirst;
import net.ziwei.preferences.PrefPageGeneral;

import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {
	@Override
    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }
    @Override
	public String getInitialWindowPerspectiveId() {
		return PerspectiveFactoryFirst.PERSPECTIVE_ID;
	}
    @Override
	public String getMainPreferencePageId() {
		return PrefPageGeneral.ID;
	}
	@Override
	public void initialize(final IWorkbenchConfigurer configurer) {
		configurer.setSaveAndRestore(true);
	}
	@Override
	public boolean preShutdown() {
		return true;
	}
}
