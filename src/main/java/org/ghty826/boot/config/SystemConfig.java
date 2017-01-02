package org.ghty826.boot.config;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

/**
 * ˵������Ϸϵͳ��������
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-28 ����9:19:51
 */
public class SystemConfig {
	private static SystemConfig instance;

	/**
	 * ���ر��ࣨ��Ϸϵͳ��������{@link SystemConfig}����Ψһʵ����<br />
	 * 
	 * ˫����,��ͬ�����ݵ��·�{@code if}�ڲ������ҽ��е�һ�λ�ȡ����ʱ��ͬ����
	 * 
	 * @param system
	 *          xml�ļ�Ԫ��
	 * @return ����{@link SystemConfig}��Ψһʵ��
	 */
	public static SystemConfig getInstance(Element system) {
		if (instance == null) {
			synchronized (GameConfig.class) {
				if (null == instance) {
					instance = new SystemConfig(system);
				}
			}
		}
		return instance;
	}

	private final int minX;
	private final int maxX;
	private final int minY;
	private final int maxY;
	private final List<Point[]> typeConfig;

	private final List<Boolean> typeRound;

	private SystemConfig(Element system) {
		minX = Integer.parseInt(system.attributeValue("minX"));
		maxX = Integer.parseInt(system.attributeValue("maxX"));
		minY = Integer.parseInt(system.attributeValue("minY"));
		maxY = Integer.parseInt(system.attributeValue("maxY"));
		@SuppressWarnings("unchecked")
		List<Element> rects = system.elements("rect");
		typeConfig = new ArrayList<Point[]>(rects.size());
		typeRound = new ArrayList<Boolean>(rects.size());
		for (Element e : rects) {
			typeRound.add(Boolean.parseBoolean(e.attributeValue("round")));
			@SuppressWarnings("unchecked")
			List<Element> pointCfg = e.elements("point");
			// ����Point��������
			Point[] points = new Point[pointCfg.size()];
			// ��ʼ��Point��������
			for (int i = 0; i < points.length; i++) {
				int x = Integer.parseInt(pointCfg.get(i).attributeValue("x"));
				int y = Integer.parseInt(pointCfg.get(i).attributeValue("y"));
				points[i] = new Point(x, y);
			}
			// ��Point�������鱣�浽typeConfig��
			typeConfig.add(points);
		}
		setSystemConfig(system);
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public int getMinX() {
		return minX;
	}

	public int getMinY() {
		return minY;
	}

	public List<Point[]> getTypeConfig() {
		return typeConfig;
	}

	public List<Boolean> getTypeRound() {
		return typeRound;
	}

	/**
	 * ����ϵͳ����
	 * 
	 * @param system
	 *          �����ļ���ϵͳ����Ԫ��
	 */
	private void setSystemConfig(Element system) {
		// TODO ����ϵͳ����
	}

}
