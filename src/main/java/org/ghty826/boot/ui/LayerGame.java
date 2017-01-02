package org.ghty826.boot.ui;

import org.ghty826.boot.control.GameAct;
import org.ghty826.boot.util.Img;

import java.awt.Graphics;
import java.awt.Point;

/**
 * ˵����
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-17 ����10:00:45
 */
public class LayerGame extends Layer {

	// TODO �����ļ�
	/** ͼ��ߴ���λ��ƫ���� */
	private static final int SIZE_ROL = 5;
	/** ��ǰ������߾� */
	private static final int LEFT_SIDE = 0;
	/** ��ǰ�����ұ߾� */
	private static final int RIGHT_SIDE = 9;
	/** ʧ��ͼ���±� */
	private static final int LOSE_INDEX = 8;

	public LayerGame(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	/**
	 * ���������ο�
	 * 
	 * @param x
	 * @param y
	 * @param imgIdx
	 * @param g
	 */
	private void drawActByPoint(int x, int y, int imgIdx, Graphics g) {
		// ������ˣ���ȫ���������ɫ
		imgIdx = getGameDto().isStart() ? imgIdx : LOSE_INDEX;
		g.drawImage(Img.GAME, getX() + (x << SIZE_ROL) + BORDER, getY()
		    + (y << SIZE_ROL) + BORDER, getX() + (x + 1 << SIZE_ROL) + BORDER,
		    getY() + (y + 1 << SIZE_ROL) + BORDER, imgIdx << SIZE_ROL, 0,
		    imgIdx + 1 << SIZE_ROL, 1 << SIZE_ROL, null);
	}

	/**
	 * ���ƻ����
	 * 
	 * @param points
	 * @param g
	 */
	private void drawActiveAct(Point[] points, Graphics g) {
		// ��÷������ͱ�ţ�0��6��+1
		int typeCode = getGameDto().getGameAct().getTypeCode() + 1;
		// ��ӡ����
		for (int i = 0; i < points.length; i++) {
			Point p = points[i];
			// ���������ο�
			drawActByPoint(p.x, p.y, typeCode, g);
		}
	}

	/**
	 * ������Ϸ��ͼ
	 * 
	 * @param g
	 */
	private void drawGameMap(Graphics g) {
		boolean[][] gameMap = getGameDto().getGameMap();
		// ���㵱ǰ�ѻ�ɫ
		int imgIdx = getGameDto().getCurlevel();
		imgIdx = imgIdx == 0 ? 0 : (imgIdx - 1) % (LOSE_INDEX - 1) + 1;
		// ���Ƶ�ǰ�ѻ�����
		for (int x = 0; x < gameMap.length; x++) {
			for (int y = 0; y < gameMap[x].length; y++) {
				if (gameMap[x][y]) {
					// ���������ο�
					drawActByPoint(x, y, imgIdx, g);
				}
			}
		}
	}

	/**
	 * ������Ӱ
	 * 
	 * @param points
	 * @param isShowShadow
	 * @param g
	 */
	private void drawShadow(Point[] points, boolean isShowShadow, Graphics g) {
		if (!getGameDto().isShowShadow() || !getGameDto().isStart()) {
			return;
		}
		int leftX = RIGHT_SIDE;
		int rightX = LEFT_SIDE;
		for (Point p : points) {
			leftX = p.x < leftX ? p.x : leftX;
			rightX = p.x > rightX ? p.x : rightX;
		}
		g.drawImage(Img.SHADOW, getX() + BORDER + (leftX << SIZE_ROL), getY()
		    + BORDER, (rightX - leftX + 1) << SIZE_ROL, getH() - (BORDER << 1),
		    null);
	}

	@Override
	public void paint(Graphics g) {
		createWindow(g);
		GameAct gameAct = getGameDto().getGameAct();
		if (gameAct != null) {
			// ��÷������鼯��
			Point[] points = gameAct.getActPoints();
			// ������Ӱ
			drawShadow(points, getGameDto().isShowShadow(), g);
			// ���ƻ����
			drawActiveAct(points, g);
		}
		// ������Ϸ��ͼ
		drawGameMap(g);
		if (getGameDto().isPause() && getGameDto().isStart()) {
			drawImageCenter(Img.PAUSE, g);
		}
	}
}
