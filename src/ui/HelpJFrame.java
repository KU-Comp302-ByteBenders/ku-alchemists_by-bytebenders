package ui;

import java.awt.*;
import javax.swing.*;

public class HelpJFrame extends JFrame {

  public HelpJFrame() {
    setTitle("HELP");
    setSize(800, 800);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setResizable(false);

    JPanel mainPanel = new JPanel(null);

    // Add an image to the panel
    ImageIcon boardIcon = new ImageIcon("src/ui/utils/menu.png");
    JLabel boardLabel = new JLabel(boardIcon);
    boardLabel.setBounds(650, 0, 90, 90);
    mainPanel.add(boardLabel);

    ImageIcon avatarIcon = new ImageIcon("src/ui/utils/avatar_2.png");
    JLabel avatarLabel = new JLabel(avatarIcon);
    avatarLabel.setBounds(650, 80, 90, 90);
    mainPanel.add(avatarLabel);

    ImageIcon artifactCardIcon = new ImageIcon("src/ui/utils/artifact card image.png");
    JLabel ArtifactCardLabel = new JLabel(artifactCardIcon);
    ArtifactCardLabel.setBounds(650, 180, 90, 90);
    mainPanel.add(ArtifactCardLabel);

    ImageIcon alchemyMarkerIcon = new ImageIcon("src/ui/utils/redstone.jpg");
    JLabel alchemyMarkerLabel = new JLabel(alchemyMarkerIcon);
    alchemyMarkerLabel.setBounds(650, 280, 90, 90);
    mainPanel.add(alchemyMarkerLabel);

    ImageIcon ingredientIcon = new ImageIcon("src/ui/utils/ingredient image.png");
    JLabel ingredientLabel = new JLabel(ingredientIcon);
    ingredientLabel.setBounds(650, 380, 90, 90);
    mainPanel.add(ingredientLabel);

    ImageIcon ingredientStorageIcon = new ImageIcon("src/ui/utils/potion-brewing.png");
    JLabel ingredientStorageLabel = new JLabel(ingredientStorageIcon);
    ingredientStorageLabel.setBounds(650, 480, 90, 90);
    mainPanel.add(ingredientStorageLabel);

    ImageIcon potionBrewingIcon = new ImageIcon("src/ui/utils/potion-brewing.png");
    JLabel potionBrewingLabel = new JLabel(potionBrewingIcon);
    potionBrewingLabel.setBounds(650, 580, 90, 90);
    mainPanel.add(potionBrewingLabel);

    ImageIcon deductionBoardIcon = new ImageIcon("src/ui/utils/pubboard.png");
    JLabel deductionBoardLabel = new JLabel(deductionBoardIcon);
    deductionBoardLabel.setBounds(650, 680, 90, 90);
    mainPanel.add(deductionBoardLabel);

    // Board
    addText(mainPanel, "Board", 20, 20);
    addText(mainPanel, "● Represents the laboratory setting for experiments with static locations.", 20, 40);

    // Player Tokens
    addText(mainPanel, "Player Tokens", 20, 80);
    addText(mainPanel, "● Unique avatars for each player.", 20, 100);
    addText(mainPanel, "● Track player position, manage resources (ingredients, potions), and record scores.", 20, 120);

    // Artifact Cards
    addText(mainPanel, "Artifact Cards", 20, 170);
    addText(mainPanel, "● Special cards with unique abilities lasting the entire game:", 20, 190);

    // Alchemy Markers
    addText(mainPanel, "Alchemy Markers", 20, 240);
    addText(mainPanel, "● Track player hypotheses about ingredient properties.", 20, 260);
    addText(mainPanel, "● Placed on the Deduction Board to form and test theories.", 20, 280);

    // Ingredient
    addText(mainPanel, "Ingredients", 20, 330);
    addText(mainPanel, "● Fundamental components of making potions", 20, 350);
    addText(mainPanel, "● Ingredients are combined to form potions.", 20, 370);

    // Ingredient Storage
    addText(mainPanel, "Ingredient Storage", 20, 420);
    addText(mainPanel, "● Allows players to collect ingredients for potion-making.", 20, 440);

    // Potion Brewing Area
    addText(mainPanel, "Potion Brewing Area", 20, 490);
    addText(mainPanel, "● Allows players to combine ingredients and brew potions following game rules.", 20, 510);

    // Publication Area
    addText(mainPanel, "Publication Area", 20, 560);
    addText(mainPanel, "● Displays available Publication Cards and their requirements.", 20, 580);
    addText(mainPanel, "● Facilitates submitting theories and claiming points.", 20, 600);

    // Deduction Board
    addText(mainPanel, "Deduction Board", 20, 650);
    addText(mainPanel, "● Showcases where players place alchemy markers to create and test theories.", 20, 670);
    addText(mainPanel, "● Provides a user-friendly interface for making guesses and confirming theories.", 20, 690);

    // Add the panel to the content pane
    getContentPane().add(mainPanel);

    setLocationRelativeTo(null); // Center the frame on the screen

    // Make the JFrame visible
    setVisible(true);
  }

  private void addText(Container container, String text, int x, int y) {
    JLabel label = new JLabel(text);
    label.setFont(new Font("Arial", Font.PLAIN, 14));
    label.setBounds(x, y, 800, 20);
    container.add(label);
  }
}
