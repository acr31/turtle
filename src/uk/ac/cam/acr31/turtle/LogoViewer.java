package uk.ac.cam.acr31.turtle;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class LogoViewer {

	public static final int SIZE = 500;
	
	public static final int ZOOM = 2;
	
	private JFrame window;
	private BufferedImage buf;
	
	public LogoViewer() {
		buf = new BufferedImage(SIZE*ZOOM,SIZE*ZOOM, BufferedImage.TYPE_INT_ARGB);
		window = new JFrame() {
			private static final long serialVersionUID = 1L;
			@Override
			public void paint(Graphics g) {
				g.drawImage(buf,0,0,null);
			}
		};
		window.setSize(SIZE*ZOOM,SIZE*ZOOM);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);		
	}
	
	public void drawLine(int x1, int y1, int x2, int y2, Color c) {
		Graphics2D g = buf.createGraphics();
		g.setColor(c);
		g.setStroke(new BasicStroke(1.0f * ZOOM, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
		g.drawLine(x1*ZOOM, y1*ZOOM,x2*ZOOM,y2*ZOOM);
		g.dispose();
		window.repaint();
	}
}
