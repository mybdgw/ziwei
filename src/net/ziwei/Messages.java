package net.ziwei;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	
	private static final String	BUNDLE_NAME	= "net.ziwei.messages";

	public static String App_Title;
	public static String App_Splash_Copyright;
	
	public static String External_Link_HomePage;
	public static String External_Link_Forum;
	
	public static String App_Action_Menu_File;
	public static String App_Action_Menu_Window;
	public static String App_Action_Menu_Help;
	public static String App_Action_Help;
	public static String App_Action_Intro;
	public static String App_Action_About;
	
	public static String PrefPageGeneral_Store_Path;
	public static String PrefPageGeneral_Server_Configuration;
	public static String PrefPageGeneral_Server_IP_Label;
	public static String PrefPageGeneral_Server_Port_Label;
	public static String PrefPageGeneral_User_Name_Label;
	public static String PrefPageGeneral_User_Password_Label;
	public static String PrefPageGeneral_Error_value_must_be_integer;
	
	public static String StatusLine_TimeInfo_Content;
	public static String StatusLine_TimeInfo_Title;
	
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {}
}
