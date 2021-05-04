package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ChangeStrategiesCommand  extends Command {
	
	private GameWorld gw;
	
	public ChangeStrategiesCommand(GameWorld gw) {
		super("Change Strategies");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.gw.changeStrategies();
		System.out.println("Change Stragies button has been pressed");
	}
}