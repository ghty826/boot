package org.ghty826.boot.entity;

import java.io.Serializable;

/**
 * ˵����������ʵ����
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-18 ����8:33:58
 */
public class LayerConfig implements Serializable {
	private static final long serialVersionUID = 649482103212063499L;
	private String clazz;
	/** ͼ�����Ͻ�x���� */
	private int x;
	/** ͼ�����Ͻ�y���� */
	private int y;
	/** ͼ�����Ͻ�x����ƫ���� */
	private int w;
	/** ͼ�����Ͻ�y����ƫ���� */
	private int h;

	public LayerConfig(String clazz, int x, int y, int w, int h) {
		this.clazz = clazz;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public String getClazz() {
		return clazz;
	}

	public int getH() {
		return h;
	}

	public int getW() {
		return w;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
