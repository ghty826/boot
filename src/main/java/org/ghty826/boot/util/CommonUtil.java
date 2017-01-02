package org.ghty826.boot.util;

import org.ghty826.boot.ui.cfg.JFrameConfig;

import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * ˵������ͨ������
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-24 ����11:25:07
 */
public final class CommonUtil {
	public static class Test {

		public static void main(String[] args) {
			for (int i = 0; i < 50; i++) {
				long sleep = 8909 / (i + 13);
				System.out.println(i + " " + sleep);
			}
		}

		public static void test(String[] args) {
			System.out.println(getURL("note.txt"));
			System.out.println(getURL("readme.xlsx"));
			System.out.println(getURL("org.ghty826.boot/image/window/Window.png"));
		}

		public static void ttt(String[] args) {
			for (String s : getDefaultKeyCfg().keySet()) {
				System.out.println(s);
				System.out.println(getDefaultKeyCfg().get(s));
			}
		}
	}

	public static Map<String, Integer> getDefaultKeyCfg() {
		Map<String, Integer> keyCfg = new HashMap<String, Integer>();
		keyCfg.put(JFrameConfig.METHOD_NAMES[0], KeyEvent.VK_F);
		keyCfg.put(JFrameConfig.METHOD_NAMES[1], KeyEvent.VK_E);
		keyCfg.put(JFrameConfig.METHOD_NAMES[2], KeyEvent.VK_S);
		keyCfg.put(JFrameConfig.METHOD_NAMES[3], KeyEvent.VK_D);
		keyCfg.put(JFrameConfig.METHOD_NAMES[4], KeyEvent.VK_J);
		keyCfg.put(JFrameConfig.METHOD_NAMES[5], KeyEvent.VK_U);
		keyCfg.put(JFrameConfig.METHOD_NAMES[6], KeyEvent.VK_K);
		keyCfg.put(JFrameConfig.METHOD_NAMES[7], KeyEvent.VK_I);
		return keyCfg;
	}

	/**
	 * ��ȡ�ļ�������
	 * 
	 * @param path
	 *          ���·��
	 * @return
	 */
	public static InputStream getInputStream(String path) {
		return ClassLoader.getSystemResourceAsStream(path);
	}

	public static long getSleepTimeByLevel(int level) {
		long sleep = 8909 / (level + 13);
		// sleep = -40 * level + 750;
		// sleep = sleep < 100 ? 100 : sleep;
		return sleep;
	}

	/**
	 * ��ȡURL
	 * 
	 * @param path
	 *          ���·��
	 * @return
	 */
	public static URL getURL(String path) {
		return ClassLoader.getSystemResource(path);
	}
}
