package checks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuickClassesCheck {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameFramee();
            }
        });
    }
}

class BasePanell extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void commonMethod() {
        // Your custom method implementation
    }
}

class MainMenuPanell extends BasePanell {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainMenuPanell(CardLayout cardLayout, JPanel cardPanel) {
        JButton rulesButton = new JButton("Rules");
        JButton playButton = new JButton("Play");

        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Rules");
                commonMethod();
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Play");
                commonMethod();
            }
        });

        add(rulesButton);
        add(playButton);
    }
}

class RulesPanell extends BasePanell {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RulesPanell(CardLayout cardLayout, JPanel cardPanel) {
        JButton backButton = new JButton("Back");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "MainMenu");
                commonMethod();
            }
        });

        add(backButton);
    }
}

class PlayPanell extends BasePanell {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlayPanell(CardLayout cardLayout, JPanel cardPanel) {
        JButton backButton = new JButton("Back");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "MainMenu");
                commonMethod();
            }
        });

        add(backButton);
    }
}

class GameFramee extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel cardPanel;
    private CardLayout cardLayout;

    public GameFramee() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        MainMenuPanell mainMenuPanell = new MainMenuPanell(cardLayout, cardPanel);
        RulesPanell rulesPanell = new RulesPanell(cardLayout, cardPanel);
        PlayPanell playPanell = new PlayPanell(cardLayout, cardPanel);

        cardPanel.add(mainMenuPanell, "MainMenu");
        cardPanel.add(rulesPanell, "Rules");
        cardPanel.add(playPanell, "Play");
        
        setLayout(new BorderLayout());
        add(cardPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
