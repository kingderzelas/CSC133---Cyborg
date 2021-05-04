package com.mycompany.a3;

public interface IGameWorld {
	
	public int getLives();
	
	public int getTick();
	
	public float getWorldWidth();
		
	public float getWorldHeight();
	
	public void setDimensions(int x, int y);
	
	public boolean getSound();
	
	public void toggleSound();
	
	public void accelerate();
	
	public void brake();
	
	public void turnLeft();
	
	public void turnRight();
	
	public void cyborgCollision();
	
	public void baseCollision(int baseNumber);
	
	public void stationCollision();
	
	public void droneCollision();
	
	public void clockTick(double elapsedTime);
		
	public void exitGame();	
	
	public int getLastBaseReached();
	
	public int getEnergyLevel();
	
	public int getDamageLevel();
	
	public String printMap();
	
}