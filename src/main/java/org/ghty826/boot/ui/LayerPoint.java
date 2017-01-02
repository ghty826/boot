package org.ghty826.boot.ui;

import org.ghty826.boot.dto.GameDto;
import org.ghty826.boot.util.Img;

import java.awt.Graphics;

/**
 * ˵����
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-17 ����10:00:45
 */
public class LayerPoint extends Layer {
	/** �������λ�� */
	private static final int POINT_BIT = 5;
	/** ����y���� */
	private final int rmLineY;
	/** ����y���� */
	private final int pointY;
	/** ����ֵ��y���� */
	private final int expY;
	/** ��ͨx���� */
	private int commonX;

	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		// ��ʼ����ͨx����
		commonX = getW() - NUMBER_W * POINT_BIT - PADDING_LEFT;
		// ��ʼ��������ʾ��y����
		pointY = PADDING_TOP;
		// ��ʼ��������ʾ��y����
		rmLineY = pointY + Img.POINT.getHeight(null) + PADDING_TOP;
		// ��ʼ������ֵ��y����
		expY = rmLineY + Img.RMLINE.getHeight(null) + PADDING_TOP;
	}

	@Override
	public void paint(Graphics g) {
		createWindow(g);
		// ���ڱ��⣨������
		g.drawImage(Img.POINT, getX() + PADDING_LEFT, getY() + pointY, null);
		// ��ʾ����
		drawNumberLeftPad(commonX, pointY, getGameDto().getCurPoint(), POINT_BIT, g);
		// ���ڱ��⣨���У�
		g.drawImage(Img.RMLINE, getX() + PADDING_LEFT, getY() + rmLineY, null);
		// ��ʾ����
		drawNumberLeftPad(commonX, rmLineY, getGameDto().getCurRemoveLine(),
		    POINT_BIT, g);
		// ��ǰ������
		int rmLine = getGameDto().getCurRemoveLine();
		// ������������������������ֵ
		double percent = (double) (rmLine % GameDto.LEVEL_UP)
		    / (double) (GameDto.LEVEL_UP - 1);
		// ����ֵ�ۣ�����ֵ��
		drawRect(expY, "��һ��", null, 0, percent, g);
	}
}
