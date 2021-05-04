package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command {

	private GameWorld gw;
	
	public HelpCommand(GameWorld gw) {
		super("Help");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Here is a list of keys you can press: \n");
		System.out.println("Accelerate: Causes the cyborg to go faster\n");
		System.out.println("Brake: Causes the cyborg to stop\n");
		System.out.println("Turn Left: Turns the cyborg left\n");
		System.out.println("Turn Right: Turns the cyborg right\n");
		System.out.println("Cyborg Collision: Collides with a non-player cyborg\n");
		System.out.println("Base Collision: Collide with a user specified base\n");
		System.out.println("Energy Station Collision: Collide with an energy station\n");
		System.out.println("Drone Collision: Collide with a drone\n");
		System.out.println("Clock Tick: Advances the game clock by one tick\n");
		System.out.println("Exit Game: Prompts user confirming to exit game\n");
		System.out.println("Change Strategies: Changes the strategies of the NPC's\n");
		System.out.println("Toggle Sound: Turns sound on and off\n");
		System.out.println("About: Displays general information about the game\n");
	}
}