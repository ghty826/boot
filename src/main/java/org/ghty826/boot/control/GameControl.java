package org.ghty826.boot.control;

import org.ghty826.boot.dto.GameDto;
import org.ghty826.boot.service.GameService;
import org.ghty826.boot.service.impl.GameServiceImpl;
import org.ghty826.boot.ui.JFrameGame;
import org.ghty826.boot.ui.JPanelGame;
import org.ghty826.boot.ui.cfg.JFrameConfig;
import org.ghty826.boot.util.CommonUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

/**
 * ˵����������ҿ����¼�<br />
 * 
 * ���ƻ��棬������Ϸ�߼�
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-19 ����9:50:57
 */
public class GameControl {
	private class TetrisThread extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(gameDto.getSleepTimpe());
				} catch (InterruptedException e) {
				}
				if (!gameDto.isStart()) {
					panelGame.setBtnEnabled(true);
					continue;
				}
				if (gameDto.isPause()) {
					continue;
				}
				gameService.downAct();
				panelGame.repaint();
			}
		}
	}

	/** ��Ϸ����� */
	private JPanelGame panelGame;
	/** ��Ϸ�߼��� */
	private GameService gameService;
	private JFrameConfig frameConfig;
	private Thread gameThread;
	// ������Ϸ����Դ
	private GameDto gameDto;
	// ������Ϸ���ڣ���װ��Ϸ��壩
	private JFrameGame frameGame;
	/** ���̰����¼� */
	private final Map<Integer, Method> keyPre;
	/** �����ɿ��¼� */
	private final Map<Integer, Method> keyRel;
	private static final String KEY_RIGHT = JFrameConfig.METHOD_NAMES[0];
	private static final String KEY_UP = JFrameConfig.METHOD_NAMES[1];
	private static final String KEY_LEFT = JFrameConfig.METHOD_NAMES[2];
	private static final String KEY_DOWN = JFrameConfig.METHOD_NAMES[3];
	private static final String KEY_FUN_LEFT = JFrameConfig.METHOD_NAMES[4];
	private static final String KEY_FUN_UP = JFrameConfig.METHOD_NAMES[5];
	private static final String KEY_FUN_RIGHT = JFrameConfig.METHOD_NAMES[6];
	private static final String KEY_FUN_DOWN = JFrameConfig.METHOD_NAMES[7];
	private static final Class<GameService> clazz = GameService.class;
	private final Map<String, Integer> ctrls;
	private static Method keyRight;
	private static Method keyUp;
	private static Method keyLeft;
	private static Method keyDownRel;
	private static Method keyDown;
	private static Method keyFunLeft;
	private static Method keyFunUp;
	private static Method keyFunRight;
	private static Method keyFunDown;

	static {
		// ���̰����¼�
		try {
			keyRight = clazz.getDeclaredMethod(KEY_RIGHT);
			keyUp = clazz.getDeclaredMethod(KEY_UP);
			keyLeft = clazz.getDeclaredMethod(KEY_LEFT);
			keyDownRel = clazz.getDeclaredMethod("keyDownReleased");
			keyDown = clazz.getDeclaredMethod(KEY_DOWN);
			keyFunLeft = clazz.getDeclaredMethod(KEY_FUN_LEFT);
			keyFunUp = clazz.getDeclaredMethod(KEY_FUN_UP);
			keyFunRight = clazz.getDeclaredMethod(KEY_FUN_RIGHT);
			keyFunDown = clazz.getDeclaredMethod(KEY_FUN_DOWN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GameControl() {
		keyPre = new HashMap<Integer, Method>(6);
		keyRel = new HashMap<Integer, Method>(3);
		ctrls = new HashMap<String, Integer>(8);
		// ������Ϸ����Դ
		gameDto = new GameDto();
		// ������Ϸ�߼��飨������Ϸ����Դ��
		gameService = new GameServiceImpl(gameDto);
		// ������Ϸ��壨������Ϸ����Դ��
		panelGame = new JPanelGame(this, gameDto);
		// ������Ϸ���ڣ���װ��Ϸ��壩
		frameGame = new JFrameGame(panelGame);
		// ������Ϸ���ڿɼ�
		frameGame.setVisible(true);
		// ����Ĭ�Ϲر����ԣ����������
		frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameConfig = new JFrameConfig(this);
		setKeyCfg();
	}

	/**
	 * �����������
	 */
	public void hidePlayerConfig() {
		setKeyCfg();
		frameGame.setEnabled(true);
		frameConfig.setVisible(false);
	}

	/**
	 * ִ�з��䷽��
	 * 
	 * @param method
	 */
	private void invoke(Method method) {
		try {
			if (gameDto.isStart()) {
				method.invoke(gameService);
				panelGame.repaint();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������Ұ��¼����¼�������Ϸ ��Ϊ
	 * 
	 * @param keyCode
	 */
	public void keyPressed(int keyCode) {
		if (!gameDto.isPause() && keyPre.containsKey(keyCode)) {
			invoke(keyPre.get(keyCode));
		}
	}

	/**
	 * ��������ɿ������¼�������Ϸ ��Ϊ
	 * 
	 * @param keyCode
	 */
	public void keyReleased(int keyCode) {
		if (keyRel.containsKey(keyCode)) {
			invoke(keyRel.get(keyCode));
		}
	}

	@SuppressWarnings("unchecked")
	public void setKeyCfg() {
		ObjectInput oi = null;
		try {
			oi = new ObjectInputStream(new FileInputStream(
			    JFrameConfig.CONTROL_DAT_PATH));
			ctrls.clear();
			ctrls.putAll((Map<String, Integer>) oi.readObject());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (oi != null) {
				try {
					oi.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		if (ctrls.isEmpty()) {
			ctrls.putAll(CommonUtil.getDefaultKeyCfg());
		}
		keyPre.put(ctrls.get(KEY_RIGHT), keyRight);
		keyPre.put(ctrls.get(KEY_LEFT), keyLeft);
		keyPre.put(ctrls.get(KEY_DOWN), keyDown);
		keyPre.put(ctrls.get(KEY_FUN_LEFT), keyFunLeft);
		keyPre.put(ctrls.get(KEY_FUN_RIGHT), keyFunRight);
		keyPre.put(ctrls.get(KEY_FUN_DOWN), keyFunDown);
		keyRel.put(ctrls.get(KEY_UP), keyUp);
		keyRel.put(ctrls.get(KEY_DOWN), keyDownRel);
		keyRel.put(ctrls.get(KEY_FUN_UP), keyFunUp);
	}

	/**
	 * ��ʾ�������
	 */
	public void showPlayerConfig() {
		gameService.pause(true);
		panelGame.repaint();
		frameGame.setEnabled(false);
		frameConfig.setVisible(true);
	}

	/**
	 * ��ʼ��Ϸ
	 */
	public void startGame() {
		gameDto.clear();
		panelGame.setBtnEnabled(false);
		gameService.startGame();
		panelGame.repaint();
		if (gameThread == null) {
			gameThread = new TetrisThread();
			gameThread.start();
		}
	}

}
