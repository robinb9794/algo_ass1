package workers.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import gui.elements.dialogs.RotateWindow;
import models.MorphValues;
import models.math.Matrix;
import workers.PixelCoordinator;
import workers.SuperUserInteractionHandler;

public class RotateActionHandler extends SuperUserInteractionHandler{
	private static RotateWindow rotateWindow;
	
	public static void handle() {
		resetScreenListener();
		initDialog();
	}
	
	private static void initDialog() {
		rotateWindow = new RotateWindow();
		addWindowListener();
		addListenersToButtons();
		rotateWindow.pack();
		rotateWindow.setLocationRelativeTo(null);
		rotateWindow.setVisible(true);
	}
	
	private static void addWindowListener() {
		rotateWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				rotateWindow.dispose();
				enableSingleButton("Save");
			}
		});
	}
	
	private static void addListenersToButtons() {
		rotateWindow.clockwise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rotateAlpha = MorphValues.ROTATE_ALPHA;
				System.out.println("rotateAlpha: " + rotateAlpha);
				PixelCoordinator.setTargetPixels(viewModel.getTargetPixels());
				setRotationMatrix(rotateAlpha);
				morph();
				gui.reloadScreen();
			}
		});
	}
	
	private static void setRotationMatrix(int rotateAlpha) {
		Matrix translationToOriginMatrix = Matrix.translate(-getRotationCenterX(), -getRotationCenterY()); 
		Matrix rotationMatrix = Matrix.rotate(rotateAlpha); 
		Matrix rotationAtOriginMatrix = Matrix.multiply(translationToOriginMatrix, rotationMatrix); 
		Matrix translationToTargetMatrix = Matrix.translate(getRotationCenterX(), getRotationCenterY()); 
		Matrix rotateAtTargetMatrix = Matrix.multiply(rotationAtOriginMatrix, translationToTargetMatrix); 		
		Matrix morphMatrix = Matrix.multiply(rotateAtTargetMatrix, viewModel.getMorphMatrix());
		viewModel.setMorphMatrix(morphMatrix);
	}
	
	private static int getRotationCenterX() {
		return getStartX() + ((getEndX() - getStartX()) / 2);
	}
	
	private static int getRotationCenterY() {
		return getStartY() + ((getEndY() - getStartY()) / 2);
	}
}
