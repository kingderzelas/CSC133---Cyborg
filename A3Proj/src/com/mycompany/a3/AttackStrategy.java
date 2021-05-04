package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.util.MathUtil;

public class AttackStrategy implements IStrategy {

	@Override
	public void apply(NonPlayerCyborg nonPlayerCyborg, GameWorld gw) {
		Point nextNonPlayerCyborgLocation = new Point();
		PlayerCyborg playerCyborg = PlayerCyborg.getInstance();
		nextNonPlayerCyborgLocation = playerCyborg.getLocation();
		Point distance = new Point();
		distance.setX(nextNonPlayerCyborgLocation.getX() - nonPlayerCyborg.getLocation().getX());
		distance.setY(nextNonPlayerCyborgLocation.getY() - nonPlayerCyborg.getLocation().getY());
		int theta = (int) MathUtil.atan(distance.getY()/distance.getX());
		nonPlayerCyborg.setHeading(theta);
	}

}
