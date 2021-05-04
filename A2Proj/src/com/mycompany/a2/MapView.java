package com.mycompany.a2;

import java.util.Observer;
import java.util.Observable;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
/*
 * Forgot that MapView was supposed to be an empty container. I commented out the code that
 * output the text to the MapView container so it outputs to the console like in A1
 */
public class MapView extends Container implements Observer {
	
	private GameWorldProxy gwp;
	
	//private TextArea textMap;
	
	public MapView(GameWorldProxy gwp) {
		this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		this.gwp = gwp;
		this.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(255,0,0)));
		
		//this.textMap = new TextArea();
		
		//this.add(this.textMap);
	}
	
	public void update (Observable o, Object arg) {
		this.gwp = (GameWorldProxy)o;
		//this.textMap.setText(""+this.gwp.printMap());
		System.out.println(gwp.printMap());
		this.repaint();
	}
}