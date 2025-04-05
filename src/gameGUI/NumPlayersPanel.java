package gameGUI;

import javax.swing.*;

import logHandler.OperationLog;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NumPlayersPanel extends BasePanel {

	private static final long serialVersionUID = 1L;
	
	public int numHumanPlayers;
    public int numMachinePlayers;
	
	public JPanel createNumPlayersPanel(CardLayout cardLayout, JPanel cardPanel, String logFileName) {
		// initial values
		numHumanPlayers = 1;
		numMachinePlayers = 1;
		
		JPanel numPlayersPanel = new JPanel();
        numPlayersPanel.setLayout(new BoxLayout(numPlayersPanel, BoxLayout.Y_AXIS));
        numPlayersPanel.setBackground(Color.lightGray);

        Font comicSansFont = new Font("Comic Sans MS", Font.PLAIN, 18);
        
        JLabel numHumanChooseLabel = createLabel("Choose the number of human players (1-4):", comicSansFont, Color.blue);
        JLabel numHumanPlayersLabel = createLabel(Integer.toString(numHumanPlayers), comicSansFont, Color.blue);
        JPanel humanChoose = new JPanel();
        humanChoose.setLayout(new BoxLayout(humanChoose, BoxLayout.X_AXIS));
        humanChoose.setBackground(Color.lightGray);
        
        JLabel numMachineChooseLabel = createLabel("Choose the number of additional machine players:", comicSansFont, Color.blue);
        JLabel numMachinePlayersLabel = createLabel(Integer.toString(numMachinePlayers), comicSansFont, Color.blue);
        JPanel machineChoose = new JPanel();
        machineChoose.setLayout(new BoxLayout(machineChoose, BoxLayout.X_AXIS));
        machineChoose.setBackground(Color.lightGray);
        
        JLabel leftHuman = createHoverLabel("<", comicSansFont, Color.blue);
        JLabel rightHuman = createHoverLabel(">", comicSansFont, Color.blue);
        JLabel leftMachine = createHoverLabel("<", comicSansFont, Color.blue);
        JLabel rightMachine = createHoverLabel(">", comicSansFont, Color.blue);
        
        leftHuman.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (numHumanPlayers != 1) {
                	if (numHumanPlayers == 2 && numMachinePlayers == 0) {
                		numMachinePlayers = 1;
                	}
                	numHumanPlayers--;
                }
                numHumanPlayersLabel.setText(Integer.toString(numHumanPlayers));
                numMachinePlayersLabel.setText(Integer.toString(numMachinePlayers));
            }
        });
        
        rightHuman.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (numHumanPlayers != 4) {
	        		if (numHumanPlayers + numMachinePlayers == 4) {
	        			numMachinePlayers--;
	        		}
	        		numHumanPlayers++;
        		}
        		numHumanPlayersLabel.setText(Integer.toString(numHumanPlayers));
                numMachinePlayersLabel.setText(Integer.toString(numMachinePlayers));
        	}
        });
        
        leftMachine.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (numMachinePlayers != 0) {
        			if (!(numMachinePlayers == 1 && numHumanPlayers == 1)) {
        				numMachinePlayers--;
        			}
        		}
        		numHumanPlayersLabel.setText(Integer.toString(numHumanPlayers));
                numMachinePlayersLabel.setText(Integer.toString(numMachinePlayers));
        	}
        });
        
        rightMachine.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (numMachinePlayers != 3) {
        			if (numHumanPlayers + numMachinePlayers == 4) {
        				numHumanPlayers--;
        			}
        			numMachinePlayers ++;
        		}
        		numHumanPlayersLabel.setText(Integer.toString(numHumanPlayers));
                numMachinePlayersLabel.setText(Integer.toString(numMachinePlayers));
        	}
        });
        
        humanChoose.add(leftHuman);
        humanChoose.add(numHumanPlayersLabel);
        humanChoose.add(rightHuman);
        machineChoose.add(leftMachine);
        machineChoose.add(numMachinePlayersLabel);
        machineChoose.add(rightMachine);
        
        JLabel nextLabel = createHoverLabel("Next", comicSansFont, Color.blue);
        JLabel backLabel = createHoverLabel("Back", comicSansFont, Color.blue);

        nextLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	new OperationLog("Number of human players: " + numHumanPlayers + "\n", logFileName);
            	new OperationLog("Number of machine players: " + numMachinePlayers + "\n", logFileName);
            	PlayersSetupPanel playersSetupPanel = new PlayersSetupPanel();
            	cardPanel.add(playersSetupPanel.createPlayersSetupPanel(cardLayout, cardPanel, 
                		numHumanPlayers, numMachinePlayers, logFileName), "PlayersSetup");
                cardLayout.show(cardPanel, "PlayersSetup");
            }
        });
        
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "MainMenu");
            }
        });
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.lightGray);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(numHumanChooseLabel);
        centerPanel.add(humanChoose);
        centerPanel.add(numMachineChooseLabel);
        centerPanel.add(machineChoose);
        numPlayersPanel.add(centerPanel);
        
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.lightGray);
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.X_AXIS));
        footerPanel.add(Box.createVerticalGlue());
        footerPanel.add(backLabel);
        footerPanel.add(nextLabel);
        numPlayersPanel.add(Box.createVerticalGlue());
        numPlayersPanel.add(footerPanel, Component.BOTTOM_ALIGNMENT);
        
        return numPlayersPanel;
	}
	
	// getters

	public int getNumHumanPlayers() {
		return this.numHumanPlayers;
	}

	public int getNumMachinePlayers() {
		return this.numMachinePlayers;
	}
	
	// setters

	public void setNumHumanPlayers(int numHumanPlayers) {
		this.numHumanPlayers = numHumanPlayers;
	}

	public void setNumMachinePlayers(int numMachinePlayers) {
		this.numMachinePlayers = numMachinePlayers;
	}
}