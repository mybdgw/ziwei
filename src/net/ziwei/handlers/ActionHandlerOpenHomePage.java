package net.ziwei.handlers;

import net.ziwei.Messages;
import net.ziwei.util.Util;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;


public class ActionHandlerOpenHomePage extends AbstractHandler {

	public Object execute(final ExecutionEvent event) throws ExecutionException {

		Util.openLink(Display.getCurrent().getActiveShell(), Messages.External_Link_HomePage);

		return null;
	}

}
