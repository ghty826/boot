package org.ghty826.boot.dao;

import org.ghty826.boot.entity.Player;

import java.util.List;

/**
 * ˵�������ݷ��ʽӿ�
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-24 ����7:55:12
 */
public interface Data {
	/**
	 * �������
	 * 
	 * @return
	 */
	public List<Player> loadData();

	/**
	 * ��������
	 */
	public void saveData(List<Player> save);
}
