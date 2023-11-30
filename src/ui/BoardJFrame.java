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


        JPanel westPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,100,20));  // Ingredients and Artifacts Of Board
        JPanel eastPanel = new JPanel(new BorderLayout());  // Theories and Potions
        JPanel southPanel = new JPanel(new BorderLayout());
        JPanel northPanel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new BorderLayout()); // Deduction Board

        JPanel westOfSouthPanel = new JPanel(new BorderLayout());
        JPanel eastOfSouthPanel = new JPanel(new BorderLayout());

        JPanel ingredientCardsArea = new JPanel(new FlowLayout(FlowLayout.CENTER));  // Ingredient Cards Area
        JPanel artifactCardsArea = new JPanel(new FlowLayout());  // Artifact Cards Area
        JPanel effectArea = new JPanel(new FlowLayout());  // Effect Area
        JPanel potionArea = new JPanel(new FlowLayout()); // Hand Potion Area
        JPanel avatarArea = new JPanel(new FlowLayout()); // Avatar Area
        JPanel opponentsPotionArea = new JPanel(new FlowLayout()); // Opponents Potion Area
        JPanel opponentsAvatarArea = new JPanel(new FlowLayout()); // Opponents Avatar Area
        JPanel opponentsIngredientCardsArea = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Opponents Ingredient Cards Area
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

        ImageIcon obsidianIcon = new ImageIcon("src/ui/utils/obsidian.jpg"); 
        ImageIcon saffronIcon = new ImageIcon("src/ui/utils/saffron.jpg"); 
        ImageIcon featherIcon = new ImageIcon("src/ui/utils/feather.png"); 
        ImageIcon emeraldIcon = new ImageIcon("src/ui/utils/emerald.jpg"); 
        ImageIcon redstoneIcon = new ImageIcon("src/ui/utils/redstone.jpg");
        ImageIcon moondustIcon = new ImageIcon("src/ui/utils/moondust.jpg");
        ImageIcon gingerIcon = new ImageIcon("src/ui/utils/ginger.png");
        ImageIcon dragonIcon = new ImageIcon("src/ui/utils/dragon fru.jpg");

       
       
        ImageIcon  bigArtifactBackIcon = new ImageIcon("src/ui/utils/artifact card image.png"); 
        ImageIcon bigIngredientBackIcon = new ImageIcon("src/ui/utils/ingredient image.png");
        ImageIcon centerIcon = new ImageIcon("src/ui/utils/pubboard.png");
        
        JLabel bigIngBackLabel = new JLabel(bigIngredientBackIcon);
        JLabel bigArtifactBackLabel = new JLabel(bigArtifactBackIcon);
        JLabel  centerLabel= new JLabel(centerIcon);

        westPanel.add(bigIngBackLabel);
        westPanel.add(bigArtifactBackLabel);
        centerPanel.add(centerLabel);

        ImageIcon smallIngredientBackIcon = new ImageIcon("src/ui/utils/small-ingredient-image.png");

        int token2IngNum;
        int token1IngNum=token1.getIngredients().size();

        for (token2IngNum=token2.getIngredients().size(); token2IngNum>0;token2IngNum--){
            JLabel opponentIngLabel = new JLabel(smallIngredientBackIcon);
            opponentsIngredientCardsArea.add(opponentIngLabel);
        }
        
        for (int i=0; i<token1IngNum;i++){
            Ingredient token1Ingredient = token1.getIngredients().get(i);
            int ingID = token1Ingredient.getID();
            switch(ingID){
                case 1:
                    JLabel case1Label = new JLabel(dragonIcon);    
                    ingredientCardsArea.add(case1Label);
                    break;
                case 2:
                    JLabel case2Label = new JLabel(emeraldIcon);
                    ingredientCardsArea.add(case2Label);
                    break;
                case 3:
                    JLabel case3Label = new JLabel(featherIcon);
                    ingredientCardsArea.add(case3Label);
                    break;
                case 4:
                     JLabel case4Label = new JLabel(gingerIcon);
                    ingredientCardsArea.add(case4Label);
                case 5:
                    JLabel case5Label = new JLabel(moondustIcon);
                    ingredientCardsArea.add(case5Label);
                    break;
                case 6:
                    JLabel case6Label = new JLabel(obsidianIcon);
                    ingredientCardsArea.add(case6Label);
                    break;
                case 7:
                    JLabel case7Label = new JLabel(redstoneIcon);
                    ingredientCardsArea.add(case7Label);
                    break;
                case 8:        
                    JLabel case8Label = new JLabel(saffronIcon);
                    ingredientCardsArea.add(case8Label);
                    break;
            }
        }

 
        
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

        JPanel controlbackgroundPanelToken1 = new JPanel();
        controlbackgroundPanelToken1.setBackground(Color.LIGHT_GRAY);

        JLabel scoreLabel1 = new JLabel();
        scoreLabel1.setText("Token1 Score=" + token1.getScore());
        scoreLabel1.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel controlbackgroundPanelToken2 = new JPanel();
        controlbackgroundPanelToken2.setBackground(Color.LIGHT_GRAY);

        JLabel scoreLabel2 = new JLabel();
        scoreLabel2.setText("Token2 Score=" + token2.getScore());
        scoreLabel2.setFont(new Font("Arial", Font.BOLD, 20));

        ImageIcon pauseIcon = new ImageIcon("src/ui/utils/pause.png");
        JLabel pauseLabel = new JLabel(pauseIcon);

        ImageIcon exitIcon = new ImageIcon("src/ui/utils/exit.png");
        JLabel exitLabel = new JLabel(exitIcon);

        ImageIcon menuIcon = new ImageIcon("src/ui/utils/menu.png");
        JLabel menuLabel = new JLabel(menuIcon);

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

        controlbackgroundPanelToken1.add(scoreLabel1);
        controlbackgroundPanelToken2.add(scoreLabel2);
        controlArea.add(controlbackgroundPanelToken1);
        controlArea.add(controlbackgroundPanelToken2);
        controlArea.add(pauseLabel);
        controlArea.add(menuLabel);
        controlArea.add(exitLabel);
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
