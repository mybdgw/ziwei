package net.ziwei.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

public class ActionHandlerSetup extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		MessageBox dialog = new MessageBox(Display.getCurrent().getActiveShell(), SWT.NONE);
		dialog.setMessage("xxxxxxxxxxxxxxxx");
		dialog.open();
		return null;
	}

}
