package net.ziwei.ui;

import net.ziwei.application.Activator;

import org.eclipse.jface.resource.ImageRegistry;

public class UI {

	public static final String								EMPTY_STRING					= "";											//$NON-NLS-1$
	public static final String								SPACE							= " ";											//$NON-NLS-1$
	public static final String								DASH							= "-";											//$NON-NLS-1$
	public static final String								SYMBOL_DOT						= ".";											//$NON-NLS-1$
	public static final ImageRegistry						IMAGE_REGISTRY;
	public static final String                              APPLICATION_IMAGE               = "icons/application/ziwei16.png";
	static{
		IMAGE_REGISTRY = Activator.getDefault().getImageRegistry();
		IMAGE_REGISTRY.put(APPLICATION_IMAGE, Activator.getImageDescriptor(APPLICATION_IMAGE));
	}
}

