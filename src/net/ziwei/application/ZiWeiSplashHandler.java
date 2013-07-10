package net.ziwei.application;

import net.ziwei.Messages;

import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.branding.IProductConstants;
import org.eclipse.ui.splash.BasicSplashHandler;


/**
 * This was a copy of EclipseSplashHandler Parses the well known product constants and constructs a
 * splash handler accordingly.
 */
public class ZiWeiSplashHandler extends BasicSplashHandler {

	@Override
	public void init(final Shell splash) {

		super.init(splash);

		// keep the splash handler to be used outside of this splash handlers
		Activator.setSplashHandler(this);

		String progressRectString = null;
		String messageRectString = null;

		final IProduct product = Platform.getProduct();
		if (product != null) {
			progressRectString = product.getProperty(IProductConstants.STARTUP_PROGRESS_RECT);
			messageRectString = product.getProperty(IProductConstants.STARTUP_MESSAGE_RECT);
		}

		// set progressbar position
		Rectangle progressRect = parseRect(progressRectString);
		if (progressRect == null) {
			progressRect = new Rectangle(10, 0, 300, 15);
		}
		setProgressRect(progressRect);

		// set message position
		Rectangle messageRect = parseRect(messageRectString);
		if (messageRect == null) {
			messageRect = new Rectangle(10, 25, 300, 15);
		}
		setMessageRect(messageRect);

		// set message color
		int foregroundColorInteger;
		foregroundColorInteger = 0xffffff;

		setForeground(new RGB(
				(foregroundColorInteger & 0xFF0000) >> 16,
				(foregroundColorInteger & 0xFF00) >> 8,
				foregroundColorInteger & 0xFF));

		getContent().addPaintListener(new PaintListener() {

			public void paintControl(final PaintEvent e) {
				onPaint(e);
			}
		});
	}

	private void onPaint(final PaintEvent e) {
		final GC gc = e.gc;
		gc.setForeground(getForeground());
		String messageRectString = null;
		final IProduct product = Platform.getProduct();
		if (product != null) {
			messageRectString = product.getProperty(IProductConstants.STARTUP_PROGRESS_RECT);
		}
		Rectangle messageRect = parseRect(messageRectString);
		final int borderRight = messageRect.x;
		final int borderBottom = messageRect.y;

		final String copyRight = Messages.App_Splash_Copyright;
		final int textHeight = gc.textExtent(copyRight).y;

		final String version = "Version " + ApplicationVersion.getVersionSimple(); //$NON-NLS-1$
		final Point versionExtent = gc.textExtent(version);

		final String qualifier = ApplicationVersion.getVersionQualifier();
		final Point qualifierExtent = gc.textExtent(qualifier);
		gc.setFont(new Font(gc.getDevice(), "Arial", 10, SWT.BOLD)); 

		gc.drawText(
				version,
				borderRight + versionExtent.x,
				borderBottom + versionExtent.y + qualifierExtent.y,
				true);
		gc.drawText(qualifier, borderRight + qualifierExtent.x, borderBottom + versionExtent.y, true);

		gc.drawText(copyRight, messageRect.x, messageRect.y + textHeight, true);
	}

	private Rectangle parseRect(final String string) {
		if (string == null) {
			return null;
		}
		int x, y, w, h;
		int lastPos = 0;
		try {
			int i = string.indexOf(',', lastPos);
			x = Integer.parseInt(string.substring(lastPos, i));
			lastPos = i + 1;
			i = string.indexOf(',', lastPos);
			y = Integer.parseInt(string.substring(lastPos, i));
			lastPos = i + 1;
			i = string.indexOf(',', lastPos);
			w = Integer.parseInt(string.substring(lastPos, i));
			lastPos = i + 1;
			h = Integer.parseInt(string.substring(lastPos));
		} catch (final RuntimeException e) {
			// sloppy error handling
			return null;
		}
		return new Rectangle(x, y, w, h);
	}
}

