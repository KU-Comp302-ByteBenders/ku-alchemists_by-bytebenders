package ui;

import java.awt.*;
import javax.swing.*;

public class HelpJFrame extends JFrame {

    public HelpJFrame() {
        setTitle("HELP");
        setSize(1150, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(null);

        JLabel gameObjectsLabel = new JLabel("GAME OBJECTS");
        gameObjectsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        int gameObjectsLabelWidth = gameObjectsLabel.getPreferredSize().width;
        gameObjectsLabel.setBounds((1050 - gameObjectsLabelWidth) / 2, 0, gameObjectsLabelWidth, 90);
        mainPanel.add(gameObjectsLabel);

        // Add images to the panel
        ImageIcon avatarIcon = new ImageIcon("src/ui/utils/avatar_2.png");
        JLabel avatarLabel = new JLabel(avatarIcon);
        avatarLabel.setBounds(900, 100, 90, 90);
        mainPanel.add(avatarLabel);

        ImageIcon artifactCardIcon = new ImageIcon("src/ui/utils/artifact card image.png");
        JLabel artifactCardLabel = new JLabel(artifactCardIcon);
        artifactCardLabel.setBounds(900, 200, 90, 90);
        mainPanel.add(artifactCardLabel);

        ImageIcon alchemyMarkerIcon = new ImageIcon("src/ui/utils/markers/alchemy_marker2.png");
        JLabel alchemyMarkerLabel = new JLabel(alchemyMarkerIcon);
        alchemyMarkerLabel.setBounds(900, 300, 90, 90);
        mainPanel.add(alchemyMarkerLabel);

        ImageIcon ingredientIcon = new ImageIcon("src/ui/utils/ingredient_7.jpg");
        JLabel ingredientLabel = new JLabel(ingredientIcon);
        ingredientLabel.setBounds(900, 400, 90, 90);
        mainPanel.add(ingredientLabel);

        ImageIcon ingredientStorageIcon = new ImageIcon("src/ui/utils/ingredient image.png");
        JLabel ingredientStorageLabel = new JLabel(ingredientStorageIcon);
        ingredientStorageLabel.setBounds(900, 500, 90, 90);
        mainPanel.add(ingredientStorageLabel);

        ImageIcon potionBrewingIcon = new ImageIcon("src/ui/utils/potion-brewing.png");
        JLabel potionBrewingLabel = new JLabel(potionBrewingIcon);
        potionBrewingLabel.setBounds(900, 600, 90, 90);
        mainPanel.add(potionBrewingLabel);

        ImageIcon publicationBoardIcon = new ImageIcon("src/ui/utils/publication-brewing.png");
        JLabel publicationBoardLabel = new JLabel(publicationBoardIcon);
        publicationBoardLabel.setBounds(900, 700, 90, 90);
        mainPanel.add(publicationBoardLabel);

        ImageIcon deductionBoardIcon = new ImageIcon("src/ui/utils/pubboard.png");
        JLabel deductionBoardLabel = new JLabel(deductionBoardIcon);
        deductionBoardLabel.setBounds(900, 800, 90, 90);
        mainPanel.add(deductionBoardLabel);

        // Player Tokens
        addText(mainPanel, "Player Tokens", 20, 120);
        addText(mainPanel, "● Unique avatars for each player. Track player position, manage resources (ingredients, potions), and record scores.", 20, 140);

        // Artifact Cards
        addText(mainPanel, "Artifact Cards", 20, 220);
        addText(mainPanel, "● Special cards with unique abilities lasting the entire game.", 20, 240);

        // Alchemy Markers
        addText(mainPanel, "Alchemy Markers", 20, 320);
        addText(mainPanel, "● Track player hypotheses about ingredient properties. Placed on the Deduction Board to form and test theories.", 20, 340);

        // Ingredient
        addText(mainPanel, "Ingredients", 20, 420);
        addText(mainPanel, "● Fundamental components of making potions and combined to form potions", 20, 440);

        // Ingredient Storage
        addText(mainPanel, "Ingredient Storage", 20, 520);
        addText(mainPanel, "● Ingredient deck on the board. Allows players to collect ingredients for potion-making.", 20, 540);

        // Potion Brewing Area
        addText(mainPanel, "Potion Brewing Area", 20, 620);
        addText(mainPanel, "● Dedicated area to brew potions. Allows players to combine ingredients and brew potions following game rules.", 20, 640);

        // Publication Area
        addText(mainPanel, "Publication Area", 20, 720);
        addText(mainPanel, "● Displays available Publication Cards and their requirements, and facilitates submitting theories and claiming points.", 20, 740);

        // Deduction Board
        addText(mainPanel, "Deduction Board", 20, 820);
        addText(mainPanel, "● Players place alchemy markers to test theories with a user-friendly interface for guessing and confirming.", 20, 840);

        // GAME FEATURES label
        JLabel gameFeaturesLabel = new JLabel("GAME FEATURES");
        gameFeaturesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        int gameFeaturesLabelWidth = gameFeaturesLabel.getPreferredSize().width;
        gameFeaturesLabel.setBounds((1050 - gameFeaturesLabelWidth) / 2, 920, gameFeaturesLabelWidth, 90);
        mainPanel.add(gameFeaturesLabel);


        // Forage for Ingredients
        addText(mainPanel, "Forage for Ingredients", 20, 1020);
        addText(mainPanel, "● When it is your turn to forage for an ingredient, you may draw 1 random ingredient from the top of the deck.", 20, 1040);

        // Transmute Ingredient
        addText(mainPanel, "Transmute Ingredient", 20, 1120);
        addText(mainPanel, "● When you play this action, discard 1 ingredient and take 1 gold piece from the bank.", 20, 1140);

        // Buy Artifact Card
        addText(mainPanel, "Buy Artifact Card", 20, 1220);
        addText(mainPanel, "● When you buy an artifact, take one of the cards in the artifact deck and pay 3 golds.", 20, 1240);

        // Use Artifact Card
        addText(mainPanel, "Use Artifact Card", 20, 1320);
        addText(mainPanel, "● Activate the special abilities of the artifact card you own.", 20, 1340);

        // Make Experiment
        addText(mainPanel, "Make Experiment", 20, 1420);
        addText(mainPanel, "● You can make experiments where you mix two ingredients and see what happens. You can test your potion on a student or yourself.", 20, 1440);

        // Sell Potion
        addText(mainPanel, "Sell Potion", 20, 1520);
        addText(mainPanel, "● You can sell the potion that you get to an adventurer if you want.", 20, 1540);

        // Publish Theory
        addText(mainPanel, "Publish Theory", 20, 1620);
        addText(mainPanel, "● You can publish a theory about an ingredient when you know it's alchemical.", 20, 1640);

        // Debunk Theory
        addText(mainPanel, "Debunk Theory", 20, 1720);
        addText(mainPanel, "● If you think a published theory is wrong and prove that, you gain 2 points of reputation.", 20, 1740);

        // HOW TO PLAY label
        JLabel howToPlayLabel = new JLabel("HOW TO PLAY");
        howToPlayLabel.setFont(new Font("Arial", Font.BOLD, 16));
        int howToPlayLabelWidth = howToPlayLabel.getPreferredSize().width;
        howToPlayLabel.setBounds((1050 - howToPlayLabelWidth) / 2, 1780, howToPlayLabelWidth, 90);
        mainPanel.add(howToPlayLabel);

        // First Round instructions
        addText(mainPanel, "First Round:", 20, 1880);
        addText(mainPanel, "● There are four allowed actions for the first round: Forage for Ingredient,", 40, 1900);
        addText(mainPanel, "● Transmute Ingredient, Make Experiments, and Buy Artifacts.", 40, 1920);
        addText(mainPanel, "● You may perform a list of allowed actions in a round when it is.", 40, 1940);

        // Second Round instructions
        addText(mainPanel, "Second Round:", 20, 2040);
        addText(mainPanel, "● In the second round, two more actions are allowed in addition to the actions in the first round:", 40, 2060);
        addText(mainPanel, "● Sell a Potion and Publish a Theory.", 40, 2080);

        // Final Round instructions
        addText(mainPanel, "Final Round:", 20, 2180);
        addText(mainPanel, "● In the final round, you can Debunk or Endorse a Theory in addition to the actions allowed in the first two rounds.", 40, 2200);

        // Final Scoring instructions
        addText(mainPanel, "Final Scoring:", 20, 2300);
        addText(mainPanel, "When the game finishes, the final score of each player is calculated as follows:", 40, 2320);

        // Score Calculation content
        addText(mainPanel, "● Each reputation point a player has at the end of the game becomes 10 score points in the final score.", 40, 2340);
        addText(mainPanel, "  For example, if a player has 5 reputation points, his/her score is 50.", 40, 2360);
        addText(mainPanel, "● If a player has any artifact cards left in his/her hand, he/she exchanges each artifact card for 2 gold pieces.", 40, 2380);
        addText(mainPanel, "● Now score one-third of a score point for each gold piece.", 40, 2400);
        addText(mainPanel, "  Buy 1 score point for every 3 gold pieces, and keep any leftover gold pieces as a tiebreaker.", 40, 2420);

      // HOW SCORES CALCULATED content
      addText(mainPanel, "● The winner of the game is the player with the most score points. Break ties using leftover gold pieces.", 40, 2440);
      addText(mainPanel, "  If players are still tied, they remain tied.", 40, 2460);

        // Adjust the panel size to fit all components
        mainPanel.setPreferredSize(new Dimension(1000, 2520));

        // Add the panel to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add the JScrollPane to the content pane
        getContentPane().add(scrollPane);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Make the JFrame visible
        setVisible(true);
    }

    private void addText(Container container, String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setBounds(x, y, 900, 20);
        container.add(label);
    }
}
