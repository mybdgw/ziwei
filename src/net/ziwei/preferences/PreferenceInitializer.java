package net.ziwei.preferences;

import net.ziwei.application.Activator;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class PreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		// TODO Auto-generated method stub
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(IPreferences.SERVER_IP, "127.0.0.1");
		store.setDefault(IPreferences.SERVER_PORT, 3306);
		store.setDefault(IPreferences.USER_NAME, "root");
		store.setDefault(IPreferences.USER_PASSWORD, "123456");
		store.setDefault(IPreferences.DEFAULT_STORE_PATH, "");
	}
}
