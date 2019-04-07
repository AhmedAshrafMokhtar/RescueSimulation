package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


//import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.Player;
//import sun.audio.AudioData;
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;
//import sun.audio.ContinuousAudioDataStream;

@SuppressWarnings("restriction")
public class MainMenu extends JFrame implements MouseListener{
	
	private ImagePanel panel;
	private About AboutPanel;
	private JLabel Title;
	private JLabel NewGame;
	private JLabel Help;
	private JLabel About;
	private JLabel Quit;
	private Clip intro;
	private int click = 0;
	
	@SuppressWarnings("resource")
	public MainMenu() {
		
		//Frame
		setSize(1280, 720);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon("icons/MainIcon.jpg");
		setIconImage(icon.getImage());
		
		
		//Main Panel
		panel = new ImagePanel("icons/Main Menu Background(empty).png");
		panel.setSize(1280, 720);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
	
		
		//Title
		Title = new JLabel("",JLabel.CENTER);
		ImageIcon tIcon = new ImageIcon("icons/Title.png");
		Title.setIcon(tIcon);
		Title.setAlignmentX(Component.CENTER_ALIGNMENT);
		Title.setForeground(Color.DARK_GRAY);
		Title.addMouseListener(this);
		
		//New Game Button
		NewGame = new JLabel("",JLabel.CENTER);
		ImageIcon nIcon = new ImageIcon("icons/new game.png");
		NewGame.setIcon(nIcon);
		NewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		NewGame.setForeground(Color.DARK_GRAY);
		NewGame.addMouseListener(this);
		
		//Help Button
		Help = new JLabel("",JLabel.CENTER);
		ImageIcon hIcon = new ImageIcon("icons/Help.png");
		Help.setIcon(hIcon);
		Help.setAlignmentX(Component.CENTER_ALIGNMENT);
		Help.setForeground(Color.DARK_GRAY);
		Help.addMouseListener(this);
		
		//About Button
		About = new JLabel("",JLabel.CENTER);
		ImageIcon aIcon = new ImageIcon("icons/About.png");
		About.setIcon(aIcon);
		About.setAlignmentX(Component.CENTER_ALIGNMENT);
		About.setForeground(Color.DARK_GRAY);
		About.addMouseListener(this);
		
		//About panel
		AboutPanel = new About();
		
		
		//Quit Button
		Quit = new JLabel("",JLabel.CENTER);
		ImageIcon qIcon = new ImageIcon("icons/Quit.png");
		Quit.setIcon(qIcon);
		Quit.setAlignmentX(Component.CENTER_ALIGNMENT);
		Quit.setForeground(Color.DARK_GRAY);
		Quit.addMouseListener(this);
		
		
		//Layout
		panel.add(Box.createRigidArea(new Dimension(0,25)));
		panel.add(Title);
		panel.add(Box.createRigidArea(new Dimension(0,100)));
		panel.add(NewGame);
		panel.add(Box.createRigidArea(new Dimension(0,50)));
		panel.add(Help);
		panel.add(Box.createRigidArea(new Dimension(0,50)));
		panel.add(About);
		panel.add(Box.createRigidArea(new Dimension(0,50)));
		panel.add(Quit);
		
		
		
		//visibility
		add(AboutPanel);
		add(panel);
		setVisible(true);
		this.setLocationRelativeTo(null);
		try {
			intro =PlaySound("sounds/intro.wav");
			FloatControl volume= (FloatControl) intro.getControl(FloatControl.Type.MASTER_GAIN); 
			volume.setValue(-30.0f);
			intro.start();
			intro.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {		
		JLabel temp = (JLabel) e.getSource();
		
		if(temp == Title) {
			click++;
			
			if(click == 5) {
				ImageIcon tIcon = new ImageIcon("icons/5.png");
				Title.setIcon(tIcon);
			}
			
			if(click == 10) {
				ImageIcon tIcon = new ImageIcon("icons/10.png");
				Title.setIcon(tIcon);
			}
			
			if(click == 15) {
				System.exit(0);
			}
		}
		
		else if(temp == NewGame) {
			intro.stop();
			Game game = new Game();
			this.dispose();
			
		}
		else if (temp == About) {
			panel.setVisible(false);
			AboutPanel.setVisible(true);
			
		}
		
		else if (temp == Help) {
			
		}
		
		else if (temp == Quit) {
			System.exit(0);
		}
		

		if(temp == Title) {
			try {
				PlaySound("sounds/Quack.wav").start();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(e.getSource() != Title) {
			try {
				PlaySound("sounds/Hero.wav").start();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel temp = (JLabel) e.getSource();
		
		if(temp == Title) {
			
		}
		
		else if(temp == NewGame) {
			ImageIcon nIcon = new ImageIcon("icons/new game1.png");
			NewGame.setIcon(nIcon);
		}
		else if (temp == About) {
			ImageIcon aIcon = new ImageIcon("icons/About1.png");
			About.setIcon(aIcon);
		}
		
		else if (temp == Help) {
			ImageIcon hIcon = new ImageIcon("icons/Help1.png");
			Help.setIcon(hIcon);
		}
		
		else if (temp == Quit) {
			ImageIcon qIcon = new ImageIcon("icons/Quit1.png");
			Quit.setIcon(qIcon);
		}
		
		if(temp != Title) {
			try {
				PlaySound("sounds/bottle.wav").start();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel temp = (JLabel) e.getSource();
		
		if(temp == NewGame) {
			ImageIcon nIcon = new ImageIcon("icons/new game.png");
			NewGame.setIcon(nIcon);
		}
		else if (temp == About) {
			ImageIcon aIcon = new ImageIcon("icons/About.png");
			About.setIcon(aIcon);
		}
		
		else if (temp == Help) {
			ImageIcon hIcon = new ImageIcon("icons/Help.png");
			Help.setIcon(hIcon);
		}
		
		else if (temp == Quit) {
			ImageIcon qIcon = new ImageIcon("icons/Quit.png");
			Quit.setIcon(qIcon);
		}
	
	
	}
	
	public Clip PlaySound(String dir) throws UnsupportedAudioFileException, IOException, LineUnavailableException {    
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(dir).getAbsoluteFile());
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		return clip;
	}
}

class ImagePanel extends JPanel {

	  private Image img;
	  
	  public ImagePanel(String img) {
	    this(new ImageIcon(img).getImage());
	  }

	  public ImagePanel(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	  }

	  public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }
	  
	  public void setImage(ImageIcon img) {
		    this.img = img.getImage();
		    repaint();
		}

}

