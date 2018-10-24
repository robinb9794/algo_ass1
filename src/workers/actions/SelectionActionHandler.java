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
			resetScreenListener();
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
				select();
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
				enableSingleButton("Reset");
			}
			
		});
	}
	
	public static void select() {
		int selectionStartX = getStartX();
		int selectionEndX= getEndX();
		int selectionStartY = getStartY();
		int selectionEndY = getEndY();
		for(int i = selectionStartX; i <= selectionEndX; i++) {
			for(int j = selectionStartY; j <= selectionEndY; j++) {
				if(pixelIsOnSelectionLine(i, j, selectionStartX, selectionEndX, selectionStartY, selectionEndY)) {
					int index = PixelCoordinator.getPixelIndex(i, j);
					int selectionColor = getSelectionColor();
					PixelCoordinator.setSingleTargetPixel(index, selectionColor);
				}							
			}
		}
	}
	
	private static int getStartX() {
		return Math.min((int) viewModel.getSelectionStartPoint().getX(), (int) viewModel.getSelectionEndPoint().getX());
	}
	
	private static int getEndX() {
		return Math.max((int) viewModel.getSelectionStartPoint().getX(), (int) viewModel.getSelectionEndPoint().getX());
	}
	
	private static int getStartY() {
		return Math.min((int) viewModel.getSelectionStartPoint().getY(), (int) viewModel.getSelectionEndPoint().getY());
	}
	
	private static int getEndY() {
		return Math.max((int) viewModel.getSelectionStartPoint().getY(), (int) viewModel.getSelectionEndPoint().getY());
	}
	
	private static boolean pixelIsOnSelectionLine(int i, int j, int startX, int endX, int startY, int endY) {
		return i == startX || i == endX || j == startY || j == endY ;
	}
	
	private static int getSelectionColor() {
		return 255 << 24 | 255 << 16 | 0 << 8 | 78;
	}
}
