package workers.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.elements.dialogs.TranslateWindow;
import models.MorphValues;
import models.math.Matrix;
import workers.PixelCoordinator;
import workers.SuperUserInteractionHandler;

public class TranslateActionHandler extends SuperUserInteractionHandler{
	private static TranslateWindow translateWindow;
	
	public static void handle() {
		resetScreenListener();
		initDialog();
		disableSingleButton("Translate");
	}
	
	private static void initDialog() {
		translateWindow = new TranslateWindow();
		translateWindow.initButtons();
		addListenerToButtons();
		translateWindow.pack();
		translateWindow.setLocationRelativeTo(null);
		translateWindow.setVisible(true);
	}
	
	private static void addListenerToButtons() {
		translateWindow.left.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MorphValues.translateX -= 5;
				System.out.println("translateX: " + MorphValues.translateX);
				PixelCoordinator.setTargetPixels(viewModel.getSourcePixels());
				setTranslationMatrix();
				morph();
				gui.reloadScreen();
			}
		});
		
		translateWindow.right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MorphValues.translateX += 5;
				System.out.println("translateX: " + MorphValues.translateX);
				PixelCoordinator.setTargetPixels(viewModel.getSourcePixels());
				setTranslationMatrix();
				morph();
				gui.reloadScreen();
			}
		});
		
		translateWindow.up.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MorphValues.translateY -= 5;
				System.out.println("translateY: " + MorphValues.translateY);
				PixelCoordinator.setTargetPixels(viewModel.getSourcePixels());
				setTranslationMatrix();
				morph();
				gui.reloadScreen();
			}
		});
		
		translateWindow.down.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MorphValues.translateY += 5;
				System.out.println("translateY: " + MorphValues.translateY);
				PixelCoordinator.setTargetPixels(viewModel.getSourcePixels());
				setTranslationMatrix();
				morph();
				gui.reloadScreen();
			}
		});
	}
	
	private static void setTranslationMatrix() {
		Matrix translationMatrix = Matrix.translate(MorphValues.translateX, MorphValues.translateY);
		viewModel.setMorphMatrix(translationMatrix);
	}
}