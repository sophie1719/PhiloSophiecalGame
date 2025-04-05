package gameGUI;

import javax.swing.*;

import logHandler.OperationLog;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuPanel extends BasePanel {

	private static final long serialVersionUID = 1L;

	public JPanel createMainMenuPanel(CardLayout cardLayout, JPanel cardPanel) {
		JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel, BoxLayout.Y_AXIS));
        mainMenuPanel.setBackground(Color.lightGray);

        Font comicSansFont = new Font("Comic Sans MS", Font.PLAIN, 24);

        JLabel titleLabel = createLabel("This is the PhiloSOPHIEcal game", comicSansFont, Color.blue);
        JLabel rulesLabel = createHoverLabel("rules", comicSansFont, Color.blue);
        JLabel playLabel = createHoverLabel("play", comicSansFont, Color.blue);
        JLabel quitLabel = createHoverLabel("quit", comicSansFont, Color.blue);
        JLabel authorLabel = createLabel("author: sophie sokolovskaya", comicSansFont, Color.blue);

        rulesLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "Rules");
            }
        });

        playLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	String logFileName = OperationLog.logFileNameGenerator();
            	NumPlayersPanel numPlayersPanel = new NumPlayersPanel();
            	cardPanel.add(numPlayersPanel.createNumPlayersPanel(cardLayout, cardPanel, logFileName), "NumPlayers");
                cardLayout.show(cardPanel, "NumPlayers");
            }
        });

        quitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        mainMenuPanel.add(Box.createVerticalStrut(20));
        mainMenuPanel.add(titleLabel);
        mainMenuPanel.add(Box.createVerticalStrut(20));
        mainMenuPanel.add(rulesLabel);
        mainMenuPanel.add(Box.createVerticalStrut(10));
        mainMenuPanel.add(playLabel);
        mainMenuPanel.add(Box.createVerticalStrut(10));
        mainMenuPanel.add(quitLabel);
        mainMenuPanel.add(Box.createVerticalGlue()); // Add spacing between main content and footer
        mainMenuPanel.add(authorLabel);
        
        return mainMenuPanel;
	}
}
