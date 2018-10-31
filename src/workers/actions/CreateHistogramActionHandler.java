package workers.actions;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import models.LoadedImage;
import workers.SuperUserInteractionHandler;

public class CreateHistogramActionHandler extends SuperUserInteractionHandler {
	public static void handle() {
		if(userHasSelectedOneImage()) {
			createHistogram();
		}else {
			showErrorDialog("Please select exactly one image.");
		}
	}
	
	private static boolean userHasSelectedOneImage() {
		return viewModel.getSelectedImages() != null && viewModel.getSelectedImages().size() == 1;
	}
	
	private static void createHistogram() {
		Writer writer;
		try {
			LoadedImage displayedImage = viewModel.getSelectedImages().get(0);
			Map<String, Integer> histogram = new HashMap<String, Integer>();
			for (int i = 0; i < viewModel.getScreenWidth() * viewModel.getScreenHeight(); i++) {
				String color = binaryToDec(Integer.toBinaryString(displayedImage.getGrabbedPixels()[i]));
				int number = 1;
				if (!histogram.containsKey(color)) {
					histogram.put(color, number);
				} else {
					number = histogram.get(color);
					histogram.replace(color, number + 1);
				}
			}
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./histogram.txt"), "UTF-8"));
			for (Entry e : histogram.entrySet()) {
				writer.write(e.getKey() + ":" + e.getValue() + "\n");
			}
			writer.close();
			showInfoDialog("Success!", "./histogram.txt has been created!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String binaryToDec(String bin){
		String b="",g="",r="";
		for(int i=0; i<8;i++){
			r=r+bin.charAt(8+i);
			g=g+bin.charAt(8+i+8);
			b=b+bin.charAt(8+i+2*8);			
		}
		return new Integer(Integer.parseInt(r,2)).toString()+"_"+new Integer(Integer.parseInt(g,2)).toString()+"_"+new Integer(Integer.parseInt(b,2)).toString();
	}
}
