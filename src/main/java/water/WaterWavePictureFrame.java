package water;

import java.awt.*;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WaterWavePictureFrame extends JFrame {
	private static final long serialVersionUID = 4644169942716200520L;
	private WaterWavePicturePanel wavePanel = new WaterWavePicturePanel();
	private static final int WIDTH = 1920;
	private static final int HEIGTH = 1200;

	public WaterWavePictureFrame() {
		super();
		setTitle("水波效果的图片");
		setBounds(0, 0, WIDTH, HEIGTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(wavePanel);
	}

	class WaterWavePicturePanel extends JPanel {
		private static final long serialVersionUID = 8977214389304589839L;
		private Graphics graphics; // Graphics对象
		private Graphics waveGraphics; // 绘制水波的Graphics对象
		private Image oldImage; // 原图像对象
		private Image waveImage; // 声明表示水波效果的图像对象
		private int currentImage, imageWidth, imageHeight;
		private boolean isImageLoaded; // 表示图片是否被加载的标记

		public void paint(Graphics g) {
			drawWaterWave();
			if (waveImage != null) {
				g.drawImage(waveImage, -currentImage * imageWidth, 0, this); // 绘制图像
			}
			g.clearRect(imageWidth, 0, imageWidth * 4, imageHeight * 2);// 清除显示区域右侧的内容
		}

		public void drawWaterWave() {
			currentImage = 0;
			if (!isImageLoaded) { // 如果未加载图片
				try {
					graphics = getGraphics(); // 获得绘图上下文对象
					MediaTracker mediatracker = new MediaTracker(this); // 创建媒体跟踪对象
					URL imgUrl = WaterWavePictureFrame.class.getResource("/water/boatwater.jpg");// 获取图片资源的路径
					oldImage = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
					mediatracker.addImage(oldImage, 0); // 添加图片
					mediatracker.waitForAll(); // 加载图片
					isImageLoaded = !mediatracker.isErrorAny(); // 是否有错误发生
				} catch (InterruptedException ex) {
				}
				if (!isImageLoaded) { // 图片加载失败
					graphics.drawString("图片加载错误", 10, 40); // 绘制错误信息
					return;
				}
				imageWidth = oldImage.getWidth(this); // 得到图像宽度
				imageHeight = oldImage.getHeight(this); // 得到图像高度
				try {
					createWave();// 创建水波效果
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void createWave() throws InterruptedException {
			Image img = createImage(imageWidth, imageHeight); // 以图像宽度和高度创建图像对象
			Graphics g = null;
			if (img != null) {
				g = img.getGraphics(); // 得到Image对象的Graphics对象
				g.drawImage(oldImage, 0, 0, this); // 绘制原图像对象
				for (int i = 0; i < imageHeight; i++) {
					g.copyArea(0, imageHeight - 1 - i, imageWidth, 1, 0, -imageHeight + 1 + (i * 2)); // 拷贝图像区域
				}
			}
			waveImage = createImage(13 * imageWidth, imageHeight); // 得到水波效果的图像对象
			if (waveImage != null) {
				waveGraphics = waveImage.getGraphics(); // 得到水波效果的绘图上下文对象
				waveGraphics.drawImage(img, 12 * imageWidth, 0, this); // 绘制图像
				int j = 0;
				while (j < 12) {
					simulateWaves(waveGraphics, j);// 调用方法，模拟水波效果
					j++;
				}
			}
		}

		public void simulateWaves(Graphics g, int i) { // 水波效果模拟
			int j = (12 - i) * imageWidth;// 计算复制像素的水平距离
			int waveHeight = imageHeight / 16;// 计算水波高度
			for (int h = 0; h < imageHeight; h++) {
				int k = (int) ((waveHeight * (h + 28) * Math.sin(waveHeight * (imageHeight - h) / (h + 1)))
						/ imageHeight);// 计算复制像素的垂直距离
				if (h < -k) {
					g.copyArea(12 * imageWidth, h, imageWidth, 1, -j, 0); // 拷贝图像区域,形成水波
				} else {
					g.copyArea(12 * imageWidth, h + k, imageWidth, 1, -j, -k);// 拷贝图像区域,形成水波
				}
			}
		}
	}

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		WaterWavePictureFrame frame = new WaterWavePictureFrame();
		frame.setVisible(true);
	}
}