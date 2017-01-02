package org.ghty826.boot.ui;

import org.ghty826.boot.util.Img;

import java.awt.Graphics;

/**
 * ˵����
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-17 ����10:54:14
 */
public class LayerBackground extends Layer {

	public LayerBackground(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	protected void paint(Graphics g) {
		g.drawImage(
		    Img.BG_LIST.get(getGameDto().getCurlevel() % Img.BG_LIST.size()), 0, 0,
		    JFrameGame.WIDTH, JFrameGame.HEIGHT, null);
	}

}
