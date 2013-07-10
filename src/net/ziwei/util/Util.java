package net.ziwei.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

public class Util {
	/**
	 * Open a link
	 */
	public static void openLink(final Shell shell, String href) {
		
		// format the href for an html file (file:///<filename.html>
		// required for Mac only.
		if (href.startsWith("file:")) { //$NON-NLS-1$
			href = href.substring(5);
			while (href.startsWith("/")) { //$NON-NLS-1$
				href = href.substring(1);
			}
			href = "file:///" + href; //$NON-NLS-1$
		}
		
		final IWorkbenchBrowserSupport support = PlatformUI.getWorkbench().getBrowserSupport();
		
		try {

			final IWebBrowser browser = support.getExternalBrowser();
			browser.openURL(new URL(urlEncodeForSpaces(href.toCharArray())));

		} catch (final MalformedURLException e) {
			StatusUtil.showStatus(e);
		} catch (final PartInitException e) {
			StatusUtil.showStatus(e);
		}
	}
	
	/**
	 * This method encodes the url, removes the spaces from the url and replaces
	 * the same with <code>"%20"</code>. This method is required to fix Bug
	 * 77840.
	 * 
	 * @since 3.0.2
	 */
	private static String urlEncodeForSpaces(final char[] input) {
		final StringBuffer retu = new StringBuffer(input.length);
		for (final char element : input) {
			if (element == ' ') {
				retu.append("%20"); //$NON-NLS-1$
			} else {
				retu.append(element);
			}
		}
		return retu.toString();
	}
	
//	/**
//	 * Open view
//	 * 
//	 * @param viewId
//	 * @param isActivateView
//	 *            View is activated when <code>true</code>, otherwise it is only visible.
//	 * @return
//	 */
//	public static IViewPart showView(final String viewId, final boolean isActivateView) {
//
//		try {
//
//			final IWorkbench wb = PlatformUI.getWorkbench();
//			if (wb == null) {
//				return null;
//			}
//
//			final IWorkbenchWindow wbWin = wb.getActiveWorkbenchWindow();
//			if (wbWin == null) {
//				return null;
//			}
//
//			final IWorkbenchPage page = wbWin.getActivePage();
//			if (page == null) {
//				return null;
//			}
//
//			final int activationMode = isActivateView ? IWorkbenchPage.VIEW_ACTIVATE : IWorkbenchPage.VIEW_VISIBLE;
//
//			return page.showView(viewId, null, activationMode);
//
//		} catch (final PartInitException e) {
//			StatusUtil.showStatus(e);
//		}
//
//		return null;
//	}
}
