package org.ghty826.boot.service.impl;

import org.ghty826.boot.control.GameAct;
import org.ghty826.boot.dto.GameDto;
import org.ghty826.boot.service.GameService;

import java.awt.Point;
import java.util.Random;

/**
 * ˵������Ϸҵ���߼�
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-19 ����9:56:18
 */
public class GameServiceImpl implements GameService {
	/** ��Ϸ���ݴ������ */
	private GameDto gameDto;
	/** ��һֱ��������ļ� */
	private boolean isAlwaysPressedDown = false;
	/** ���·��� */
	private boolean isNewGamePoints = true;
	/** ����������� */
	private Random random = new Random(System.currentTimeMillis());

	// TODO �����ļ�
	/** ����������� */
	private static final int MAX_TYPE = 7;

	public GameServiceImpl(GameDto gameDto) {
		this.gameDto = gameDto;
	}

	/**
	 * �����Ϸʧ��
	 */
	private boolean checkLose() {
		boolean isLose = false;
		Point[] next = gameDto.getGameAct().getActPoints();
		boolean[][] map = gameDto.getGameMap();
		for (Point p : next) {
			if (map[p.x][p.y]) {
				isLose = true;
				break;
			}
		}
		return isLose;
	}

	@Override
	public boolean downAct() {
		synchronized (gameDto) {
			if (gameDto.getGameAct() == null) {
				return false;
			}
			// ���������ƶ������ж��Ƿ��ƶ��ɹ�
			if (gameDto.getGameAct().move(0, 1, gameDto.getGameMap())) {
				return false;
			}
			// ��ȡ��Ϸ��ͼ����
			boolean[][] gameMap = gameDto.getGameMap();
			// ��÷������
			Point[] gameAct = gameDto.getGameAct().getActPoints();
			// ������ѻ�����ͼ����
			for (Point p : gameAct) {
				gameMap[p.x][p.y] = true;
			}
			// �����������
			int plusExp = plusExp();
			// �ж�������������0
			if (plusExp > 0) {
				// �������
				plusPoint(plusExp);
			}
			// ˢ��һ���µķ���
			// ������һ������
			gameDto.getGameAct().init(gameDto.getNext());
			isNewGamePoints = true;
			// ���������һ����������
			gameDto.setNext(random.nextInt(MAX_TYPE));
			// �����Ϸʧ��
			if (checkLose()) {
				onLose();
			}
			return true;
		}
	}

	/**
	 * �ж�ĳһ���Ƿ������
	 * 
	 * @param rowIndex
	 * @param map
	 * @return
	 */
	private boolean isCanRemoeLine(int rowIndex, boolean[][] map) {
		for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
			if (!map[x][rowIndex]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * ����������£�
	 */
	@Override
	public boolean keyDown() {
		// �ж��Ƿ����µķ���
		if (isNewGamePoints) {
			// �Ƿ�һֱ���������
			if (isAlwaysPressedDown) {
				return false;
			}
			// �������µķ���
			isNewGamePoints = false;
		}
		isAlwaysPressedDown = true;
		return downAct();
	}

	/**
	 * ����������£�<br />
	 * �ɿ�����
	 * 
	 * @return
	 */
	@Override
	public boolean keyDownReleased() {
		isAlwaysPressedDown = false;
		return isAlwaysPressedDown;
	}

	/**
	 * ���ܲ������£�<br />
	 * ֱ�����䵽��
	 * 
	 * @return
	 */
	@Override
	public boolean keyFunDown() {
		synchronized (gameDto) {
			while (!downAct())
				;
		}
		return true;
	}

	/**
	 * ���ܲ�������<br />
	 * ˳ʱ����ת
	 * 
	 * @return
	 */
	@Override
	public boolean keyFunLeft() {
		synchronized (gameDto) {
			return gameDto.getGameAct().round(gameDto.getGameMap(), true);
		}
	}

	/**
	 * ���ܲ������ң�<br />
	 * ��ʱ����ת
	 * 
	 * @return
	 */
	@Override
	public boolean keyFunRight() {
		synchronized (gameDto) {
			return gameDto.getGameAct().round(gameDto.getGameMap(), false);
		}

	}

	/**
	 * ���ܲ������ϣ�<br />
	 * �Ƿ���ʾ��Ӱ
	 * 
	 * @return
	 */
	@Override
	public boolean keyFunUp() {
		// TODO ����
		// plusPoint(random.nextInt(9) + 1);
		gameDto.setShowShadow(!gameDto.isShowShadow());
		return gameDto.isShowShadow();
	}

	/**
	 * �����������
	 * 
	 * @return
	 */
	@Override
	public boolean keyLeft() {
		synchronized (gameDto) {
			return gameDto.getGameAct().move(-1, 0, gameDto.getGameMap());
		}
	}

	/**
	 * ����������ң�
	 * 
	 * @return
	 */
	@Override
	public boolean keyRight() {
		synchronized (gameDto) {
			return gameDto.getGameAct().move(1, 0, gameDto.getGameMap());
		}
	}

	/**
	 * ����������ϣ�<br />
	 * ��ͣ
	 * 
	 * @return
	 */
	@Override
	public boolean keyUp() {
		// synchronized (gameDto) {
		// return gameDto.getGameAct().round(gameDto.getGameMap(), true);
		// }
		if (gameDto.isStart()) {
			gameDto.setPause(!gameDto.isPause());
		}
		return gameDto.isPause();
	}

	/**
	 * ����Ϸʧ��ʱ
	 */
	private void onLose() {
		gameDto.setStart(false);
		// TODO ��ʾ������ִ���
	}

	@Override
	public boolean pause(boolean pause) {
		if (gameDto.isStart()) {
			gameDto.setPause(pause);
		}
		return false;
	}

	/**
	 * ���в�������������
	 * 
	 * @return
	 */
	private int plusExp() {
		boolean[][] map = gameDto.getGameMap();
		int exp = 0;
		for (int y = 0; y < GameDto.GAMEZONE_H; y++) {
			if (isCanRemoeLine(y, map)) {
				removeLine(y, map);
				exp++;
			}
		}
		return exp;
	}

	/**
	 * �����Ѿ��������������ӷ�,�����У��ӵȼ�<br />
	 * ���ݾ������ӻ���
	 * 
	 * @param plusExp
	 *          ���ӵľ���
	 */
	private void plusPoint(int plusExp) {
		int level = gameDto.getCurlevel();
		int rmLine = gameDto.getCurRemoveLine();
		int point = gameDto.getCurPoint();
		if (rmLine % GameDto.LEVEL_UP + plusExp >= GameDto.LEVEL_UP) {
			gameDto.setCurlevel(++level);
		}
		gameDto.setCurRemoveLine(rmLine + plusExp);
		// TODO ���н������������ļ�
		gameDto.setCurPoint(point + (plusExp << 1) * 10);
	}

	/**
	 * ���д���
	 * 
	 * @param rowIndex
	 * @param map
	 */
	private void removeLine(int rowIndex, boolean[][] map) {
		for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
			for (int y = rowIndex; y > 0; y--) {
				map[x][y] = map[x][y - 1];
			}
			map[x][0] = false;
		}
	}

	@Override
	public void startGame() {
		GameAct act = new GameAct(random.nextInt(MAX_TYPE));
		gameDto.setGameAct(act);
		gameDto.setNext(random.nextInt(MAX_TYPE));
		gameDto.setStart(true);
	}

}
