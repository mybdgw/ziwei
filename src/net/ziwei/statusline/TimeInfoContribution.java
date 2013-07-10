package net.ziwei.statusline;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.menus.WorkbenchWindowControlContribution;

public class TimeInfoContribution extends WorkbenchWindowControlContribution {
	private TimeInfoManager	fTimeInfoManager;
	private TimeInfoControl	fInfoWidget;

	@Override
	protected Control createControl(final Composite parent) {
		if (fTimeInfoManager == null) {
			fTimeInfoManager = TimeInfoManager.getInstance();
		}
		fInfoWidget = new TimeInfoControl(parent, getOrientation());

		fTimeInfoManager.setInfoWidget(fInfoWidget);

		return fInfoWidget;
	}
}
