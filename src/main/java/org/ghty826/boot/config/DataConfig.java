package org.ghty826.boot.config;

import org.ghty826.boot.entity.DataInterfaceConfig;

import org.dom4j.Element;

/**
 * ˵������Ϸ������������
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-28 ����9:20:01
 */
public class DataConfig {
	private final int maxRow;
	private static DataConfig instance;

	/**
	 * ���ر��ࣨ��Ϸ������������{@link DataConfig}����Ψһʵ����<br />
	 * 
	 * ˫����,��ͬ�����ݵ��·�{@code if}�ڲ������ҽ��е�һ�λ�ȡ����ʱ��ͬ����<br />
	 * 
	 * @param data
	 *          xml�ļ�Ԫ��
	 * @return ����{@link DataConfig}��Ψһʵ��
	 */
	public static DataConfig getInstance(Element data) {
		if (instance == null) {
			synchronized (GameConfig.class) {
				if (null == instance) {
					instance = new DataConfig(data);
				}
			}
		}
		return instance;
	}

	private final DataInterfaceConfig dataDB;

	private final DataInterfaceConfig dataDisk;

	private DataConfig(Element data) {
		maxRow = Integer.parseInt(data.attributeValue("maxRow"));
		dataDB = new DataInterfaceConfig(data.element("dataDB"));
		dataDisk = new DataInterfaceConfig(data.element("dataDisk"));
		setDataConfig(data);
	}

	public DataInterfaceConfig getDataDB() {
		return dataDB;
	}

	public DataInterfaceConfig getDataDisk() {
		return dataDisk;
	}

	public int getMaxRow() {
		return maxRow;
	}

	/**
	 * �������ݿ���ʲ���
	 * 
	 * @param data
	 *          �����ļ������ݿ�����Ԫ��
	 */
	private void setDataConfig(Element data) {
		// TODO �������ݿ���ʲ���

	}

}
