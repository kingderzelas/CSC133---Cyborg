package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.models.Point;

public abstract class GameObject implements IDrawable
{
	private int size;
	private int color;
	private Point location = new Point();

	public GameObject() {

	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int r, int g, int b) {
		this.color = ColorUtil.rgb(r, g, b);
	}

	public Point getLocation() {
		return new Point(this.location.getX(), this.location.getY());
	}

	public void setLocation(float x, float y) {
		this.location.setX(x);
		this.location.setY(y);
	}
	
	public String toString(){
		return "location = " + location.getX() + ", " + location.getY() + ", size: " + getSize() +
				", color = [" + ColorUtil.red(color) + ", " +  
							   ColorUtil.green(color) + ", " +
							   ColorUtil.blue(color) + "] ";
	}
}
