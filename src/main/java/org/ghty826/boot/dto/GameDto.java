package org.ghty826.boot.dto;

import org.ghty826.boot.control.GameAct;
import org.ghty826.boot.dao.Data;
import org.ghty826.boot.dao.impl.DataBase;
import org.ghty826.boot.dao.impl.DataDisk;
import org.ghty826.boot.entity.Player;
import org.ghty826.boot.util.CommonUtil;

import java.util.Collections;
import java.util.List;

/**
 * ˵������Ϸ���ݴ���
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-19 ����10:54:17
 */
public class GameDto {
	// TODO �����ļ�
	/** ��Ϸ��� */
	public static final int GAMEZONE_W = 10;
	/** ��Ϸ�߶� */
	public static final int GAMEZONE_H = 18;
	/** �������� */
	public static final int LEVEL_UP = 20;
	// �Ƿ���ʾ��Ӱ
	private boolean isShowShadow;
	// ��Ϸ�Ѿ���ʼ
	private boolean isStart;
	private Data disk;
	private Data db;
	private boolean isPause;

	/** ���ݿ��¼ */
	private List<Player> dbRecord;

	/** ���ؼ�¼ */
	private List<Player> diskRecord;

	/** ��Ϸ��ͼ */
	private boolean[][] gameMap;

	/** ���䷽�� */
	private GameAct gameAct;

	/** ��һ������ */
	private int next;

	/** �ȼ� */
	private int curlevel;

	/** ��ǰ���� */
	private int curPoint;

	/** ���� */
	private int curRemoveLine;

	private long sleepTimpe;

	/**
	 * ���캯��
	 */
	public GameDto() {
		super();
		initGameDto();
		setCurlevel(curlevel);
	}

	public void clear() {
		for (boolean[] row : gameMap) {
			for (int i = 0; i < row.length; i++) {
				row[i] = false;
			}
		}
		setCurlevel(0);
		setCurPoint(0);
		setCurRemoveLine(0);
	}

	public int getCurlevel() {
		return curlevel;
	}

	public int getCurPoint() {
		return curPoint;
	}

	public int getCurRemoveLine() {
		return curRemoveLine;
	}

	public List<Player> getDbRecord() {
		dbRecord = db.loadData();
		Collections.sort(dbRecord);
		return dbRecord;
	}

	public List<Player> getDiskRecord() {
		diskRecord = disk.loadData();
		Collections.sort(diskRecord);
		return diskRecord;
	}

	public GameAct getGameAct() {
		return gameAct;
	}

	public boolean[][] getGameMap() {
		return gameMap;
	}

	public int getNext() {
		return next;
	}

	public long getSleepTimpe() {
		return sleepTimpe;
	}

	/**
	 * gameDto��ʼ��
	 */
	public void initGameDto() {
		gameMap = new boolean[GAMEZONE_W][GAMEZONE_H];
		disk = new DataDisk();
		db = new DataBase();
	}

	public boolean isPause() {
		return isPause;
	}

	public boolean isShowShadow() {
		return isShowShadow;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setCurlevel(int curlevel) {
		sleepTimpe = CommonUtil.getSleepTimeByLevel(curlevel);
		this.curlevel = curlevel;
	}

	public void setCurPoint(int curPoint) {
		this.curPoint = curPoint;
	}

	public void setCurRemoveLine(int curRemoveLine) {
		this.curRemoveLine = curRemoveLine;
	}

	public void setDbRecord(List<Player> dbRecord) {
		this.dbRecord = dbRecord;
		db.saveData(dbRecord);
	}

	public void setDiskRecord(List<Player> diskRecord) {
		this.diskRecord = diskRecord;
		disk.saveData(diskRecord);
	}

	public void setGameAct(GameAct gameAct) {
		this.gameAct = gameAct;
	}

	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public void setPause(boolean isPause) {
		this.isPause = isPause;
	}

	public void setShowShadow(boolean isShowShadow) {
		this.isShowShadow = isShowShadow;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

}
