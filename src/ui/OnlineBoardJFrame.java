package ui;

import game.*;
import game.ArtifactCards.ArtifactCard;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import ui.interfaces.BoardFrame;
import ui.interfaces.ChangeableVisibility;
import ui.uihelpers.RoundedButton;

public class OnlineBoardJFrame extends JFrame implements ChangeableVisibility, BoardFrame {

  Token token1;
  Token token2;
  Token token3;
  Token token4;
  Border lineBorder;
  Board board;
  State state;

  JPanel westPanel;
  JPanel eastPanel;
  JPanel southPanel;
  JPanel northPanel;
  JPanel centerPanel;

  JPanel westOfSouthPanel;
  JPanel eastOfSouthPanel;

  JLabel bigIngBackLabel;
  JLabel bigArtifactBackLabel;
  JLabel centerLabel;
  JLabel goldLabel;
  //JLabel goldLabel;
  private JLabel opponentsGoldLabel;
  private JLabel reputationLabel;
  private JLabel opponentsReputationLabel;

  private JLabel opponentsGoldLabel2;
  private JLabel reputationLabel2;
  private JLabel opponentsReputationLabel2;

  private JLabel opponentsGoldLabel3;
  private JLabel reputationLabel3;
  private JLabel opponentsReputationLabel3;

  JPanel ingredientCardsArea;
  JPanel artifactCardsArea;
  JPanel effectArea;
  JPanel potionArea;
  JPanel avatarArea;
  JPanel opponentsPotionArea;
  JPanel opponentsAvatarArea;
  JPanel opponentsPotionArea2;
  JPanel opponentsAvatarArea2;
  JPanel opponentsPotionArea3;
  JPanel opponentsAvatarArea3;
  JPanel opponentsIngredientCardsArea;
  JPanel opponentsIngredientCardsArea2;
  JPanel opponentsIngredientCardsArea3;
  JPanel controlArea;
  JPanel opponentsSegmentedAvatarArea;
  JPanel opponentsSegmentedAvatarArea2;
  JPanel opponentsSegmentedAvatarArea3;

  ImageIcon obsidianIcon = new ImageIcon("src/ui/utils/obsidian.jpg");
  ImageIcon saffronIcon = new ImageIcon("src/ui/utils/saffron.jpg");
  ImageIcon featherIcon = new ImageIcon("src/ui/utils/feather.png");
  ImageIcon emeraldIcon = new ImageIcon("src/ui/utils/emerald.jpg");
  ImageIcon redstoneIcon = new ImageIcon("src/ui/utils/redstone.jpg");
  ImageIcon moondustIcon = new ImageIcon("src/ui/utils/moondust.jpg");
  ImageIcon gingerIcon = new ImageIcon("src/ui/utils/ginger.png");
  ImageIcon dragonIcon = new ImageIcon("src/ui/utils/dragon fru.jpg");
  ImageIcon bigArtifactBackIcon = new ImageIcon("src/ui/utils/artifact card image.png");
  ImageIcon bigIngredientBackIcon = new ImageIcon("src/ui/utils/ingredient image.png");
  ImageIcon smallIngredientBackIcon = new ImageIcon("src/ui/utils/small-ingredient-image.png");

  ImageIcon smallPotion1Icon = new ImageIcon("src/ui/utils/potion-.jpg");
  ImageIcon smallPotion2Icon = new ImageIcon("src/ui/utils/potion+.jpg");
  ImageIcon smallPotion3Icon = new ImageIcon("src/ui/utils/potion3.jpg");

  ImageIcon artifact1Icon = new ImageIcon("src/ui/utils/artifact1jpg");
  ImageIcon artifact2Icon = new ImageIcon("src/ui/utils/artifact2.jpg");
  ImageIcon artifact3Icon = new ImageIcon("src/ui/utils/artifact3.jpg");
  ImageIcon artifact4Icon = new ImageIcon("src/ui/utils/artifact4.jpg");
  ImageIcon artifact5Icon = new ImageIcon("src/ui/utils/artifact5.jpg");
  ImageIcon artifact6Icon = new ImageIcon("src/ui/utils/artifact6.png");
  ImageIcon artifact7Icon = new ImageIcon("src/ui/utils/artifact7.jpeg");
  ImageIcon artifact8Icon = new ImageIcon("src/ui/utils/artifact8.jpeg");
  ImageIcon artifact9Icon = new ImageIcon("src/ui/utils/artifact9.jpeg");
  JButton forageButton;
  JButton transmuteButton;
  JButton experimentButton;
  JButton artifactBuyerButton;
  JButton publishButton;
  JButton debunkButton;
  JButton publicationTrackButton;
  JButton sellPotionButton;
  JButton endTurnButton;
  OnlineBoardJFrame thisBoardJFrame = this;

  JPanel row3;

  Map<ArtifactCard, ImageIcon> artifactImageMap;

  private int numOfPlayers;

  public OnlineBoardJFrame(Token currentToken, Board board) {
    super("KUALCH ONLINE");
    this.board = board;
    this.numOfPlayers = board.getTokens().size();
    token1 = currentToken;

    if (numOfPlayers == 2) {
      for (Token token : board.getTokens()) {
        if (token != token1) {
          token2 = token;
        }
      }
    } else if (numOfPlayers == 3) {
      for (Token token : board.getTokens()) {
        if (token != token1) {
          if (token2 != null) {
            if (token != token2) {
              token3 = token;
            }
          } else {
            token2 = token;
          }
        }
      }
    } else if (numOfPlayers == 4) {
      for (Token token : board.getTokens()) {
        if (token != token1) {
          if (token2 != null) {
            if (token3 != null) {
              if (token != token2 && token != token3) {
                token4 = token;
              }
            } else {
              token3 = token;
            }
          } else {
            token2 = token;
          }
        }
      }
    }

    setSize(1600, 900);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setLayout(new BorderLayout());

    this.artifactImageMap =
      Map.of(
        board.getArtifactCards().get(0),
        artifact1Icon,
        board.getArtifactCards().get(1),
        artifact2Icon,
        board.getArtifactCards().get(2),
        artifact3Icon,
        board.getArtifactCards().get(3),
        artifact4Icon
      );

    // CREATE THE JPANELS AND JLABELS

    lineBorder = new LineBorder(new Color(229, 113, 133), 1);

    westPanel = new JPanel(new BorderLayout()); // Ingredients and Artifacts Of Board
    eastPanel = new JPanel(new BorderLayout()); // Theories and Potions
    southPanel = new JPanel(new GridLayout(1, 7));
    northPanel = new JPanel(new GridLayout(1, 6));
    centerPanel = new JPanel(new BorderLayout()); // Deduction Board

    FlowLayout rowLayout = new FlowLayout(FlowLayout.CENTER);
    JPanel row1 = new JPanel(rowLayout);
    row1.setPreferredSize(new Dimension(450, 100));
    JPanel row2 = new JPanel(rowLayout);
    row2.setPreferredSize(new Dimension(450, 150));
    row3 = new JPanel(rowLayout);
    row3.setPreferredSize(new Dimension(450, 210));

    westOfSouthPanel = new JPanel(new BorderLayout());
    eastOfSouthPanel = new JPanel(new BorderLayout());

    ingredientCardsArea = new JPanel(new FlowLayout(FlowLayout.CENTER));
    artifactCardsArea = new JPanel(new FlowLayout());
    effectArea = new JPanel(new FlowLayout());
    potionArea = new JPanel(new FlowLayout());
    avatarArea = new JPanel(new FlowLayout());
    opponentsPotionArea = new JPanel(new FlowLayout());
    opponentsAvatarArea = new JPanel(new FlowLayout());
    opponentsPotionArea2 = new JPanel(new FlowLayout()); //coppies
    opponentsAvatarArea2 = new JPanel(new FlowLayout()); //coppies
    opponentsPotionArea3 = new JPanel(new FlowLayout()); //coppies
    opponentsAvatarArea3 = new JPanel(new FlowLayout()); //coppies
    opponentsIngredientCardsArea = new JPanel(new FlowLayout(FlowLayout.CENTER));
    opponentsIngredientCardsArea2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    opponentsIngredientCardsArea3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    controlArea = new JPanel(new GridLayout(5, 1));
    opponentsSegmentedAvatarArea = new JPanel(new BorderLayout());
    opponentsSegmentedAvatarArea2 = new JPanel(new BorderLayout());
    opponentsSegmentedAvatarArea3 = new JPanel(new BorderLayout());

    bigIngBackLabel = new JLabel(bigIngredientBackIcon);
    addTooltipToComponent(bigIngBackLabel, "Board's Ingredients");
    bigArtifactBackLabel = new JLabel(bigArtifactBackIcon);
    addTooltipToComponent(bigArtifactBackLabel, "Board's Artifact Cards"); //added tool tips

    // SET THE DIMENSIONS OF THE JPANELS AND JLABELS

    northPanel.setPreferredSize(new Dimension(1600, 220));
    westPanel.setPreferredSize(new Dimension(450, 460));
    eastPanel.setPreferredSize(new Dimension(450, 460));
    southPanel.setPreferredSize(new Dimension(1600, 220));

    westOfSouthPanel.setPreferredSize(new Dimension(550, 220));
    eastOfSouthPanel.setPreferredSize(new Dimension(550, 220));
    ingredientCardsArea.setPreferredSize(new Dimension(500, 220));

    artifactCardsArea.setPreferredSize(new Dimension(300, 220));
    effectArea.setPreferredSize(new Dimension(250, 220));

    potionArea.setPreferredSize(new Dimension(250, 220));
    avatarArea.setPreferredSize(new Dimension(300, 220));

    opponentsPotionArea.setPreferredSize(new Dimension(450, 220));
    opponentsPotionArea2.setPreferredSize(new Dimension(450, 220));
    opponentsPotionArea3.setPreferredSize(new Dimension(450, 220));
    opponentsAvatarArea.setPreferredSize(new Dimension(450, 220));
    opponentsAvatarArea2.setPreferredSize(new Dimension(450, 220));
    opponentsAvatarArea3.setPreferredSize(new Dimension(450, 220));
    opponentsIngredientCardsArea.setPreferredSize(new Dimension(700, 170));
    opponentsIngredientCardsArea2.setPreferredSize(new Dimension(700, 170));
    opponentsIngredientCardsArea3.setPreferredSize(new Dimension(700, 170));
    controlArea.setPreferredSize(new Dimension(700, 50));

    // SET BORDERS

    northPanel.setBorder(lineBorder);
    westPanel.setBorder(lineBorder);
    eastPanel.setBorder(lineBorder);
    southPanel.setBorder(lineBorder);
    westOfSouthPanel.setBorder(lineBorder);
    eastOfSouthPanel.setBorder(lineBorder);
    ingredientCardsArea.setBorder(lineBorder);
    artifactCardsArea.setBorder(lineBorder);
    effectArea.setBorder(lineBorder);
    potionArea.setBorder(lineBorder);
    avatarArea.setBorder(lineBorder);
    opponentsPotionArea.setBorder(lineBorder);
    opponentsPotionArea2.setBorder(lineBorder);
    opponentsPotionArea3.setBorder(lineBorder);
    opponentsAvatarArea.setBorder(lineBorder);
    opponentsAvatarArea2.setBorder(lineBorder);
    opponentsAvatarArea3.setBorder(lineBorder);
    opponentsIngredientCardsArea.setBorder(lineBorder);
    opponentsIngredientCardsArea2.setBorder(lineBorder);
    opponentsIngredientCardsArea3.setBorder(lineBorder);
    controlArea.setBorder(lineBorder);

    // ADD INGREDIENTS TO THE BOARD

    int token2IngredientsNumber = token2.getIngredients().size();
    if (token3 != null) {
      int token3IngredientsNumber = token3.getIngredients().size();
      for (int i = 0; i < token3IngredientsNumber; i++) {
        JLabel opponentIngLabel = new JLabel(smallIngredientBackIcon);
        opponentsIngredientCardsArea.add(opponentIngLabel);
        addTooltipToComponent(opponentIngLabel, "Opponent's Ingredient"); //added tool tips
      }
    }
    if (token4 != null) {
      int token4IngredientsNumber = token4.getIngredients().size();
      for (int i = 0; i < token4IngredientsNumber; i++) {
        JLabel opponentIngLabel = new JLabel(smallIngredientBackIcon);
        opponentsIngredientCardsArea2.add(opponentIngLabel);
        addTooltipToComponent(opponentIngLabel, "Opponent's Ingredient"); //added tool tips
      }
    }

    int token1IngredientsNumber = token1.getIngredients().size();
    ///// Token 2 area
    for (int i = 0; i < token2IngredientsNumber; i++) {
      JLabel opponentIngLabel = new JLabel(smallIngredientBackIcon);
      opponentsIngredientCardsArea3.add(opponentIngLabel);
      addTooltipToComponent(opponentIngLabel, "Opponent's Ingredient"); //added tool tips
    }

    for (int i = 0; i < token1IngredientsNumber; i++) {
      Ingredient token1Ingredient = token1.getIngredients().get(i);
      int ingID = token1Ingredient.getID();
      ImageIcon ingredientIcon = new ImageIcon("src/ui/utils/ingredient_" + ingID + ".jpg");
      JLabel ingredientLabel = new JLabel(ingredientIcon);
      addTooltipToComponent(ingredientLabel, token1Ingredient.getName()); //added tool tips
      ingredientCardsArea.add(ingredientLabel);
    }

    // ADD THE TOKENS ARTIFACT CARDS TO THE BOARD

    int token1ArtifactsNumber = token1.getArtifactCards().size();

    //this will have to be changed into something turn based
    for (int i = 0; i < token1ArtifactsNumber; i++) {
      ArtifactCard myArtifactCard = token1.getArtifactCards().get(i);
      createArtifactUseButton(myArtifactCard, token1);
    }

    // CREATE THE JLABELS THAT CONTAIN WORDS.

    JLabel theories = new JLabel("THEORIES:");
    theories.setFont(new Font("Arial", Font.BOLD, 20));
    theories.setForeground(Color.BLACK);

    JLabel effects = new JLabel("EFFECTS:");
    effects.setFont(new Font("Arial", Font.BOLD, 20));
    effects.setForeground(Color.BLACK);

    goldLabel = new JLabel();
    goldLabel.setText("GOLD:" + token1.getGoldBalance());
    goldLabel.setFont(new Font("Arial", Font.BOLD, 20));
    avatarArea.add(goldLabel);

    opponentsGoldLabel = new JLabel();
    opponentsGoldLabel.setText("GOLD:" + token2.getGoldBalance());
    opponentsGoldLabel.setFont(new Font("Arial", Font.BOLD, 20));
    opponentsSegmentedAvatarArea.add(opponentsGoldLabel, BorderLayout.NORTH);

    if (token3 != null) {
      opponentsGoldLabel2 = new JLabel();
      opponentsGoldLabel2.setText("GOLD:" + token3.getGoldBalance());
      opponentsGoldLabel2.setFont(new Font("Arial", Font.BOLD, 20));
      opponentsSegmentedAvatarArea2.add(opponentsGoldLabel2, BorderLayout.NORTH);
    }
    if (token4 != null) {
      opponentsGoldLabel3 = new JLabel();
      opponentsGoldLabel3.setText("GOLD:" + token4.getGoldBalance());
      opponentsGoldLabel3.setFont(new Font("Arial", Font.BOLD, 20));
      opponentsSegmentedAvatarArea3.add(opponentsGoldLabel3, BorderLayout.NORTH);
    }

    JLabel potionLabel = new JLabel();
    potionLabel.setText("POTIONS:");
    potionLabel.setFont(new Font("Arial", Font.BOLD, 20));

    reputationLabel = new JLabel();
    reputationLabel.setText("REPUTATION:" + token1.getReputation());
    reputationLabel.setFont(new Font("Arial", Font.BOLD, 20));

    JLabel opponentsPotionLabel = new JLabel();
    opponentsPotionLabel.setText("POTIONS:");
    opponentsPotionLabel.setFont(new Font("Arial", Font.BOLD, 20));

    opponentsPotionArea.add(opponentsPotionLabel);

    int token2PotionsNumber = token2.getPotions().size();
    if (token3 != null) {
      JLabel opponentsPotionLabel2 = new JLabel();
      opponentsPotionLabel2.setText("POTIONS:");
      opponentsPotionLabel2.setFont(new Font("Arial", Font.BOLD, 20));
      opponentsPotionArea2.add(opponentsPotionLabel2);
      int token3PotionsNumber = token3.getPotions().size();
      for (int i = 0; i < token3PotionsNumber; i++) {
        Potion token1Potion = token3.getPotions().get(i);
        ImageIcon potionIcon = new ImageIcon(
          "src/ui/utils/mini_potion" + token1Potion.getPotionColor() + token1Potion.getName() + ".jpg"
        );
        JLabel insidePotionLabel = new JLabel(potionIcon);
        addTooltipToComponent(insidePotionLabel, token1Potion.getPotionColor() + " " + token1Potion.getName()); //added tool tips
        opponentsPotionArea2.add(insidePotionLabel);
        addTooltipToComponent(insidePotionLabel, "Opponent's Potion"); //added tool tips
      }
    }
    if (token4 != null) {
      JLabel opponentsPotionLabel3 = new JLabel();
      opponentsPotionLabel3.setText("POTIONS:");
      opponentsPotionLabel3.setFont(new Font("Arial", Font.BOLD, 20));
      opponentsPotionArea3.add(opponentsPotionLabel3);
      int token4PotionsNumber = token4.getPotions().size();

      for (int i = 0; i < token4PotionsNumber; i++) {
        Potion token1Potion = token4.getPotions().get(i);
        ImageIcon potionIcon = new ImageIcon(
          "src/ui/utils/mini_potion" + token1Potion.getPotionColor() + token1Potion.getName() + ".jpg"
        );
        JLabel insidePotionLabel = new JLabel(potionIcon);
        addTooltipToComponent(insidePotionLabel, token1Potion.getPotionColor() + " " + token1Potion.getName()); //added tool tips
        opponentsPotionArea3.add(insidePotionLabel);
        addTooltipToComponent(insidePotionLabel, "Opponent's Potion"); //added tool tips
      }
    }
    int token1PotionsNumber = token1.getPotions().size();

    for (int i = 0; i < token2PotionsNumber; i++) {
      Potion token1Potion = token2.getPotions().get(i);
      ImageIcon potionIcon = new ImageIcon(
        "src/ui/utils/mini_potion" + token1Potion.getPotionColor() + token1Potion.getName() + ".jpg"
      );
      JLabel insidePotionLabel = new JLabel(potionIcon);
      addTooltipToComponent(insidePotionLabel, token1Potion.getPotionColor() + " " + token1Potion.getName()); //added tool tips
      opponentsPotionArea.add(insidePotionLabel);
      addTooltipToComponent(insidePotionLabel, "Opponent's Potion"); //added tool tips
    }

    for (int i = 0; i < token1PotionsNumber; i++) {
      Potion token1Potion = token1.getPotions().get(i);
      ImageIcon potionIcon = new ImageIcon(
        "src/ui/utils/mini_potion" + token1Potion.getPotionColor() + token1Potion.getName() + ".jpg"
      );
      JLabel insidePotionLabel = new JLabel(potionIcon);
      addTooltipToComponent(insidePotionLabel, token1Potion.getPotionColor() + " " + token1Potion.getName()); //added tool tips
      potionArea.add(insidePotionLabel);
    }

    opponentsReputationLabel = new JLabel();
    opponentsReputationLabel.setText("REPUTATION:" + token2.getReputation());
    opponentsReputationLabel.setFont(new Font("Arial", Font.BOLD, 20));
    if (token3 != null) {
      opponentsReputationLabel2 = new JLabel();
      opponentsReputationLabel2.setText("REPUTATION:" + token3.getReputation());
      opponentsReputationLabel2.setFont(new Font("Arial", Font.BOLD, 20));
      JPanel controlbackgroundPanelToken3 = new JPanel();
      controlbackgroundPanelToken3.setBackground(Color.LIGHT_GRAY);
      ImageIcon opponentsAvatarIcon2 = new ImageIcon("src/ui/utils/" + token3.getAvatarImage() + ".png");
      JLabel opponentsAvatarLabel2 = new JLabel(opponentsAvatarIcon2);
      JLabel username3 = new JLabel(token3.getUsername());
      username3.setFont(new Font("Arial", Font.BOLD, 20));
      opponentsSegmentedAvatarArea2.add(opponentsReputationLabel2, BorderLayout.CENTER);
      opponentsSegmentedAvatarArea2.add(username3, BorderLayout.SOUTH);
      opponentsAvatarArea2.add(opponentsAvatarLabel2);
    }
    if (token4 != null) {
      opponentsReputationLabel3 = new JLabel();
      opponentsReputationLabel3.setText("REPUTATION:" + token4.getReputation());
      opponentsReputationLabel3.setFont(new Font("Arial", Font.BOLD, 20));
      ImageIcon opponentsAvatarIcon3 = new ImageIcon("src/ui/utils/" + token4.getAvatarImage() + ".png");
      JLabel opponentsAvatarLabel3 = new JLabel(opponentsAvatarIcon3);
      opponentsAvatarArea3.add(opponentsAvatarLabel3);
      JLabel username4 = new JLabel(token4.getUsername());
      username4.setFont(new Font("Arial", Font.BOLD, 20));
      opponentsSegmentedAvatarArea3.add(opponentsReputationLabel3, BorderLayout.CENTER);
      opponentsSegmentedAvatarArea3.add(username4, BorderLayout.SOUTH);
    }

    ImageIcon avatarIcon = new ImageIcon("src/ui/utils/" + token1.getAvatarImage() + ".png");
    JLabel avatarLabel = new JLabel(avatarIcon);

    ImageIcon opponentsAvatarIcon = new ImageIcon("src/ui/utils/" + token2.getAvatarImage() + ".png");
    JLabel opponentsAvatarLabel = new JLabel(opponentsAvatarIcon);

    JLabel username1 = new JLabel(token1.getUsername());
    username1.setFont(new Font("Arial", Font.BOLD, 20));

    JLabel username2 = new JLabel(token2.getUsername());
    username2.setFont(new Font("Arial", Font.BOLD, 20));

    JLabel artifactCardsLabel = new JLabel();
    artifactCardsLabel.setText("ARTIFACT CARDS:");
    artifactCardsLabel.setFont(new Font("Arial", Font.BOLD, 20));

    // ADD THE JPANELS AND JLABELS TO THE JPANELS

    westOfSouthPanel.add(artifactCardsArea, BorderLayout.WEST);
    westOfSouthPanel.add(effectArea, BorderLayout.EAST);

    eastOfSouthPanel.add(potionArea, BorderLayout.WEST);
    eastOfSouthPanel.add(avatarArea, BorderLayout.EAST);

    southPanel.add(opponentsAvatarArea3);
    southPanel.add(opponentsPotionArea3);
    southPanel.add(artifactCardsArea);
    southPanel.add(effectArea);
    //southPanel.add(ingredientCardsArea); //might be commented out.
    southPanel.add(potionArea);
    southPanel.add(avatarArea);

    row1.add(opponentsIngredientCardsArea3);
    row2.add(ingredientCardsArea);

    eastPanel.add(row1, BorderLayout.NORTH);
    eastPanel.add(row3, BorderLayout.CENTER);
    eastPanel.add(row2, BorderLayout.SOUTH);

    northPanel.add(opponentsAvatarArea2);
    northPanel.add(opponentsPotionArea2);
    northPanel.add(controlArea);
    northPanel.add(opponentsPotionArea);
    northPanel.add(opponentsAvatarArea);
    //northPanel.add(opponentsIngredientCardsArea);

    westPanel.add(opponentsIngredientCardsArea, BorderLayout.NORTH);
    westPanel.add(opponentsIngredientCardsArea2, BorderLayout.SOUTH);

    opponentsSegmentedAvatarArea.add(opponentsReputationLabel, BorderLayout.CENTER);
    opponentsSegmentedAvatarArea.add(username2, BorderLayout.SOUTH);

    //controlArea.add(Box.createVerticalStrut(10));
    controlArea.add(new JLabel("Turn: " + board.getTurn()));
    controlArea.add(new JLabel("Round: " + board.getRound()));
    controlArea.add(new JLabel("This is the turn of: " + board.getTokens().get(board.getWhoseTurn()).getUsername()));
    controlArea.add(pauseButton());
    controlArea.add(exitButton());
    effectArea.add(effects);
    potionArea.add(potionLabel);
    avatarArea.add(reputationLabel);
    avatarArea.add(avatarLabel);
    avatarArea.add(username1);
    opponentsAvatarArea.add(opponentsSegmentedAvatarArea);
    opponentsAvatarArea.add(opponentsAvatarLabel);
    opponentsAvatarArea2.add(opponentsSegmentedAvatarArea2);
    opponentsAvatarArea3.add(opponentsSegmentedAvatarArea3);
    artifactCardsArea.add(artifactCardsLabel);

    if (board.getTokens().get(board.getWhoseTurn()).equals(currentToken)) {
      controlRoundActions(false, null);
    }

    this.add(westPanel, BorderLayout.WEST);
    this.add(eastPanel, BorderLayout.EAST);
    this.add(southPanel, BorderLayout.SOUTH);
    this.add(northPanel, BorderLayout.NORTH);
    this.add(arrangeBoardTriangle(), BorderLayout.CENTER);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - this.getWidth()) / 2;
    int y = (screenSize.height - this.getHeight()) / 2;

    // Set the frame location
    this.setLocation(x, y);

    this.setVisible(true);
  }

  public JButton endTurnButton() {
    JButton endTurnButton = new JButton("End Turn");
    endTurnButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Game game = Game.getInstance();
          game.publishAction("Action " + board.getTokens().indexOf(token1) + " EndTurn");
          board.endTurn();
          game.reopenOnlineBoard(token1, board);
        }
      }
    );
    return endTurnButton;
  }

  public JButton createForageButton() {
    JButton forageButton = new JButton("Forage For Ingredients");
    forageButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          controlRoundActions(true, null);
          Ingredient ingredient = token1.forageForIngredient(board);
          addIngredient(ingredient);
          Game game = Game.getInstance();
          game.publishAction("Action " + board.getTokens().indexOf(token1) + " ForageForIngredient");
        }
      }
    );
    return forageButton;
  }

  public JButton createTransmuteButton(String ingredientName) {
    JButton transmuteButton = new JButton("Transmute For Ingredients");
    transmuteButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (OnlineBoardJFrame.this != null) {
            //TransmuteIngredientFrame tif = new TransmuteIngredientFrame(new ArrayList<>(token1.getIngredients()), board, BoardJFrame.this);
            //tif.setVisible(true);
            Game.activateTransmuteIngredientFrame(
              new ArrayList<>(token1.getIngredients()),
              board,
              OnlineBoardJFrame.this,
              state
            );
          }
        }
      }
    );
    return transmuteButton;
  }

  // ACTION SELECTON BUTTON FOR BUYING ARTIFACT CARDS
  public JButton createArtifactBuyerButton() {
    JButton ArtifactBuyerButton = new JButton("Buy Artifact Cards");
    ArtifactBuyerButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Open the BuyArtifactFrame when the button is clicked
          // Controller version
          Game.openArtifactBuyScreen(thisBoardJFrame, board, token1, state);
        }
      }
    );
    return ArtifactBuyerButton;
  }

  // BUTTONS FOR THE ARTIFACT CARDS OF THE TOKEN.
  // SO THAT HE/SHE CAN USE THE ARTIFACT CARD WHEN CLICKED
  public void createArtifactUseButton(ArtifactCard artifactCard, Token token) {
    JButton artifactUseButton = new JButton(artifactImageMap.get(artifactCard));
    artifactUseButton.setPreferredSize(new Dimension(60, 60));
    addTooltipToComponent(artifactUseButton, artifactCard.getName()); //added tool tips

    //If it is printing press, it will not be clickable/actionable.
    if ("Printing Press" != artifactCard.getName() && "Magic Mortar" != artifactCard.getName()) {
      artifactUseButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            token.useArtifactCard(artifactCard, board);
            artifactUseButton.setVisible(false);
            token.removeArtifactCard(artifactCard);
            updateTokensReputationLabel();
          }
        }
      );
    }

    artifactCardsArea.add(artifactUseButton);
    restartBoard();
  }

  public void addIngredient(Ingredient ingredient) {
    int ingID = ingredient.getID();

    ImageIcon ingredientIcon = new ImageIcon("src/ui/utils/ingredient_" + ingID + ".jpg");
    JLabel ingredientLabel = new JLabel(ingredientIcon);
    ingredientCardsArea.add(ingredientLabel);
    addTooltipToComponent(ingredientLabel, ingredient.getName()); //added tool tips
    restartBoard();
  }

  public void removeIngredientFromBoardByName(String ingredientName) {
    String path = "";
    // Iterate over the components of ingredientCardsArea
    for (Component component : ingredientCardsArea.getComponents()) {
      if (component instanceof JLabel) {
        JLabel label = (JLabel) component;
        ImageIcon labelIcon = (ImageIcon) label.getIcon();
        Ingredient ingredient = findIngredientByImagePath(labelIcon.getDescription());

        if (ingredient != null && ingredient.getName().equals(ingredientName)) {
          path = labelIcon.getDescription();
          ingredientCardsArea.remove(label);
          token1.addGold(1);
          updateTokensGoldLabel();
          break;
        }
      }
    }

    Iterator<Ingredient> iterator = token1.getIngredients().iterator();
    while (iterator.hasNext()) {
      Ingredient ingredient = iterator.next();
      if (ingredient.getName().equals(ingredientName)) {
        iterator.remove();
        break;
      }
    }
    restartBoard();
  }

  public Ingredient findIngredientByImagePath(String imagePath) {
    for (Ingredient ingredient : token1.getIngredients()) {
      if (ingredient.getImagePath().equals(imagePath)) {
        return ingredient;
      }
    }
    return null;
  }

  public String findImagePathByIngredient(String ing) { //finding image path of ingredient from string name
    for (Ingredient ingred : token1.getIngredients()) {
      if (ingred.getName().equals(ing)) {
        return ingred.getImagePath();
      }
    }
    return null;
  }

  public JButton createExperimentButton(Board board) {
    JButton experimentButton = new JButton("Make Experiment");
    experimentButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Game.openExperimentFrame(token1, board, OnlineBoardJFrame.this, state);
        }
      }
    );
    return experimentButton;
  }

  public JButton SellPotionButton() {
    JButton useSellPotionButton = new JButton("Sell Potion");

    useSellPotionButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Game.openPotionJFrame(token1, board, OnlineBoardJFrame.this, state);
        }
      }
    );
    return useSellPotionButton;
  }

  public JButton exitButton() {
    ImageIcon exitIcon = new ImageIcon("src/ui/utils/exit.png");
    JButton exitButton = new JButton(exitIcon);

    exitButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          System.exit(0);
        }
      }
    );
    return exitButton;
  }

  public JButton pauseButton() {
    ImageIcon pauseIcon = new ImageIcon("src/ui/utils/pause.png");
    JButton pauseButton = new JButton(pauseIcon);

    pauseButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          openingPauseMenu();
        }
      }
    );
    return pauseButton;
  }

  public void openingPauseMenu() {
    Game.openPauseMenu(this);
    Game.inactivateBoard(this);
  }

  public void updateTokensGoldLabel() {
    goldLabel.setText("Gold: " + token1.getGoldBalance());
  }

  public void updateTokensReputationLabel() {
    reputationLabel.setText("Reputation: " + token1.getReputation());
  }

  public JPanel arrangeBoardTriangle() {
    // this method's purpose is adding buttons to deduction image. There are 36 different buttons and we arrange they in the for loop.
    JPanel mainPanel = new JPanel(new GridBagLayout());

    ImageIcon centerIcon = new ImageIcon("src/ui/utils/pubboard.png");
    JLabel centerLabel = new JLabel(centerIcon);
    RoundedButton[] roundedButtons = new RoundedButton[36];
    for (int i = 0; i < roundedButtons.length; i++) { // creating 36 buttons.
      Boolean flag = false;
      for(int index: token1.getDeductionBoardIndexCS().keySet()){
        if(i==index){
          flag = true;

        }

      }
      if(!flag){
        roundedButtons[i] = new RoundedButton("∅");
      }
      else{
        roundedButtons[i] = new RoundedButton(token1.getDeductionBoardIndexCS().get(i).substring(0,1));

        if("Red".equals(token1.getDeductionBoardIndexCS().get(i).substring(1))){
          roundedButtons[i].setBackground(Color.RED);
        }
        if("Blue".equals(token1.getDeductionBoardIndexCS().get(i).substring(1))){
          roundedButtons[i].setBackground(Color.BLUE);
        }
        if("Yellow".equals(token1.getDeductionBoardIndexCS().get(i).substring(1))){
          roundedButtons[i].setBackground(Color.YELLOW);
        }

      }
      roundedButtons[i].setBorder(BorderFactory.createEmptyBorder(11, 11, 11, 11));

      int finals = i;
      roundedButtons[i].addActionListener(
          new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              Game.openTriangleBoard(roundedButtons[finals], token1, finals, board);
            } // This action for the changing of button shape, color and features. We send call to game due to game is our controller.
          }
        );
    }

    GridBagConstraints gbcCenterLabel = new GridBagConstraints();
    gbcCenterLabel.gridx = 0;
    gbcCenterLabel.gridy = 0;
    gbcCenterLabel.insets = new Insets(0, 0, 0, 0);
    mainPanel.add(centerLabel, gbcCenterLabel);

    int startery = 283;
    int starterx = 0;
    int nodeNumber = 0;
    for (int k = 1; k < 9; k++) { // we used 2 different for loop. They used for rows and number of buttons.
      for (int i = 0; i < k; i++) {
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 0;
        gbcButton.insets = new Insets(0, 0, startery, starterx - (126 * i));
        mainPanel.add(roundedButtons[nodeNumber], gbcButton);
        mainPanel.setComponentZOrder(roundedButtons[nodeNumber], 0);

        nodeNumber++;
      }
      startery = startery - 64;
      starterx = starterx + 63;
    }

    return mainPanel;
  }

  public JButton publishTheoryButton() {
    JButton publishButton = new JButton("Publish Theory");

    publishButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          openPublishMenu();
        }
      }
    );
    return publishButton;
  }

  public JButton debunkTheoryButton() {
    JButton debunkButton = new JButton("Debunk Theory");

    debunkButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          openDebunkMenu();
        }
      }
    );
    return debunkButton;
  }

  public JButton publicationTrackButton() {
    JButton publicationTrackButton = new JButton("Publication Track");

    publicationTrackButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          openPublicationTrack();
        }
      }
    );
    return publicationTrackButton;
  }

  public void openPublishMenu() {
    Game.openPublishMenu(this, board, state, token1);
  }

  public void openDebunkMenu() {
    Game.openDebunkMenu(this, board, state, token1);
  }

  public void openPublicationTrack() {
    Game.openPublicationTrack(this, board);
    // this.setFocusable(false);
  }

  //adding tooltip to components for see messages when mouse is on the component
  public void addTooltipToComponent(JComponent component, String tooltipText) {
    component.addMouseMotionListener(
      new MouseMotionAdapter() {
        @Override
        public void mouseMoved(MouseEvent e) {
          ((JComponent) e.getComponent()).setToolTipText(tooltipText);
        }
      }
    );
  }

  public void addMiniPotionImage(Potion potion) {
    ImageIcon potionIcon = new ImageIcon(
      "src/ui/utils/mini_potion" + potion.getPotionColor() + potion.getName() + ".jpg"
    );
    JLabel potionLabel = new JLabel(potionIcon);
    addTooltipToComponent(potionLabel, potion.getPotionColor() + " " + potion.getName()); //added tool tips
    potionArea.add(potionLabel);
    restartBoard();
  }

  public void removeMiniPotionImage(String potionImagePath) {
    for (Component component : potionArea.getComponents()) {
      if (component instanceof JLabel) {
        JLabel label = (JLabel) component;
        ImageIcon labelIcon = (ImageIcon) label.getIcon();
        if (labelIcon != null) {
          if (labelIcon.getDescription().equals(potionImagePath)) {
            potionArea.remove(label);
            break;
          }
        }
      }
    }
    restartBoard();
  }

  public void removeIngredient(String imagePath) {
    for (Component component : ingredientCardsArea.getComponents()) {
      if (component instanceof JLabel) {
        JLabel label = (JLabel) component;
        ImageIcon labelIcon = (ImageIcon) label.getIcon();
        if (labelIcon != null) {
          if (labelIcon.getDescription().equals(imagePath)) {
            ingredientCardsArea.remove(label);
            break;
          }
        }
      }
    }
    restartBoard();
  }

  public void closeBoard() {
    this.setVisible(false);
  }

  public void restartBoard() {
    this.setVisible(false);
    this.setVisible(true);
  }

  @Override
  public void changeVisible(Boolean visible) {
    this.setVisible(visible);
  }

  @Override
  public void activateTransmuteIngredientFrame(
    ArrayList<Ingredient> displayedIngredients,
    Board mainBoard,
    State state
  ) {
    TransmuteIngredientFrame transmuteFrame = new TransmuteIngredientFrame(
      displayedIngredients,
      mainBoard,
      this,
      state
    );
  }

  @Override
  public void controlRoundActions(Boolean endTurnFlag, State state) {
    // CONTROL THE BUTTONS
    if (endTurnFlag) {
      if (board.getRound() == 1) {
        row3.remove(forageButton);
        row3.remove(transmuteButton);
        row3.remove(experimentButton);
        row3.remove(artifactBuyerButton);
      } else if (board.getRound() == 2) {
        row3.remove(forageButton);
        row3.remove(transmuteButton);
        row3.remove(publishButton);
        row3.remove(publicationTrackButton);
        row3.remove(experimentButton);
        row3.remove(artifactBuyerButton);
        row3.remove(sellPotionButton);
      } else if (board.getRound() == 3) {
        row3.remove(forageButton);
        row3.remove(transmuteButton);
        row3.remove(publishButton);
        row3.remove(debunkButton);
        row3.remove(publicationTrackButton);
        row3.remove(experimentButton);
        row3.remove(artifactBuyerButton);
        row3.remove(sellPotionButton);
      }
    } else {
      if (board.getRound() == 1) {
        forageButton = createForageButton();
        row3.add(forageButton);
        transmuteButton = createTransmuteButton(getName());
        row3.add(transmuteButton);
        experimentButton = createExperimentButton(board);
        row3.add(experimentButton);
        artifactBuyerButton = createArtifactBuyerButton();
        row3.add(artifactBuyerButton);
        endTurnButton = endTurnButton();
        row3.add(endTurnButton);
      } else if (board.getRound() == 2) {
        forageButton = createForageButton();
        row3.add(forageButton);
        transmuteButton = createTransmuteButton(getName());
        row3.add(transmuteButton);
        experimentButton = createExperimentButton(board);
        row3.add(experimentButton);
        artifactBuyerButton = createArtifactBuyerButton();
        row3.add(artifactBuyerButton);
        publishButton = publishTheoryButton();
        row3.add(publishButton);
        publicationTrackButton = publicationTrackButton();
        row3.add(publicationTrackButton);
        sellPotionButton = SellPotionButton();
        row3.add(sellPotionButton);
        endTurnButton = endTurnButton();
        row3.add(endTurnButton);
      } else if (board.getRound() == 3) {
        forageButton = createForageButton();
        row3.add(forageButton);
        transmuteButton = createTransmuteButton(getName());
        row3.add(transmuteButton);
        debunkButton = debunkTheoryButton();
        row3.add(debunkButton);
        experimentButton = createExperimentButton(board);
        row3.add(experimentButton);
        artifactBuyerButton = createArtifactBuyerButton();
        row3.add(artifactBuyerButton);
        publishButton = publishTheoryButton();
        row3.add(publishButton);
        publicationTrackButton = publicationTrackButton();
        row3.add(publicationTrackButton);
        sellPotionButton = SellPotionButton();
        row3.add(sellPotionButton);
        endTurnButton = endTurnButton();
        row3.add(endTurnButton);
      }
    }
  }
}
