package checks;

import javax.swing.*;
import java.awt.*;

public class BottomOfPageExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bottom of Page Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create a main panel with BorderLayout
            JPanel mainPanel = new JPanel(new BorderLayout());

            // Create your content panel
            JPanel contentPanel = new JPanel();
            contentPanel.add(new JLabel("This is the main content."));

            // Add content panel to the main panel with constraints
            mainPanel.add(contentPanel, BorderLayout.CENTER);

            // Create a bottom panel to be placed at the bottom
            JPanel bottomPanel = new JPanel();
            bottomPanel.add(new JLabel("This is at the bottom."));

            // Add the bottom panel to the main panel at the SOUTH position
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

