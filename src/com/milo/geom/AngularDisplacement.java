package com.milo.geom;

import java.awt.geom.Point2D;

public class AngularDisplacement
{
	private double angle;
	private double distance;
	public AngularDisplacement(double angle, double distance)
	{
		// TODO Auto-generated constructor stub
		this.angle=angle;
		this.distance = distance;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
}
