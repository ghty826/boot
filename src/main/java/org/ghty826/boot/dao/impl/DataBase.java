package org.ghty826.boot.dao.impl;

import org.ghty826.boot.dao.Data;
import org.ghty826.boot.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * ˵����
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-28 ����10:08:45
 */
public class DataBase implements Data {

	@Override
	public List<Player> loadData() {
		// TODO ���ݿ��ȡ����
		List<Player> db = new ArrayList<Player>(5);
		db.add(new Player("�ظ���", 38600));
		db.add(new Player("�ϰ�˹", 5300));
		db.add(new Player("����", 29800));
		db.add(new Player("���", 9800));
		db.add(new Player("��˹", 3100));
		return db;
	}

	@Override
	public void saveData(List<Player> save) {
		// TODO ���ݿⱣ������
	}

}
