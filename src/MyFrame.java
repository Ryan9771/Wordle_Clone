import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class MyFrame extends JFrame implements KeyListener {

  private final Game game;
  private int rowProg;
  private int currAttempt;
  private final JButton[] letters = new JButton[30];
  private final JLabel revealWord = new JLabel();


  public MyFrame() {
    ImageIcon logo = new ImageIcon("logo.png");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Wordle");
    setResizable(false);
    setSize(500,700);
    setLocationRelativeTo(null);
    setIconImage(logo.getImage());
    getContentPane().setBackground(new Color(0x292525));
    setLayout(null);
    // pack();

    // Label title
    JLabel header = new JLabel();
    header.setText("Wordle");
    header.setFont(new Font("Calibri", Font.BOLD, 40));
    header.setForeground(Color.WHITE);
    header.setBounds(170, 10, 500, 50);

    // Game
    this.game = new Game();
    this.rowProg = 0;
    this.currAttempt = 0;

    // Label game over word
    revealWord.setText("The word was : " + game.getWord());
    revealWord.setFont(new Font("Calibri", Font.BOLD, 20));
    revealWord.setForeground(new Color(0x10D445));
    revealWord.setBounds(130, 480, 400, 100);
    revealWord.setVisible(false);


    // Panel for the words
    JPanel panel = new JPanel();
    panel.setBounds(120,162,250,300);
    panel.setLayout(new GridLayout(6,5,10,10));
    panel.setBackground(new Color(0x292525));

    // Buttons for the Panel

    for (int i = 0; i < 30; i++) {
      JButton tempButton = new JButton();
      tempButton.setBorder(new LineBorder(Color.GRAY));
      tempButton.setFocusable(false);
      tempButton.setOpaque(false);
      tempButton.setForeground(Color.white);
      tempButton.setBackground(Color.black);
      tempButton.setFont(new Font("Calibri", Font.BOLD, 15));
      letters[i] = tempButton;
      panel.add(tempButton);
    }

    // Adding to Frame
    this.add(header);
    this.add(panel);
    setVisible(true);
    this.add(revealWord);

    // Adding key listener
    this.addKeyListener(this);

  }

  // **** KEY LISTENER ****

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

    int code = e.getKeyCode();

    if (code >= 65 && code <= 90 && rowProg < 5) { // checking for valid letters
      letters[nextIndex()].setText(Character.toString(code));
      rowProg++;
    } else {
      switch (code) {
        case 8 -> { // Backspace
          if (rowProg != 0) {
            rowProg--;
            letters[nextIndex()].setText("");
          }
        }

        case 10 -> { // Enter
          if (rowProg == 5) {

            // Create a string out of chars entered
            StringBuilder input = new StringBuilder();
            int startIndex = nextIndex() - 5;
            for (int i = startIndex; i < nextIndex(); i++) {
              input.append(letters[i].getText());
            }

            // Return indexes in and out of place

            List<Integer> indexesInPlace = game.returnGreen(input.toString());
            List<Integer> indexesOutPlace = game.returnOrange(input.toString());

            // Set colours based on these indexes

            for (int i = startIndex; i < nextIndex(); i++) {
              letters[i].setOpaque(true);
              if (indexesInPlace.contains(i - startIndex)) {
                letters[i].setBackground(new Color(0x30a067)); // green ish
              } else if (indexesOutPlace.contains(i - startIndex)) {
                letters[i].setBackground(new Color(0xcc8d3d)); // orange-ish yellow
              } else {
                letters[i].setBackground(new Color(0x434545)); // greyish
              }
            }
            if (currAttempt < 5 && !game.isGameOver()) {
              currAttempt++;
              rowProg = 0;
            } else {
              revealWord.setVisible(true);
            }
          }
        }

        default -> {}
      }
    }
  }

  private int nextIndex() {
    return currAttempt * 5 + rowProg;
  }


}
