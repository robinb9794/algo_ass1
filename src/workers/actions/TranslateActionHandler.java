package workers.actions;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gui.elements.dialogs.TranslateWindow;
import models.MorphValues;
import models.math.Matrix;
import workers.PixelCoordinator;
import workers.SuperUserInteractionHandler;

public class TranslateActionHandler extends SuperUserInteractionHandler{
	private static TranslateWindow translateWindow;
	
	public static void handle() {
		initDialog();
		disableSingleButton("Translate");
	}
	
	private static void initDialog() {
		translateWindow = new TranslateWindow();
		addHorizontalElementsToDialog();
		addVerticalElementsToDialog();
		translateWindow.pack();
		translateWindow.setLocationRelativeTo(null);
		translateWindow.setVisible(true);
	}	
	
	private static void addHorizontalElementsToDialog() {
		translateWindow.add(new JLabel(" Left/Right:"));
		translateWindow.horizontalSlider = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);
		addHorizontalSliderListener();
		translateWindow.add(translateWindow.horizontalSlider);
	}
	
	private static void addHorizontalSliderListener() {
		translateWindow.horizontalSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("translateX: " + translateWindow.horizontalSlider.getValue());
				MorphValues.translateX = translateWindow.horizontalSlider.getValue() * -1;
				PixelCoordinator.setTargetPixels(viewModel.getSourcePixels());
				setTranslationMatrix();
				morph();
			}
		});
	}
	
	private static void addVerticalElementsToDialog() {
		translateWindow.add(new JLabel(" Up/Down:"));
		translateWindow.verticalSlider = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);
		addVerticalSliderListener();
		translateWindow.add(translateWindow.verticalSlider);
	}
	
	private static void addVerticalSliderListener() {
		translateWindow.verticalSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				MorphValues.translateY = translateWindow.horizontalSlider.getValue() * -1;
				setTranslationMatrix();
				morph();
			}
		});
	}
	
	private static void setTranslationMatrix() {
		Matrix translationMatrix = Matrix.translate(MorphValues.translateX, MorphValues.translateY);
		viewModel.setMorphMatrix(translationMatrix);
	}
}