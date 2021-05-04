package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

public class Game extends Form 
{
	private GameWorld gw;
	
	public Game()
	{
		gw = new GameWorld();
		gw.init();
		play();
	}
	
	private void play()
	{
		Label textLabel = new Label ("Enter a command: ");
		this.addComponent(textLabel);
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		this.show();
		
		myTextField.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent evt){
				
				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				if (sCommand.length() != 0)
					switch (sCommand.charAt(0)){
						//given command from player, call method from GameWord to 
						//accelerate the cyborg
						case 'a':{
							gw.accelerate();
							System.out.println("Cyborg is accelerating\n");
							break;
						}
							
						//given command from player, call method from GameWord to
						//cause the cyborg to stop
						case 'b':{
							gw.brake();
							System.out.println("Cyborg is braking\n");
							break;
						}
							
						//given command from player, call method from GameWord to
						//make the cyborg turn left
						case 'l':{
							gw.turnLeft('l');
							System.out.println("Cyborg is turning left\n");
							break;
						}
							
						//given command from player, call method from GameWord to
						//make the cyborg turn right
						case 'r':{
							gw.turnRight('r');
							System.out.println("Cyborg is turning right\n");
							break;
						}
							
						//given command from player, call method from GameWord to
						//simulate a collision with another cyborg
						case 'c':{
							gw.cyborgCollision();
							System.out.println("Cyborg collided with another cyborg");
							break;
						}
							
						//given command from player, call method from GameWord to
						//simulate collision with base 1
						case '1':{
							gw.baseCollision(1);
							System.out.println("Cyborg reached base 1");
							break;
						}
							
						//given command from player, call method from GameWord to
						//simulate collision with base 2
						case '2':{
							gw.baseCollision(2);
							System.out.println("Cyborg reached base 2");
							break;
						}
							
						//given command from player, call method from GameWord to
						//simulate collision with base 3
						case '3':{
							gw.baseCollision(3);
							System.out.println("Cyborg reached base 3");
							break;
						}
							
						//given command from player, call method from GameWord to
						//simulate collision with base 4
						case '4':{
							gw.baseCollision(4);
							System.out.println("Cyborg reached base 4");
							break;
						}
							
						//given command from player, call method from GameWord to
						//simulate collision with base 5
						case '5':{
							gw.baseCollision(5);
							System.out.println("Cyborg reached base 5");
							break;
						}
							
						//given command from player, call method from GameWord to
						//simulate collision with base 6
						case '6':{
							gw.baseCollision(6);
							System.out.println("Cyborg reached base 6");
							break;
						}
							
						//given command from player, call method from GameWord to
						//simulate collision with base 7
						case '7':{
							gw.baseCollision(7);
							System.out.println("Cyborg reached base 7");
							break;
						}
							
						//given command from player, call method from GameWord to
						//simulate collision with base 8
						case '8':{
							gw.baseCollision(8);
							System.out.println("Cyborg reached base 8");
							break;
						}
							
						//given command from player, call method from GameWord to
						//simulate collision with base 9
						case '9':{
							gw.baseCollision(9);
							System.out.println("Cyborg reached base 9");
							break;
						}
							
						//given command from player, call method from GameWord to
						//simulate collision with an energy station
						case 'e':{
							gw.stationCollision();
							System.out.println("Cyborg collided with an energy station, energy restored");
							break;
						}
							
						//given command from player, call method from GameWord to
						//simulate collision with a drone
						case 'g':{
							gw.droneCollision();
							System.out.println("Cyborg was hit by a drone");
							break;
						}
							
						//given command from player, call method from GameWord to
						//advance the game clock
						case 't':{
							gw.clockTick();
							System.out.println("Clock time is advanced by one tick");
							break;
						}
							
						//given command from player, call method from GameWord to
						//display current game state data
						case 'd':{
							System.out.println("Displaying current game state data: \n");
							gw.display();
							break;
						}
							
						//given command from player, call method from GameWord to
						//display location of all objects in the world
						case 'm':{
							System.out.println("Displaying map of the game world: \n");
							gw.map();
							break;
						}
							
						//given command from player, call method from GameWord to
						//cause the game to terminate if user accepts
						case 'x':{
							System.out.println("Would you like to exit the game?");
							break;
						}
							
						//given command from player, call method from GameWord
						//for the user to say yes
						case 'y':{
							System.out.println("Exiting game");
							gw.exitGame();
							break;
						}
							
						//given command from player, call method from GameWord
						//for the user to say no
						case 'n':{
							System.out.println("Exit game cancelled");
							break;
						}
							
						default:
							System.err.println("Not a valid command");
							break;
					}//end switch statement
				}//end actionPerformed
		});//end .addActionListener
	}//end play()
}//end Game()