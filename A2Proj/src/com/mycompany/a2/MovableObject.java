package com.mycompany.a2;

import com.codename1.charts.models.Point;

public abstract class MovableObject extends GameObject
{	
	private int heading;
	private int speed;
	
	public int getHeading() {
		return heading;
	}

	public void setHeading(int heading) {
		this.heading = heading;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void move(){
		Point oldLocation = getLocation();
		Point newLocation = new Point();
		double deltaX = Math.cos(Math.toRadians(90-heading)*speed);
		double deltaY = Math.sin(Math.toRadians(90-heading)*speed);
		
		if (newLocation.getX() <= 0 && getHeading() <= 359 && getHeading() > 180 || newLocation.getX() >= 1000 && getHeading() > 0 && getHeading() < 180){
			deltaX = Math.cos( Math.toRadians(180 - heading) ) * speed;
			newLocation.setX((float)deltaX + oldLocation.getX());
		}
		if (newLocation.getY() <= 0 && getHeading() < 90 && getHeading() > 270 || newLocation.getY() >= 1000 && getHeading() > 90 && getHeading() < 270){
			deltaX = Math.cos( Math.toRadians(180 - heading) ) * speed;
			newLocation.setY((float)deltaY + oldLocation.getY());
		}
		
		newLocation.setX((float) (deltaX + oldLocation.getX()));
		newLocation.setY((float) (deltaY + oldLocation.getY()));
		
		setLocation(newLocation.getX(), newLocation.getY());
	}
}