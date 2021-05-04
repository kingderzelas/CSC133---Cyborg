package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command {

	private GameWorld gw; 
	
	public ExitCommand(GameWorld gw) {
		super("Exit Game");
		this.gw = gw;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Exit Game Command Pressed");
		Command cOk = new Command("Ok");
		Command cCancel = new Command("Cancel");
		Command[] cmds = new Command[] {cOk, cCancel};
		Command ConfirmExit = Dialog.show("Confirm Exit", "Are you sure you want to exit?", cmds);
		if(ConfirmExit == cOk) {
			gw.exitGame();
			System.out.println("Game Exited");
		}	
	}
}