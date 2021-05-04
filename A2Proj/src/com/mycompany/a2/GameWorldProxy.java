package com.mycompany.a2;

import java.util.Observer;
import java.util.Observable;

public class GameWorldProxy extends Observable implements IGameWorld, Observer {
	
	private GameWorld gw;
	
	public GameWorldProxy(GameWorld gw) {
		this.gw = gw;
	}

	@Override
	public int getLives() {
		return gw.getLives();
	}

	@Override
	public int getTick() {
		return gw.getTick();
	}

	@Override
	public float getWorldWidth() {
		return gw.getWorldWidth();
	}
	
	@Override
	public float getWorldHeight() {
		return gw.getWorldHeight();
	}
	
	@Override
	public void setDimensions(int x, int y) {
		System.out.println("Illegally accessed method.");
	}

	@Override
	public boolean getSound() {
		return gw.getSound();
	}
	
	@Override
	public void toggleSound() {
		System.out.println("Illegally accessed method.");
	}

	@Override
	public void accelerate() {
		System.out.println("Illegally accessed method.");
	}

	@Override
	public void brake() {
		System.out.println("Illegally accessed method.");
	}

	@Override
	public void turnLeft() {
		System.out.println("Illegally accessed method.");
	}

	@Override
	public void turnRight() {
		System.out.println("Illegally accessed method.");
	}

	@Override
	public void clockTick() {
		System.out.println("Illegally accessed method.");	
	}

	@Override
	public void baseCollision(int baseNumber) {
		System.out.println("Illegally accessed method.");
	}
	
	@Override
	public void exitGame() {
		System.out.println("Illegally accessed method.");
	}
	
	@Override
	public void cyborgCollision() {
		System.out.println("Illegally accessed method.");
	}
	
	@Override
	public void stationCollision() {
		System.out.println("Illegally accessed method.");
	}
	
	@Override
	public void droneCollision() {
		System.out.println("Illegally accessed method.");
	}
	
	@Override
	public int getLastBaseReached() {
		return gw.getLastBaseReached();
	}
	
	@Override
	public int getEnergyLevel() {
		return gw.getEnergyLevel();
	}
	
	@Override
	public int getDamageLevel() {
		return gw.getDamageLevel();
	}

	@Override
	public void update(Observable observable, Object obj) {
		this.gw = (GameWorld)obj;
	    this.setChanged();
	    this.notifyObservers(this.gw);
	}
	
	@Override
	public String printMap() {
		return gw.printMap();
	}
	
	@Override
	public String toString() {
		return gw.toString();
	}
	
}