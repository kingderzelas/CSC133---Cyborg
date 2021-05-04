package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class GameWorld extends Observable implements IGameWorld {
	private int lives;
	private int tick;
	private boolean sound;
	private float worldHeight;
	private float worldWidth;
	private int lastBaseReached;
	private int energyLevel;
	private int damageLevel;
	private int elapsedTime;
	private GameObjectCollection gameObjectCollection;
	private IIterator gameIterator;
	private Random rand = new Random();
	private PlayerCyborg playerCyborg;
	private Sound explosion;
	
	//Created the base variables here for the NPC comparison in the tick command
	private Base baseOne;
	private Base baseTwo;
	private Base baseThree;
	private Base baseFour;
	
	public void init(){
		this.tick = 0;
		this.sound = true;
		this.lives = 3;
		this.worldWidth = 0;
		this.worldHeight = 0;
		this.gameObjectCollection = new GameObjectCollection();
		
		/* Initialize the same bases from A1Prj */
		baseOne = new Base(100, 200);
		baseOne.setSequenceNumber(1);
		baseTwo = new Base(567, 782);
		baseTwo.setSequenceNumber(2);
		baseThree = new Base(2, 980);
		baseThree.setSequenceNumber(3);
		baseFour = new Base(255, 600);
		baseFour.setSequenceNumber(4);
		this.gameObjectCollection.add(baseOne);
		this.gameObjectCollection.add(baseTwo);
		this.gameObjectCollection.add(baseThree);
		this.gameObjectCollection.add(baseFour);
		
		/* Initializes the same drones from A1Prj, but set to the confines of the new dimensions */
		Drone droneOne = new Drone();
		Drone droneTwo = new Drone();
		this.gameObjectCollection.add(droneOne);
		this.gameObjectCollection.add(droneTwo);
		droneOne.setLocation(this.rand.nextFloat() * worldWidth, this.rand.nextFloat() * worldHeight);
		droneTwo.setLocation(this.rand.nextFloat() * worldWidth, this.rand.nextFloat() * worldHeight);
		
		/* Initializes the same energy stations from A1Prj, but set to the confines of the new dimensions */
		EnergyStation energyStationOne = new EnergyStation();
		EnergyStation energyStationTwo = new EnergyStation();
		this.gameObjectCollection.add(energyStationOne);
		this.gameObjectCollection.add(energyStationTwo);
		energyStationOne.setLocation(this.rand.nextFloat() * worldWidth, this.rand.nextFloat() * worldHeight);
		energyStationTwo.setLocation(this.rand.nextFloat() * worldWidth, this.rand.nextFloat() * worldHeight);
		
		/* Initializes the player cyborg if it doesnt already exist*/
		if(this.playerCyborg == null) {
			this.playerCyborg = PlayerCyborg.getPlayerCyborg(baseOne.getLocation().getX(), baseOne.getLocation().getY());
			this.gameObjectCollection.add(this.playerCyborg);
		    this.setChanged();
		    this.notifyObservers(this);
		}
		else {
			System.out.println("Player Cyborg entity already exists");
		}
		
		/* Initializes the non player cyborgs */
		NonPlayerCyborg cyborgOne = new NonPlayerCyborg(baseOne.getLocation().getX() + 20, baseOne.getLocation().getY() + 20);
		cyborgOne.setStrategy(new BaseStrategy());
		NonPlayerCyborg cyborgTwo = new NonPlayerCyborg(baseOne.getLocation().getX() + 15, baseOne.getLocation().getY() + 15);
		cyborgTwo.setStrategy(new AttackStrategy());
		NonPlayerCyborg cyborgThree = new NonPlayerCyborg(baseOne.getLocation().getX() + 10, baseOne.getLocation().getY() + 10);
		cyborgThree.setStrategy(new AttackStrategy());
		this.gameObjectCollection.add(cyborgOne);
		this.gameObjectCollection.add(cyborgTwo);
		this.gameObjectCollection.add(cyborgThree);

	    this.setChanged();
	    this.notifyObservers(this);
	}
	
/**************************************************************
 * 													    	  *
 * The following are functions to manipulating the game world *
 * 													          *
 **************************************************************/
	
	/*
	 * This is the function to change strategies. Included the @SuppressWarnings because I had to initalize
	 * nonPlayerCyborg to null to be able to use it in the iterator.
	 */
	public void changeStrategies() {
		BaseStrategy baseStrategy = new BaseStrategy();
		AttackStrategy attackStrategy = new AttackStrategy();
		this.gameIterator = this.gameObjectCollection.getIterator() ;
		
		while (gameIterator.hasNext()) {
			GameObject gameObject = gameIterator.getNext();
			if(gameObject instanceof NonPlayerCyborg) {
				NonPlayerCyborg nonPlayerCyborg = (NonPlayerCyborg) gameObject;
				//Determines what strategy the current NPC is using, and changes it to the other strategy
				if(nonPlayerCyborg.getCurStrategy() == baseStrategy)
					nonPlayerCyborg.setStrategy(attackStrategy);
				else if(nonPlayerCyborg.getCurStrategy() == attackStrategy)
					nonPlayerCyborg.setStrategy(baseStrategy);
			}
		}
	}
	
	public void accelerate(){
		this.playerCyborg.setSpeed(this.playerCyborg.getSpeed() + 3);
		
	    this.setChanged();
	    this.notifyObservers(this);
	}
	
	public void brake(){
		this.playerCyborg.setSpeed(0);
		
	    this.setChanged();
	    this.notifyObservers(this);
	}

	public void turnLeft(){
		this.playerCyborg.turn('l');
		
	    this.setChanged();
	    this.notifyObservers(this);
	}

	public void turnRight(){
		this.playerCyborg.turn('r');
		
	    this.setChanged();
	    this.notifyObservers(this);
	}

	public void cyborgCollision(){
		// Code for effect on player for a cyborg collision
		this.playerCyborg.setDamageLevel(this.playerCyborg.getDamageLevel() + 1);
		this.playerCyborg.setCurMaxSpeed(this.playerCyborg.getCurMaxSpeed() - this.playerCyborg.getDamageLevel());
		this.playerCyborg.setColor(0, this.playerCyborg.getColor() - 25, 0);
		if (this.playerCyborg.getCurMaxSpeed() < this.playerCyborg.getSpeed())
			this.playerCyborg.setSpeed(this.playerCyborg.getCurMaxSpeed());
		
		// Finds an NPC and increases its damage level for collision with the player
		getNonPlayerCyborg();
		this.getNonPlayerCyborg().setDamageLevel(this.getNonPlayerCyborg().getDamageLevel() + 1);
		
	    this.setChanged();
	    this.notifyObservers(this);
	}

	public void baseCollision(int baseNumber){
		if ((baseNumber - this.playerCyborg.getLastBaseReached()) == 1) {
			this.playerCyborg.setLastBaseReached(baseNumber);
			if (baseNumber == 4) {
				System.out.println("You win");
				exitGame();
			}
		}
		else
			System.out.println("You went out of order");
		
	    this.setChanged();
	    this.notifyObservers(this);
	}

	public void stationCollision(){
		getEnergyStations();
		this.playerCyborg.setEnergyLevel(this.playerCyborg.getEnergyLevel() + getEnergyStations().getCapacity());
		getEnergyStations().setColor(0, 125, 0);
		getEnergyStations().setCapacity(0);
		EnergyStation newStation = new EnergyStation();
		this.gameObjectCollection.add(newStation);
		
	    this.setChanged();
	    this.notifyObservers(this);
	}
	
	public void droneCollision(){
		this.playerCyborg.setDamageLevel(this.playerCyborg.getDamageLevel() + 1);
		this.playerCyborg.setCurMaxSpeed(this.playerCyborg.getCurMaxSpeed() - this.playerCyborg.getDamageLevel());
		this.playerCyborg.setColor(0, this.playerCyborg.getColor() - 25, 0);
		if (this.playerCyborg.getCurMaxSpeed() < this.playerCyborg.getSpeed())
			this.playerCyborg.setSpeed(this.playerCyborg.getCurMaxSpeed());
		
	    this.setChanged();
	    this.notifyObservers(this);
	}
	
	public void changeSoundSetting()
	{
		sound = !sound;
		this.setChanged();
	    this.notifyObservers(this);
	}
	
	public void createBGSound(BGSound sound) {
		sound = new BGSound("background.wav");
		sound.play();
	}
	
	public void clockTick(double elapsedTime){
		setTick(getTick() + 1);
		
		// Determines if the player cyborg is still able to move, if so, moves. If not, reduce lives and reinitialize. If lives = 0, end the game
		if(this.playerCyborg.getEnergyLevel() != 0 && this.playerCyborg.getDamageLevel() != this.playerCyborg.getMaximumDamage() && this.playerCyborg.getSpeed() != 0) {
			this.playerCyborg.move();
			this.playerCyborg.setEnergyLevel(this.playerCyborg.getEnergyLevel() - this.playerCyborg.getEnergyDecayRate());
		}else if(this.playerCyborg.getEnergyLevel() <= 0) {
			explosion = new Sound("endSound.wav");
			explosion.play();
			this.setLives(getLives()-1);
			reinitialize();
			if(getLives() == 0) {
				System.out.println("You ran out of lives!");
				exitGame();
			}
		}

		// Move drones
		ArrayList<Drone> drones = this.getDrones();
		for(Drone listOfDrones: drones) {
			listOfDrones.move();
		}
	   
		// Move non player cyborgs
		ArrayList<NonPlayerCyborg> nonPlayerCyborgs = this.getAllNonPlayerCyborgs();
		for(NonPlayerCyborg listOfNPC: nonPlayerCyborgs) {
			//invokes the strategy for each npc and then moves the npc based on the new strategy
			listOfNPC.invokeStrategy();
			listOfNPC.move();
			
			// Checks if an NPC has reached the last base after it has moved, if so ends the game
			this.gameIterator = this.gameObjectCollection.getIterator() ;
			while (gameIterator.hasNext()) {
				GameObject gameObject = gameIterator.getNext();
				// Compares the location of each NPC within the gameObjectCollection with the last base in the sequence
				if(gameObject.getLocation() == baseFour.getLocation()) {
					System.out.println("A NonPlayerCyborg has reached the last base before you, Game Over!");
					exitGame();
				}// end of IF statement
			}// end of while statement
		}//end of for statement
		
		checkCollision();
		
		this.elapsedTime += elapsedTime;
		this.setChanged();
	    this.notifyObservers(this);
	}
	
	private void checkCollision(){
		this.gameIterator = this.gameObjectCollection.getIterator();
		while (gameIterator.hasNext()){
			GameObject thisObject = gameIterator.getNext();
			if (thisObject instanceof ICollider){
				ICollider thisColliderObject = (ICollider) thisObject;
				
				IIterator otherIterator = this.gameObjectCollection.getIterator();
				while (otherIterator.hasNext()){
					GameObject otherObject = otherIterator.getNext();
					if (otherObject instanceof ICollider && !(thisObject.equals(otherObject))){
						ICollider otherColliderObj = (ICollider) otherObject;
						
						if (thisColliderObject.collidesWith(otherColliderObj)){
							thisColliderObject.handleCollision(otherColliderObj);
						}
					}
				}
			}
		}
		
		RemoveFlaggedObjects();
	}
	
	private void RemoveFlaggedObjects()
	{
		this.gameIterator = this.gameObjectCollection.getIterator();
		
		while(gameIterator.hasNext())
		{
			GameObject object = gameIterator.getNext();
			if (object instanceof ICollider && ((ICollider)object).getCollisionFlag())
			{
				gameIterator.remove(object);
				if (object instanceof PlayerCyborg)
				{
					explosion = new Sound("endSound.wav");
					explosion.play();
					this.setLives(getLives()-1);
					if (this.getLives() > 0)
					{						
						reinitialize();
					}
				}
			}
		}
	}
	
	public void DeselectObjects()
	{
		this.gameIterator = this.gameObjectCollection.getIterator();
		
		while (gameIterator.hasNext())
		{
			Object object = gameIterator.getNext();
			
			if (object instanceof ISelectable)
			{
				ISelectable selectObj = (ISelectable)object;
				
				if (selectObj.isSelected())
				{
					selectObj.setSelected(false);
				}
			}
		}
	}
	
	public void reinitialize() {
		new GameWorld();
	}

	public void exitGame(){
		System.exit(0);
	}
	
	public String printMap() {
		String printMap = "";
	    String newLine = System.getProperty("line.separator");
		
		this.gameIterator = this.gameObjectCollection.getIterator() ;
		
		while (gameIterator.hasNext()) {	
			GameObject gameObject = gameIterator.getNext();	
					printMap = printMap + gameObject.toString() + newLine;		
		}
		
		return printMap;
	}
	
/********************************************************
 * 													    *
 * The following helper functions to find objects in GW *
 * 													    *
 ********************************************************/
	
	// Helper function to get all the drones to move in tick
	private ArrayList<Drone> getDrones() {
		
		ArrayList<Drone> listOfDrones = new ArrayList<>(); 
		
		this.gameIterator = this.gameObjectCollection.getIterator() ;
		
		while(gameIterator.hasNext()) {
			GameObject gameObject = gameIterator.getNext();
			if(gameObject instanceof Drone)
				listOfDrones.add((Drone)gameObject);
		}
		
		return listOfDrones;
	}
	
	// Helper function to get a single energy station for the player to collide with
	private EnergyStation getEnergyStations() {
		EnergyStation stationWanted = null;
		
		this.gameIterator = this.gameObjectCollection.getIterator() ;
		
		while (gameIterator.hasNext()) {
			GameObject gameObject = gameIterator.getNext();
			if(gameObject instanceof EnergyStation)
				stationWanted = (EnergyStation)gameObject;
		}
		
		return stationWanted;
	}
	
	// Helper function to find a single cyborg to collide with in cyborg collision
	private NonPlayerCyborg getNonPlayerCyborg() {
		NonPlayerCyborg npcWanted = null;
		
		this.gameIterator = this.gameObjectCollection.getIterator();
		
		while(gameIterator.hasNext()) {
			GameObject gameObject = gameIterator.getNext();
			if(gameObject instanceof NonPlayerCyborg)
				npcWanted = (NonPlayerCyborg)gameObject;
		}
		return npcWanted;
	}
	
	// Helper function to find all NPC's in game for tick
	private ArrayList<NonPlayerCyborg> getAllNonPlayerCyborgs() {
		
		ArrayList<NonPlayerCyborg> nonPlayerCyborgs = new ArrayList<>(); 
		
		this.gameIterator = this.gameObjectCollection.getIterator() ;
		
		while (gameIterator.hasNext()) {
			GameObject gameObject = gameIterator.getNext();
			if(gameObject instanceof NonPlayerCyborg) {
				nonPlayerCyborgs.add((NonPlayerCyborg)gameObject);
			}
		}
		
		return nonPlayerCyborgs;
	}
	
/*******************************************************
 * 													   *
 * The following are setters and getters for functions *
 * 													   *
 *******************************************************/
	
	public int getTick() {
		return this.tick;
	}

	public void setTick(int tick) {
		this.tick = tick;
	}
	
	public int getLives() {
		return this.lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public int getLastBaseReached() {
		return lastBaseReached;
	}
	
	public int getEnergyLevel() {
		return energyLevel;
	}
	
	public int getDamageLevel() {
		return damageLevel;
	}

	public boolean getSound() {
		return this.sound;
	}
	
	public void toggleSound() {
		this.sound = !this.sound;
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public void setDimensions(int x, int y) {
		this.worldWidth = x;
		this.worldHeight = y;
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public float getWorldHeight() {
		return this.worldHeight;
	}
	
	public float getWorldWidth() {
		return this.worldWidth;
	}
	
	public GameObjectCollection getGameObjectCollection() {
		return gameObjectCollection;
	}
}