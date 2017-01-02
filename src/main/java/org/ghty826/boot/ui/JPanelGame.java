package org.ghty826.boot.ui;

import org.ghty826.boot.config.FrameConfig;
import org.ghty826.boot.config.GameConfig;
import org.ghty826.boot.control.GameControl;
import org.ghty826.boot.control.PlayerControl;
import org.ghty826.boot.dto.GameDto;
import org.ghty826.boot.entity.LayerConfig;
import org.ghty826.boot.util.Img;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * ˵������Ϸ��Panel
 * 
 * @author ����@org.ghty826.boot
 * @version ����ʱ�䣺2013-3-17 ����8:17:56
 */
public class JPanelGame extends JPanel {

	private static final long serialVersionUID = 4952360706078623773L;

	private List<Layer> layers;
	private JButton jbtnStart;
	private JButton jbtnConfig;
	private GameControl gameControl;

	public JPanelGame(GameControl gameControl, GameDto gameDto) {
		// ��װ��Ϸ����������Ϸ���
		this.gameControl = gameControl;
		setLayout(null);
		// ��ʼ����
		initLayer(gameDto);
		initComponent();
		// ��װ���̼�����
		addKeyListener(new PlayerControl(gameControl));
	}

	/**
	 * ��ʼ�����
	 * 
	 */
	private void initComponent() {
		Layer lay = null;
		for (Layer layer : layers) {
			if (layer instanceof LayerButton) {
				lay = layer;
			}
		}
		int btnW = (lay.getW() - 3 * Layer.PADDING_LEFT - 2 * Layer.BORDER) / 2;
		int btnH = lay.getH() - 2 * Layer.PADDING_TOP;
		int btnStartX = lay.getX() + Layer.PADDING_LEFT + Layer.BORDER;
		int btnConfgX = btnStartX + Layer.PADDING_LEFT + btnW;
		int btnY = lay.getY() + Layer.PADDING_TOP;
		jbtnStart = new JButton(Img.START);
		jbtnStart.setBounds(btnStartX, btnY, btnW, btnH);
		jbtnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.startGame();
			}
		});
		add(jbtnStart);
		jbtnConfig = new JButton(Img.CONFIG);
		jbtnConfig.setBounds(btnConfgX, btnY, btnW, btnH);
		jbtnConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.showPlayerConfig();
			}
		});
		add(jbtnConfig);
	}

	/**
	 * ��ʼ����
	 */
	private void initLayer(GameDto gameDto) {
		// �����Ϸ����
		FrameConfig cfg = GameConfig.getFrameConfig();
		// ��ò�����
		List<LayerConfig> layerCfgs = cfg.getLayerConfigs();
		// �����Ϸ������
		layers = new ArrayList<Layer>(layerCfgs.size());
		try {
			// �������в����
			for (LayerConfig layerCfg : layerCfgs) {
				// ��������
				@SuppressWarnings("unchecked")
				Class<Layer> clazz = (Class<Layer>) Class.forName(layerCfg.getClazz());
				// ��ù��캯��
				Constructor<Layer> ctr = clazz.getConstructor(int.class, int.class,
				    int.class, int.class);
				// ���ù��캯����������
				Layer layer = ctr.newInstance(layerCfg.getX(), layerCfg.getY(),
				    layerCfg.getW(), layerCfg.getH());
				// ������Ϸ���ݶ���
				layer.setGameDto(gameDto);
				// �Ѵ�����layer������뼯��
				layers.add(layer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		// ���ø��෽��
		super.paintComponent(g);
		// ������Ϸ����
		for (int i = 0; i < layers.size(); layers.get(i++).paint(g))
			;
		requestFocus();
	}

	public void setBtnEnabled(boolean enabled) {
		jbtnStart.setEnabled(enabled);
	}

}
