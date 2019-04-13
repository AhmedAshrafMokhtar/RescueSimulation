package view;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class MidArea extends JPanel{
	private MouseListener listener;
	private MidWest midWest;
	private Grid midGrid;
	private MiddleEast middleEast;
	
	//getters
	
	public MouseListener getListener() {
		return listener;
	}

	
	public MidWest getMidWest() {
		return midWest;
	}

	
	public Grid getMidGrid() {
		return midGrid;
	}

	public MiddleEast getMiddleEast() {
		return middleEast;
	}

	public MidArea(MouseListener e) {
		super();
		listener = e;
		setSize(1425, 502);
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		//Mid West
		midWest = new MidWest();
		add(midWest);
		
		//Grid (Center)
		midGrid = new Grid(listener);
		add(midGrid);
		
		//Middle east
		middleEast = new MiddleEast();
		add(middleEast);
	}

	}