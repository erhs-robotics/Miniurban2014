package erhs53.mapping.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MappingGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	BufferedImage mapImage;
	
	ArrayList<Thing> path = new ArrayList<Thing>();
	
	public MappingGUI() {
		super("Miniurban Mapping GUI");
		setSize(600, 600);
		
		try {
			mapImage = ImageIO.read(new File("res/map.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		createGUI();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void createGUI() {
		JPanel controlPanel;
		JPanel imagePanel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(mapImage, 0, 0, null);
				sketchPath(g);
			}
		};
	}
	
	public void sketchPath(Graphics g) {
		
	}
	
	public static void main(String[] args) {
		new MappingGUI();
	}
	
	private class Thing {
		
	}

}
