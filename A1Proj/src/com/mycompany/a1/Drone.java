package com.mycompany.a1;

import java.util.Random;

public class Drone extends MovableObject
{
	private int speed;
	private int heading;
	Random rand = new Random();
	
	public Drone() {
		super.setSize(10);
		super.setColor(255, 0, 255);
		super.setLocation(rand.nextFloat() * (1000 - 1) + 1, rand.nextFloat() * (1000 - 1) + 1);
		setSpeed(rand.nextInt(10) + 5);
		setHeading(rand.nextInt(359) + 0);
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getHeading() {
		return heading;
	}

	public void setHeading() {
		Random randomN = new Random();
		int random = randomN.nextInt(2);
		if (random == 0)
			setHeading(getHeading() + 5);
		else 
			setHeading(getHeading() - 5);
	}

	public void setSize(int size) {
		
	}
	
	public void setColor(int r, int g, int b) {
		
	}
	
	@Override
	public void move() {
		this.setHeading();
		super.move();
	}

	@Override
	public String toString()
	{
		return "Drone: " + super.toString() + ", size: " + super.getSize() + ", speed: " + getSpeed() + 
				", heading: " + getHeading();
	}
}
