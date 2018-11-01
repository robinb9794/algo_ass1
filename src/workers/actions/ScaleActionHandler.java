package workers.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import gui.elements.dialogs.ScaleWindow;
import models.MorphValues;
import models.math.Matrix;
import workers.PixelCoordinator;
import workers.SuperUserInteractionHandler;

public class ScaleActionHandler extends SuperUserInteractionHandler {
	private static ScaleWindow scaleWindow;
	
	public static void handle() {
		resetScreenListener();
		initDialog();
	}
	
	private static void initDialog() {
		scaleWindow = new ScaleWindow();
		addWindowListener();
		addListenerToButton();
		scaleWindow.pack();
		scaleWindow.setLocationRelativeTo(null);
		scaleWindow.setVisible(true);
	}
	
	private static void addWindowListener() {
		scaleWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				enableSingleButton("Save");
				scaleWindow.dispose();
			}
		});
	}
	
	private static void addListenerToButton() {
		scaleWindow.scale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hier");
				double scale = MorphValues.SCALE;
				PixelCoordinator.setTargetPixels(viewModel.getSourcePixels());
				setScaleMatrix(scale);
				morph();
			}
		});
	}
	
	private static void setScaleMatrix(double scale) {
		Matrix shiftMatrix = Matrix.translate(-getCenterX(), -getCenterY());
		Matrix morphMatrix = viewModel.getMorphMatrix();
		morphMatrix = Matrix.multiply(shiftMatrix, morphMatrix);
		Matrix scaleMatrix = Matrix.scale(scale);
		morphMatrix = Matrix.multiply(scaleMatrix, morphMatrix);
		shiftMatrix = Matrix.translate(getCenterX(), getCenterY());
		morphMatrix = Matrix.multiply(shiftMatrix, morphMatrix);
		viewModel.setMorphMatrix(morphMatrix);
	}
}
