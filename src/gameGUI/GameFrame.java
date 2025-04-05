package gameGUI;

import javax.swing.*;
import java.awt.*;

class GameFrame extends JFrame {
    
	private static final long serialVersionUID = 1L;
	private JPanel cardPanel;
    private CardLayout cardLayout;

    MainMenuPanel mainMenuPanel = new MainMenuPanel();
    RulesPanel rulesPanel = new RulesPanel();
    NumPlayersPanel numPlayersPanel = new NumPlayersPanel();
    PlayersSetupPanel playersSetupPanel = new PlayersSetupPanel();
    PlayPanel playPanel = new PlayPanel();
    
    public GameFrame() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(mainMenuPanel.createMainMenuPanel(cardLayout, cardPanel), "MainMenu");
        cardPanel.add(rulesPanel.createRulesPanel(cardLayout, cardPanel), "Rules");
        // the numPlayersPanel is create in createMainMenuPanel()
        // the playersSetupPanel is created in createNumPlayersPanel()
        // the playPanel is created in createPlayersSetupPanel()

        add(cardPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}