package workers.actions;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import workers.PixelCoordinator;
import workers.SuperUserInteractionHandler;

public class SelectionActionHandler extends SuperUserInteractionHandler{	
	public static void handle() {
		if(userHasSelectedAtLeastOneImage()) {
			addMouseMotionListenerToScreen();
			addMouseListenerToScreen();
		}
		else
			showErrorDialog("Please select at least one image.");
	}
	
	private static boolean userHasSelectedAtLeastOneImage() {
		return viewModel.getSelectedImages().size() >= 1;
	}	
		
	private static void addMouseMotionListenerToScreen() {
		viewModel.getScreen().addCustomMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				viewModel.setSelectionEndPoint(e.getX(), e.getY());
				PixelCoordinator.setTargetPixels(viewModel.getSourcePixels());
				PixelCoordinator.setSelectionPixels();
				gui.reloadScreen();
			}
			
		});
	}
	
	private static void addMouseListenerToScreen() {
		viewModel.getScreen().addCustomMouseListener(new MouseAdapter() {		

			@Override
			public void mouseEntered(MouseEvent e) {
				viewModel.getScreen().setCustomCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				viewModel.setSelectionStartPoint(e.getX(), e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				viewModel.setSelectionEndPoint(e.getX(), e.getY());
				enableResetButton();
			}
			
		});
	}
}
