package org.ghty826.boot.ui;

import org.ghty826.boot.config.FrameConfig;
import org.ghty826.boot.config.GameConfig;
import org.ghty826.boot.dto.GameDto;
import org.ghty826.boot.util.Img;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 * ˵����ͼ�񲼾ֳ�����
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-17 ����8:23:08
 */
public abstract class Layer {
	private static final int WINDOW_W = Img.WINDOW.getWidth(null);
	private static final int WINDOW_H = Img.WINDOW.getHeight(null);
	/** IMGͼ�����Ͻ�����������η���߳� */
	protected static final int BORDER;
	/** �߿��ڲ��Ϸ�������� */
	protected static final int PADDING_TOP;
	/** �߿��ڲ���������� */
	protected static final int PADDING_LEFT;
	static {
		// �����Ϸ����
		FrameConfig cfg = GameConfig.getFrameConfig();
		BORDER = cfg.getBorder();
		PADDING_TOP = cfg.getPaddingTop();
		PADDING_LEFT = cfg.getPaddingLeft();
	}
	/** ͼ�����Ͻ�x���� */
	private int x;
	/** ͼ�����Ͻ�y���� */
	private int y;
	/** ͼ��x����ƫ���� */
	private int w;
	/** ͼ��y����ƫ���� */
	private int h;
	/** ��Ϸ���� */
	private GameDto gameDto;
	/** ������Ƭ�Ŀ�� */
	protected static final int NUMBER_W = Img.NUMBER.getWidth(null) / 10;
	/** ������Ƭ�ĸ߶� */
	protected static final int NUMBER_H = Img.NUMBER.getHeight(null);
	/** ����ֵ��ͼƬ�߶� */
	protected static final int RECT_H = Img.RECT.getHeight(null);
	/** ����ֵ��ͼƬ��� */
	protected static final int RECT_W = Img.RECT.getWidth(null);
	/** ���� */
	protected static final Font DEFAULT_FONT = new Font("����", Font.BOLD, 20);
	// TODO �ּ��
	/** �ּ�� */
	protected static final int WORD_PADDING = 5;
	/** ����ֵ��w���� */
	private final int EXP_W;

	public Layer(int x, int y, int w, int h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		// ��ʼ������ֵ��w����
		EXP_W = getW() - (PADDING_LEFT << 1);
	}

	/**
	 * ���ƴ��ڱ߿�ͼ��
	 * 
	 * @param g
	 *          ����
	 */
	public void createWindow(Graphics g) {
		// ����
		g.drawImage(Img.WINDOW, x, y, x + BORDER, y + BORDER, 0, 0, BORDER, BORDER,
		    null);
		// ����
		g.drawImage(Img.WINDOW, x + BORDER, y, x + w - BORDER, y + BORDER, BORDER,
		    0, WINDOW_W - BORDER, BORDER, null);
		// ����
		g.drawImage(Img.WINDOW, x + w - BORDER, y, x + w, y + BORDER, WINDOW_W
		    - BORDER, 0, WINDOW_W, BORDER, null);
		// ����
		g.drawImage(Img.WINDOW, x, y + BORDER, x + BORDER, y + h - BORDER, 0,
		    BORDER, BORDER, WINDOW_H - BORDER, null);
		// ����
		g.drawImage(Img.WINDOW, x + BORDER, y + BORDER, x + w - BORDER, y + h
		    - BORDER, BORDER, BORDER, WINDOW_W - BORDER, WINDOW_H - BORDER, null);
		// ����
		g.drawImage(Img.WINDOW, x + w - BORDER, y + BORDER, x + w, y + h - BORDER,
		    WINDOW_W - BORDER, BORDER, WINDOW_W, WINDOW_H - BORDER, null);
		// ����
		g.drawImage(Img.WINDOW, x, y + h - BORDER, x + BORDER, y + h, 0, WINDOW_H
		    - BORDER, BORDER, WINDOW_H, null);
		// ����
		g.drawImage(Img.WINDOW, x + BORDER, y + h - BORDER, x + w - BORDER, y + h,
		    BORDER, WINDOW_H - BORDER, WINDOW_W - BORDER, WINDOW_H, null);
		// ����
		g.drawImage(Img.WINDOW, x + w - BORDER, y + h - BORDER, x + w, y + h,
		    WINDOW_W - BORDER, WINDOW_H - BORDER, WINDOW_W, WINDOW_H, null);
	}

	/**
	 * ���л�ͼ
	 * 
	 * @param img
	 * @param g
	 */
	protected void drawImageCenter(Image img, Graphics g) {
		int imgW = img.getWidth(null);
		int imgH = img.getHeight(null);
		g.drawImage(img, getX() + (getW() - imgW >> 1), getY()
		    + (getH() - imgH >> 1), null);
	}

	/**
	 * �Ҷ�����ʾ����(�������ʾ����)
	 * 
	 * @param x
	 *          ���Ͻ�x����
	 * @param y
	 *          ���Ͻ�y����
	 * @param num
	 *          Ҫ��ʾ������
	 * @param maxBit
	 *          ����λ��
	 * @param g
	 *          ���ʶ���
	 */
	protected void drawNumberLeftPad(int x, int y, int num, int maxBit, Graphics g) {
		// ��Ҫ��ӡ������ת�����ַ���
		String strNum = String.valueOf(num);
		int strNumLength = strNum.length();
		// ѭ�����������Ҷ���
		for (int i = 0; i < maxBit; i++) {
			// �ж��Ƿ������������
			if (maxBit - i > strNumLength) {
				continue;
			}
			// ����������ַ������±�
			int idx = i - maxBit + strNumLength;
			// ������number�е�ÿһλȡ��
			int bit = strNum.charAt(idx) - '0';
			// ��������
			g.drawImage(Img.NUMBER, getX() + x + NUMBER_W * i + WORD_PADDING, getY()
			    + y, getX() + x + NUMBER_W * (i + 1), getY() + y + NUMBER_H, NUMBER_W
			    * bit, 0, NUMBER_W * (bit + 1), NUMBER_H, null);
		}
	}

	/**
	 * ����ֵ��
	 * 
	 * @param expY
	 * @param title
	 * @param number
	 * @param vale
	 * @param maxValue
	 * @param g
	 */
	protected void drawRect(int expY, String title, String number,
	    int maxNumberSize, double percent, Graphics g) {
		// ����ֵ��ʼ��
		int realRect_x = getX() + PADDING_LEFT;
		int realRect_y = getY() + expY;
		// ���Ʊ���
		g.setColor(Color.BLACK);
		g.fillRect(realRect_x, realRect_y, EXP_W, RECT_H + 4);
		g.setColor(Color.WHITE);
		g.fillRect(realRect_x + 1, realRect_y + 1, EXP_W - 2, RECT_H + 2);
		g.setColor(Color.BLACK);
		g.fillRect(realRect_x + 2, realRect_y + 2, EXP_W - 4, RECT_H);
		// ����ֵ��
		// ������
		int curW = (int) (percent * (EXP_W - 4));
		// �����ɫ
		int subIdx = (int) (percent * RECT_W) - 1;
		g.drawImage(Img.RECT, realRect_x + 2, realRect_y + 2,
		    realRect_x + 2 + curW, realRect_y + 2 + RECT_H, subIdx, 0, subIdx + 1,
		    RECT_H, null);
		g.setColor(Color.WHITE);
		g.setFont(DEFAULT_FONT);
		g.drawString(title, realRect_x + 5, realRect_y + 23);
		if (number != null) {
			// TODO �Ҷ�����ʾ
			int x = realRect_x + 230 + 10 * (maxNumberSize - number.length());
			g.drawString(number, x, realRect_y + 22);
		}
	}

	protected GameDto getGameDto() {
		return gameDto;
	}

	protected int getH() {
		return h;
	}

	protected int getW() {
		return w;
	}

	protected int getX() {
		return x;
	}

	protected int getY() {
		return y;
	}

	/**
	 * ��Ϸˢ�¾�������
	 * 
	 * @param g
	 *          ����
	 */
	protected abstract void paint(Graphics g);

	public void setGameDto(GameDto gameDto) {
		this.gameDto = gameDto;
	}

}
