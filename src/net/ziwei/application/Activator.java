package net.ziwei.application;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.osgi.framework.internal.core.AbstractBundle;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Version;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "net.ziwei"; //$NON-NLS-1$

	// The shared instance
	private static Activator _instance;
	private static BundleContext _bundleContext;
	private Version	_version;
	private static ZiWeiSplashHandler	_splashHandler;
	/**
	 * The constructor
	 */
	public Activator() {
	}
	
	public static BundleContext getBundleContext() {
		return _bundleContext;
	}
	
	public Version getVersion() {
		return _version;
	}
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		_instance = this;
		_bundleContext = context;
		final Bundle bundle = context.getBundle();
		if (bundle instanceof AbstractBundle) {
			final AbstractBundle abstractBundle = (AbstractBundle) bundle;
			_version = abstractBundle.getVersion();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		_instance = null;
		_bundleContext = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return _instance;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	public static void setSplashHandler(final ZiWeiSplashHandler splashHandler) {
		_splashHandler = splashHandler;
	}
	
	public static ZiWeiSplashHandler getSplashHandler() {
		return _splashHandler;
	}
}
