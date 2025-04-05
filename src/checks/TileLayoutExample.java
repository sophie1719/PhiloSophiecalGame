package checks;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class TileLayoutExample {
    private static JPanel cardPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Tile Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardPanel = new JPanel(new GridLayout(2, 2));

        // Create four custom panels representing tiles
        double imageScale = 0.5;
        JPanel tile1 = createTile("Tile 1", "Text for Tile 1", "images/HeroKid.png", imageScale);
        JPanel tile2 = createTile("Tile 2", "Text for Tile 2", "images/HeroAdult.png", imageScale);
        JPanel tile3 = createTile("Tile 3", "Text for Tile 3", "images/HeroElder.png", imageScale);
        JPanel tile4 = createTile("Tile 4", "Text for Tile 4", "images/Shield.png", imageScale);

        // Add custom panels to the cardPanel with corresponding names
        cardPanel.add(tile1, "TILE1");
        cardPanel.add(tile2, "TILE2");
        cardPanel.add(tile3, "TILE3");
        cardPanel.add(tile4, "TILE4");
        
        // Create a footer panel
        JPanel footerPanel = new JPanel();
        JLabel footerLabel = new JLabel("Footer Text");
        footerPanel.add(footerLabel);

        frame.add(cardPanel, BorderLayout.CENTER);
        frame.add(footerPanel, BorderLayout.SOUTH);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    private static JPanel createTile(String title, String description, String imagePath, double scale) {
        JPanel tilePanel = new JPanel(new BorderLayout());

        // Text at the top
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        tilePanel.add(titleLabel, BorderLayout.NORTH);

        // Image in the center
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        int newWidth = (int) (originalImage.getWidth(null) * scale);
        int newHeight = (int) (originalImage.getHeight(null) * scale);
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        tilePanel.add(imageLabel, BorderLayout.CENTER);
        
        // Text at the bottom
        JLabel descriptionLabel = new JLabel("<html><div style='text-align: center;'>" + description + "</div></html>", SwingConstants.CENTER);
        tilePanel.add(descriptionLabel, BorderLayout.SOUTH);
        
        // Add a 3px brown border to each tile
        Border border = BorderFactory.createLineBorder(new Color(139, 69, 19), 3);
        tilePanel.setBorder(border);
        
        // Paint the background of the second tile yellow
        if (title.equals("Tile 2")) {
            tilePanel.setBackground(Color.YELLOW);
        }

        return tilePanel;
    }
}
