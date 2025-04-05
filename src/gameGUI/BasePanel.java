package gameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BasePanel extends JPanel {
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;

	protected JLabel createLabel(String text, Font font, Color foreground) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(foreground);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        return label;
    }

    protected JLabel createHoverLabel(String text, Font font, Color foreground) {
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
    
    protected ImageIcon scaleImage(ImageIcon originalIcon, double scale) {
        Image originalImage = originalIcon.getImage();
        int newWidth = (int) (originalImage.getWidth(null) * scale);
        int newHeight = (int) (originalImage.getHeight(null) * scale);
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
