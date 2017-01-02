package org.ghty826.boot.ui;

import org.ghty826.boot.util.Img;

import java.awt.Graphics;

/**
 * ˵����
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-17 ����10:00:45
 */
public class LayerNext extends Layer {

	public LayerNext(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		createWindow(g);
		if (getGameDto().isStart()) {
			drawImageCenter(Img.NEXT_ACTS[getGameDto().getNext()], g);
		}
	}
}
