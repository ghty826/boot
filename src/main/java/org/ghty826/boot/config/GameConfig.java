package org.ghty826.boot.config;

import org.ghty826.boot.util.CommonUtil;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * ˵������Ϸ������
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-18 ����8:31:47
 */
public class GameConfig {

	private static final Element game;
	static {
		// ����XML��ȡ��
		SAXReader reader = new SAXReader();
		// ��ȡXML�ļ�
		// Document doc = reader.read("./config/cfg.xml");
		Document doc = null;
		try {
			doc = reader.read(CommonUtil.getURL("org.ghty826.boot/config/cfg.xml"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		// ���XML�ļ��ĸ��ڵ�
		game = doc.getRootElement();
	}

	/**
	 * ���أ���Ϸ������������{@link DataConfig}����Ψһʵ����
	 * 
	 * @return ����{@link DataConfig}��Ψһʵ��
	 */
	public static DataConfig getDataConfig() {
		// �������ݿ���ʲ���
		return DataConfig.getInstance(game.element("data"));
	}

	/**
	 * ���أ���Ϸ������������{@link FrameConfig}����Ψһʵ����
	 * 
	 * @return ����{@link FrameConfig}��Ψһʵ��
	 */
	public static FrameConfig getFrameConfig() {
		// ���ô��ڲ���
		return FrameConfig.getInstance(game.element("frame"));
	}

	/**
	 * ���أ���Ϸϵͳ��������{@link SystemConfig}����Ψһʵ����
	 * 
	 * @return ����{@link SystemConfig}��Ψһʵ��
	 */
	public static SystemConfig getSystemConfig() {
		// ����ϵͳ����
		return SystemConfig.getInstance(game.element("system"));
	}

	private GameConfig() {

	}

}
