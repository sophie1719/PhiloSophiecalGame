package gameGUI;

import javax.swing.*;

import attack.*;
import defence.*;
import hero.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RulesPanel extends BasePanel {

	private static final long serialVersionUID = 1L;

	public JPanel createRulesPanel(CardLayout cardLayout, JPanel cardPanel) {
		JPanel scrollablePanel = new JPanel();
		JPanel rulesPanel = new JPanel();
        rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.Y_AXIS));
        rulesPanel.setBackground(Color.lightGray);

        Font comicSansFont = new Font("Comic Sans MS", Font.PLAIN, 18);

        JLabel rulesContentLabel0 = createLabel("In this game you play for a character, who is all alone" 
        										+ " against the world and other people around.\n", comicSansFont, Color.blue);
        JLabel rulesContentLabel1 = createLabel("Along with the way to attack and the way to defend," 
        										+ " your Hero represents your worldview.", comicSansFont, Color.blue);
        JLabel rulesContentLabel2 = createLabel("The maximum numbers of players is 4, and if there's not enough human players, you can add machines.", comicSansFont, Color.blue);
        JLabel rulesContentLabel3 = createLabel("There are three types of weapons from which you can choose:", comicSansFont, Color.blue);
        JLabel rulesContentLabel4 = createLabel("And also three types of shields", comicSansFont, Color.blue);
        JLabel rulesContentLabel5 = createLabel("When the shield is active, you will see it upon your hero, like this:", comicSansFont, Color.blue);
        
        JLabel IconKid = new JLabel(scaleImage(new ImageIcon("images/HeroKid.png"), 0.3));
        JLabel IconAdult = new JLabel(scaleImage(new ImageIcon("images/HeroAdult.png"), 0.3));
        JLabel IconElder = new JLabel(scaleImage(new ImageIcon("images/HeroElder.png"), 0.3));
        JLabel IconShield = new JLabel(scaleImage(new ImageIcon("images/Shield.png"), 0.3));
        JLabel backLabel = createHoverLabel("Back", comicSansFont, Color.blue);

        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "MainMenu");
            }
        });

        rulesPanel.add(Box.createVerticalStrut(20));
        rulesPanel.add(rulesContentLabel0);
        rulesPanel.add(rulesContentLabel1);
        rulesPanel.add(Box.createVerticalStrut(20));
        
        rulesPanel.add(rulesContentLabel2);
        JPanel heroCardPanel = new JPanel(new GridLayout(1, 3));
        heroCardPanel.add(createExplanationTile(IconKid, new HeroKid()));
        heroCardPanel.add(createExplanationTile(IconAdult, new HeroAdult()));
        heroCardPanel.add(createExplanationTile(IconElder, new HeroElder()));
        rulesPanel.add(heroCardPanel);
        
        rulesPanel.add(Box.createVerticalStrut(20));
        rulesPanel.add(rulesContentLabel3);
        JPanel weaponCardPanel = new JPanel(new GridLayout(1, 3));
        weaponCardPanel.add(createExplanationTile(new WeaponAmbitions()));
        weaponCardPanel.add(createExplanationTile(new WeaponCuriosity()));
        weaponCardPanel.add(createExplanationTile(new WeaponRelations()));
        rulesPanel.add(weaponCardPanel);
        
        rulesPanel.add(Box.createVerticalStrut(20));
        rulesPanel.add(rulesContentLabel4);
        JPanel shieldCardPanel = new JPanel(new GridLayout(1, 3));
        shieldCardPanel.add(createExplanationTile(new ShieldEscapism()));
        shieldCardPanel.add(createExplanationTile(new ShieldAdaptability()));
        shieldCardPanel.add(createExplanationTile(new ShieldOvercoming()));
        rulesPanel.add(shieldCardPanel);
        
        rulesPanel.add(Box.createVerticalStrut(20));
        JPanel shieldImagePanel = new JPanel();
        shieldImagePanel.setLayout(new BoxLayout(shieldImagePanel, BoxLayout.X_AXIS));
        shieldImagePanel.setBackground(Color.lightGray);
        rulesContentLabel5.setAlignmentX(Component.RIGHT_ALIGNMENT);
        shieldImagePanel.add(rulesContentLabel5);
        IconShield.setAlignmentX(Component.LEFT_ALIGNMENT);
        shieldImagePanel.add(IconShield);
        rulesPanel.add(shieldImagePanel);
        
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.lightGray);
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.X_AXIS));
        footerPanel.add(Box.createVerticalGlue());
        footerPanel.add(backLabel);
        rulesPanel.add(Box.createVerticalGlue());
        rulesPanel.add(footerPanel, Component.BOTTOM_ALIGNMENT);
        
        JScrollPane scrollPane = new JScrollPane(rulesPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollablePanel.setLayout(new BorderLayout());
        scrollablePanel.add(scrollPane);
        
        return scrollablePanel;
	}
	
	private JPanel createExplanationTile(JLabel image, Hero hero) {
		JPanel explanationTile = new JPanel();
		explanationTile.setBackground(Color.lightGray);
		explanationTile.setLayout(new BoxLayout(explanationTile, BoxLayout.Y_AXIS));
		explanationTile.add(createLabel(hero.toString(), new Font("Comic Sans MS", Font.PLAIN, 18), Color.blue.darker()));
		image.setAlignmentX(Component.CENTER_ALIGNMENT);
		explanationTile.add(image);
		explanationTile.add(createLabel(hero.getFancyDescription(), new Font("Comic Sans MS", Font.PLAIN, 12), Color.blue.darker()));
		return explanationTile;
	}
	
	private JPanel createExplanationTile(Weapon weapon) {
		JPanel explanationTile = new JPanel();
		explanationTile.setBackground(Color.lightGray);
		explanationTile.setLayout(new BoxLayout(explanationTile, BoxLayout.Y_AXIS));
		explanationTile.add(createLabel(weapon.toString(), new Font("Comic Sans MS", Font.PLAIN, 18), Color.blue.darker()));
		explanationTile.add(createLabel(weapon.getFancyDescription(), new Font("Comic Sans MS", Font.PLAIN, 12), Color.blue.darker()));
		return explanationTile;
	}
	
	private JPanel createExplanationTile(Shield shield) {
		JPanel explanationTile = new JPanel();
		explanationTile.setBackground(Color.lightGray);
		explanationTile.setLayout(new BoxLayout(explanationTile, BoxLayout.Y_AXIS));
		explanationTile.add(createLabel(shield.toString(), new Font("Comic Sans MS", Font.PLAIN, 18), Color.blue.darker()));
		explanationTile.add(createLabel(shield.getFancyDescription(), new Font("Comic Sans MS", Font.PLAIN, 12), Color.blue.darker()));
		return explanationTile;
	}
}
