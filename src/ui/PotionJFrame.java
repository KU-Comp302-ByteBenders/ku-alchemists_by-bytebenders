package ui;

import game.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;



public class PotionJFrame extends JFrame {
    BoardJFrame boardFrame;
    Board board;
    Token token1;
    Border lineBorder;
    
    JPanel southPanel;
    JPanel northPanel;
    JPanel centerPanel;

    JPanel westOfSouthPanel;
    JPanel eastOfSouthPanel;

    JButton closePotionButton;
    JButton sellPotionButton;
    JButton imageButton;
    JLabel imageLabel;
    ImageIcon potionIcon;

    private JComboBox<String> potionComboBox1;
    private DefaultComboBoxModel<String> potionModel;



  public PotionJFrame(Board board, BoardJFrame boardFrame, State state) {
    this.board = board;
    this.boardFrame = boardFrame;
    token1 = board.getTokens().get(0);

    potionModel = new DefaultComboBoxModel<>();
    potionComboBox1 = new JComboBox<>(potionModel);
    
    this.setSize(1200, 360);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setResizable(false);

    lineBorder = new LineBorder(new Color(1, 50, 32), 1);

    // Create a panel with FlowLayout for the north half
    northPanel = new JPanel();
    northPanel.setLayout(new FlowLayout());
    JLabel northLabel = new JLabel("SELL POTION");
    northLabel.setFont(new Font("Serif", Font.BOLD, 30));
    northLabel.setForeground(Color.BLUE);
    northPanel.add(northLabel);

    // Create a panel with BorderLayout for the south half
    southPanel = new JPanel();
    southPanel.setLayout(new BorderLayout());

    centerPanel = new JPanel(new FlowLayout());
    westOfSouthPanel = new JPanel(new BorderLayout());
    eastOfSouthPanel = new JPanel(new BorderLayout());

    northPanel.setPreferredSize(new Dimension(1200, 80));
    centerPanel.setPreferredSize(new Dimension(1200, 180));
    southPanel.setPreferredSize(new Dimension(1200, 100));

    westOfSouthPanel.setPreferredSize(new Dimension(200, 50));
    eastOfSouthPanel.setPreferredSize(new Dimension(200, 50));
    
    potionImageGenerator(token1);//can create with whole token

    this.add(southPanel, BorderLayout.SOUTH);
    this.add(northPanel, BorderLayout.NORTH);
    this.add(centerPanel, BorderLayout.CENTER);
    westOfSouthPanel.setBorder(lineBorder);
    eastOfSouthPanel.setBorder(lineBorder);
    northPanel.setBorder(lineBorder);
    southPanel.setBorder(lineBorder);

    // Add the north and south panels to the main container

    //closing PotionJFrame
    closePotionButton = new JButton("Return Board");
    closePotionButton.setPreferredSize(new Dimension(150, 30));
    closePotionButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          PotionJFrame.this.dispose();
        }
      }
    ); 
    
    //selling potion if you have any
    sellPotionButton = new JButton("SELL POTION");
    sellPotionButton.setEnabled(false);
    sellPotionButton.setPreferredSize(new Dimension(150, 30));
    sellPotionButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

          if(potionComboBox1.getItemCount() == 0){//checking whether you have any potion to sell
            JOptionPane.showMessageDialog(
              PotionJFrame.this,
              "You don't have any potion to sell.",
              "Error",
              JOptionPane.ERROR_MESSAGE 
            );PotionJFrame.this.dispose();
            return;
          }
          String typeOfPotion = potionComboBox1.getSelectedItem().toString();//getting the color and name of potion
          
          String signOfPotion = typeOfPotion.substring(typeOfPotion.length() - 1);//getting the sign of potion(name)
          
          token1.sellPotion(signOfPotion);
          JOptionPane.showMessageDialog(
              PotionJFrame.this,
              "Congratulations! Your gold balance increased to " + token1.getGoldBalance() + ".",
              "Success",
              JOptionPane.INFORMATION_MESSAGE
            );
          boardFrame.removeMiniPotionImage("src/ui/utils/mini_potion"+ typeOfPotion + ".jpg");//deleting from boardFrame
          Game.controlRoundAction(boardFrame, state, true);
          closingMenu(PotionJFrame.this);
        }
      }
    );
    
  

    JPanel potionComboBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    potionComboBoxPanel.add(potionComboBox1);
    sellPotionButton.setEnabled(true);
    

    southPanel.add(eastOfSouthPanel, BorderLayout.EAST);
    southPanel.add(westOfSouthPanel, BorderLayout.WEST);
    southPanel.add(eastOfSouthPanel, BorderLayout.EAST);
    eastOfSouthPanel.add(sellPotionButton, BorderLayout.CENTER);
    westOfSouthPanel.add(closePotionButton, BorderLayout.CENTER);
    southPanel.add(potionComboBoxPanel, BorderLayout.CENTER);


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - this.getWidth()) / 2;
    int y = (screenSize.height - this.getHeight()) / 2;

    // Set the frame location
    this.setLocation(x, y);

    // Make the frame visible
    this.setVisible(true);
  }

  public void potionImageGenerator(Token token){
    int potionNumber = token.getPotions().size();
    for (int i = 0; i < potionNumber; i++) {
      Potion potion = token.getPotions().get(i);
      JLabel imageLabel = new JLabel();

      potionIcon = new ImageIcon("src/ui/utils/potion"+ potion.getPotionColor() + potion.getName() + ".jpg");
      potionModel.addElement(token.getPotions().get(i).getPotionColor()+token.getPotions().get(i).getName()); //giving information to combobox
      imageLabel.setIcon(potionIcon);
      centerPanel.add(imageLabel);
      
        }
  }

  public void closingMenu(JFrame potionFrame) {
    boardFrame.updateTokensGoldLabel();
    potionFrame.dispose();
  }

  
}
