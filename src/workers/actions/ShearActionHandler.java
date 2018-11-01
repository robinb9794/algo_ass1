package workers.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import gui.elements.dialogs.ShearWindow;
import models.MorphValues;
import models.math.Matrix;
import workers.SuperUserInteractionHandler;

public class ShearActionHandler extends SuperUserInteractionHandler{
	private static ShearWindow shearWindow;
	
	public static void handle() {
		resetScreenListener();
		initDialog();
	}
	
	private static void initDialog() {
		shearWindow = new ShearWindow();
		shearWindow.initButtons();		
		addWindowListener();
		addListenersToButtons();
		shearWindow.setLocationRelativeTo(null);
		shearWindow.pack();
		shearWindow.setVisible(true);
	}
	
	private static void addWindowListener() {
		shearWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				shearWindow.dispose();
				enableSingleButton("Save");
			}
		});
	}
	
	private static void addListenersToButtons() {
		shearWindow.shearXLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double shearX = -MorphValues.SHEAR_X;
				double shearY = 0;
				setShearMatrix(shearX, shearY);
				morph();
			}
		});
		
		shearWindow.shearXRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double shearX = MorphValues.SHEAR_X;
				double shearY = 0;
				setShearMatrix(shearX, shearY);
				morph();
			}
		});
		
		shearWindow.shearYUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double shearX = 0;
				double shearY = -MorphValues.SHEAR_Y;
				setShearMatrix(shearX, shearY);
				morph();
			}
		});
		
		shearWindow.shearYDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double shearX = 0;
				double shearY = MorphValues.SHEAR_Y;
				setShearMatrix(shearX, shearY);
				morph();
			}
		});
	}
	
	private static void setShearMatrix(double shearX, double shearY) {
		Matrix shiftMatrix = Matrix.translate(-getCenterX(), -getCenterY());
		Matrix morphMatrix = viewModel.getMorphMatrix();
		morphMatrix = Matrix.multiply(shiftMatrix, morphMatrix);
		Matrix shearMatrix = Matrix.shear(shearX, shearY);
		morphMatrix = Matrix.multiply(shearMatrix, morphMatrix);
		shiftMatrix = Matrix.translate(getCenterX(), getCenterY());
		morphMatrix = Matrix.multiply(shiftMatrix, morphMatrix);
		viewModel.setMorphMatrix(morphMatrix);
	}
}
