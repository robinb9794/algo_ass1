package workers;

import java.util.Map.Entry;

import javax.swing.JOptionPane;

import interfaces.View;
import interfaces.buttons.ButtonField;
import models.LoadedImage;
import models.ViewModel;
import models.math.Matrix;
import models.math.Vector;

public class SuperUserInteractionHandler {
	protected static ViewModel viewModel;
	protected static View gui;
	
	protected static boolean isFading;
		
	public static void init(ViewModel viewModel, View gui) {
		SuperUserInteractionHandler.viewModel = viewModel;
		SuperUserInteractionHandler.gui = gui;
		isFading = false;		
	}
	
	protected static void showInfoDialog(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	protected static void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Error!", JOptionPane.ERROR_MESSAGE);
	}
	
	protected static void enableSingleButton(String value) {
		for(Entry<ButtonField, String> entry : viewModel.getButtons().entrySet()) {
			ButtonField button = entry.getKey();
			String buttonValue = entry.getValue();
		    if(buttonValue.equals(value)) {
		    	button.enableButton(true);
		    }
		}
	}
	
	protected static void disableSingleButton(String value) {
		for(Entry<ButtonField, String> entry : viewModel.getButtons().entrySet()) {
			ButtonField button = entry.getKey();
			String buttonValue = entry.getValue();
		    if(buttonValue.equals(value)) {
		    	button.enableButton(false);
		    }
		}
	}
	
	protected static void enableOrDisableButtonsAfterSelection(boolean enable) {
		for(Entry<ButtonField, String> entry : viewModel.getButtons().entrySet()) {
			ButtonField button = entry.getKey();
			String value = entry.getValue();
		    switch(value) {
		    case "Translate":
		    	button.enableButton(enable);
		    	break;
		    case "Rotate":
		    	button.enableButton(enable);
		    	break;
		    case "Scale":
		    	button.enableButton(enable);
		    	break;
		    case "Shear":
		    	button.enableButton(enable);
		    	break;
		    case "Lines":
		    	button.enableButton(enable);
		    	break;
		    case "Circles":
		    	button.enableButton(enable);
		    	break;
		    case "Reset":
		    	button.enableButton(enable);
		    	break;
		    }
		}
	}
	
	protected static void resetScreenListener() {
		viewModel.getScreen().resetMouseActions();
	}
	
	protected static void morph() {
		LoadedImage backgroundImage = viewModel.getSelectedImages().getFirst();
		for(int i = 0; i < viewModel.getScreenWidth(); i++) {
			for(int j = 0; j < viewModel.getScreenHeight(); j++) {
				Vector targetVector = new Vector(i, j);
				int targetIndex = PixelCoordinator.getPixelIndex(targetVector.getX(), targetVector.getY());
				Vector sourceVector = Matrix.multiply(viewModel.getMorphMatrix(), targetVector);
				int sourceIndex = PixelCoordinator.getPixelIndex(sourceVector.getX(), sourceVector.getY());
				if(sourcePixelIsInSelectionArea(sourceVector)) {
					int morphPixel = PixelCoordinator.getSingleSourcePixel(sourceIndex);
					PixelCoordinator.setSingleTargetPixel(targetIndex, morphPixel);
				}else {
					int backgroundPixel = PixelCoordinator.getSinglePixelFromImage(backgroundImage, targetIndex);
					PixelCoordinator.setSingleTargetPixel(targetIndex, backgroundPixel);
				}
			}
		}
		gui.reloadScreen();
	}
	
	protected static int getStartX() {
		return Math.min((int) viewModel.getSelectionStartPoint().getX(), (int) viewModel.getSelectionEndPoint().getX());
	}
	
	protected static int getEndX() {
		return Math.max((int) viewModel.getSelectionStartPoint().getX(), (int) viewModel.getSelectionEndPoint().getX());
	}
	
	protected static int getStartY() {
		return Math.min((int) viewModel.getSelectionStartPoint().getY(), (int) viewModel.getSelectionEndPoint().getY());
	}
	
	protected static int getEndY() {
		return Math.max((int) viewModel.getSelectionStartPoint().getY(), (int) viewModel.getSelectionEndPoint().getY());
	}
	
	private static boolean sourcePixelIsInSelectionArea(Vector sourceVector) {
		return sourceVector.getX() >= getStartX() && sourceVector.getX() <= getEndX() && sourceVector.getY() >= getStartY() && sourceVector.getY() <= getEndY();
	}
}
