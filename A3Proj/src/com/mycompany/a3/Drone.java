package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Drone extends MovableObject
{
	private int speed;
	private int heading;
	private Random rand = new Random();
	private GameWorldProxy gwp;
	private boolean collisionFlag = false;
	private boolean selected = false;
	
	public Drone() {
		super.setSize(10);
		super.setColor(255, 0, 255);
		//super.setLocation(rand.nextFloat() * (1000 - 1) + 1, rand.nextFloat() * (1000 - 1) + 1);
		setSpeed(rand.nextInt(10) + 5);
		setHeading(rand.nextInt(359) + 0);
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) 
	{
		
		if (isSelected())
		{
			g.setColor(ColorUtil.GREEN);
		}
		else
		{
			g.setColor(this.getColor());
		}
		
		int xLoc = (int)this.getLocation().getX() + pCmpRelPrnt.getX();
		int yLoc = (int)this.getLocation().getY() + pCmpRelPrnt.getY();
		
		int[] xPoints = { xLoc, (xLoc - getSize()), (xLoc + getSize()), xLoc };
		
		int[] yPoints = { (yLoc + getSize()), (yLoc - getSize()), (yLoc - getSize()), (yLoc + getSize()) };
		
		int nPoints = 4;
		
		g.drawPolygon(xPoints, yPoints, nPoints);
		g.fillPolygon(xPoints, yPoints, nPoints);
	}
	
	public void setSelected(boolean yes)
	{
		selected = yes;
	}
	
	public boolean isSelected()
	{
		return selected;
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
	
	public void setCollisionFlag()
	{
		collisionFlag = true;
	}

	public boolean getCollisionFlag()
	{
		return collisionFlag;
	}
	
	public void handleCollision(ICollider other)
	{
		if (other instanceof PlayerCyborg || other instanceof NonPlayerCyborg)
		{
			this.setCollisionFlag();
			other.setCollisionFlag();
			gwp.droneCollision();
		}
	}
	
	/****************************************/
	/****************************************/
	
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
