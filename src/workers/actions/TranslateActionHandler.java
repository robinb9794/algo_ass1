package workers.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
	}
	
	private static void initDialog() {
		translateWindow = new TranslateWindow();
		translateWindow.initButtons();
		addWindowListener();
		addListenersToButtons();
		translateWindow.pack();
		translateWindow.setLocationRelativeTo(null);
		translateWindow.setVisible(true);
	}
	
	private static void addWindowListener() {
		translateWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				translateWindow.dispose();
				enableSingleButton("Save");
			}
		});
	}
	
	private static void addListenersToButtons() {
		translateWindow.left.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int translateX = -MorphValues.TRANSLATE_X;
				int translateY = 0;
				System.out.println("translateX: " + translateX);
				PixelCoordinator.setTargetPixels(viewModel.getSourcePixels());
				setTranslationMatrix(translateX, translateY);
				morph();
				gui.reloadScreen();
			}
		});
		
		translateWindow.right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int translateX = MorphValues.TRANSLATE_X;
				int translateY = 0;
				System.out.println("translateX: " + translateX);
				PixelCoordinator.setTargetPixels(viewModel.getSourcePixels());
				setTranslationMatrix(translateX, translateY);
				morph();
				gui.reloadScreen();
			}
		});
		
		translateWindow.up.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int translateX = 0;
				int translateY = -MorphValues.TRANSLATE_Y;
				System.out.println("translateY: " + translateY);
				PixelCoordinator.setTargetPixels(viewModel.getSourcePixels());
				setTranslationMatrix(translateX, translateY);
				morph();
				gui.reloadScreen();
			}
		});
		
		translateWindow.down.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int translateX = 0;
				int translateY = MorphValues.TRANSLATE_Y;
				System.out.println("translateY: " + translateY);
				PixelCoordinator.setTargetPixels(viewModel.getSourcePixels());
				setTranslationMatrix(translateX, translateY);
				morph();
				gui.reloadScreen();
			}
		});
	}
	
	private static void setTranslationMatrix(int translateX, int translateY) {
		Matrix translationMatrix = Matrix.translate(translateX, translateY);
		Matrix morphMatrix = Matrix.multiply(translationMatrix, viewModel.getMorphMatrix());
		viewModel.setMorphMatrix(morphMatrix);
	}
}