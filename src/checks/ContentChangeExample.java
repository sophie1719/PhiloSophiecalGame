package checks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContentChangeExample {
    private static JPanel cardPanel;
    private static CardLayout cardLayout;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Content Change Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // First content
        JPanel firstContent = new JPanel();
        JButton switchButton = new JButton("Switch Content");
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the second content when the button is clicked
                cardLayout.show(cardPanel, "SECOND");
            }
        });
        firstContent.add(new JLabel("This is the first content."));
        firstContent.add(switchButton);

        // Second content
        JPanel secondContent = new JPanel();
        JButton switchBackButton = new JButton("Switch Back");
        switchBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch back to the first content when the button is clicked
                cardLayout.show(cardPanel, "FIRST");
            }
        });
        secondContent.add(new JLabel("This is the second content."));
        secondContent.add(switchBackButton);

        // Add contents to the cardPanel with corresponding names
        cardPanel.add(firstContent, "FIRST");
        cardPanel.add(secondContent, "SECOND");

        // Show the initial content
        cardLayout.show(cardPanel, "FIRST");

        frame.getContentPane().add(cardPanel);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }
}

