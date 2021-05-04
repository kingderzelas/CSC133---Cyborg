package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.util.MathUtil;

public class BaseStrategy implements IStrategy {

	public void apply(NonPlayerCyborg nonPlayerCyborg, GameWorld gw) {
		int nextBase = nonPlayerCyborg.getLastBaseReached() + 1;
		Point nextBaseLocation = new Point();
		IIterator iterator = gw.getGameObjectCollection().getIterator();
		if(iterator == null)
			System.out.println("Iterator in BaseStrategy is null");
		while (iterator.hasNext()) {
			GameObject object = iterator.getNext();
			if(object instanceof Base) {
				Base base = (Base)object;
				if(base.getSequenceNumber() == nextBase) {
					nextBaseLocation = base.getLocation();
				}
			}
		}
		Point distance = new Point();
		distance.setX(nextBaseLocation.getX() - nonPlayerCyborg.getLocation().getX());
		distance.setY(nextBaseLocation.getY() - nonPlayerCyborg.getLocation().getY());
		int theta = (int) MathUtil.atan(distance.getY()/distance.getX());
		nonPlayerCyborg.setHeading(theta);
	}

}
