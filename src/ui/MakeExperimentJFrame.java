package ui;

import game.*;
import ui.interfaces.BoardFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class MakeExperimentJFrame extends JFrame {

  Board board;
  Token token1;
  Border lineBorder;

  JPanel westPanel;
  JPanel eastPanel;
  JPanel southPanel;
  JPanel northPanel;
  JPanel centerPanel;

  JPanel returnGamePanel;
  JPanel makeJPanel;
  JPanel resultPanel;
  JPanel comboPanel;
  JLabel resJLabel;

  private DefaultComboBoxModel<String> ingredientModel1;
  private DefaultComboBoxModel<String> ingredientModel2;
  private JComboBox<String> ingredientComboBox1;
  private JComboBox<String> ingredientComboBox2;
  private JComboBox<String> testComboBox;
  private JButton selectIngredientsButton;
  private JButton selectPersonButton;
  BoardFrame boardFrame;
  Map<String, JLabel> smallImages = new HashMap<>();

  ImageIcon potionIcon1;
  ImageIcon potionIcon2;
  ImageIcon potionIcon3;
  Potion newPotion;

  public MakeExperimentJFrame(Token token, Board board, BoardFrame boardFrame, State state) {
    this.board = board;
    this.boardFrame = boardFrame;

    token1 = token;

    ingredientModel1 = new DefaultComboBoxModel<>();
    ingredientModel2 = new DefaultComboBoxModel<>();

    lineBorder = new LineBorder(new Color(25, 25, 112), 1);

    ingredientComboBox1 = new JComboBox<>(ingredientModel1);
    ingredientComboBox2 = new JComboBox<>(ingredientModel2);

    this.setSize(1280, 720);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setResizable(false);

    // Create panels
    westPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 20)); //
    eastPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 75)); // tests
    southPanel = new JPanel(new FlowLayout());
    northPanel = new JPanel(new BorderLayout());
    centerPanel = new JPanel(new BorderLayout());

    northPanel.setPreferredSize(new Dimension(1280, 100));
    westPanel.setPreferredSize(new Dimension(300, 350));
    eastPanel.setPreferredSize(new Dimension(300, 350));
    centerPanel.setPreferredSize(new Dimension(680, 350));
    southPanel.setPreferredSize(new Dimension(1280, 270));

    returnGamePanel = new JPanel(new BorderLayout());
    makeJPanel = new JPanel(new BorderLayout());
    resultPanel = new JPanel(new BorderLayout());
    comboPanel = new JPanel(new BorderLayout());

    returnGamePanel.setPreferredSize(new Dimension(300, 50));
    makeJPanel.setPreferredSize(new Dimension(100, 100));
    resultPanel.setPreferredSize(new Dimension(50, 50));
    comboPanel.setPreferredSize(new Dimension(300, 150));

    northPanel.setBorder(lineBorder);
    westPanel.setBorder(lineBorder);
    eastPanel.setBorder(lineBorder);
    centerPanel.setBorder(lineBorder);
    southPanel.setBorder(lineBorder);

    // Create labels
    JLabel versionLabel = new JLabel("POTION BREWING AREA");
    versionLabel.setForeground(Color.decode("#A020F0"));
    versionLabel.setFont(new Font("Arial", Font.BOLD, 35));
    versionLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel MagicMortarLabel = new JLabel(
        "If you have magic mortar artifact card, the first ingredient you choose will remain with you.");
    MagicMortarLabel.setForeground(Color.decode("#A020F0"));
    MagicMortarLabel.setFont(new Font("Arial", Font.BOLD, 15));
    MagicMortarLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel panelLabel = new JLabel("INGREDIENTS");
    panelLabel.setForeground(Color.decode("#A020F0"));
    panelLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel resultLabel = new JLabel("RESULT");
    resultLabel.setForeground(Color.decode("#A020F0"));

    // getting ingredients that token has
    JPanel ingredPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    ingredPanel.setBorder(lineBorder);

    int token1IngredientsNumber = token1.getIngredients().size();

    for (int i = 0; i < token1IngredientsNumber; i++) {
      Ingredient token1Ingredient = token1.getIngredients().get(i);
      int ingID = token1Ingredient.getID();
      ImageIcon ingredientIcon = new ImageIcon("src/ui/utils/ingredient_" + ingID + ".jpg");
      JLabel ingredientLabel = new JLabel(ingredientIcon);

      ingredientModel1.addElement(token1Ingredient.getName());
      ingredientModel2.addElement(token1Ingredient.getName()); // Buradan ilk eklenen element çıkarılmalı

      ingredPanel.add(ingredientLabel);
    }
    ingredPanel.add(panelLabel);

    // to return to the board
    JButton closeExperimentFrame = new JButton("Return the Game");
    closeExperimentFrame.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            closingMenu(MakeExperimentJFrame.this);
          }
        });

    // Create a button to mix ingredients
    JButton mixButton = new JButton("Mix");

    mixButton.setEnabled(false);
    mixButton.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            String ingName1 = ingredientComboBox1.getSelectedItem().toString();
            String ingName2 = ingredientComboBox2.getSelectedItem().toString();

            String path1 = boardFrame.findImagePathByIngredient(ingName1);
            String path2 = boardFrame.findImagePathByIngredient(ingName2);

            boardFrame.removeIngredient(path1);
            boardFrame.removeIngredient(path2);

            if (token1.getArtifactCardByName("Magic Mortar") != null) {
              token1.getArtifactCardByName("Magic Mortar").applyEffect(token1, board, ingName1);
              Ingredient mortarIng = token1.findIngredientByName(ingName1);
              boardFrame.addIngredient(mortarIng);
            }

            Game game = Game.getInstance();
            if (!game.isOffline()) {
              game.publishAction(
                  "Action " + board.getTokens().indexOf(token1) + " MakeExperiment " + ingName1 + " " + ingName2 + " "
                      + testResult(testComboBox.getSelectedItem().toString()));
            }

            newPotion = token1.makeExperiment(
                ingName1,
                ingName2,
                testResult(testComboBox.getSelectedItem().toString()));

            boardFrame.addMiniPotionImage(newPotion);
            showResult(newPotion.getName(), newPotion.getPotionColor());
            Game.controlRoundAction(boardFrame, state, true);
            mixButton.setEnabled(false);
          }
        });

    // Create a button to select ingredients

    selectIngredientsButton = new JButton("Select Ingredients");
    selectIngredientsButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if (ingredientComboBox1.getSelectedItem().toString()
                .equals(ingredientComboBox2.getSelectedItem().toString())) {
              JOptionPane.showMessageDialog(
                  MakeExperimentJFrame.this,
                  "You cannot choose same ingredient!",
                  "Error",
                  JOptionPane.ERROR_MESSAGE);
              return;
            }
            mixButton.setEnabled(true);
            handleIngredientSelection(
                ingredientComboBox1.getSelectedItem().toString(),
                ingredientComboBox2.getSelectedItem().toString());
          }
        });

    selectPersonButton = new JButton("Select Person");
    selectPersonButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            handleIngredientSelection(testComboBox.getSelectedItem().toString());
          }
        });

    String[] testON = { "Test On Student", "Test On Yourself" };
    testComboBox = new JComboBox<>(testON);

    // Add components to the frame
    JPanel topComboBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    topComboBoxPanel.add(ingredientComboBox1);
    topComboBoxPanel.add(ingredientComboBox2);

    JPanel bottomComboBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 150, 20));
    bottomComboBoxPanel.add(testComboBox);

    ingredientComboBox1.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            updateSelectButtonState();
          }
        });

    ingredientComboBox2.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            updateSelectButtonState();
          }
        });

    westPanel.add(topComboBoxPanel, BorderLayout.NORTH);
    westPanel.add(selectIngredientsButton, BorderLayout.SOUTH);
    westPanel.add(bottomComboBoxPanel, BorderLayout.SOUTH);
    westPanel.add(selectPersonButton, BorderLayout.SOUTH);
    southPanel.add(MagicMortarLabel, BorderLayout.SOUTH);

    this.add(westPanel, BorderLayout.WEST);
    this.add(eastPanel, BorderLayout.EAST);
    this.add(southPanel, BorderLayout.SOUTH);
    this.add(northPanel, BorderLayout.NORTH);
    this.add(centerPanel, BorderLayout.CENTER);

    westPanel.add(comboPanel, BorderLayout.CENTER);
    centerPanel.add(ingredPanel, BorderLayout.CENTER);

    centerPanel.add(panelLabel, BorderLayout.NORTH);
    resultPanel.add(resultLabel, BorderLayout.CENTER);

    makeJPanel.add(versionLabel, BorderLayout.CENTER);
    returnGamePanel.add(closeExperimentFrame);

    eastPanel.add(mixButton, BorderLayout.CENTER);

    northPanel.add(returnGamePanel, BorderLayout.EAST);
    northPanel.add(makeJPanel, BorderLayout.CENTER);
    southPanel.add(resultPanel, BorderLayout.CENTER);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - this.getWidth()) / 2;
    int y = (screenSize.height - this.getHeight()) / 2;

    this.setLocation(x, y);
    this.setVisible(true);
  }

  // close the frame
  public void closingMenu(JFrame experimentFrame) {
    boardFrame.updateTokensGoldLabel();
    experimentFrame.dispose();
  }

  // Enable the button only if both ComboBoxes have selections
  private void updateSelectButtonState() {
    selectIngredientsButton.setEnabled(
        ingredientComboBox1.getSelectedItem() != null && ingredientComboBox2.getSelectedItem() != null);
  }

  private void handleIngredientSelection(String ingredient1, String ingredient2) {
    // Process the selected ingredients
    System.out.println("Selected Ingredients: " + ingredient1 + ", " + ingredient2);
  }

  private void handleIngredientSelection(String person) {
    // Process the selected ingredients
    System.out.println("Selected Person: " + person);
  }

  private boolean testResult(String person) {
    if (testComboBox.getSelectedItem().toString().equals("Test On Student")) {
      return false;
    }
    return true;
  }

  // this method is used to show the result of the experiment and close the frame
  // after 7 seconds
  public void showResult(String newString, String potionColor) {
    if (newString.equals("-")) {
      if (potionColor.equals("Red")) {
        resJLabel = new JLabel("It is a red negative potion", SwingConstants.CENTER);
        potionIcon1 = new ImageIcon("src/ui/utils/potionRed-.jpg");
      } else if (potionColor.equals("Yellow")) {
        resJLabel = new JLabel("It is a yellow negative potion", SwingConstants.CENTER);
        potionIcon1 = new ImageIcon("src/ui/utils/potionYellow-.jpg");
      } else if (potionColor.equals("Blue")) {
        resJLabel = new JLabel("It is a blue negative potion", SwingConstants.CENTER);
        potionIcon1 = new ImageIcon("src/ui/utils/potionBlue-.jpg");
      }
      resJLabel.setIcon(potionIcon1);
      southPanel.add(resJLabel, BorderLayout.EAST);
      this.setVisible(false);
      this.setVisible(true);
    } else if (newString.equals("0")) {
      resJLabel = new JLabel("It is a neutral potion", SwingConstants.CENTER);
      ImageIcon potionIcon2 = new ImageIcon("src/ui/utils/potiontransparent0.jpg");
      resJLabel.setIcon(potionIcon2);
      southPanel.add(resJLabel, BorderLayout.EAST);
      this.setVisible(false);
      this.setVisible(true);
    } else {
      if (potionColor.equals("Red")) {
        resJLabel = new JLabel("It is a red positive potion", SwingConstants.CENTER);
        potionIcon3 = new ImageIcon("src/ui/utils/potionRed+.jpg");
      } else if (potionColor.equals("Yellow")) {
        resJLabel = new JLabel("It is a yellow positive potion", SwingConstants.CENTER);
        potionIcon3 = new ImageIcon("src/ui/utils/potionYellow+.jpg");
      } else if (potionColor.equals("Blue")) {
        resJLabel = new JLabel("It is a blue positive potion", SwingConstants.CENTER);
        potionIcon3 = new ImageIcon("src/ui/utils/potionBlue+.jpg");
      }
      resJLabel.setIcon(potionIcon3);
      southPanel.add(resJLabel, BorderLayout.EAST);
      this.setVisible(false);
      this.setVisible(true);
    }
  }
}
