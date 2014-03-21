package erhs53.mapping.gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import erhs53.mapping.Goal;
import erhs53.mapping.Map;
import erhs53.mapping.search.Action;
import erhs53.mapping.search.Path;

public class MappingGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	BufferedImage mapImage;
	BufferedImage screenImage;
	
	JPanel imagePanel;
	JPanel controlPanel;
	
	boolean closeRequested = false;
	
	HashMap<String, Goal> goals;
	HashMap<String, RoadPos> roads;
	
	Goal[] goalList;
	JButton[] labels = new JButton[10];
	
	ArrayList<ArrayList<RoadPos>> paths = new ArrayList<ArrayList<RoadPos>>();
	
	Color[] colors = new Color[] {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.pink};
	int colorIndex = 0;
	
	public MappingGUI() {
		super("Miniurban Mapping GUI");
		setSize(1300, 600);
		
		goals = new HashMap<>();
		goals.put("G1", Map.G1);
		goals.put("G2", Map.G2);
		goals.put("G3", Map.G3);
		goals.put("G4", Map.G4);
		goals.put("G5", Map.G5);
		goals.put("G6", Map.G6);
		goals.put("G7", Map.G7);
		goals.put("G8", Map.G8);
		goals.put("G9", Map.G9);
		
		roads = new HashMap<>();
		roads.put("AH0", RoadPos.AH0);
		roads.put("AH1", RoadPos.AH1);
		roads.put("AH2", RoadPos.AH2);
		roads.put("AH3", RoadPos.AH3);
		roads.put("AH4", RoadPos.AH4);
		roads.put("AH5", RoadPos.AH5);
		roads.put("AH7", RoadPos.AH7);
		roads.put("AH8", RoadPos.AH8);
		roads.put("AV0", RoadPos.AV0);
		roads.put("AV1", RoadPos.AV1);
		roads.put("AV2", RoadPos.AV2);
		roads.put("AV3", RoadPos.AV3);
		roads.put("AV4", RoadPos.AV4);
		roads.put("AV5", RoadPos.AV5);
		roads.put("AV6", RoadPos.AV6);
		roads.put("AV7", RoadPos.AV7);
		roads.put("AV8", RoadPos.AV8);
		roads.put("AC0", RoadPos.AC0);
		roads.put("AC1", RoadPos.AC1);
	    roads.put("AC2", RoadPos.AC2);
	    roads.put("AC3B", RoadPos.AC3B);
	    
		roads.put("BH0", RoadPos.BH0);
		roads.put("BH1", RoadPos.BH1);
		roads.put("BH2", RoadPos.BH2);
		roads.put("BH3", RoadPos.BH3);
		roads.put("BH4", RoadPos.BH4);
		roads.put("BH5", RoadPos.BH5);
		roads.put("BH6", RoadPos.BH6);
		roads.put("BH7", RoadPos.BH7);
		roads.put("BH8", RoadPos.BH8);
		roads.put("BH9", RoadPos.BH9);
		roads.put("BH10", RoadPos.BH10);
		roads.put("BH11", RoadPos.BH11);
		roads.put("BH12", RoadPos.BH12);
		roads.put("BV0", RoadPos.BV0);
		roads.put("BV1", RoadPos.BV1);
		roads.put("BV2", RoadPos.BV2);
		roads.put("BV3", RoadPos.BV3);
		roads.put("BV4", RoadPos.BV4);
		roads.put("BV5", RoadPos.BV5);
		roads.put("BV6", RoadPos.BV6);
		roads.put("BV7", RoadPos.BV7);
		roads.put("BV8", RoadPos.BV8);
		roads.put("BV9", RoadPos.BV9);
		roads.put("BV10", RoadPos.BV10);
		roads.put("BV11", RoadPos.BV11);
		roads.put("BV12", RoadPos.BV12);
		roads.put("BC0", RoadPos.BC0);
		roads.put("BC1", RoadPos.BC1);
		
		roads.put("CH0", RoadPos.CH0);
		roads.put("CH1", RoadPos.CH1);
		roads.put("CH2", RoadPos.CH2);
		roads.put("CH3", RoadPos.CH3);
		roads.put("CH4", RoadPos.CH4);
		roads.put("CH6", RoadPos.CH6);
		roads.put("CH7", RoadPos.CH7);
		roads.put("CH8", RoadPos.CH8);
		roads.put("CH9", RoadPos.CH9);
		roads.put("CH11", RoadPos.CH11);
		roads.put("CH12", RoadPos.CH12);
		roads.put("CV0", RoadPos.CV0);
		roads.put("CV1", RoadPos.CV1);
		roads.put("CV2", RoadPos.CV2);
		roads.put("CV3", RoadPos.CV3);
		roads.put("CV4", RoadPos.CV4);
		roads.put("CV5", RoadPos.CV5);
		roads.put("CV7", RoadPos.CV7);
		roads.put("CV8", RoadPos.CV8);
		roads.put("CV9", RoadPos.CV9);
		roads.put("CV10", RoadPos.CV10);
		
		
		try {
			mapImage = ImageIO.read(new File("res/map.jpg"));
			screenImage = new BufferedImage(mapImage.getWidth(), mapImage.getHeight(), mapImage.getType());
			screenImage.getGraphics().drawImage(mapImage, 0, 0, null);
			((Graphics2D) screenImage.getGraphics()).setStroke(new BasicStroke(10));
			
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
				int i = 0;
				while(!closeRequested) {
					imagePanel.repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("Recieved close request from window");
				System.exit(0);
			}
		});
		updater.start();
		
		createGUI();
		
		/*this.addWindowListener(new WindowListener() {

			public void windowClosed(WindowEvent arg0) {
				closeRequested = true;
			}
			public void windowActivated(WindowEvent arg0) {}
			public void windowClosing(WindowEvent arg0) {}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowOpened(WindowEvent arg0) {}
		});*/
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private class LabelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			sketchPath(Integer.parseInt(e.getActionCommand()));
		}
	}
	
	public void resetLabels() {
		LabelListener ll = new LabelListener();
		for(int i=0; i<labels.length; i++) {
			labels[i] = new JButton();
			labels[i].setVisible(false);
			labels[i].setActionCommand(Integer.toString(i));
			labels[i].addActionListener(ll);
		}
	}
	
	public void createGUI() {
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout());
		for(int i=0; i<labels.length; i++) {
			labels[i] = new JButton();
			labels[i].setVisible(false);
			labels[i].setActionCommand(Integer.toString(i));
			LabelListener ll = new LabelListener();
			labels[i].addActionListener(ll);
			labelPanel.add(labels[i]);
		}
		
		controlPanel = new JPanel();
		final JTextField goalsField = new JTextField(20);
		JButton sketchButton = new JButton("Sketch Path");
		sketchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String goalString = goalsField.getText();
				goalList = new Goal[goalString.split(", ").length];
				for(int i=0; i<goalList.length; i++) {
					goalList[i] = goals.get(goalString.split(", ")[i]);
					String g = goalString.split(", ")[i];
					labels[i].setText(g);
					labels[i].setVisible(true);
					System.out.println(g);
					goals.get(g).set(1, Map.PL);
				}
				System.out.println("Calculating path");
//				sketchPath(Map.generatePath(goalList));
				loadPaths(Map.generatePath(goalList));
				/*for(int i=0; i<paths.size(); i++) {
					sketchPath(i);
				}*/
				sketchPath(-1);
			}
		});
		controlPanel.add(goalsField);
		controlPanel.add(sketchButton);
		
		this.setLayout(new BorderLayout());
		this.add(labelPanel, BorderLayout.NORTH);
		this.add(controlPanel, BorderLayout.SOUTH);
		this.add(imagePanel, BorderLayout.CENTER);
	}
	
	public void loadPaths(Path path) {
		if(path == null) return;
		for(Action a : path.actions) {
			System.out.println(a.state.name);
		}
		int i = 0;
		paths.add(new ArrayList<RoadPos>());
		for(Action a: path.actions) {
			System.out.print(a.state.name + ",");
			if(a.state.name.startsWith("G")) {
				i++;
				paths.add(new ArrayList<RoadPos>());
				continue;
			}
			else {
				paths.get(i).add(roads.get(a.state.name));
			}
		}
	}
	
	public void sketchPath(Path path) {
		System.out.println("Sketching path");
		if(path == null) return;
		colorIndex = 0; 
		screenImage.getGraphics().drawImage(mapImage, 0, 0, null);
		for(Action a : path.actions) {
			System.out.print(a.state.name + ",");
			if(a.state.name.startsWith("G") || a.state.name.equals("END")) {
				colorIndex++;
				screenImage.getGraphics().setColor(colors[colorIndex]);
				continue;
			}
			RoadPos rpos = roads.get(a.state.name);
			Graphics2D g = (Graphics2D) screenImage.getGraphics();
			g.setColor(colors[colorIndex]);
			g.setStroke(new BasicStroke(3));
			if(rpos == null) System.out.println("\nrpos is null");
			/*for(int i=1; i<rpos.points.length; i++) {
				g.drawLine(rpos.points[i-1].x, rpos.points[i-1].y, rpos.points[i].x, rpos.points[i-1].y);
			}*/
			g.drawLine(rpos.points[0].x, rpos.points[0].y, rpos.points[1].x, rpos.points[1].y);
		}
		System.out.println("Done");
	}
	
	public void sketchPath(int path) {
		System.out.println("Sketching paths[" + path + "]");
		colorIndex = 0; 
		screenImage.getGraphics().drawImage(mapImage, 0, 0, null);
		Graphics2D g = (Graphics2D) screenImage.getGraphics();
		g.setColor(colors[colorIndex]);
		g.setStroke(new BasicStroke(3));
		if(path == -1) {
			for(int i=0; i<paths.size(); i++) {
				for(int j=0; j<paths.get(i).size(); j++) {
					RoadPos rpos = paths.get(i).get(j);
					if(rpos == null) continue;
					System.out.println(rpos.points[0] + ", " + rpos.points[1]);
					g.drawLine(rpos.points[0].x, rpos.points[0].y, rpos.points[1].x, rpos.points[1].y);
				}
				colorIndex++;
			}
			return;
		}
		for(int i=0; i<paths.get(path).size(); i++) {
			RoadPos rpos = paths.get(path).get(i);
			if(rpos == null) continue;
			System.out.println(rpos.points[0] + ", " + rpos.points[1]);
			g.drawLine(rpos.points[0].x, rpos.points[0].y, rpos.points[1].x, rpos.points[1].y);
		}
	}
	
	public static void main(String[] args) {
		new MappingGUI();
	}

}
