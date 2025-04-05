package checks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BottomPanelExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bottom Panel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create a main panel with BorderLayout
            JPanel mainPanel = new JPanel(new BorderLayout());

            // Create your content panels
            JPanel topPanel = new JPanel();
            JPanel bottomPanel = new JPanel(new BorderLayout()); // Use BorderLayout for centering

            // Add components to the content panels
            topPanel.add(new JLabel("This is the top panel."));

            JLabel backLabel = new JLabel("Back");
            backLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle back label click
                }
            });

            JLabel nextLabel = new JLabel("Next");
            nextLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle next label click
                }
            });

            JPanel centeringPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Centering panel
            centeringPanel.add(backLabel);
            centeringPanel.add(nextLabel);

            bottomPanel.add(centeringPanel, BorderLayout.CENTER);

            // Add content panels to the main panel with constraints
            mainPanel.add(topPanel, BorderLayout.CENTER);
            mainPanel.add(bottomPanel, BorderLayout.SOUTH);

            // Add the main panel to the frame
            frame.getContentPane().add(mainPanel);

            // Set frame properties
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);
        });
    }
}

