package ui;

import game.Board;
import game.Game;
import game.Ingredient;
import ui.interfaces.BoardFrame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ElixirJFrame extends JFrame {

  public ElixirJFrame(Board board) {
    super("Elixir of Insight");
    setLayout(new FlowLayout());

    JPanel ingredPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    // Create the label
    JLabel reorderLabel = new JLabel("REORDER THE TOP3 INGREDIENTS OF THE DECK");

    // Set the position and size of the label
    reorderLabel.setBounds(50, 10, 300, 30);

    // Add the label to the frame
    this.add(reorderLabel);

    // Rest of the code...

    // Create combo boxes
    JComboBox<String> comboBox1 = new JComboBox<>();
    JComboBox<String> comboBox2 = new JComboBox<>();
    JComboBox<String> comboBox3 = new JComboBox<>();

    // Create labels for combo boxes
    JLabel label1 = new JLabel("First Ingredient:");
    JLabel label2 = new JLabel("Second Ingredient:");
    JLabel label3 = new JLabel("Third Ingredient:");

    label1.setPreferredSize(new Dimension(200, 30));
    label2.setPreferredSize(new Dimension(200, 30));
    label3.setPreferredSize(new Dimension(200, 30));

    // Set the position and size of combo boxes
    comboBox1.setBounds(50, 50, 200, 30);
    comboBox2.setBounds(50, 100, 200, 30);
    comboBox3.setBounds(50, 150, 200, 30);

    for (int i = 0; i < 3; i++) {
      Ingredient ing = board.getIngredients().get(i);
      int ingID = ing.getID();
      ImageIcon ingredientIcon = new ImageIcon("src/ui/utils/ingredient_" + ingID + ".jpg");
      JLabel ingredientLabel = new JLabel(ingredientIcon);

      comboBox1.addItem(ing.getName());
      comboBox2.addItem(ing.getName());
      comboBox3.addItem(ing.getName());

      ingredPanel.add(ingredientLabel);
    }

    this.add(ingredPanel);

    // Add labels and combo boxes to the frame
    this.add(label1);
    this.add(comboBox1);
    this.add(label2);
    this.add(comboBox2);
    this.add(label3);
    this.add(comboBox3);

    // Create submit button
    JButton submitButton = new JButton("SUBMIT");
    submitButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
          String firstIngredient = (String) comboBox1.getSelectedItem();
          String secondIngredient = (String) comboBox2.getSelectedItem();
          String thirdIngredient = (String) comboBox3.getSelectedItem();

          Ingredient firstIng = board.getIngredientByName(firstIngredient);
          Ingredient secondIng = board.getIngredientByName(secondIngredient);
          Ingredient thirdIng = board.getIngredientByName(thirdIngredient);

          board.reorderIngredients(firstIng, secondIng, thirdIng);

          ElixirJFrame.this.dispose();

        Game game = Game.getInstance();
        if (!game.isOffline()) {
          game.publishAction("Action "  + "1 " + "ElixirReorder " + firstIngredient + " " + secondIngredient + " " + thirdIngredient);
        }
        }
        
      }
    );
    this.add(submitButton);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - this.getWidth()) / 2;
    int y = (screenSize.height - this.getHeight()) / 2;

    // Set the frame location
    this.setLocation(x - 170, y - 150);

    // Set frame properties
    this.setSize(350, 370);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }
}
