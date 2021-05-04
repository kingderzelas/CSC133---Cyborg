package com.mycompany.a2;

public class Base extends FixedObject
{
	private int sequenceNumber;

	Base(float x, float y){
		super.setSize(10);
		super.setColor(255, 0, 0);
		super.setLocation(x, y);
	}

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
