package ziwei;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	
	private static final String	BUNDLE_NAME	= "ziwei.messages";

	public static String App_Title;
	public static String App_Splash_Copyright;  //闪屏文字	
	
	public static String Menu_File;
	public static String Menu_Window;
	public static String Menu_Help;
	public static String Menu_Quit;
	public static String Menu_Intro;
	public static String Menu_About;
	
	public static String StatusLine;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {}
}
