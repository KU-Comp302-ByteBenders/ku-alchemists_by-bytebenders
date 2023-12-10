package ui;

import game.Board;
import game.Ingredient;
import game.Potion;
import game.Token;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
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
  BoardJFrame boardFrame;
  public static ArrayList<Potion> potionsForFrame;

  ImageIcon potionMinusIcon = new ImageIcon("src/ui/utils/potion-.jpg");
  ImageIcon potionPlusIcon = new ImageIcon("src/ui/utils/potion+.jpg");

  ImageIcon potionIcon1;
  ImageIcon potionIcon2;

  public MakeExperimentJFrame(Board board, BoardJFrame boardFrame) {
    this.board = board;
    this.boardFrame = boardFrame;

    token1 = board.getTokens().get(0);

    potionsForFrame = new ArrayList<Potion>();

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

    JLabel panelLabel = new JLabel("INGREDIENTS");
    panelLabel.setForeground(Color.decode("#A020F0"));
    panelLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel resultLabel = new JLabel("RESULT");
    resultLabel.setForeground(Color.decode("#A020F0"));

    //getting ingredients that token has
    JPanel ingredPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    ingredPanel.setBorder(lineBorder);

    int token1IngredientsNumber = token1.getIngredients().size();

    for (int i = 0; i < token1IngredientsNumber; i++) {
      Ingredient token1Ingredient = token1.getIngredients().get(i);
      int ingID = token1Ingredient.getID();
      ImageIcon ingredientIcon = new ImageIcon("src/ui/utils/ingredient_" + ingID + ".jpg");
      JLabel ingredientLabel = new JLabel(ingredientIcon);

      ingredientModel1.addElement(token1Ingredient.getName());
      ingredientModel2.addElement(token1Ingredient.getName()); //Buradan ilk eklenen element çıkarılmalı

      ingredPanel.add(ingredientLabel);
    }
    ingredPanel.add(panelLabel);

    //to return to the board
    JButton closeExperimentFrame = new JButton("Return the Game");
    closeExperimentFrame.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          closingMenu(MakeExperimentJFrame.this);
        }
      }
    );

    //Create a button to mix ingredients
    JButton mixButton = new JButton("Mix");

    mixButton.setEnabled(false);
    mixButton.addActionListener(
      new ActionListener() {
        String newString = "";

        @Override
        public void actionPerformed(ActionEvent e) {
          newString =
            token1.makeExperiment(
              ingredientComboBox1.getSelectedItem().toString(),
              ingredientComboBox2.getSelectedItem().toString(),
              testResult(testComboBox.getSelectedItem().toString())
            );
          showResult(newString);
        }
      }
    );

    // Create a button to select ingredients

    selectIngredientsButton = new JButton("Select Ingredients");
    selectIngredientsButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (
            ingredientComboBox1.getSelectedItem().toString().equals(ingredientComboBox2.getSelectedItem().toString())
          ) {
            JOptionPane.showMessageDialog(
              MakeExperimentJFrame.this,
              "You cannot choose same ingredient!",
              "Error",
              JOptionPane.ERROR_MESSAGE
            );
            return;
          }
          mixButton.setEnabled(true);
          handleIngredientSelection(
            ingredientComboBox1.getSelectedItem().toString(),
            ingredientComboBox2.getSelectedItem().toString()
          );
        }
      }
    );

    selectPersonButton = new JButton("Select Person");
    selectPersonButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          handleIngredientSelection(testComboBox.getSelectedItem().toString());
        }
      }
    );

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
      }
    );

    ingredientComboBox2.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          updateSelectButtonState();
        }
      }
    );

    //when the frame is closed, the potion is added to the board
    MakeExperimentJFrame.this.addWindowListener(
        (WindowListener) new WindowAdapter() {
          @Override
          public void windowClosed(WindowEvent e) {
            // Add the ImageIcon objects to potionArea
            JLabel potionMinusLabel = new JLabel(potionMinusIcon);
            JLabel potionPlusLabel = new JLabel(potionPlusIcon);

            if (potionsForFrame.size() != 0) {
              if (potionsForFrame.get(0).getName().equals("-")) {
                boardFrame.potionArea.add(potionMinusLabel);
              } else if (potionsForFrame.get(0).getName().equals("+") || potionsForFrame.get(0).getName().equals("0")) {
                boardFrame.potionArea.add(potionPlusLabel);
              }
            }

            // Repaint the potionArea JPanel to show the new images
            boardFrame.setVisible(false);
            boardFrame.setVisible(true);
          }
        }
      );

    westPanel.add(topComboBoxPanel, BorderLayout.NORTH);
    westPanel.add(selectIngredientsButton, BorderLayout.SOUTH);
    westPanel.add(bottomComboBoxPanel, BorderLayout.SOUTH);
    westPanel.add(selectPersonButton, BorderLayout.SOUTH);

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

  //close the frame
  public void closingMenu(JFrame experimentFrame) {
    experimentFrame.dispose();
  }

  // Enable the button only if both ComboBoxes have selections
  private void updateSelectButtonState() {
    selectIngredientsButton.setEnabled(
      ingredientComboBox1.getSelectedItem() != null && ingredientComboBox2.getSelectedItem() != null
    );
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

  //this method is used to show the result of the experiment and close the frame after 7 seconds
  public void showResult(String newString) {
    if (newString.equals("-")) {
      resJLabel = new JLabel("It is a negative potion", SwingConstants.CENTER);
      potionIcon1 = new ImageIcon("src/ui/utils/potionbig2.jpg");
      resJLabel.setIcon(potionIcon1);
      southPanel.add(resJLabel, BorderLayout.EAST);
      this.setVisible(false);
      this.setVisible(true);
    } else {
      resJLabel = new JLabel("It is a non-negative potion", SwingConstants.CENTER);
      ImageIcon potionIcon2 = new ImageIcon("src/ui/utils/potionbig1.jpg");
      resJLabel.setIcon(potionIcon2);
      southPanel.add(resJLabel, BorderLayout.EAST);
      this.setVisible(false);
      this.setVisible(true);
    }
    Timer timer = new Timer(
      7000,
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Close the JFrame
          closeFrame();
        }
      }
    );
    timer.setRepeats(false); // Set to false to run the ActionListener only once
    timer.start();
  }

  //this method is used to close the frame
  public void closeFrame() {
    this.dispose();
  }
}
