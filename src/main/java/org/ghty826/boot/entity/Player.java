package org.ghty826.boot.entity;

import java.io.Serializable;

/**
 * ˵�������ʵ����
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-19 ����10:50:35
 */
public class Player implements Serializable, Comparable<Player> {
	private static final long serialVersionUID = 2622853774446125526L;
	private String name;
	private int point;

	public Player() {
	}

	public Player(String name, int point) {
		this.name = name;
		this.point = point;
	}

	@Override
	public int compareTo(Player player) {
		return player.point - point;
	}

	public String getName() {
		return name;
	}

	public int getPoint() {
		return point;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", point=" + point + "]";
	}

}
