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
				PixelCoordinator.setTargetPixels(viewModel.getSourcePixels());
				setRotationMatrix(rotateAlpha);
				morph();
				gui.reloadScreen();
			}
		});
	}
	
	private static void setRotationMatrix(int rotateAlpha) {
		Matrix shiftMatrix = Matrix.translate(-getCenterX(), -getCenterY()); 
		Matrix rotationMatrix = Matrix.rotate(rotateAlpha); 
		Matrix rotationAtOriginMatrix = Matrix.multiply(shiftMatrix, rotationMatrix);
		shiftMatrix = Matrix.translate(getCenterX(), getCenterY());
		Matrix backToSourceMatrix = Matrix.multiply(shiftMatrix, rotationAtOriginMatrix);
		Matrix morphMatrix = Matrix.multiply(backToSourceMatrix, viewModel.getMorphMatrix());
		viewModel.setMorphMatrix(morphMatrix);
	}
}
