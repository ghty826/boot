package org.ghty826.boot.ui;

import org.ghty826.boot.util.Img;

import java.awt.Graphics;

/**
 * ˵����
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-17 ����10:00:45
 */
public class LayerLevel extends Layer {

	/** ����ͼƬ��� */
	private static final int IMG_LV_W = Img.LEVEL.getWidth(null);

	public LayerLevel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		createWindow(g);
		int centerX = (getW() - IMG_LV_W) >> 1;
		// ���ڱ���
		g.drawImage(Img.LEVEL, getX() + centerX, getY() + PADDING_TOP, null);
		// ��ʾ�ȼ�
		drawNumberLeftPad(centerX, (getY() >> 2) + PADDING_TOP, getGameDto()
		    .getCurlevel(), 2, g);
	}
}
