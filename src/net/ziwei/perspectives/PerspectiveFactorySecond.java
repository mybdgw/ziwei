package net.ziwei.perspectives;

import net.ziwei.views.DaYunView;
import net.ziwei.views.LiuNianView;
import net.ziwei.views.TianPanView;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class PerspectiveFactorySecond implements IPerspectiveFactory {
	public static final String	PERSPECTIVE_ID	= "net.ziwei.perspectives.PerspectiveFactorySecond";

	private static final String	FOLDER_ID_RIGHT	= "right";
	private static final String	FOLDER_ID_LEFT	= "left";

	/**
	 * Creates the initial layout for a page.
	 */
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);

		final IFolderLayout leftFolder = layout.createFolder(FOLDER_ID_LEFT,
				IPageLayout.LEFT,
				0.5f,
				IPageLayout.ID_EDITOR_AREA);
		leftFolder.addView(TianPanView.ID);		

	
		final IFolderLayout rightFolder = layout.createFolder(FOLDER_ID_RIGHT,
				IPageLayout.RIGHT,
				0.5f,
				IPageLayout.ID_EDITOR_AREA);
		rightFolder.addView(DaYunView.ID);
		rightFolder.addView(LiuNianView.ID);
		
	}
}
