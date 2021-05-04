package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class StationCollisionCommand extends Command {

	int baseNumber;
	private GameWorld gw;
	
	public StationCollisionCommand(GameWorld gw) {
		super("Collide With Energy Station");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.stationCollision();
		System.out.println("Player Cyborg Collided With An Energy Station");
	}
}