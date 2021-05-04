package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TurnLeftCommand extends Command {

	private GameWorld gw;
	
	public TurnLeftCommand(GameWorld gw) {
		super("Turn Left");
		this.gw = gw;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.turnLeft(); 
		System.out.println("Cyborg Turning Left");
	}
}