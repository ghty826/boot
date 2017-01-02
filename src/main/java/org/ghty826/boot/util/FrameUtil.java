package org.ghty826.boot.util;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

/**
 * ˵����
 * 
 * @author org.ghty826.boot
 * @version ����ʱ�䣺2013-5-7 ����9:43:39
 */
public class FrameUtil {
	/**
	 * ����frame����
	 * 
	 * @param frame
	 */
	public static void setFrameCenter(Frame frame) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = screen.width - frame.getWidth() >> 1;
		int y = (screen.height - frame.getHeight() >> 1) - 32;
		frame.setLocation(x, y);
	}
}
