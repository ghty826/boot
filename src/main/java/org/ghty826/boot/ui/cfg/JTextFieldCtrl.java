package org.ghty826.boot.ui.cfg;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

/**
 * ˵����
 * 
 * @author org.ghty826.boot
 * @version ����ʱ�䣺2013-5-8 ����9:21:30
 */
public class JTextFieldCtrl extends JTextField {
	private static final long serialVersionUID = 8712258851300325924L;
	private final String methodName;
	private int keyCode;

	public JTextFieldCtrl(int x, int y, int w, int h, String methodName) {
		this.methodName = methodName;
		// �����ı���λ��
		setBounds(x, y, w, h);
		// �����ı�����̼����¼�
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				keyCode = e.getKeyCode();
				String keyChar = KeyEvent.getKeyText(e.getKeyCode());
				setText(keyChar);
			}
		});
	}

	public int getKeyCode() {
		return keyCode;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
		setText(KeyEvent.getKeyText(keyCode));
	}

}
