package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Base extends FixedObject implements ISelectable
{
	private int sequenceNumber;
	private GameWorldProxy gwp;
	private boolean collisionFlag = false;
	private boolean selected = false;

	Base(float x, float y){
		super.setSize(10);
		super.setColor(255, 0, 0);
		super.setLocation(x, y);
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
		
		String s=String.valueOf(sequenceNumber);
		g.drawString(s, 0, 0, 0);
	}
	

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt)
	{
		int px = pPtrRelPrnt.getX();
		int py = pPtrRelPrnt.getY();
		
		int xLoc = (int)this.getLocation().getX() + pCmpRelPrnt.getX();
		int yLoc = (int)this.getLocation().getY() + pCmpRelPrnt.getY();
		
		if ( ((px >= xLoc - getSize() /  2) && (px <= xLoc + getSize() / 2)) && 
				((py >= yLoc - getSize() / 2) && (py <= yLoc + getSize() / 2)))
		{
			return true;
		}
		else
		{
			return false;			
		}
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
	
	public void handleCollision(ICollider other)
	{
		if (other instanceof Drone || other instanceof PlayerCyborg || other instanceof NonPlayerCyborg || other instanceof Base || other instanceof EnergyStation)
		{
			this.setCollisionFlag();
			other.setCollisionFlag();
			gwp.baseCollision(sequenceNumber);
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
	
	/****************************************/
	/****************************************/

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	public void setLocation(float x, float y) {
		
	}
	
	public void setColor(int r, int g, int b) {
		
	}
	
	public void setSize(int size) {
		
	}
	
	@Override
	public String toString(){
		return "Base: " + super.toString() + ", " + "Base Number: " + getSequenceNumber();
	}
}
