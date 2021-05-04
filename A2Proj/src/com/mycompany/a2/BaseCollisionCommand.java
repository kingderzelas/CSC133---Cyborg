package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class BaseCollisionCommand extends Command {

	private GameWorld gw;
	
	public BaseCollisionCommand(GameWorld gw) {
		super("Collide With Base");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Command cOk = new Command("Ok");
		Command cCancel = new Command("Cancel");
		Command[] cmds = new Command[] {cOk, cCancel};
		TextField myTF = new TextField();

		Command c = Dialog.show("Enter the base number to advance to:", myTF, cmds);
		
		try {
			int baseNumber = Integer.parseInt(myTF.getText());
			if(c == cOk && 1 <= baseNumber && baseNumber <= 4) {
				gw.baseCollision(baseNumber);
				System.out.println("Colliding with base #" + baseNumber);
			}
			else
				System.out.println("Please input a number between 1 and 4");
		}catch(NumberFormatException e1) {
			System.out.println("Error, not a number");
		}	
	}
}