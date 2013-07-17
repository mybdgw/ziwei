package net.ziwei.perspectives;

import net.ziwei.views.TianPanView;
import net.ziwei.views.DaYunView;
import net.ziwei.views.LiuNianView;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class PerspectiveFactoryFirst implements IPerspectiveFactory {

	public static final String			PERSPECTIVE_ID		= "net.ziwei.perspectives.PerspectiveFactoryFirst";
	private static final String	FOLDER_ID_LEFT		= "left";


	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		
		final IFolderLayout leftFolder = layout.createFolder(
				FOLDER_ID_LEFT,
				IPageLayout.LEFT,
				1.0f,
				IPageLayout.ID_EDITOR_AREA);
		leftFolder.addView(TianPanView.ID);		
		leftFolder.addView(DaYunView.ID);
		leftFolder.addView(LiuNianView.ID);		
	}
}
