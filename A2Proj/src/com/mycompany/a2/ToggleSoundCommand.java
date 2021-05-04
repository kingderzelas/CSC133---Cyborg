package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ToggleSoundCommand  extends Command {

	private GameWorld gw;
	
	public ToggleSoundCommand(GameWorld gw) {
		super("Toggle Sound");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.gw.toggleSound();
		System.out.println("Toggle Sound");

	}
}