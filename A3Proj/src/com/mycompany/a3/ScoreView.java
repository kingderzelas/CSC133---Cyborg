package com.mycompany.a3;

import java.util.Observer;
import java.util.Observable;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer {
	
	private GameWorldProxy gwp;
	private Label timeLabel;
	private Label soundLabel;
	private Label livesLabel;
	private String soundVal;
	private Label baseReached;
	private Label energyLevel;
	private Label damageLevel;
	
	public ScoreView(IGameWorld gwp) {
		
		this.gwp = (GameWorldProxy)gwp;
		
		if(gwp.getSound()) {
			this.soundVal = "ON";
		}else {
			this.soundVal = "OFF";
		}
		
		this.setLayout(new FlowLayout());
		
		/*Initializes the labels and gives them names */
		this.timeLabel = new Label("Time: " + this.gwp.getTick());
		this.soundLabel = new Label("Sound: " + this.soundVal);
		this.livesLabel = new Label("Lives: " + this.gwp.getLives());
		this.baseReached = new Label("Last Base Reached: " + this.gwp.getLastBaseReached());
		this.energyLevel = new Label("Energy Level: " + this.gwp.getEnergyLevel());
		this.damageLevel = new Label("Damage Level: " + this.gwp.getDamageLevel());
		
		/* Add all components to the container */
		this.add(this.timeLabel);
		this.add(this.soundLabel);
		this.add(this.livesLabel);
		this.add(this.baseReached);
		this.add(this.energyLevel);
		this.add(this.damageLevel);
				
	}
	
	public void update (Observable o, Object obj) {
		
		IGameWorld gw =  (IGameWorld) obj;
		
		if(gw.getSound()) {
			this.soundVal = "ON";
		}else {
			this.soundVal = "OFF";
		}
		this.timeLabel.setText("Time: " + gw.getTick());
		this.soundLabel.setText("Sound: " + this.soundVal);
		this.livesLabel.setText("Lives: " + gw.getLives());
		this.baseReached.setText("Last Base Reached: " + gw.getLastBaseReached());
		this.energyLevel.setText("Energy Level: " + gw.getEnergyLevel());
		this.damageLevel.setText("Damage Level: " + gw.getDamageLevel());
		
		this.repaint();
	}
		
}