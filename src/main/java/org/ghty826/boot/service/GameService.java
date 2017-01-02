package org.ghty826.boot.service;

/**
 * ˵����
 * 
 * @author org.ghty826.boot
 * @version ����ʱ�䣺2013-5-9 ����8:22:26
 */
public interface GameService {
	/**
	 * ��������
	 */
	public boolean downAct();

	/**
	 * ����������£�
	 */
	public boolean keyDown();

	/**
	 * ��������ɿ����£�
	 */
	public boolean keyDownReleased();

	/**
	 * ���ܲ������¡���棩
	 */
	public boolean keyFunDown();

	/**
	 * ���ܲ������󡢷��飩
	 */
	public boolean keyFunLeft();

	/**
	 * ���ܲ������ҡ�ԲȦ��
	 */
	public boolean keyFunRight();

	/**
	 * ���ܲ������ϡ����ǣ�
	 */
	public boolean keyFunUp();

	/**
	 * �����������
	 */
	public boolean keyLeft();

	/**
	 * ����������ң�
	 */
	public boolean keyRight();

	/**
	 * ����������ϣ�
	 */
	public boolean keyUp();

	/**
	 * ��ͣ�߳�
	 * 
	 * @return
	 */
	public boolean pause(boolean pause);

	/**
	 * �������߳�
	 */
	public void startGame();

}
