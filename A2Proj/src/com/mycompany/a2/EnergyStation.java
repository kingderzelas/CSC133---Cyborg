package com.mycompany.a2;

import java.util.Random;

public class EnergyStation extends FixedObject
{
	private int capacity;
	Random rand = new Random();
	
	EnergyStation(){
		setCapacity(rand.nextInt(50) + 10);
		super.setColor(0, 255, 0);
		//super.setLocation(rand.nextFloat()*(1000 - 1) + 1, rand.nextFloat()*(1000 - 1) + 1);
		super.setSize(getCapacity());
	}
	
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setSize(int size) {

	}

	public void setLocation(float x, float y) {

	}
	
	@Override
	public String toString(){
		return "Energy Station: " + super.toString() + ", " + "Capacity: " + getCapacity();
	}

}
