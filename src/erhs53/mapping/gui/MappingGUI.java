package erhs53.mapping.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import erhs53.mapping.Action;
import erhs53.mapping.Path;

public class MappingGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	BufferedImage mapImage;
	BufferedImage screenImage;
	
	JPanel imagePanel;
	JPanel controlPanel;
	boolean closeRequested = false;
	
	ArrayList<Thing> path = new ArrayList<Thing>();
	
	public MappingGUI() {
		super("Miniurban Mapping GUI");
		setSize(600, 600);
		
		try {
			mapImage = ImageIO.read(new File("res/map.jpg"));
			screenImage = new BufferedImage(mapImage.getWidth(), mapImage.getHeight(), mapImage.getType());
			screenImage.getGraphics().drawImage(mapImage, 0, 0, null);
		} catch (IOException e) {
			System.err.println("Fatal Error: Could not load map image");
			System.exit(1);
		}
		
		Thread updater = new Thread(new Runnable() {
			public void run() {
				imagePanel = new JPanel() {
					@Override
					public void paint(Graphics g) {
						g.drawImage(screenImage, 0, 0, null);
					}
				};
				while(!closeRequested) {
					imagePanel.repaint();
				}
				System.out.println("Recieved close request from window");
				System.exit(0);
			}
		});
		updater.start();
		
		createGUI();
		
		this.addWindowListener(new WindowListener() {

			public void windowClosed(WindowEvent arg0) {
				closeRequested = true;
			}
			public void windowActivated(WindowEvent arg0) {}
			public void windowClosing(WindowEvent arg0) {}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowOpened(WindowEvent arg0) {}
		});
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	
	public void createGUI() {
		controlPanel = new JPanel();
		
		this.setLayout(new BorderLayout());
		this.add(controlPanel, BorderLayout.SOUTH);
		this.add(imagePanel, BorderLayout.NORTH);
	}
	
	public void sketchPath(Path path) {
		Graphics g = screenImage.getGraphics();
		g.drawImage(mapImage, 0, 0, null);
		for(Action a : path.actions) {
			g.drawLine(a.road.startPos.x, a.road.startPos.y, a.road.endPos.x, a.road.endPos.y);
		}
	}
	
	public static void main(String[] args) {
		new MappingGUI();
	}
	
	private class Thing {
		
	}

}
