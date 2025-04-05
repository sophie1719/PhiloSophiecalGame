package gameGUI;

import javax.swing.*;
import javax.swing.border.Border;

import logHandler.OperationLog;
import player.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PlayPanel extends BasePanel {

	private static final long serialVersionUID = 1L;

    Font littleFont = new Font("Comic Sans MS", Font.PLAIN, 14);
    Font comicSansFont = new Font("Comic Sans MS", Font.PLAIN, 18);
    Font boldFont = new Font("Comic Sans MS", Font.BOLD, 22);
    
    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Player> playersAlive = new ArrayList<Player>();
    Player activePlayer;
    Player targetPlayer;
    
    CardLayout cardLayout;
	JPanel cardPanel;
	JPanel centerPanel;
	String logFileName;
	JLabel logMessageI;
	JLabel logMessageII;
	JLabel logMessageIII;
	// yes, I am using Roman numerals to index the log messages. it is fun and good-looking.
	JLabel turnMessage;
	
	public JPanel createPlayPanel(CardLayout cardLayout, JPanel cardPanel, ArrayList<Player> players, String logFileName) {
		this.players = players;
		activePlayer = players.get(0);
		for (int i = 0; i < players.size(); i++) {
			playersAlive.add(players.get(i));
		}
		
		this.cardLayout = cardLayout;
		this.cardPanel = cardPanel;
		this.logFileName = logFileName;
        
        this.logMessageI = createLabel("Nothing happened", littleFont, Color.blue);
		this.logMessageII = createLabel("Nothing happened", littleFont, Color.blue);
		this.logMessageIII = createLabel("Nothing happened", littleFont, Color.blue);
		this.turnMessage = createLabel(activePlayer.getNickname() + "'s turn", comicSansFont, Color.blue);
		
		JPanel playPanel = new JPanel();
        playPanel.setLayout(new BoxLayout(playPanel, BoxLayout.Y_AXIS));
        playPanel.setBackground(Color.lightGray);
        
        centerPanel = new JPanel();
		centerPanel.setBackground(Color.lightGray);
		centerPanel.setLayout(new GridLayout(2, 2));
		for (int i = 0; i < players.size(); i++) {
			centerPanel.add(createTile(i));
		}
        
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.lightGray);
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));
        footerPanel.add(Box.createVerticalGlue());
		footerPanel.add(logMessageI);
		footerPanel.add(logMessageII);
		footerPanel.add(logMessageIII);
		footerPanel.add(turnMessage);
        
        playPanel.add(centerPanel);
        playPanel.add(Box.createVerticalGlue());
        playPanel.add(footerPanel);
        
        return playPanel;
	}
	
	private JPanel createTile(int i) {
		JPanel tile = new JPanel();
		tile.setBackground(Color.lightGray);
		tile.setLayout(new BoxLayout(tile, BoxLayout.Y_AXIS));
		JLabel name = createLabel(players.get(i).getNickname(), boldFont, Color.blue);
		tile.add(name);
		
		JPanel character = new JPanel();
		character.setBackground(Color.lightGray);
		character.setLayout(new BoxLayout(character, BoxLayout.X_AXIS));
		
		JPanel characterInfo = new JPanel();
		characterInfo.setBackground(Color.lightGray);
		characterInfo.setLayout(new GridLayout(4, 2));
		JLabel hp 	  = createLabel("HP:     " +
									String.format("%.2f", players.get(i).getHP()),
									littleFont, Color.blue);
		hp.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel hero   = createLabel("Hero:   " +
									players.get(i).getHero().toString(),
									littleFont, Color.blue);
		hero.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel weapon = createLabel("Weapon: " +
									players.get(i).getWeapon().toString(),
									littleFont, Color.blue);
		weapon.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel shield = createLabel("Shield: " +
									players.get(i).getShield().toString(),
									littleFont, Color.blue);
		shield.setAlignmentX(Component.LEFT_ALIGNMENT);
		characterInfo.add(hp);
		characterInfo.add(hero);
		characterInfo.add(weapon);
		characterInfo.add(shield);
		character.add(characterInfo);
		
		String heroType = players.get(i).getHero().getName();
		ImageIcon icon = scaleImage(new ImageIcon("images/" + heroType + ".png"), 0.4);
		JLabel IconHero = new JLabel(icon);
		IconHero.setAlignmentX(Component.LEFT_ALIGNMENT);
		character.add(IconHero);
		
		tile.add(character);
		
		Color green = new Color(102, 225, 102);
		
		tile.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		if (!players.get(i).getIsDead() && !activePlayer.getIsMachine()) {
		    		if (activePlayer.equals(players.get(i))) {
						tileSetBackground(tile, green);
						setIconShieldSuggested(IconHero, i);
					} else {
						tileSetBackground(tile, Color.pink);
					}
        		}
        	}
			@Override
			public void mouseExited(MouseEvent e) {
				if (!players.get(i).getIsDead() && !activePlayer.getIsMachine()) {
					tileSetBackground(tile, Color.lightGray);
					if (players.get(i).getShield().getIsActive()) {
						setIconShielded(IconHero, i);
					} else {
						IconHero.setIcon(icon);
					}
				}
			}
			public void mouseClicked(MouseEvent e) {
				if (!players.get(i).getIsDead() && !activePlayer.getIsMachine()) {
					targetPlayer = players.get(i);
					if (turn()) {
						WinPanel winPanel = new WinPanel();
			        	cardPanel.add(winPanel.createWinPanel(cardLayout, cardPanel, activePlayer.getNickname(), logFileName), "Win");
			            cardLayout.show(cardPanel, "Win");
					}
					while (activePlayer.getIsMachine()) {
						// making a delay of 1 second to simulate the machine's thinking process
						// I know we should use Swing.timer with Swing instead of Thread.sleep,
						// and initially I did (as seen in the commented code below), but it didn't work.
						// I don't know why.
//						// simulate machine thinking
//						Timer timer = new Timer(1000, new ActionListener() {
//				            @Override
//				            public void actionPerformed(ActionEvent evt) {
//				            	System.out.println("Machine thinking...");
//				            	int option = activePlayer.chooseOptionRandomly(playersAlive.size());
//				            	targetPlayer = playersAlive.get(option);
//				            	turn();
//				            }
//				        });
//				        timer.setRepeats(false); // Set to false for a one-time delay
//				        timer.start();
						// Also, exactly in this case using Thread.sleep with Swing is okay,
						// since we don't need a responsive GUI for that 1 second when machine is "thinking".
				        int option = activePlayer.chooseOptionRandomly(playersAlive.size());
				        targetPlayer = playersAlive.get(option);
				        if (turn()) {
							WinPanel winPanel = new WinPanel();
				        	cardPanel.add(winPanel.createWinPanel(cardLayout, cardPanel, activePlayer.getNickname(), logFileName), "Win");
				            cardLayout.show(cardPanel, "Win");
						}
					}
				}
			}
        });
		
		Border border = BorderFactory.createLineBorder(new Color(139, 69, 19), 3);
        tile.setBorder(border);
        
		return tile;
	}
	
	ActionListener clickListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!activePlayer.getIsMachine()) {
				
			}
		}
	};
	
	private boolean turn() {
		boolean winnerExists = false;
		int activePlayerIndex = players.indexOf(activePlayer);
		int targetPlayerIndex = players.indexOf(targetPlayer);
		JPanel activePlayerTile = (JPanel) centerPanel.getComponent(activePlayerIndex);
		JPanel targetPlayerTile = (JPanel) centerPanel.getComponent(targetPlayerIndex);
		JLabel activePlayerIcon = getIconLabel(activePlayerIndex, centerPanel);
		JLabel targetPlayerHp = getHpLabel(targetPlayerIndex, centerPanel);
		
		// If and only if a mouse is clicked to a player that is alive, the turn was made.
		// This could only be made by an active player, so this player is placed to the end
		// of the playersAlive queue, so that the next active player can take the turn after that.
		playersAlive.remove(0);
		playersAlive.add(activePlayer);
		
		// supposed this.activePlayer and this.targetPlayer are set before this method is called
		if (activePlayer.equals(targetPlayer)) {
			// if the active player is chosen, the shield has to be activated
			activePlayer.getShield().setIsActive(true);
			tileSetBackground(activePlayerTile, Color.lightGray);
			setIconShielded(activePlayerIcon, activePlayerIndex);
			String logText = "Action: " + activePlayer.getNickname() + " raises the shield.\n";
			new OperationLog(logText, logFileName);
			showLogMessage(logText);
		} else {
			// is another player is chosen, strike is made
			targetPlayer.getStruck(activePlayer.strike());
			String logText = "Action: " + activePlayer.getNickname() + " hits " + targetPlayer.getNickname() + ".\n";
			new OperationLog(logText, logFileName);
			showLogMessage(logText);
			if (targetPlayer.getHP() <= 0) {
				// if the target player dies
				targetPlayer.die(logFileName);
				playersAlive.remove(targetPlayer);
				tileSetBackground(targetPlayerTile, Color.gray);
				targetPlayerHp.setText("HP:     0.00");
				logMessageI.setText(logMessageI.getText() + " " + targetPlayer.getNickname() + " dies.");
			} else {
				// if the target player survives the strike
				tileSetBackground(targetPlayerTile, Color.lightGray);
				OperationLog.hpLog(targetPlayer, logFileName);
				targetPlayerHp.setText("HP:     " + String.format("%.2f", targetPlayer.getHP()));
			}
		}
		
		// set the next active player. it is the first player in the playersAlive queue
		activePlayer = playersAlive.get(0);
		activePlayerIndex = players.indexOf(activePlayer);
		turnMessage.setText(activePlayer.getNickname() + "'s turn.");
		// removing new active player's shield
		activePlayer.getShield().setIsActive(false);
		activePlayerIcon = getIconLabel(activePlayerIndex, centerPanel);
		setIconEmpty(activePlayerIcon, activePlayerIndex);
		
		// check if there is a winner
		if (playersAlive.size() == 1) {
			activePlayer.win(logFileName);
			winnerExists = true;
		}
		return winnerExists;
	}
		
	private void tileSetBackground(JPanel tile, Color color) {
		JPanel character = (JPanel) tile.getComponent(1);
		JPanel characterInfo = (JPanel) character.getComponent(0);
		tile.setBackground(color);
		character.setBackground(color);
		characterInfo.setBackground(color);
	}
	
	private JLabel getHpLabel(int playerIndex, JPanel centerPanel) {
		JPanel tile = (JPanel) centerPanel.getComponent(playerIndex);
		JPanel character = (JPanel) tile.getComponent(1);
		JPanel characterInfo = (JPanel) character.getComponent(0);
		JLabel hpLabel = (JLabel) characterInfo.getComponent(0);
		return hpLabel;
	}
	
	private JLabel getIconLabel(int playerIndex, JPanel centerPanel) {
		JPanel tile = (JPanel) centerPanel.getComponent(playerIndex);
		JPanel character = (JPanel) tile.getComponent(1);
		JLabel iconLabel = (JLabel) character.getComponent(1);
		return iconLabel;
	}
	
	private void setIconEmpty(JLabel iconLabel, int playerIndex) {
		ImageIcon icon = scaleImage(new ImageIcon("images/"
												  + players.get(playerIndex).getHero().getName()
												  + ".png"), 0.4);
		iconLabel.setIcon(icon);
	}
	
	private void setIconShielded(JLabel iconLabel, int playerIndex) {
		ImageIcon icon = scaleImage(new ImageIcon("images/"
												  + players.get(playerIndex).getHero().getName()
												  + "Shielded.png"), 0.4);
		iconLabel.setIcon(icon);
	}
	
	private void setIconShieldSuggested(JLabel iconLabel, int playerIndex) {
		ImageIcon icon = scaleImage(new ImageIcon("images/"
									+ players.get(playerIndex).getHero().getName()
									+ "ShieldSuggested.png"), 0.4);
		iconLabel.setIcon(icon);
	}
	
	private void showLogMessage(String message) {
		// comment what this function does
		// this function shows the log message in the upper log label,
		// moving the previous log messages down
		logMessageIII.setText(logMessageII.getText());
		logMessageII.setText(logMessageI.getText());
		logMessageI.setText(message);
	}
}
