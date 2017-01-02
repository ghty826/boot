package org.ghty826.boot.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * ˵������ҿ�����
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-19 ����9:36:47
 */
public class PlayerControl extends KeyAdapter {
	private GameControl gameControl;

	public PlayerControl(GameControl gameControl) {
		this.gameControl = gameControl;
	}

	/**
	 * ���̰����¼�
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		gameControl.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gameControl.keyReleased(e.getKeyCode());
	}
}
