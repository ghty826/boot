package org.ghty826.boot.ui;

import org.ghty826.boot.entity.Player;
import org.ghty826.boot.util.Img;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

/**
 * ˵����
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-24 ����8:41:29
 */
public abstract class LayerData extends Layer {
	// TODO �����ļ�
	private static final int MAX_ROW = 5;
	/** ��ʼY���� */
	private static int START_Y = 0;

	private static final int REAL_RECT_H = RECT_H + 4;
	/** ��� */
	private static int SPA = 0;

	public LayerData(int x, int y, int w, int h) {
		super(x, y, w, h);
		SPA = (getH() - REAL_RECT_H * 5 - (PADDING_TOP << 1) - Img.DB
		    .getHeight(null)) / MAX_ROW;
		START_Y = PADDING_TOP + Img.DB.getHeight(null) + SPA;
	}

	/**
	 * ���Ƹ�����ֵ��
	 * 
	 * @param imgTitle
	 *          ����ͼƬ
	 * @param players
	 *          ����Դ
	 * @param g
	 *          ����
	 */
	protected void showData(Image imgTitle, List<Player> players, Graphics g) {
		// ���Ʊ���
		g.drawImage(imgTitle, getX() + PADDING_LEFT, getY() + PADDING_TOP, null);
		// ������ڷ���
		int curPoint = getGameDto().getCurPoint();
		// ѭ�����Ƽ�¼
		for (int i = 0; i < MAX_ROW; i++) {
			// ���һ����Ҽ�¼
			Player player = players.get(i);
			// ��ø÷���
			int recodePoint = player.getPoint();
			// �������ڷ������¼������ֵ
			double percent = (double) curPoint / recodePoint;
			// ����Ѿ��Ƽ�¼����ֵ��Ϊ100%
			percent = percent > 1 ? 1D : percent;
			// ���Ƶ�����¼
			drawRect(START_Y + i * (REAL_RECT_H + SPA), player.getName(),
			    String.valueOf(recodePoint), 6, percent, g);
		}
	}
}
