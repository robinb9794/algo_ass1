package gui.elements.buttons;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import interfaces.buttons.ButtonField;

public abstract class SuperButton extends JButton implements ButtonField, ActionListener{
	public SuperButton() {
		setBackground(Color.LIGHT_GRAY);
		addActionListener(this);
	}
}
