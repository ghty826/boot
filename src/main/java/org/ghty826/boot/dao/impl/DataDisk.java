package org.ghty826.boot.dao.impl;

import org.ghty826.boot.dao.Data;
import org.ghty826.boot.entity.Player;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * ˵����
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-26 ����7:57:58
 */
public class DataDisk implements Data {

	public static class Test {
		public static void main(String[] ghty) {
			DataDisk dd = new DataDisk();
			List<Player> diskRecord = new ArrayList<Player>(5);
			diskRecord.add(new Player("�ظ���", 15500));
			diskRecord.add(new Player("�ϰ�˹", 3100));
			diskRecord.add(new Player("����", 19300));
			diskRecord.add(new Player("���", 8600));
			diskRecord.add(new Player("��˹", 1300));
			dd.saveData(diskRecord);
			for (Player player : dd.loadData()) {
				System.out.println(player);
			}
		}
	}

	private static final String SAVE_PATH = "data/tetris_record.dat";

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> loadData() {
		ObjectInput oi = null;
		List<Player> record = null;
		try {
			oi = new ObjectInputStream(new FileInputStream(SAVE_PATH));
			record = (List<Player>) oi.readObject();
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
		List<Player> diskRecord = new ArrayList<Player>(5);
		diskRecord.add(new Player("�ظ���", 15500));
		diskRecord.add(new Player("�ϰ�˹", 3100));
		diskRecord.add(new Player("����", 19300));
		diskRecord.add(new Player("���", 8600));
		diskRecord.add(new Player("��˹", 1300));
		return record == null ? diskRecord : record;
	}

	@Override
	public void saveData(List<Player> save) {
		ObjectOutput oo = null;
		try {
			oo = new ObjectOutputStream(new FileOutputStream(SAVE_PATH));
			oo.writeObject(save);
			oo.flush();
		} catch (Exception e) {
		} finally {
			if (oo != null) {
				try {
					oo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
