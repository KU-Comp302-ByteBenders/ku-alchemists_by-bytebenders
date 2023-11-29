package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import game.*;

import java.awt.*;
import java.util.ArrayList;

public class BoardJFrame extends JFrame {

    public BoardJFrame(
        Token token1,
        Token token2,
        ArrayList<Ingredient> ingredientList, 
        ArrayList<ArtifactCard> artifactCardsList, 
        ArrayList<Theory> theoryList
        ) {

        super("KUALCH");
        setSize(1600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        Border lineBorder = new LineBorder(Color.BLACK, 2);


        JPanel westPanel = new JPanel(new FlowLayout());  // Ingredients and Artifacts Of Board
        JPanel eastPanel = new JPanel(new BorderLayout());  // Theories and Potions
        JPanel southPanel = new JPanel(new BorderLayout());
        JPanel northPanel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new FlowLayout()); // Deduction Board

        JPanel westOfSouthPanel = new JPanel(new BorderLayout());
        JPanel eastOfSouthPanel = new JPanel(new BorderLayout());


        JPanel ingredientCardsArea = new JPanel(new FlowLayout());  // Ingredient Cards Area
        JPanel artifactCardsArea = new JPanel(new FlowLayout());  // Artifact Cards Area
        JPanel effectArea = new JPanel(new FlowLayout());  // Effect Area
        JPanel potionArea = new JPanel(new FlowLayout()); // Hand Potion Area
        JPanel avatarArea = new JPanel(new FlowLayout()); // Avatar Area
        JPanel opponentsPotionArea = new JPanel(new FlowLayout()); // Opponents Potion Area
        JPanel opponentsAvatarArea = new JPanel(new FlowLayout()); // Opponents Avatar Area
        JPanel opponentsIngredientCardsArea = new JPanel(new FlowLayout()); // Opponents Ingredient Cards Area
        JPanel controlArea = new JPanel(new FlowLayout()); // Control Area
        JPanel opponentsSegmentedAvatarArea = new JPanel(new BorderLayout());


        northPanel.setPreferredSize(new Dimension(1600, 220));
        westPanel.setPreferredSize(new Dimension(450, 460));
        eastPanel.setPreferredSize(new Dimension(450, 460));
        centerPanel.setPreferredSize(new Dimension(700, 460));
        southPanel.setPreferredSize(new Dimension(1600, 220));

        westOfSouthPanel.setPreferredSize(new Dimension(550, 220));
        eastOfSouthPanel.setPreferredSize(new Dimension(550, 220));
        ingredientCardsArea.setPreferredSize(new Dimension(500, 220));

        artifactCardsArea.setPreferredSize(new Dimension(300, 220)); 
        effectArea.setPreferredSize(new Dimension(250, 220));

        potionArea.setPreferredSize(new Dimension(250, 220));
        avatarArea.setPreferredSize(new Dimension(300, 220));

        opponentsPotionArea.setPreferredSize(new Dimension(450, 220));
        opponentsAvatarArea.setPreferredSize(new Dimension(450, 220));
        opponentsIngredientCardsArea.setPreferredSize(new Dimension(700, 170));
        controlArea.setPreferredSize(new Dimension(700, 50));

        southPanel.add(westOfSouthPanel, BorderLayout.WEST);
        southPanel.add(eastOfSouthPanel, BorderLayout.EAST);
        southPanel.add(ingredientCardsArea, BorderLayout.CENTER);

        westOfSouthPanel.add(artifactCardsArea, BorderLayout.WEST);
        westOfSouthPanel.add(effectArea, BorderLayout.EAST);

        eastOfSouthPanel.add(potionArea, BorderLayout.WEST);
        eastOfSouthPanel.add(avatarArea, BorderLayout.EAST);

        northPanel.add(opponentsPotionArea, BorderLayout.WEST);
        northPanel.add(opponentsAvatarArea, BorderLayout.EAST);
        northPanel.add(opponentsIngredientCardsArea, BorderLayout.CENTER);
        northPanel.add(controlArea, BorderLayout.NORTH);


        northPanel.setBorder(lineBorder);
        westPanel.setBorder(lineBorder);
        eastPanel.setBorder(lineBorder);
        centerPanel.setBorder(lineBorder);
        southPanel.setBorder(lineBorder);
        westOfSouthPanel.setBorder(lineBorder);
        eastOfSouthPanel.setBorder(lineBorder);
        ingredientCardsArea.setBorder(lineBorder);
        artifactCardsArea.setBorder(lineBorder);
        effectArea.setBorder(lineBorder);
        potionArea.setBorder(lineBorder);
        avatarArea.setBorder(lineBorder);
        opponentsPotionArea.setBorder(lineBorder);
        opponentsAvatarArea.setBorder(lineBorder);
        opponentsIngredientCardsArea.setBorder(lineBorder);
        controlArea.setBorder(lineBorder);

        JLabel theories = new JLabel("THEORIES:");
        theories.setFont(new Font("Arial", Font.BOLD, 20));
        theories.setForeground(Color.BLACK);

        JLabel effects = new JLabel("EFFECTS:");
        effects.setFont(new Font("Arial", Font.BOLD, 20));
        effects.setForeground(Color.BLACK);

        JLabel goldLabel = new JLabel();
        goldLabel.setText("GOLD:" + token1.getGoldBalance());
        goldLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel potionLabel = new JLabel();
        potionLabel.setText("POTIONS:");
        potionLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel reputationLabel = new JLabel();
        reputationLabel.setText("REPUTATION:" + token1.getReputation());
        reputationLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel opponentsPotionLabel = new JLabel();
        opponentsPotionLabel.setText("POTIONS:");
        opponentsPotionLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel opponentsReputationLabel = new JLabel();
        opponentsReputationLabel.setText("REPUTATION:" + token2.getReputation());
        opponentsReputationLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel opponentsGoldLabel = new JLabel();
        opponentsGoldLabel.setText("GOLD:" + token2.getGoldBalance());
        opponentsGoldLabel.setFont(new Font("Arial", Font.BOLD, 20));

        ImageIcon pauseIcon = new ImageIcon("src/ui/utils/pause.png");
        JLabel pauseLabel = new JLabel(pauseIcon);

        ImageIcon avatarIcon = new ImageIcon("src/ui/utils/"+token1.getAvatarImage()+".png");
        JLabel avatarLabel = new JLabel(avatarIcon);

        ImageIcon opponentsAvatarIcon = new ImageIcon("src/ui/utils/"+token2.getAvatarImage()+".png");
        JLabel opponentsAvatarLabel = new JLabel(opponentsAvatarIcon);

        JLabel username1 = new JLabel(token1.getUsername());
        username1.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel username2 = new JLabel(token2.getUsername());
        username2.setFont(new Font("Arial", Font.BOLD, 20));

        ImageIcon potionBrewingIcon = new ImageIcon("src/ui/utils/potion-brewing.png");
        JLabel potionBrewingLabel = new JLabel(potionBrewingIcon);

        ImageIcon publicationAreaIcon = new ImageIcon("src/ui/utils/publication-brewing.png");
        JLabel publicationAreaLabel = new JLabel(publicationAreaIcon);

        JLabel artifactCardsLabel = new JLabel();
        artifactCardsLabel.setText("ARTIFACT CARDS:");
        artifactCardsLabel.setFont(new Font("Arial", Font.BOLD, 20));


        opponentsSegmentedAvatarArea.add(opponentsGoldLabel, BorderLayout.NORTH);
        opponentsSegmentedAvatarArea.add(opponentsReputationLabel, BorderLayout.CENTER);
        opponentsSegmentedAvatarArea.add(username2, BorderLayout.SOUTH);

        controlArea.add(pauseLabel);
        effectArea.add(effects);
        eastPanel.add(potionBrewingLabel, BorderLayout.NORTH);
        eastPanel.add(publicationAreaLabel, BorderLayout.SOUTH);
        potionArea.add(potionLabel);
        avatarArea.add(goldLabel);
        avatarArea.add(reputationLabel);
        avatarArea.add(avatarLabel);
        avatarArea.add(username1);
        opponentsPotionArea.add(opponentsPotionLabel);
        opponentsAvatarArea.add(opponentsSegmentedAvatarArea);
        opponentsAvatarArea.add(opponentsAvatarLabel);

        
        artifactCardsArea.add(artifactCardsLabel);

        this.add(westPanel, BorderLayout.WEST);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);

        setVisible(true);

    }
}
