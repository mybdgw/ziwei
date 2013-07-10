package net.ziwei.statusline;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.ziwei.Messages;


public class TimeInfoManager {

	private static TimeInfoManager	_instance;
	
	private SimpleDateFormat formatter = new SimpleDateFormat(Messages.StatusLine_TimeInfo_Content);      

	private TimeInfoControl	_infoWidget;

	public static TimeInfoManager getInstance() {

		if (_instance == null) {
			_instance = new TimeInfoManager();
		}

		return _instance;
	}

	void setInfoWidget(final TimeInfoControl infoWidget) {
		_infoWidget = infoWidget;
		updateUI();
	}

	private void updateUI() {
		// check widget
		if ((_infoWidget == null) || _infoWidget.isDisposed()) {
			return;
		}
		Date   curDate   =   new   Date(System.currentTimeMillis());
		
		_infoWidget.setText(Messages.StatusLine_TimeInfo_Title+" ： " +
				formatter.format(curDate));
	}

}

