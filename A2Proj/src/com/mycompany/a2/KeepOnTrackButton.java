package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;

public class KeepOnTrackButton extends Button {	
	KeepOnTrackButton(String label) {
		super(label);
		//this.getAllStyles().setPadding(3,2);
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.BLUE);
		this.getAllStyles().setFgColor(ColorUtil.CYAN);
	}
}