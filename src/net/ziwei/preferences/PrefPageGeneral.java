package net.ziwei.preferences;

import net.ziwei.Messages;
import net.ziwei.application.Activator;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class PrefPageGeneral extends FieldEditorPreferencePage  implements IWorkbenchPreferencePage{
	public static final String	ID	= "net.ziwei.preferences.PrefPageGeneral"; //$NON-NLS-1$

	private IPreferenceStore	_prefStore;
	private DirectoryFieldEditor _storePath;
	private StringFieldEditor _serverIP;
	private IntegerFieldEditor _serverPort;
	private StringFieldEditor _userName;
	private StringFieldEditor _userPassword;
	/**
	 * Create the preference page.
	 */
	public PrefPageGeneral() {
		super(FLAT);
	}

	/**
	 * Create contents of the preference page.
	 */
	@Override
	protected void createFieldEditors() {
		// Create the field editors
		createUI();
	}
	
	private void createUI() {
		final Composite parent = getFieldEditorParent();
		GridLayoutFactory.fillDefaults().applyTo(parent);
		
		Group groupContainer = new Group(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).span(3, 1).applyTo(groupContainer);
		GridLayoutFactory.fillDefaults().applyTo(groupContainer);
		groupContainer.setText(Messages.PrefPageGeneral_Server_Configuration);
		
		_serverIP = new StringFieldEditor(IPreferences.SERVER_IP, Messages.PrefPageGeneral_Server_IP_Label, groupContainer);
		_serverIP.setPreferenceStore(_prefStore);
		_serverIP.setPage(this);
		_serverIP.setTextLimit(15);
		_serverIP.setEmptyStringAllowed(false);
		_serverIP.load();
			
		_serverPort = new IntegerFieldEditor(IPreferences.SERVER_PORT, Messages.PrefPageGeneral_Server_Port_Label, groupContainer);
		_serverPort.setPreferenceStore(_prefStore);
		_serverPort.setPage(this);
		_serverPort.setTextLimit(4);
		_serverPort.setErrorMessage(Messages.PrefPageGeneral_Error_value_must_be_integer);
		_serverPort.load();
	
		_userName = new StringFieldEditor(IPreferences.USER_NAME, Messages.PrefPageGeneral_User_Name_Label, groupContainer);
		_userName.setPreferenceStore(_prefStore);
		_userName.setPage(this);
		_userName.setEmptyStringAllowed(false);
		_userName.load();
		
		_userPassword = new StringFieldEditor(IPreferences.USER_PASSWORD, Messages.PrefPageGeneral_User_Password_Label, groupContainer);
		_userPassword.setPreferenceStore(_prefStore);
		_userPassword.setPage(this);
		_userPassword.load();
		
		_storePath = new DirectoryFieldEditor(IPreferences.DEFAULT_STORE_PATH, Messages.PrefPageGeneral_Store_Path, parent);
		_storePath.setValidateStrategy(StringFieldEditor.VALIDATE_ON_KEY_STROKE);
		_storePath.setPreferenceStore(_prefStore);
		_storePath.setPage(this);
		_storePath.load();
		
	}
	/**
	 * Initialize the preference page.
	 */
	public void init(IWorkbench workbench) {
		// Initialize the preference page
		_prefStore = Activator.getDefault().getPreferenceStore();
		setPreferenceStore(_prefStore);
	}
	
	@Override
	public boolean performOk() {
		saveState();
		return super.performOk();
	}
	@Override
	public boolean performCancel() {
		return super.performCancel();
	}

	@Override
	protected void performApply() {
		saveState();
		super.performApply();
	}
	@Override
	protected void performDefaults() {
		_serverIP.loadDefault();
		_serverPort.loadDefault();
		_userName.loadDefault();
		_userPassword.loadDefault();
		_storePath.loadDefault();
		super.performDefaults();
	}
	
	private void saveState(){
		_serverIP.store();
		_serverPort.store();
		_userName.store();
		_userPassword.store();
		_storePath.store();
	}
}
