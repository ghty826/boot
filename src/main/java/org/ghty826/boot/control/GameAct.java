package org.ghty826.boot.control;

import org.ghty826.boot.config.GameConfig;
import org.ghty826.boot.config.SystemConfig;

import java.awt.Point;
import java.util.List;

/**
 * ˵����
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-19 ����11:01:01
 */
public class GameAct {
	/** �������� */
	private Point[] actPoints;
	/** ��СX */
	private static final int MIN_X;
	/** ���X */
	private static final int MAX_X;
	/** ��СY */
	private static final int MIN_Y;
	/** ���Y */
	private static final int MAX_Y;
	private Point temp = new Point();
	/** �������ͱ�� */
	private int typeCode;
	private static final List<Point[]> TYPE_CONFIG;
	private static final List<Boolean> TYPE_ROUND;
	static {
		SystemConfig sysCfg = GameConfig.getSystemConfig();
		MIN_X = sysCfg.getMinX();
		MAX_X = sysCfg.getMaxX();
		MIN_Y = sysCfg.getMinY();
		MAX_Y = sysCfg.getMaxY();
		TYPE_CONFIG = sysCfg.getTypeConfig();
		TYPE_ROUND = sysCfg.getTypeRound();
	}

	public GameAct(int typeCode) {
		init(typeCode);
	}

	/**
	 * �ƶ�ǰԤ���Ƿ�����ƶ�
	 * 
	 * @param moveX
	 * @param moveY
	 * @param gameMap
	 * @return
	 */
	public boolean canMove(int moveX, int moveY, boolean[][] gameMap) {
		boolean canMove = true;
		for (int i = 0; i < actPoints.length; i++) {
			Point curPoint = actPoints[i];
			int nextX = moveX + curPoint.x;
			int nextY = moveY + curPoint.y;
			if (isOverMap(nextX, nextY, gameMap)) {
				canMove = false;
			}
		}
		return canMove;
	}

	public Point[] getActPoints() {
		return actPoints;
	}

	/**
	 * ��÷������ͱ��
	 * 
	 * @return
	 */
	public int getTypeCode() {
		return typeCode;
	}

	public void init(int typeCode) {
		this.typeCode = typeCode;
		Point[] points = TYPE_CONFIG.get(typeCode);
		actPoints = new Point[points.length];
		for (int i = 0; i < points.length; i++) {
			actPoints[i] = new Point(points[i].x, points[i].y);
		}
	}

	/**
	 * �ж��Ƿ񳬳��߽�
	 * 
	 * @return
	 */
	private boolean isOverMap(int x, int y, boolean[][] gameMap) {
		return x < MIN_X || x > MAX_X || y < MIN_Y || y > MAX_Y || gameMap[x][y];
	}

	/**
	 * �����ƶ�
	 * 
	 * @param moveX
	 *          X��ƫ����
	 * @param moveY
	 *          Y��ƫ����
	 */
	public boolean move(int moveX, int moveY, boolean[][] gameMap) {
		// �ƶ�����
		if (canMove(moveX, moveY, gameMap)) {
			for (int i = 0; i < actPoints.length; i++) {
				Point curPoint = actPoints[i];
				curPoint.x += moveX;
				curPoint.y += moveY;
			}
			return true;
		}
		return false;
	}

	/**
	 * ������ת <br />
	 * ˳ʱ��<br />
	 * A.x = O.y + O.x - B.y <br />
	 * A.y = O.y - O.x + B.x <br />
	 * ��ʱ��<br />
	 * A.x = O.x - O.y + B.y <br />
	 * A.y = O.x + O.y - B.x <br />
	 */
	private Point rond(Point o, Point b, boolean isClockwise) {
		if (isClockwise) {
			temp.x = o.y + o.x - b.y;
			temp.y = o.y - o.x + b.x;
		} else {
			temp.x = o.x - o.y + b.y;
			temp.y = o.x + o.y - b.x;
		}
		return temp;
	}

	/**
	 * ������ת
	 */
	public boolean round(boolean[][] gameMap, boolean isClockwise) {
		if (!TYPE_ROUND.get(typeCode)) {
			return false;
		}
		Point o = actPoints[0];
		for (Point b : actPoints) {
			Point a = rond(o, b, isClockwise);
			if (isOverMap(a.x, a.y, gameMap)) {
				return false;
			}
		}
		for (Point b : actPoints) {
			Point a = rond(o, b, isClockwise);
			b.x = a.x;
			b.y = a.y;
		}
		return true;
	}

}
