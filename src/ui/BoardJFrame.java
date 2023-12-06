package ui;

import game.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class BoardJFrame extends JFrame {

  Token token1;
  Token token2;
  Border lineBorder;
  Board board;

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

  JPanel ingredientCardsArea;
  JPanel artifactCardsArea;
  JPanel effectArea;
  JPanel potionArea;
  JPanel avatarArea;
  JPanel opponentsPotionArea;
  JPanel opponentsAvatarArea;
  JPanel opponentsIngredientCardsArea;
  JPanel controlArea;
  JPanel opponentsSegmentedAvatarArea;

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
  ImageIcon centerIcon = new ImageIcon("src/ui/utils/pubboard.png");
  ImageIcon smallIngredientBackIcon = new ImageIcon("src/ui/utils/small-ingredient-image.png");

  public BoardJFrame(Board board) {
    super("KUALCH");
    this.board = board;
    token1 = board.getTokens().get(0);
    token2 = board.getTokens().get(1);

    setSize(1600, 900);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setLayout(new BorderLayout());

    // CREATE THE JPANELS AND JLABELS

    lineBorder = new LineBorder(new Color(229, 113, 133), 1);

    westPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 20)); // Ingredients and Artifacts Of Board
    eastPanel = new JPanel(new FlowLayout()); // Theories and Potions
    southPanel = new JPanel(new BorderLayout());
    northPanel = new JPanel(new BorderLayout());
    centerPanel = new JPanel(new BorderLayout()); // Deduction Board

    westOfSouthPanel = new JPanel(new BorderLayout());
    eastOfSouthPanel = new JPanel(new BorderLayout());

    ingredientCardsArea = new JPanel(new FlowLayout(FlowLayout.CENTER));
    artifactCardsArea = new JPanel(new FlowLayout());
    effectArea = new JPanel(new FlowLayout());
    potionArea = new JPanel(new FlowLayout());
    avatarArea = new JPanel(new FlowLayout());
    opponentsPotionArea = new JPanel(new FlowLayout());
    opponentsAvatarArea = new JPanel(new FlowLayout());
    opponentsIngredientCardsArea = new JPanel(new FlowLayout(FlowLayout.CENTER));
    controlArea = new JPanel(new FlowLayout());
    opponentsSegmentedAvatarArea = new JPanel(new BorderLayout());

    bigIngBackLabel = new JLabel(bigIngredientBackIcon);
    bigArtifactBackLabel = new JLabel(bigArtifactBackIcon);
    centerLabel = new JLabel(centerIcon);

    // SET THE DIMENSIONS OF THE JPANELS AND JLABELS

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

    // SET BORDERS

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

    // ADD INGREDIENTS TO THE BOARD

    int token2IngredientsNumber = token2.getIngredients().size();
    int token1IngredientsNumber = token1.getIngredients().size();

    for (int i = 0; i < token2IngredientsNumber; i++) {
      JLabel opponentIngLabel = new JLabel(smallIngredientBackIcon);
      opponentsIngredientCardsArea.add(opponentIngLabel);
    }

    System.out.println("token1.getIngredients().size() = " + token1.getIngredients().size());

    for (int i = 0; i < token1IngredientsNumber; i++) {
      Ingredient token1Ingredient = token1.getIngredients().get(i);
      int ingID = token1Ingredient.getID();
      ImageIcon ingredientIcon = new ImageIcon("src/ui/utils/ingredient_" + ingID + ".jpg");
      JLabel ingredientLabel = new JLabel(ingredientIcon);
      ingredientCardsArea.add(ingredientLabel);
    }

    // CREATE THE JLABELS THAT CONTAIN WORDS.

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

    ImageIcon exitIcon = new ImageIcon("src/ui/utils/exit.png");
    JLabel exitLabel = new JLabel(exitIcon);

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

    westPanel.add(bigIngBackLabel);
    westPanel.add(bigArtifactBackLabel);
    centerPanel.add(centerLabel);

    opponentsSegmentedAvatarArea.add(opponentsGoldLabel, BorderLayout.NORTH);
    opponentsSegmentedAvatarArea.add(opponentsReputationLabel, BorderLayout.CENTER);
    opponentsSegmentedAvatarArea.add(username2, BorderLayout.SOUTH);

    controlbackgroundPanelToken1.add(scoreLabel1);
    controlbackgroundPanelToken2.add(scoreLabel2);
    controlArea.add(controlbackgroundPanelToken1);
    controlArea.add(controlbackgroundPanelToken2);
    controlArea.add(pauseButton());
    controlArea.add(exitLabel);
    effectArea.add(effects);
    potionArea.add(potionLabel);
    avatarArea.add(goldLabel);
    avatarArea.add(reputationLabel);
    avatarArea.add(avatarLabel);
    avatarArea.add(username1);
    opponentsPotionArea.add(opponentsPotionLabel);
    opponentsAvatarArea.add(opponentsSegmentedAvatarArea);
    opponentsAvatarArea.add(opponentsAvatarLabel);
    artifactCardsArea.add(artifactCardsLabel);
    eastPanel.add(createForageButton());
    eastPanel.add(createExperimentButton(board));

    this.add(westPanel, BorderLayout.WEST);
    this.add(eastPanel, BorderLayout.EAST);
    this.add(southPanel, BorderLayout.SOUTH);
    this.add(northPanel, BorderLayout.NORTH);
    this.add(centerPanel, BorderLayout.CENTER);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - this.getWidth()) / 2;
    int y = (screenSize.height - this.getHeight()) / 2;

    // Set the frame location
    this.setLocation(x, y);

    this.setVisible(true);
  }

  public JButton createForageButton() {
    JButton forageButton = new JButton("Forage For Ingredients");
    forageButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Ingredient ingredient = token1.forageForIngredient(board);
          addIngredient(ingredient);
        }
      }
    );
    return forageButton;
  }

  public void addIngredient(Ingredient ingredient) {
    int ingID = ingredient.getID();
    System.out.println("IIIIIIIIIIIIIII");
    for (Ingredient ing : token1.getIngredients()) {
      System.out.println("ing.getID() = " + ing.getID());
    }
    System.out.println("IIIIIIIIIIIIIII");

    ImageIcon ingredientIcon = new ImageIcon("src/ui/utils/ingredient_" + ingID + ".jpg");
    JLabel ingredientLabel = new JLabel(ingredientIcon);
    ingredientCardsArea.add(ingredientLabel);
    this.setVisible(false);
    this.setVisible(true);
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

  public void openingPauseMenu(){
    Game.openPauseMenu(this);
    Game.inactivateBoard(this);
  }
  
  //make experiment button
  public JButton createExperimentButton(Board board){
    JButton experimentButton = new JButton("Make Experiment");
    experimentButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
          Game.openExperimentFrame(board);
        }
      }
    );
    return experimentButton;
  }
}
