package net.ziwei.perspectives;

import net.ziwei.views.DaYunView;
import net.ziwei.views.LiuNianView;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class PerspectiveFactorySecond implements IPerspectiveFactory {
	public static final String	PERSPECTIVE_ID	= "net.ziwei.perspectives.PerspectiveFactorySecond";

	private static final String	FOLDER_ID_TOP	= "top";
	private static final String	FOLDER_ID_LEFT	= "left";

	/**
	 * Creates the initial layout for a page.
	 */
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);

		final IFolderLayout leftFolder = layout.createFolder(FOLDER_ID_LEFT,
				IPageLayout.LEFT,
				0.4f,
				IPageLayout.ID_EDITOR_AREA);

		leftFolder.addView(DaYunView.ID);

	
		final IFolderLayout topFolder = layout.createFolder(FOLDER_ID_TOP,
				IPageLayout.TOP,
				0.5f,
				IPageLayout.ID_EDITOR_AREA);

		topFolder.addView(LiuNianView.ID);
	}
}
