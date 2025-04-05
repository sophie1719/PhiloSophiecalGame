package gameGUI;


import javax.swing.*;
import javax.swing.border.Border;

import attack.*;
import defence.*;
import hero.*;
import logHandler.OperationLog;
import player.Player;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PlayersSetupPanel extends BasePanel {
	
	private static final long serialVersionUID = 1L;
	
	Font comicSansFont = new Font("Comic Sans MS", Font.PLAIN, 18);
	
	private ArrayList<Player> players = new ArrayList<Player>();
	
	public JPanel createPlayersSetupPanel(CardLayout cardLayout, JPanel cardPanel, int numHumanPlayers, int numMachinePlayers, String logFileName) {
		JPanel playersSetupPanel = new JPanel();
        playersSetupPanel.setLayout(new BoxLayout(playersSetupPanel, BoxLayout.Y_AXIS));
        playersSetupPanel.setBackground(Color.lightGray);
        
        JLabel nicknamesError = createLabel("All the nicknames should be different", comicSansFont, Color.lightGray);
        JLabel nextLabel = createHoverLabel("Next", comicSansFont, Color.blue);
        JLabel backLabel = createHoverLabel("Back", comicSansFont, Color.blue);

        nextLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean nicknamesAreDifferent = collectPlayerInfo(numHumanPlayers + numMachinePlayers, playersSetupPanel);
            	if (nicknamesAreDifferent) {
					nicknamesError.setForeground(Color.lightGray);
					
					for (int i = 0; i < players.size(); i++) {
						OperationLog.setupLogs(players.get(i), i, logFileName);
					}
					
					PlayPanel playPanel = new PlayPanel();
			        cardPanel.add(playPanel.createPlayPanel(cardLayout, cardPanel, players, logFileName), "Play");
            		cardLayout.show(cardPanel, "Play");
            	} else {
					nicknamesError.setForeground(Color.red);
            	}
            }
        });
        
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "NumPlayers");
            }
        });
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.lightGray);
        centerPanel.setLayout(new GridLayout(2, 2));
        for (int i = 1; i <= numHumanPlayers; i++) {
            centerPanel.add(createHumanTile());
        }
        for (int i = 1; i <= numMachinePlayers; i++) {
            centerPanel.add(createMachineTile(numHumanPlayers + i));
        }
        playersSetupPanel.add(centerPanel);
        
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.lightGray);
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));
        footerPanel.add(Box.createVerticalGlue());
        footerPanel.add(nicknamesError);
        JPanel superFooterPanel = new JPanel();
        superFooterPanel.setBackground(Color.lightGray);
        superFooterPanel.setLayout(new BoxLayout(superFooterPanel, BoxLayout.X_AXIS));
        superFooterPanel.add(Box.createVerticalGlue());
        superFooterPanel.add(backLabel);
        superFooterPanel.add(nextLabel);
        footerPanel.add(superFooterPanel);
        playersSetupPanel.add(Box.createVerticalGlue());
        playersSetupPanel.add(footerPanel, Component.BOTTOM_ALIGNMENT);
        
        return playersSetupPanel;
	}
	
	private JPanel createHumanTile() {
		JPanel tile = new JPanel();
		tile.setLayout(new BoxLayout(tile, BoxLayout.Y_AXIS));
		tile.setBackground(Color.lightGray);
		tile.add(createLabel("Nickname: ", comicSansFont, Color.blue));
        JTextField nicknameField = new JTextField();
        nicknameField.setFont(comicSansFont);
		nicknameField.setColumns(15);
		nicknameField.setMaximumSize(nicknameField.getPreferredSize());
        tile.add(nicknameField);
		
        //tile.add(createLabel("Hero:", comicSansFont, Color.blue));
        JPanel heroChoose = new JPanel();
        heroChoose.setBackground(Color.lightGray);
		String[] heroTypes = {"Kid", "Adult", "Elder"};
		JLabel leftHero = createHoverLabel("<", comicSansFont, Color.blue);
		JLabel heroName = createLabel("Kid", comicSansFont, Color.blue);
		JLabel rightHero = createHoverLabel(">", comicSansFont, Color.blue);
		leftHero.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int i = Arrays.asList(heroTypes).indexOf(heroName.getText());
        		heroName.setText(heroTypes[(i+2)%3]);
        	}
        });
		rightHero.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int i = Arrays.asList(heroTypes).indexOf(heroName.getText());
        		heroName.setText(heroTypes[(i+1)%3]);
        	}
        });
		heroChoose.add(leftHero);
		heroChoose.add(heroName);
		heroChoose.add(rightHero);
		tile.add(heroChoose);
		
		//tile.add(createLabel("Weapon:", comicSansFont, Color.blue));
        JPanel weaponChoose = new JPanel();
        weaponChoose.setBackground(Color.lightGray);
		String[] weaponTypes = {"Ambitions", "Curiosity", "Relations"};
		JLabel leftWeapon = createHoverLabel("<", comicSansFont, Color.blue);
		JLabel weaponName = createLabel("Ambitions", comicSansFont, Color.blue);
		JLabel rightWeapon = createHoverLabel(">", comicSansFont, Color.blue);
		leftWeapon.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int i = Arrays.asList(weaponTypes).indexOf(weaponName.getText());
        		weaponName.setText(weaponTypes[(i+2)%3]);
        	}
        });
		rightWeapon.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int i = Arrays.asList(weaponTypes).indexOf(weaponName.getText());
        		weaponName.setText(weaponTypes[(i+1)%3]);
        	}
        });
		weaponChoose.add(leftWeapon);
		weaponChoose.add(weaponName);
		weaponChoose.add(rightWeapon);
		tile.add(weaponChoose);
		
		//tile.add(createLabel("Shield:", comicSansFont, Color.blue));
        JPanel shieldChoose = new JPanel();
        shieldChoose.setBackground(Color.lightGray);
		String[] shieldTypes = {"Escapism", "Adaptability", "Overcoming"};
		JLabel leftShield = createHoverLabel("<", comicSansFont, Color.blue);
		JLabel shieldName = createLabel("Escapism", comicSansFont, Color.blue);
		JLabel rightShield = createHoverLabel(">", comicSansFont, Color.blue);
		leftShield.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int i = Arrays.asList(shieldTypes).indexOf(shieldName.getText());
        		shieldName.setText(shieldTypes[(i+2)%3]);
        	}
        });
		rightShield.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int i = Arrays.asList(shieldTypes).indexOf(shieldName.getText());
        		shieldName.setText(shieldTypes[(i+1)%3]);
        	}
        });
		shieldChoose.add(leftShield);
		shieldChoose.add(shieldName);
		shieldChoose.add(rightShield);
		tile.add(shieldChoose);
		
		Border border = BorderFactory.createLineBorder(new Color(139, 69, 19), 3);
        tile.setBorder(border);
		
		return tile;
	}
	
	private JPanel createMachineTile(int id) {
		Color blue = new Color(51, 153, 255);
		JPanel tile = new JPanel();
		tile.setLayout(new BoxLayout(tile, BoxLayout.Y_AXIS));
		tile.setBackground(Color.gray);
		
		Player machine = Player.generateMachine(id, "trash.txt");
		
		tile.add(createLabel("Nickname: ", comicSansFont, blue));
        JTextField nicknameField = new JTextField();
        nicknameField.setFont(comicSansFont);
        nicknameField.setColumns(15);
		nicknameField.setMaximumSize(nicknameField.getPreferredSize());
        nicknameField.setText(machine.getNickname());
        nicknameField.setEditable(false);
        tile.add(nicknameField);
		
        //tile.add(createLabel("Hero:", comicSansFont, blue));
        JPanel heroChoose = new JPanel();
        heroChoose.setBackground(Color.gray);
		JLabel leftHero = createLabel("<", comicSansFont, blue); // not a HoverLabel, bc it is a machine
		JLabel heroName = createLabel(machine.getHero().toString(), comicSansFont, blue);
		JLabel rightHero = createLabel(">", comicSansFont, blue);
		heroChoose.add(leftHero);
		heroChoose.add(heroName);
		heroChoose.add(rightHero);
		tile.add(heroChoose);
		
		//tile.add(createLabel("Weapon:", comicSansFont, blue));
        JPanel weaponChoose = new JPanel();
        weaponChoose.setBackground(Color.gray);
		JLabel leftWeapon = createLabel("<", comicSansFont, blue);
		JLabel weaponName = createLabel(machine.getWeapon().getName(), comicSansFont, blue);
		JLabel rightWeapon = createLabel(">", comicSansFont, blue);
		weaponChoose.add(leftWeapon);
		weaponChoose.add(weaponName);
		weaponChoose.add(rightWeapon);
		tile.add(weaponChoose);
		
		//tile.add(createLabel("Shield:", comicSansFont, blue));
        JPanel shieldChoose = new JPanel();
        shieldChoose.setBackground(Color.gray);
		JLabel leftShield = createLabel("<", comicSansFont, blue);
		JLabel shieldName = createLabel(machine.getShield().getName(), comicSansFont, blue);
		JLabel rightShield = createLabel(">", comicSansFont, blue);
		shieldChoose.add(leftShield);
		shieldChoose.add(shieldName);
		shieldChoose.add(rightShield);
		tile.add(shieldChoose);
		
		Border border = BorderFactory.createLineBorder(new Color(139, 69, 19), 3);
        tile.setBorder(border);
		
		return tile;
	}

    private boolean collectPlayerInfo(int numPlayers, JPanel container) {
        players.clear();
        ArrayList<String> nicknames = new ArrayList<String>();
        for (int i = 1; i <= numPlayers; i++) {
        	JPanel centerPanel = (JPanel) container.getComponent(0);
        	JPanel playerPanel = (JPanel) centerPanel.getComponent(i-1);
        	

            String nickname = ((JTextField) playerPanel.getComponent(1)).getText();
			nicknames.add(nickname);
            
            String heroText = ((JLabel)((JPanel) playerPanel.getComponent(2)).getComponent(1)).getText();
            int heroOption = 0;
            if (heroText.equals("Kid")) {
            	heroOption = 1;
            } else if (heroText.equals("Adult")) {
            	heroOption = 2;
            } else if (heroText.equals("Elder")) {
            	heroOption = 3;
            }
            Hero hero = Hero.chooseHero(heroOption);
            
            String weaponText = ((JLabel)((JPanel) playerPanel.getComponent(3)).getComponent(1)).getText();
            int weaponOption = 0;
            if (weaponText.equals("Ambitions")) {
            	weaponOption = 1;
            } else if (weaponText.equals("Curiosity")) {
            	weaponOption = 2;
            } else if (weaponText.equals("Relations")) {
            	weaponOption = 3;
            }
            Weapon weapon = Weapon.chooseWeapon(weaponOption);
            
            String shieldText = ((JLabel)((JPanel) playerPanel.getComponent(4)).getComponent(1)).getText();
            int shieldOption = 0;
            if (shieldText.equals("Escapism")) {
            	shieldOption = 1;
            } else if (shieldText.equals("Adaptability")) {
            	shieldOption = 2;
            } else if (shieldText.equals("Overcoming")) {
            	shieldOption = 3;
            }
            Shield shield = Shield.chooseShield(shieldOption);
            
            Player player = new Player(nickname, hero, weapon, shield);
            player.setId(i+1);
            if (playerPanel.getBackground().equals(Color.gray)) {
	            player.setIsMachine(true);
            }
            players.add(player);
        }
        
        // checking that there's no duplicating nicknames
        Set<String> stringSet = new HashSet<>(nicknames);
        return stringSet.size() == nicknames.size();
    }
}