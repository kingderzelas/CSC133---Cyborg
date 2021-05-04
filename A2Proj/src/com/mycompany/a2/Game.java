package com.mycompany.a2;

import com.codename1.ui.Form;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Toolbar;

public class Game extends Form {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private GameWorldProxy gwp;
	
	public Game() {
		this.gw = new GameWorld();
		this.gwp = new GameWorldProxy(this.gw);
		this.mv = new MapView(gwp);
		this.sv = new ScoreView(gwp);
		
		gw.addObserver(gwp);
		gwp.addObserver(mv);
		gwp.addObserver(sv);
		
		this.setLayout(new BorderLayout());
		
		Toolbar trackToolbar = new Toolbar();
		this.setToolbar(trackToolbar);
		trackToolbar.setTitle("Keep-on-Track Game");
		
		/* Create the panel containers to house buttons */
		Container sidePanel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container southPanel = new Container(new FlowLayout(CENTER));
		Container westPanel = new Container(new FlowLayout(CENTER));
		Container eastPanel = new Container(new FlowLayout(CENTER));
		Container northPanel = new Container(new FlowLayout(CENTER));
		
		/* Sets the west and east panels to layer the buttons vertically */
		westPanel.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		eastPanel.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

		/* Create commands for side menu and title bar*/
		AboutCommand aboutCommand = new AboutCommand(gw);
		ExitCommand exitCommand = new ExitCommand(gw);
		HelpCommand helpCommand = new HelpCommand(gw);
		ToggleSoundCommand toggleSoundCommand = new ToggleSoundCommand(gw);
		
		/* Create all required commands for manipulating the GW */
		AccelerateCommand accelerateCommand = new AccelerateCommand(gw); 
		BaseCollisionCommand baseCollisionCommand = new BaseCollisionCommand(gw);
		BrakeCommand brakeCommand = new BrakeCommand(gw);
		ChangeStrategiesCommand changeStrategiesCommand = new ChangeStrategiesCommand(gw);
		CyborgCollisionCommand cyborgCollisionCommand = new CyborgCollisionCommand(gw);
		DroneCollisionCommand droneCollisionCommand = new DroneCollisionCommand(gw);
		StationCollisionCommand stationCollisionCommand = new StationCollisionCommand(gw);
		TickCommand tickCommand = new TickCommand(gw);
		TurnLeftCommand turnLeftCommand = new TurnLeftCommand(gw);
		TurnRightCommand turnRightCommand = new TurnRightCommand(gw);
		
		/* Create buttons for commands */
		CheckBox toggleSound = new CheckBox("Sound");
		toggleSound.setSelected(true);
		 
		KeepOnTrackButton accelerateButton = new KeepOnTrackButton("Accelerate");
		KeepOnTrackButton baseCollisionButton = new KeepOnTrackButton("Base Collision");
		KeepOnTrackButton brakeButton = new KeepOnTrackButton("Brake"); 
		KeepOnTrackButton changeStrategiesButton = new KeepOnTrackButton("Change Strategies");
		KeepOnTrackButton cyborgCollisionButton = new KeepOnTrackButton("Cyborg Collision");
		KeepOnTrackButton droneCollisionButton = new KeepOnTrackButton("Drone Collision");
		KeepOnTrackButton stationCollisionButton = new KeepOnTrackButton("Energy Station Collision");
		KeepOnTrackButton tickCommandButton = new KeepOnTrackButton("Clock Tick");
		KeepOnTrackButton turnLeftButton = new KeepOnTrackButton("Turn Left");
		KeepOnTrackButton turnRightButton = new KeepOnTrackButton("Turn Right");
		KeepOnTrackButton exitButton = new KeepOnTrackButton("Exit Game");
		KeepOnTrackButton aboutButton = new KeepOnTrackButton("About");
		KeepOnTrackButton helpButton = new KeepOnTrackButton("Help");
		KeepOnTrackButton toggleSoundButton = new KeepOnTrackButton("Toggle Sound");


		/* Bind buttons to their associated commands */	
		toggleSound.setCommand(toggleSoundCommand);
		accelerateButton.setCommand(accelerateCommand);
		baseCollisionButton.setCommand(baseCollisionCommand);
		brakeButton.setCommand(brakeCommand);
		changeStrategiesButton.setCommand(changeStrategiesCommand);
		cyborgCollisionButton.setCommand(cyborgCollisionCommand);
		droneCollisionButton.setCommand(droneCollisionCommand);
		stationCollisionButton.setCommand(stationCollisionCommand);
		tickCommandButton.setCommand(tickCommand);
		turnLeftButton.setCommand(turnLeftCommand);
		turnRightButton.setCommand(turnRightCommand);
		exitButton.setCommand(exitCommand);
		aboutButton.setCommand(aboutCommand);
		helpButton.setCommand(helpCommand);
		toggleSoundButton.setCommand(toggleSoundCommand);
		
		/* Add buttons to side panel */
		sidePanel.add(toggleSound);
		sidePanel.add(exitButton);
		sidePanel.add(aboutButton);
		sidePanel.add(helpButton);
		
		/* Add buttons to south panel */
		southPanel.add(cyborgCollisionButton);
		southPanel.add(baseCollisionButton);
		southPanel.add(stationCollisionButton);
		southPanel.add(droneCollisionButton);
		southPanel.add(tickCommandButton);
		southPanel.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		/* Add buttons to west panel */
		westPanel.add(accelerateButton);
		westPanel.add(turnLeftButton);
		westPanel.add(changeStrategiesButton);
		westPanel.getAllStyles().setPadding(Component.TOP, 500);
		westPanel.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		/* Add buttons to east panel */
		eastPanel.add(brakeButton);
		eastPanel.add(turnRightButton);
		eastPanel.getAllStyles().setPadding(Component.TOP, 500);
		eastPanel.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		/* Add buttons to north panel */
		northPanel.add(sv);
		
		/* Add commands to side menu */
		trackToolbar.addCommandToSideMenu(aboutCommand);
		trackToolbar.addCommandToSideMenu(exitCommand);
		trackToolbar.addCommandToSideMenu(toggleSoundCommand);
		trackToolbar.addCommandToRightBar(helpCommand);
		
		/* Add key listeners for key bindings specified in Assignment 2 */
		addKeyListener('a', accelerateCommand);
		addKeyListener('b', brakeCommand);
		addKeyListener('l', turnLeftCommand);
		addKeyListener('r', turnRightCommand);
		addKeyListener('e', stationCollisionCommand);
		addKeyListener('d', droneCollisionCommand);
		addKeyListener('t', tickCommand);
		
		/* Add all the panels to their location in the form */
		this.add(BorderLayout.CENTER, mv);
		this.add(BorderLayout.NORTH, northPanel);
		this.add(BorderLayout.WEST, westPanel);
		this.add(BorderLayout.SOUTH, southPanel);
		this.add(BorderLayout.EAST, eastPanel);

		this.show();
				
		gw.init();
		this.gw.setDimensions(mv.getWidth(), mv.getHeight());
		
		
	
	}//end game() constructor

}//end game()
		

