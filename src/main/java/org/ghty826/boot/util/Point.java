package org.ghty826.boot.util;

import java.io.Serializable;

public class Point implements Serializable, Comparable<Point> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8870522196339887456L;
	public int x;
	public int y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public int compareTo(Point o) {
		if (null == o) {
			return -1;
		}
		return (x + y) - (o.x + o.y);
	}

	@Override
	public String toString() {
		return "{x:" + x + ", y:" + y + "}";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
