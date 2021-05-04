package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class NonPlayerCyborg extends Cyborg {
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
	private IStrategy curStrategy;
	private GameWorldProxy gwp;
	private Sound sound;
	private boolean collisionFlag = false;
	
	//for the sake of time I kept the values and variables for the NPC that are irrelevant to this assignment
	public NonPlayerCyborg(float x, float y) {
		super(x, y);
		super.setSize(40);
		super.setLocation(x, y);
		setSpeed(5);
		setHeading(0);
		super.setColor(127, 127, 127);
		setSteeringDirection(0);
		setEnergyLevel(70);
		setEnergyDecayRate(5);
		setDamageLevel(0);
		setLastBaseReached(1);
		setInitialMaxSpeed(10);
		setMaximumDamage(20);
		setMaximumSpeed(10);
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) 
	{
		int xLoc = (int)this.getLocation().getX() + pCmpRelPrnt.getX();
		int yLoc = (int)this.getLocation().getY() + pCmpRelPrnt.getY();
		
		int[] xPoints = { xLoc, (xLoc - getSize()), (xLoc + getSize()), xLoc };
		
		int[] yPoints = { (yLoc + getSize()), (yLoc - getSize()), (yLoc - getSize()), (yLoc + getSize()) };
		
		int nPoints = 4;
		
		g.drawPolygon(xPoints, yPoints, nPoints);
		g.fillPolygon(xPoints, yPoints, nPoints);
	}
	
	public boolean collidesWith(ICollider other)
	{
		boolean result = false;
		
		double thisCenterX = this.getLocation().getX();
		double thisCenterY = this.getLocation().getY();
		
		double otherCenterX = ((GameObject)other).getLocation().getX();
		double otherCenterY = ((GameObject)other).getLocation().getY();
		
		double dx = thisCenterX - otherCenterX;
		double dy = thisCenterY - otherCenterY;
		
		double distBetweenCentersSqr = (dx * dx + dy * dy);
		
		// find square of sum of radii
		int thisRadius= this.getSize() / 2;
		int otherRadius= ((GameObject)other).getSize() / 2;
		
		int radiiSqr= (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		
		if (distBetweenCentersSqr <= radiiSqr)
			result = true ;
		
		return result;
	}
	
	public void handleCollision(ICollider other)
	{
		if (other instanceof NonPlayerCyborg)
		{
			sound = new Sound("ship-explosion.wav");
			this.setCollisionFlag();
			other.setCollisionFlag();
			gwp.cyborgCollision();
			sound.play();
		}else if(other instanceof Base){
			sound = new Sound("teleport.wav");
			this.setCollisionFlag();
			other.setCollisionFlag();
			gwp.baseCollision(getLastBaseReached());
			sound.play();
		}else if(other instanceof EnergyStation) {
			sound = new Sound("teleport.wav");
			this.setCollisionFlag();
			other.setCollisionFlag();
			gwp.stationCollision();
			sound.play();
		}else if(other instanceof Drone) {
			sound = new Sound("ship-explosion.wav");
			this.setCollisionFlag();
			other.setCollisionFlag();
			gwp.droneCollision();
			sound.play();
		}else if(other instanceof PlayerCyborg) {
			sound = new Sound("ship-explosion.wav");
			this.setCollisionFlag();
			other.setCollisionFlag();
			gwp.cyborgCollision();
			sound.play();
		}
	}
	
	public void setCollisionFlag()
	{
		collisionFlag = true;
	}

	public boolean getCollisionFlag()
	{
		return collisionFlag;
	}
	
	/**************************************************************
	 * 													    	  *
	 * Code to implement the strategies for NPCS 				  *
	 * 													          *
	 **************************************************************/
	
	public void setStrategy(IStrategy newStrategy) {
		this.curStrategy = newStrategy;
	}
	
	public IStrategy getCurStrategy() {
		return curStrategy;
	}
	
	public void invokeStrategy() {
		getCurStrategy().apply(null, null);
	}
	
	/*****************************************************************
	 * 													    	     *
	 * The following are functions are inherited from Cyborg from A1 *
	 * 													             *
	 *****************************************************************/
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
	
	/**************************************************************
	 * 													    	  *
	 * The following are functions are the setters and getters    *
	 * 													          *
	 **************************************************************/
	
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
		return "NonPlayerCyborg: " + super.toString() + ", heading: " + getHeading() + ", speed: " + getSpeed() + 
				", maxSpeed: " + getCurMaxSpeed() + ", steeringDirection: " + getSteeringDirection() +
				", energyLevel: " + getEnergyLevel() + ", damageLevel: " + getDamageLevel() + ", lastBaseReached: " + getLastBaseReached()
				+ ", Current Strategy: " + getCurStrategy();
	}

}