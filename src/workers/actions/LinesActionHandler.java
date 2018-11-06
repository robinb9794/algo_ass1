package workers.actions;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JColorChooser;

import gui.elements.dialogs.ChooseColorsWindow;
import models.Mode;
import workers.PixelCoordinator;
import workers.SuperUserInteractionHandler;

public class LinesActionHandler extends SuperUserInteractionHandler{
	
	public static void handle() {
		if(userHasSelectedAtLeastOneImage()) {
			if(linesWindowIsAlreadyInitialized())
				colorWindow.setVisible(true);
			else
				initColorWindow();
			if(userIsMorphing())
				PixelCoordinator.setSourceAndTargetPixels(viewModel.getTargetPixels());;
			setCurrentMode(Mode.DRAWING);
			resetScreenListeners();
			addMouseMotionListenerToScreen();
			addMouseListenerToScreen();
		}else
			showErrorDialog("Please select at least one image first.");
	}
	
	private static boolean userHasSelectedAtLeastOneImage() {
		return viewModel.getSelectedImages().size() > 0;
	}
	
	private static boolean linesWindowIsAlreadyInitialized() {
		return colorWindow != null;
	}
	
	private static void initColorWindow() {
		colorWindow = new ChooseColorsWindow();
		colorWindow.initLabelsAndButtons(viewModel.getFirstColor(), viewModel.getSecondColor());
		addChangeListenerToColorPickers();
		colorWindow.setLocationRelativeTo(null);
		colorWindow.pack();
		colorWindow.setVisible(true);
	}
	
	private static void addChangeListenerToColorPickers() {
		colorWindow.firstColorPicker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color firstColor = JColorChooser.showDialog(null, "Pick first color:", viewModel.getFirstColor());
				if(firstColor == null)
					firstColor = viewModel.getFirstColor();
				viewModel.setFirstColor(firstColor);
				colorWindow.firstColorPicker.setBackground(firstColor);
			}			
		});
	}
	
	private static void addMouseMotionListenerToScreen() {
		viewModel.getScreen().addCustomMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				viewModel.setLineEndPoint(e.getPoint());
				PixelCoordinator.setTargetPixels(viewModel.getSourcePixels());
				drawLine();
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
				viewModel.setLineStartPoint(e.getPoint());;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				PixelCoordinator.setSourcePixels(viewModel.getTargetPixels());
				viewModel.setLineEndPoint(e.getPoint());
				enableSingleButton("Save");			
				enableSingleButton("Reset");
			}			
		});
	}
	
	private static void drawLine() {
		int x0 = (int) viewModel.getLineStartPoint().getX();
		int y0 = (int) viewModel.getLineStartPoint().getY();
		int x1 = (int) viewModel.getLineEndPoint().getX();
		int y1 = (int) viewModel.getLineEndPoint().getY();
		
		final int firstColor = viewModel.getFirstColor().getRGB();
		
		final int dx = Math.abs(x0 - x1);
		final int dy = Math.abs(y0 - y1);
		final int sgnDx = x0 < x1 ? 1 : -1;
		final int sgnDy = y0 < y1 ? 1 : -1;
		
		int shortD, longD, incXshort, incXlong, incYshort, incYlong;
		
		if(dx > dy) {
			shortD = dy;
			longD = dx;
			incXlong = sgnDx;
			incXshort = 0;
			incYlong = 0;
			incYshort = sgnDy;
		}else {
			shortD = dx;
			longD = dy;
			incXlong = 0;
			incXshort = sgnDx;
			incYlong = sgnDy;
			incYshort = 0;
		}
		
		int d = longD / 2;
		int x = x0;
		int y = y0;
		
		for(int i = 0; i <= longD; ++i) {
			int index = PixelCoordinator.getPixelIndex(x, y);
			if(PixelCoordinator.pixelIndexIsInScreenArea(index)) {
				PixelCoordinator.setSingleTargetPixel(index, firstColor);
				x += incXlong;
				y += incYlong;
				d += shortD;
				if(d >= longD){
					d -= longD;
					x += incXshort;
					y += incYshort;
				}
			}
			
		}
	}
}
