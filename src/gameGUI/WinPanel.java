package gameGUI;

import javax.swing.*;

import logHandler.OperationLog;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class WinPanel extends BasePanel {
	private static final long serialVersionUID = 1L;
	
	public JPanel createWinPanel(CardLayout cardLayout, JPanel cardPanel, String winnerName, String logFileName) {
		OperationLog.finalLine(logFileName);
		JPanel winPanel = new JPanel();
		winPanel.setLayout(new BoxLayout(winPanel, BoxLayout.Y_AXIS));
		winPanel.setBackground(Color.lightGray);
		Font comicSansFont = new Font("Comic Sans MS", Font.PLAIN, 32);
		JLabel winMessage = createLabel(winnerName + " wins!", comicSansFont, Color.blue);
		JLabel congrats = createLabel("Congratulations!!!", comicSansFont, Color.blue);
		JLabel again = createHoverLabel("play again", comicSansFont, Color.blue);
		again.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	NumPlayersPanel numPlayersPanel = new NumPlayersPanel();
            	cardPanel.add(numPlayersPanel.createNumPlayersPanel(cardLayout, cardPanel, logFileName), "NumPlayers");
                cardLayout.show(cardPanel, "NumPlayers");
            }
        });
		JLabel quit = createHoverLabel("quit", comicSansFont, Color.blue);
		quit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
		winPanel.add(Box.createVerticalStrut(100));
		winPanel.add(winMessage);
		winPanel.add(Box.createVerticalStrut(100));
		winPanel.add(congrats);
		winPanel.add(Box.createVerticalGlue());
		winPanel.add(again);
		winPanel.add(quit);
		return winPanel;
	}
}
