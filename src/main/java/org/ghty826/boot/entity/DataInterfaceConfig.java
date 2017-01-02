package org.ghty826.boot.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

/**
 * ˵�������ݽӿ�����ʵ����
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-28 ����10:13:50
 */
public class DataInterfaceConfig implements Serializable {
	private static final long serialVersionUID = -5514226738393268828L;
	private final String clazz;
	private final Map<String, String> param;

	public DataInterfaceConfig(Element data) {
		clazz = data.attributeValue("clazz");
		param = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		List<Element> params = data.elements("param");
		for (Element e : params) {
			String key = e.attributeValue("key");
			String value = e.attributeValue("value");
			param.put(key, value);
		}
	}

	public String getClazz() {
		return clazz;
	}

	public Map<String, String> getParam() {
		return param;
	}

}
