package net.ziwei.application;

import net.ziwei.ui.UI;

import org.osgi.framework.Version;
//���Ӧ�ð汾��
public class ApplicationVersion {

	private static final String	DEVELOPMENT_VERSION_TEXT	= UI.EMPTY_STRING;
	private static final String	DEV_WINDOW_TITLE			= UI.EMPTY_STRING;

	private static String		_subVersion					= UI.EMPTY_STRING;
	private static String		_versionFull;
	private static String		_versionSimple;
	private static String		_qualifierText;

	private static boolean		_isDev;

	private static void createVersionText() {

		final Version version = Activator.getDefault().getVersion();
		final String qualifier = version.getQualifier();

		_isDev = qualifier.contains("qualifier"); //$NON-NLS-1$

		_qualifierText = _isDev ? //
				//
				// this text is used to identify development versions
				DEVELOPMENT_VERSION_TEXT
				//
				: qualifier.substring(0, 8) + UI.DASH + qualifier.substring(8);

		_qualifierText += _subVersion;

		_versionSimple = UI.EMPTY_STRING
				+ version.getMajor()
				+ UI.SYMBOL_DOT
				+ version.getMinor()
				+ UI.SYMBOL_DOT
				+ version.getMicro();

		_versionFull = _versionSimple + UI.SYMBOL_DOT + _qualifierText;
	}

	public static String getDevelopmentId() {

		String id = _isDev ? DEV_WINDOW_TITLE : UI.EMPTY_STRING;

		id += _subVersion;

		return id;
	}

	public static String getVersionFull() {

		if (_versionFull != null) {
			return _versionFull;
		}

		createVersionText();

		return _versionFull;
	}

	public static String getVersionQualifier() {

		if (_qualifierText != null) {
			return _qualifierText;
		}

		createVersionText();

		return _qualifierText;
	}

	public static String getVersionSimple() {

		if (_versionSimple != null) {
			return _versionSimple;
		}

		createVersionText();

		return _versionSimple;
	}
}

