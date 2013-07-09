package ziwei.perspectives;


import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import ziwei.views.DaYunView;
import ziwei.views.LiuNianView;
import ziwei.views.TianPanView;

public class PerspectiveFactoryMain implements IPerspectiveFactory {

	public static final String	PERSPECTIVE_ID		= "ziwei.perspectives.PerspectiveFactoryMain";
	private static final String	FOLDER_ID_RIGHT		= "right";
	private static final String	FOLDER_ID_LEFT		= "left";

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		final IFolderLayout leftFolder = layout.createFolder(
				FOLDER_ID_LEFT,
				IPageLayout.LEFT,
				0.5f,
				IPageLayout.ID_EDITOR_AREA);	
	
		leftFolder.addView(TianPanView.ID);
		
		final IFolderLayout rightFolder = layout.createFolder(
				FOLDER_ID_RIGHT,
				IPageLayout.RIGHT,
				0.5f,
				IPageLayout.ID_EDITOR_AREA);
		rightFolder.addView(DaYunView.ID);
		rightFolder.addView(LiuNianView.ID);
	}
}
