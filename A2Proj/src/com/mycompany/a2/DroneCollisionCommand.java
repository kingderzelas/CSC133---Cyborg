package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class DroneCollisionCommand extends Command {

	private GameWorld gw;
	
	public DroneCollisionCommand(GameWorld gw) {
		super("Collide With Drone");
		this.gw = gw;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.droneCollision();
		System.out.println("Player Cyborg Collided With A Drone");
	}
}