package org.ghty826.boot.util;

import static org.ghty826.boot.util.CommonUtil.getURL;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * ˵����ͼƬ������,���಻�����̳�
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-24 ����5:17:26
 */
public final class Img {
	public static class Test {
		public static void main(String[] args) {
			System.out.println(Img.BG_LIST.size());
		}
	}

	/** ���� */
	public static final List<Image> BG_LIST;
	/** ��һ������ */
	public static final Image[] NEXT_ACTS;
	static {
		// TODO Ӳ����7
		NEXT_ACTS = new Image[7];
		for (int i = 0; i < NEXT_ACTS.length; i++) {
			NEXT_ACTS[i] = new ImageIcon(getURL("org.ghty826.boot/image/game/" + i + ".png"))
			    .getImage();
		}
		// ����ͼƬ����
		File bgDir = null;
		BG_LIST = new ArrayList<Image>();
		bgDir = new File("data/image/background");
		bgDir.mkdirs();
		for (File file : bgDir.listFiles()) {
			if (!file.isDirectory()) {
				BG_LIST.add(new ImageIcon(file.getPath()).getImage());
			}
		}
		if (BG_LIST.isEmpty()) {
			BG_LIST.add(new ImageIcon(getURL("org.ghty826.boot/image/window/dream.jpg"))
			    .getImage());
		}
	}

	/** ���� */
	public static final Image POINT = new ImageIcon(
	    getURL("org.ghty826.boot/image/string/point.png")).getImage();
	/** ���� */
	public static final Image RMLINE = new ImageIcon(
	    getURL("org.ghty826.boot/image/string/rmline.png")).getImage();
	/** Ӳ�� */
	public static final Image DISK = new ImageIcon(
	    getURL("org.ghty826.boot/image/string/disk.png")).getImage();
	/** ���ݿ� */
	public static final Image DB = new ImageIcon(
	    getURL("org.ghty826.boot/image/string/db.png")).getImage();
	/** ��ʼ */
	public static final ImageIcon START = new ImageIcon(
	    getURL("org.ghty826.boot/image/string/start.png"));
	/** ��ͣ */
	public static final Image PAUSE = new ImageIcon(
	    getURL("org.ghty826.boot/image/string/pause.png")).getImage();
	/** ���� */
	public static final ImageIcon CONFIG = new ImageIcon(
	    getURL("org.ghty826.boot/image/string/config.png"));
	/** �ȼ� */
	public static final Image LEVEL = new ImageIcon(
	    getURL("org.ghty826.boot/image/string/level.png")).getImage();
	/** ������ */
	public static final Image GAME = new ImageIcon(
	    getURL("org.ghty826.boot/image/game/rect.png")).getImage();
	/** ��Ӱ */
	public static final Image SHADOW = new ImageIcon(
	    getURL("org.ghty826.boot/image/game/shadow23blue.png")).getImage();
	/** ����ǩ�� */
	public static final Image SIGN = new ImageIcon(
	    getURL("org.ghty826.boot/image/string/sign.png")).getImage();
	/** ���ڱ߿� */
	public static final Image WINDOW = new ImageIcon(
	    getURL("org.ghty826.boot/image/window/Window.png")).getImage();
	/** ������ */
	public static final Image NUMBER = new ImageIcon(
	    getURL("org.ghty826.boot/image/string/num.png")).getImage();
	/** ֵ�� */
	public static final Image RECT = new ImageIcon(
	    getURL("org.ghty826.boot/image/window/rect.png")).getImage();

	/** ˽�й��췽�������಻������ʵ�� */
	private Img() {
	}
}
