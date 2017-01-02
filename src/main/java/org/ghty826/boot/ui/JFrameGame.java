package org.ghty826.boot.ui;

import org.ghty826.boot.config.FrameConfig;
import org.ghty826.boot.config.GameConfig;
import org.ghty826.boot.util.FrameUtil;

import javax.swing.JFrame;

/**
 * ˵������ϷFrame
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-17 ����7:57:43
 */
public class JFrameGame extends JFrame {

	private static final long serialVersionUID = -6194695820531427582L;
	/** ��Ϸ���ڵĿ�� */
	public static final int WIDTH;

	/** ��Ϸ���ڵĸ߶� */
	public static final int HEIGHT;

	/** ���ڱ��� */
	private static String title;

	static {
		// �����Ϸ����
		FrameConfig cfg = GameConfig.getFrameConfig();
		WIDTH = cfg.getWidth();
		HEIGHT = cfg.getHeight();
		title = cfg.getTitle();
	}

	public JFrameGame(JPanelGame panelGame) {
		// ���ñ���
		setTitle(title);
		// ���ô��ڴ�С
		setSize(WIDTH, HEIGHT);
		// �������û��ı䴰�ڴ�С
		setResizable(false);
		// ���ô��ھ���,�����ݵĲ���Ϊnull��ʱ������
		// setLocationRelativeTo(null);
		FrameUtil.setFrameCenter(this);
		// ����Ĭ��Panel
		setContentPane(panelGame);
	}

}
