package com.mycompany.a3;

public abstract class Cyborg extends MovableObject implements ISteerable {
	private int steeringDirection;
	private int maximumSpeed;
	private int energyLevel;
	private int energyDecayRate;
	private int damageLevel;
	private int lastBaseReached;
	private int maximumDamage;
	private int curMaxSpeed;
	private int initialMaxSpeed;
	private int heading;
	private int speed;
	
	public Cyborg(float x, float y) {
		super.setLocation(x, y);
	}
	
	@Override
	public void turn(char lr) {
		if(lr == 'l') {
			setSteeringDirection(steeringDirection - 5);
		}else if(lr=='r') {
			setSteeringDirection(steeringDirection + 5);
		}
	}
	
	@Override
	public void move() {
		this.setHeading(this.getSteeringDirection());
		super.move();
	}
	
	public void setHeading(int heading) {
		if(steeringDirection < -40) {
			steeringDirection = -40;
		}else if(steeringDirection > 40) {
			steeringDirection = 40;
		}
		
		int newHeading = getHeading() + steeringDirection;
		if(newHeading < 0) {
			super.setHeading(360 + newHeading);
		}else if(newHeading > 360) {
			super.setHeading(newHeading - 360);
		}else {
			super.setHeading(newHeading);
		}
		
		setSteeringDirection(0);
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed(){
		return speed;
	}

	public int getSteeringDirection() {
		return steeringDirection;
	}

	public void setSteeringDirection(int steeringDirection) {
		this.steeringDirection = steeringDirection;
	}

	public int getMaximumSpeed() {
		return maximumSpeed;
	}

	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	public int getEnergyLevel() {
		return energyLevel;
	}

	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}

	public int getEnergyDecayRate() {
		return energyDecayRate;
	}

	public void setEnergyDecayRate(int energyDecayRate) {
		this.energyDecayRate = energyDecayRate;
	}

	public int getDamageLevel() {
		return damageLevel;
	}

	public void setDamageLevel(int damageLevel) {
		this.damageLevel = damageLevel;
	}

	public int getLastBaseReached() {
		return lastBaseReached;
	}

	public void setLastBaseReached(int lastBaseReached) {
		this.lastBaseReached = lastBaseReached;
	}

	public int getMaximumDamage() {
		return maximumDamage;
	}

	public void setMaximumDamage(int maximumDamage) {
		this.maximumDamage = maximumDamage;
	}

	public int getCurMaxSpeed() {
		return curMaxSpeed;
	}

	public void setCurMaxSpeed(int curMaxSpeed) {
		this.curMaxSpeed = curMaxSpeed;
	}

	public int getInitialMaxSpeed() {
		return initialMaxSpeed;
	}

	public void setInitialMaxSpeed(int initialMaxSpeed) {
		this.initialMaxSpeed = initialMaxSpeed;
	}
	
	public void setColor(int r, int g, int b) {
		
	}
	
	public void setSize(int size) {
		
	}
	
	public void setLocation(float x, float y) {
		
	}
	
	public String toString(){
		return "Player cyborg: " + super.toString() + ", heading: " + getHeading() + ", speed: " + getSpeed() + 
				", maxSpeed: " + getCurMaxSpeed() + ", steeringDirection: " + getSteeringDirection() +
				", energyLevel: " + getEnergyLevel() + ", damageLevel: " + getDamageLevel() + ", lastBaseReached: " + getLastBaseReached();
	}
}
