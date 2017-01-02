package org.ghty826.boot.config;

import org.ghty826.boot.entity.LayerConfig;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

/**
 * ˵������Ϸ������������
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-28 ����9:19:40
 */
public class FrameConfig {
	/** ���ڿ�� */
	private int width;

	/** ���ڸ߶� */
	private int height;

	/** �߿�ߴ� */
	private int border;

	/** �߿����ϱ߾� */
	private int paddingTop;

	/** �߿�����߾� */
	private int paddingLeft;

	/** ͼ������ */
	private List<LayerConfig> layerConfigs;

	/***/
	private String title;

	private static FrameConfig instance;

	/**
	 * ���ر��ࣨ��Ϸ������������{@link FrameConfig}����Ψһʵ����<br />
	 * 
	 * ˫����,��ͬ�����ݵ��·�{@code if}�ڲ������ҽ��е�һ�λ�ȡ����ʱ��ͬ����
	 * 
	 * @param frame
	 *          xml�ļ�Ԫ��
	 * @return ����{@link FrameConfig}��Ψһʵ��
	 */
	public static FrameConfig getInstance(Element frame) {
		if (instance == null) {
			synchronized (GameConfig.class) {
				if (null == instance) {
					instance = new FrameConfig(frame);
				}
			}
		}
		return instance;
	}

	private FrameConfig(Element frame) {
		setUiConfig(frame);
	}

	public int getBorder() {
		return border;
	}

	public int getHeight() {
		return height;
	}

	public List<LayerConfig> getLayerConfigs() {
		return layerConfigs;
	}

	public int getPaddingLeft() {
		return paddingLeft;
	}

	public int getPaddingTop() {
		return paddingTop;
	}

	public String getTitle() {
		return title;
	}

	public int getWidth() {
		return width;
	}

	/**
	 * ���ô��ڲ���
	 * 
	 * @param frame
	 *          �����ļ��Ĵ�������Ԫ��
	 */
	private void setUiConfig(Element frame) {
		// ��ȡ���ڿ��
		width = Integer.valueOf(frame.attributeValue("width"));
		// ��ȡ���ڸ߶�
		height = Integer.valueOf(frame.attributeValue("height"));
		// ��ȡ�߿��ϸ
		border = Integer.valueOf(frame.attributeValue("border"));
		// ��ȡ�߿����ϱ߾�
		paddingTop = Integer.valueOf(frame.attributeValue("paddingTop"));
		// ��ȡ�߿�����߾�
		paddingLeft = Integer.valueOf(frame.attributeValue("paddingLeft"));
		// ��ȡ���ڱ���
		title = frame.attributeValue("title");
		// ��ȡ��������
		@SuppressWarnings("unchecked")
		List<Element> layers = frame.elements("layer");
		layerConfigs = new ArrayList<LayerConfig>();
		// ��ȡ���д�������
		for (Element layer : layers) {
			// ���õ�����������
			layerConfigs.add(new LayerConfig(layer.attributeValue("class"), Integer
			    .valueOf(layer.attributeValue("x")), Integer.valueOf(layer
			    .attributeValue("y")), Integer.valueOf(layer.attributeValue("w")),
			    Integer.valueOf(layer.attributeValue("h"))));
		}
	}
}
