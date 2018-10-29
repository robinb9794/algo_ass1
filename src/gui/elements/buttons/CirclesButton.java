package gui.elements.buttons;

import java.awt.event.ActionEvent;

public class CirclesButton extends SuperButton{
	public CirclesButton() {
		super();
	}

	@Override
	public void init() {
		setToolTipText("Draw circles");
		setEnabled(false);
		setIconFromUrl("https://cdn4.iconfinder.com/data/icons/religion-6/53/unification-one-three-circles-512.png");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
