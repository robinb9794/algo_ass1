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
				PixelCoordinator.setTargetPixels(viewModel.getSourcePixels());
				setRotationMatrix(rotateAlpha);
				morph();
			}
		});
	}
	
	private static void setRotationMatrix(int rotateAlpha) {
		Matrix shiftMatrix = Matrix.translate(-getCenterX(), -getCenterY());
		Matrix morphMatrix = viewModel.getMorphMatrix();
		morphMatrix = Matrix.multiply(shiftMatrix, morphMatrix);
		Matrix rotationMatrix = Matrix.rotate(rotateAlpha); 
		morphMatrix = Matrix.multiply(rotationMatrix, morphMatrix);
		shiftMatrix = Matrix.translate(getCenterX(), getCenterY());
		morphMatrix = Matrix.multiply(shiftMatrix, morphMatrix);
		viewModel.setMorphMatrix(morphMatrix);
	}
}
