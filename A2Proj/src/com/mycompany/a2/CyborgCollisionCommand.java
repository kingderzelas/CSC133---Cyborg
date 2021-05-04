package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CyborgCollisionCommand extends Command {

	private GameWorld gw;
	
	public CyborgCollisionCommand(GameWorld gw) {
		super("Colldie With NPC");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.cyborgCollision();
		System.out.println("Player Cyborg Collided With A Non-Player Cyborg");
	}
}