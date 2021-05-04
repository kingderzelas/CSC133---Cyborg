package com.mycompany.a1;

import java.util.ArrayList;

public class GameWorld{
	private ArrayList<GameObject> listGameWorld = new ArrayList<GameObject>();
	private int lives;
	private int tick;

	public void init(){
		Drone droneOne = new Drone();
		Drone droneTwo = new Drone();
		
		Base baseOne = new Base(100, 200);
		baseOne.setSequenceNumber(1);
		Base baseTwo = new Base(567, 782);
		baseTwo.setSequenceNumber(2);
		Base baseThree = new Base(2, 980);
		baseThree.setSequenceNumber(3);
		Base baseFour = new Base(255, 600);
		baseFour.setSequenceNumber(4);
		Cyborg cyborgOne = new Cyborg(baseOne.getLocation().getX(), baseOne.getLocation().getY());
		
		EnergyStation energyStationOne = new EnergyStation();
		EnergyStation energyStationTwo = new EnergyStation();
	
		listGameWorld.add(droneOne);
		listGameWorld.add(droneTwo);
		listGameWorld.add(cyborgOne);
		listGameWorld.add(baseOne);
		listGameWorld.add(baseTwo);
		listGameWorld.add(baseThree);
		listGameWorld.add(baseFour);
		listGameWorld.add(energyStationOne);
		listGameWorld.add(energyStationTwo);
	}
	
	public void accelerate(){
		((Cyborg)listGameWorld.get(2)).setSpeed(((Cyborg)listGameWorld.get(2)).getSpeed() + 3);
	}
	
	public void brake(){
		((Cyborg)listGameWorld.get(2)).setSpeed(0);
	}

	public void turnLeft(char lr){
		((Cyborg)listGameWorld.get(2)).turn('l');
	}

	public void turnRight(char lr){
		((Cyborg)listGameWorld.get(2)).turn('r');
	}

	public void cyborgCollision(){
		((Cyborg)listGameWorld.get(2)).setDamageLevel(((Cyborg)listGameWorld.get(2)).getDamageLevel() + 1);
		((Cyborg)listGameWorld.get(2)).setCurMaxSpeed(((Cyborg)listGameWorld.get(2)).getInitialMaxSpeed() - ((Cyborg)listGameWorld.get(2)).getDamageLevel());
		((Cyborg)listGameWorld.get(2)).setColor(0, ((Cyborg)listGameWorld.get(2)).getColor() - 25, 0);
		if (((Cyborg)listGameWorld.get(2)).getCurMaxSpeed() < ((Cyborg)listGameWorld.get(2)).getSpeed())
			((Cyborg)listGameWorld.get(2)).setSpeed(((Cyborg)listGameWorld.get(2)).getCurMaxSpeed());
	}

	public void baseCollision(int baseNumber){
		if ((baseNumber - ((Cyborg)listGameWorld.get(2)).getLastBaseReached()) == 1) {
			((Cyborg)listGameWorld.get(2)).setLastBaseReached(baseNumber);
			if (baseNumber == 4) {
				System.out.println("You win");
				exitGame();
			}
		}
		else
			System.out.println("You went out of order");
	}

	public void stationCollision(){
		findStation();
		((Cyborg)listGameWorld.get(2)).setEnergyLevel(((Cyborg)listGameWorld.get(2)).getEnergyLevel() + findStation().getCapacity());
		findStation().setColor(0, 125, 0);
		findStation().setCapacity(0);
		EnergyStation newStation = new EnergyStation();
		listGameWorld.add(newStation);
	}
	
	public EnergyStation findStation() {
		EnergyStation stationWanted = null;
		for(int i = 0; i < listGameWorld.size(); i++)
			if (listGameWorld.get(i) instanceof EnergyStation)
				stationWanted = (EnergyStation)listGameWorld.get(i);
		return stationWanted;
	}

	public void droneCollision(){
		((Cyborg)listGameWorld.get(2)).setDamageLevel(((Cyborg)listGameWorld.get(2)).getDamageLevel() + 1);
		((Cyborg)listGameWorld.get(2)).setCurMaxSpeed(((Cyborg)listGameWorld.get(2)).getInitialMaxSpeed()*((Cyborg)listGameWorld.get(2)).getDamageLevel());
		((Cyborg)listGameWorld.get(2)).setColor(0, ((Cyborg)listGameWorld.get(2)).getColor() - 25, 0);
		if (((Cyborg)listGameWorld.get(2)).getCurMaxSpeed() < ((Cyborg)listGameWorld.get(2)).getSpeed())
			((Cyborg)listGameWorld.get(2)).setSpeed(((Cyborg)listGameWorld.get(2)).getCurMaxSpeed());
	}

	public void clockTick(){
		setTick(getTick() + 1);
		
		if(((Cyborg)listGameWorld.get(2)).getEnergyLevel() != 0 && ((Cyborg)listGameWorld.get(2)).getDamageLevel() != ((Cyborg)listGameWorld.get(2)).getMaximumDamage() 
				&& ((Cyborg)listGameWorld.get(2)).getSpeed() != 0) {
			((Cyborg)listGameWorld.get(2)).move();
			((Cyborg)listGameWorld.get(2)).setEnergyLevel(((Cyborg)listGameWorld.get(2)).getEnergyLevel()-((Cyborg)listGameWorld.get(2)).getEnergyDecayRate());
		}else if(((Cyborg)listGameWorld.get(2)).getEnergyLevel() <= 0) {
			setLives(getLives()-1);
			reinitialize();
			if(getLives() <= 0) {
				System.exit(0);
			}
		}
		((Drone)listGameWorld.get(0)).move();
		((Drone)listGameWorld.get(1)).move();
		((Cyborg)listGameWorld.get(2)).setEnergyLevel(((Cyborg)listGameWorld.get(2)).getEnergyLevel() - ((Cyborg)listGameWorld.get(2)).getEnergyDecayRate());
	}
	
	public void display(){
		System.out.println("Current lives: " + getLives());
		System.out.println("Tick: " + getTick());
		System.out.println("Highest Base Reached: " + ((Cyborg)listGameWorld.get(2)).getLastBaseReached());
		System.out.println("Current Energy Level: " + ((Cyborg)listGameWorld.get(2)).getEnergyLevel());
		System.out.println("Current Damage Level: " + ((Cyborg)listGameWorld.get(2)).getDamageLevel());
	}

	public void map(){
		for(int i = 0; i < listGameWorld.size(); i++)
			System.out.println(listGameWorld.get(i).toString());
	}

	public void reinitialize() {
		new GameWorld();
	}

	public void exitGame(){
		System.exit(0);
	}
	
	public int getTick() {
		return tick;
	}

	public void setTick(int tick) {
		this.tick = tick;
	}
	
	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

}