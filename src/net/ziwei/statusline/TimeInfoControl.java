package net.ziwei.statusline;

import net.ziwei.Messages;

import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

public class TimeInfoControl extends CLabel {

	public TimeInfoControl(final Composite parent, final int style) {
		super(parent, style);
		setToolTipText(Messages.StatusLine_TimeInfo_Title);
	}

	@Override
	public Point computeSize(final int wHint, final int hHint, final boolean changed) {
 
		final GC gc = new GC(this);
		final Point p = gc.textExtent(Messages.StatusLine_TimeInfo_Title+ " ： "+ Messages.StatusLine_TimeInfo_Content);
		gc.dispose();

		return p;
	}
}
