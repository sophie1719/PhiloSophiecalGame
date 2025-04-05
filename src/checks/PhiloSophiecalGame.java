package checks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PhiloSophiecalGame {

    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public PhiloSophiecalGame() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("PhiloSophiecal Game");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // Main Menu
        JPanel mainMenuPanel = createMainMenuPanel();
        cardPanel.add(mainMenuPanel, "MainMenu");

        // Rules
        JPanel rulesPanel = createRulesPanel();
        cardPanel.add(rulesPanel, "Rules");

        // Play
        JPanel playPanel = createPlayPanel();
        cardPanel.add(playPanel, "Play");

        frame.add(cardPanel);

        frame.setVisible(true);
    }

    private JPanel createMainMenuPanel() {
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel, BoxLayout.Y_AXIS));
        mainMenuPanel.setBackground(Color.lightGray);

        Font comicSansFont = new Font("Comic Sans MS", Font.PLAIN, 24);

        JLabel titleLabel = createLabel("this is PhiloSOPHIEcal game", comicSansFont, Color.blue);
        JLabel rulesLabel = createHoverLabel("rules", comicSansFont, Color.blue);
        JLabel playLabel = createHoverLabel("play", comicSansFont, Color.blue);
        JLabel quitLabel = createHoverLabel("quit", comicSansFont, Color.blue);
        JLabel authorLabel = createLabel("author: sophie sokolovskaya", comicSansFont, Color.blue);

        rulesLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rulesLabel.setForeground(Color.darkGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rulesLabel.setForeground(Color.blue);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "Rules");
            }
        });

        playLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                playLabel.setForeground(Color.darkGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                playLabel.setForeground(Color.blue);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "Play");
            }
        });

        quitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                quitLabel.setForeground(Color.darkGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                quitLabel.setForeground(Color.blue);
            }

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

    private JPanel createRulesPanel() {
        JPanel rulesPanel = new JPanel();
        rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.Y_AXIS));
        rulesPanel.setBackground(Color.lightGray);

        Font comicSansFont = new Font("Comic Sans MS", Font.PLAIN, 24);

        JLabel rulesContentLabel = createLabel("Rules content goes here.", comicSansFont, Color.blue);
        
        JLabel IconKid = new JLabel(new ImageIcon("images/HeroKid.png"));
        JLabel IconAdult = new JLabel(new ImageIcon("images/HeroAdult.png"));
        JLabel backLabel = createHoverLabel("Back", comicSansFont, Color.blue);

        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "MainMenu");
            }
        });

        rulesPanel.add(Box.createVerticalStrut(20));
        rulesPanel.add(rulesContentLabel);
        rulesPanel.add(Box.createVerticalStrut(20));
        rulesPanel.add(IconKid);
        rulesPanel.add(IconAdult);
        rulesPanel.add(Box.createVerticalGlue());
        rulesPanel.add(backLabel);

        return rulesPanel;
    }

    private JPanel createPlayPanel() {
        JPanel playPanel = new JPanel();
        playPanel.setLayout(new BoxLayout(playPanel, BoxLayout.Y_AXIS));
        playPanel.setBackground(Color.lightGray);

        Font comicSansFont = new Font("Comic Sans MS", Font.PLAIN, 18);

        JLabel playContentLabel = createLabel("Play content goes here.", comicSansFont, Color.blue);
        JLabel backLabel = createHoverLabel("Back", comicSansFont, Color.blue);

        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "MainMenu");
            }
        });

        playPanel.add(Box.createVerticalStrut(20));
        playPanel.add(playContentLabel);
        playPanel.add(Box.createVerticalGlue());
        playPanel.add(backLabel);

        return playPanel;
    }

    private JLabel createLabel(String text, Font font, Color foreground) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(foreground);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        return label;
    }

    private JLabel createHoverLabel(String text, Font font, Color foreground) {
        JLabel label = createLabel(text, font, foreground);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(Color.darkGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(foreground);
            }
        });

        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PhiloSophiecalGame::new);
    }
}
