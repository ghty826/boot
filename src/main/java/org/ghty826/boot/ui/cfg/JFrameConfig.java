package org.ghty826.boot.ui.cfg;

import static org.ghty826.boot.util.CommonUtil.getURL;
import org.ghty826.boot.control.GameControl;
import org.ghty826.boot.util.CommonUtil;
import org.ghty826.boot.util.FrameUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * ˵����
 * 
 * @author org.ghty826.boot
 * @version ����ʱ�䣺2013-5-7 ����9:23:31
 */
public class JFrameConfig extends JFrame {
	private static final long serialVersionUID = 7396848045323105300L;
	private static final Image IMG_PSP = new ImageIcon(
	    getURL("org.ghty826.boot/image/cfg/psp.jpg")).getImage();
	public static final String[] METHOD_NAMES = { "keyRight", "keyUp", "keyLeft",
	    "keyDown", "keyFunLeft", "keyFunUp", "keyFunRight", "keyFunDown" };
	public static final String[] TIPS = { "�ҷ����", "��ͣ��", "�����", "�·����", "˳ʱ����ת",
	    "��Ӱ���ؼ�", "��ʱ����ת", "������׼�" };
	public static final String CONTROL_DAT_PATH = "data/control.dat";
	private final Map<String, Integer> keyCfg = new HashMap<String, Integer>();
	private JLabel jlabErrorMsg = new JLabel();
	private JButton jbtnOK = new JButton("ȷ��");
	private JButton jbtnCancel = new JButton("ȡ��");
	private JButton jbtnUse = new JButton("Ӧ��");
	private List<JTextFieldCtrl> jtfCtrls = new ArrayList<JTextFieldCtrl>(8);
	private GameControl gameControl;

	public JFrameConfig(GameControl gameControl) {
		setTitle("�������");
		this.gameControl = gameControl;
		initKeyText();
		// ���ò��ֹ�����Ϊ���߽粼�֡�
		setLayout(new BorderLayout());
		// ��������
		add(createMainPanel(), BorderLayout.CENTER);
		// ��Ӱ�ť���
		add(createButtonPanel(), BorderLayout.SOUTH);
		// ���ô��ڴ�С
		setSize(660, 335);
		// �������û��ı䴰�ڴ�С
		setResizable(false);
		// ���ô��ھ���,�����ݵĲ���Ϊnull��ʱ������
		// setLocationRelativeTo(null);
		FrameUtil.setFrameCenter(this);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent e) {
				cancelEvent();
			}
		});
		setFocusTraversalPolicy();
	}

	private void cancelEvent() {
		jlabErrorMsg.setText(null);
		gameControl.hidePlayerConfig();
	}

	/**
	 * �����������
	 * 
	 * @return
	 */
	private Component createAboutPanel() {
		JPanel jp = new JPanel();
		jp.setLayout(null);
		JLabel jlabTitle = new JLabel("Java����˹����", SwingConstants.CENTER);
		jlabTitle.setFont(new Font("����", Font.PLAIN, 31));
		jlabTitle.setForeground(Color.BLUE);
		jlabTitle.setBounds(210, 20, 220, 30);
		jp.add(jlabTitle);
		JLabel jlabAuthor = new JLabel("�������䣺", SwingConstants.CENTER);
		jlabAuthor.setFont(new Font("����", Font.ITALIC, 23));
		jlabAuthor.setBounds(120, 70, 150, 30);
		jp.add(jlabAuthor);
		JTextField jtfAuthor = new JTextField("ghty826@163.com");
		jtfAuthor.setFont(new Font("����", Font.PLAIN, 23));
		jtfAuthor.setEditable(false);
		jtfAuthor.setBounds(270, 70, 230, 30);
		jp.add(jtfAuthor);
		JLabel jlabTime = new JLabel("���ʱ�䣺", SwingConstants.CENTER);
		jlabTime.setFont(new Font("����", Font.ITALIC, 23));
		jlabTime.setBounds(120, 120, 150, 30);
		jp.add(jlabTime);
		JTextField jtfTime = new JTextField("2013-05-12 18:27");
		jtfTime.setFont(new Font("����", Font.PLAIN, 23));
		jtfTime.setEditable(false);
		jtfTime.setBounds(270, 120, 230, 30);
		jp.add(jtfTime);
		String resume = "    �����϶��������������£����ڳ���Java����˹����1.0���ˡ�"
		    + "������һ�������Bug����Ҫ���׽�档������ĸ�׽ڣ�ף���е�ĸ��������֣�";
		JTextArea jta = new JTextArea(resume);
		jta.setFont(new Font("����", Font.ITALIC, 19));
		jta.setEditable(false);
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		jta.setBounds(110, 160, 450, 70);
		jp.add(jta);
		return jp;
	}

	/**
	 * ������ť���
	 * 
	 * @return
	 */
	private Component createButtonPanel() {
		JPanel jpl = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jpl.add(jlabErrorMsg);
		jbtnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (writeCfg(e)) {
					cancelEvent();
				}
			}
		});
		jpl.add(jbtnOK);
		jbtnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelEvent();
			}
		});
		jpl.add(jbtnCancel);
		jbtnUse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeCfg(e);
			}
		});
		jpl.add(jbtnUse);
		return jpl;
	}

	/**
	 * ���������������
	 * 
	 * @return
	 */
	private Component createControlPanel() {
		JPanel jpl = new JPanel() {
			private static final long serialVersionUID = 4245303976656666783L;

			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(IMG_PSP, 0, 0, null);
			}
		};
		// ���ò��ֹ�����Ϊnull
		jpl.setLayout(null);
		for (JTextFieldCtrl jtf : jtfCtrls) {
			jpl.add(jtf);
		}
		return jpl;
	}

	/**
	 * ��������壨ѡ���壩
	 * 
	 * @return
	 */
	private Component createMainPanel() {
		JTabbedPane jtp = new JTabbedPane();
		jtp.addTab("��������", createControlPanel());
		jtp.addTab("Ƥ������", createThemePanel());
		jtp.addTab("����", createAboutPanel());
		return jtp;
	}

	/**
	 * ����Ƥ���������
	 * 
	 * @return
	 */
	private Component createThemePanel() {
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		JLabel jlab = new JLabel("������ʱû��ʵ��,�����ڴ������汾", SwingConstants.CENTER);
		jlab.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 31));
		jlab.setForeground(Color.BLUE);
		jp.add(jlab, BorderLayout.CENTER);
		return jp;
	}

	@SuppressWarnings("unchecked")
	private void initKeyText() {
		JTextFieldCtrl jtfCtrl;
		for (int i = 0; i < 4; i++) {
			jtfCtrl = new JTextFieldCtrl(10, 35 + 33 * i, 80, 20, METHOD_NAMES[i]);
			jtfCtrl.setToolTipText(TIPS[i]);
			jtfCtrls.add(jtfCtrl);

		}
		for (int i = 4; i < 8; i++) {
			jtfCtrl = new JTextFieldCtrl(560, 45 + 30 * (i - 4), 80, 20,
			    METHOD_NAMES[i]);
			jtfCtrl.setToolTipText(TIPS[i]);
			jtfCtrls.add(jtfCtrl);
		}
		ObjectInput oi = null;
		try {
			oi = new ObjectInputStream(new FileInputStream(CONTROL_DAT_PATH));
			keyCfg.clear();
			keyCfg.putAll((Map<String, Integer>) oi.readObject());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
		if (keyCfg.isEmpty()) {
			keyCfg.putAll(CommonUtil.getDefaultKeyCfg());
		}
		for (JTextFieldCtrl jtf : jtfCtrls) {
			jtf.setHorizontalAlignment(SwingConstants.CENTER);
			jtf.setKeyCode(keyCfg.get(jtf.getMethodName()));
		}
	}

	/**
	 * �����ı��򽹵��л�˳��(�����б�˳��)
	 */
	private void setFocusTraversalPolicy() {
		setFocusTraversalPolicy(new FocusTraversalPolicy() {
			@Override
			public Component getComponentAfter(Container container,
			    Component component) {
				int index = jtfCtrls.indexOf(component);
				return jtfCtrls.get((index + 1) % jtfCtrls.size());
			}

			@Override
			public Component getComponentBefore(Container container,
			    Component component) {
				int index = jtfCtrls.indexOf(component);
				return jtfCtrls.get((index - 1 + jtfCtrls.size()) % jtfCtrls.size());
			}

			@Override
			public Component getDefaultComponent(Container container) {
				return jtfCtrls.get(0);
			}

			@Override
			public Component getFirstComponent(Container container) {
				return jtfCtrls.get(0);
			}

			@Override
			public Component getLastComponent(Container container) {
				return jtfCtrls.get(jtfCtrls.size() - 1);
			}
		});
	}

	private void showErrorMsg(String msg) {
		jlabErrorMsg.setForeground(Color.RED);
		jlabErrorMsg.setText(msg);
	}

	private void showRightMsg(String msg) {
		jlabErrorMsg.setForeground(Color.GREEN);
		jlabErrorMsg.setText(msg);
	}

	private boolean writeCfg(ActionEvent e) {
		boolean flag = false;
		keyCfg.clear();
		for (JTextFieldCtrl jtf : jtfCtrls) {
			if (keyCfg.containsValue(jtf.getKeyCode())) {
				showErrorMsg("�ظ�����");
				return flag;
			}
			keyCfg.put(jtf.getMethodName(), jtf.getKeyCode());
			if (jtf.getKeyCode() == 0) {
				showErrorMsg("���󰴼�");
				return flag;
			}
		}
		ObjectOutput oo = null;
		try {
			oo = new ObjectOutputStream(new FileOutputStream(CONTROL_DAT_PATH));
			oo.writeObject(keyCfg);
			oo.flush();
			flag = true;
			jlabErrorMsg.setForeground(Color.green);
			showRightMsg("���óɹ�");
		} catch (Exception e1) {
			showErrorMsg("δ֪����");
		} finally {
			if (oo != null) {
				try {
					oo.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return flag;
	}
}
