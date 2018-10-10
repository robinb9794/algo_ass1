package interfaces.bar;

import interfaces.GUIElement;
import models.LoadedImage;

public interface DisplayedImage extends GUIElement{
	void addImageIconFromLoadedImage(LoadedImage loadedImage);
}
