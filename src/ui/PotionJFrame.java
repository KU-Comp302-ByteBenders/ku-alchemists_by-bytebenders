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

    JButton usePotionButton;
    JButton sellPotionButton;
    JButton imageButton;


  public PotionJFrame(Board board, BoardJFrame boardJFrame) {
    this.board = board;
    this.boardFrame = boardFrame;
    token1 = board.getTokens().get(0);

    
    this.setSize(1200, 360);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setResizable(false);

    lineBorder = new LineBorder(new Color(1, 50, 32), 1);

    // Create a panel with FlowLayout for the north half
    northPanel = new JPanel();
    northPanel.setLayout(new FlowLayout());
    JLabel northLabel = new JLabel("USE & SELL POTION");
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


    usePotionButton = new JButton("USE POTION");
    usePotionButton.setPreferredSize(new Dimension(150, 30));
    usePotionButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          //
        }
      }
    ); 

    sellPotionButton = new JButton("SELL POTION");
    sellPotionButton.setPreferredSize(new Dimension(150, 30));
    sellPotionButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          //
        }
      }
    );

    southPanel.add(eastOfSouthPanel, BorderLayout.EAST);
    southPanel.add(westOfSouthPanel, BorderLayout.WEST);
    southPanel.add(eastOfSouthPanel, BorderLayout.EAST);
    eastOfSouthPanel.add(sellPotionButton, BorderLayout.CENTER);
    westOfSouthPanel.add(usePotionButton, BorderLayout.CENTER);


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
      imageButton = new JButton();

      ImageIcon potionIcon = new ImageIcon("src/ui/utils/potionbig" + potion.getName() + ".jpg");
      imageButton.setIcon(potionIcon);
      centerPanel.add(imageButton);
        }
  }
}