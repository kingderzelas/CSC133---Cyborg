package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand  extends Command {
	
	public AboutCommand(GameWorld gw) {
		super("About");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("About Command Pressed!");
		Boolean bOk = Dialog.show("Keep-On-Track (A2Prj)", "Bryan Petruescu", "Close", null);
		if(bOk) {
			System.out.println("About window closed.");
		}	

	}
}