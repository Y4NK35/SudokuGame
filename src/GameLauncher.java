import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

public class GameLauncher {

  GameHelper gameHelper = new GameHelper();
  JFrame gameFrame = new JFrame("SUDOKU GAME by Y4NK35");
  JPanel gameTablePanel;
  JFormattedTextField[][] gameTable;

  public void start() {
    gameHelper.setValues(gameHelper.gameTable3);
    createGUI();
  }

  private void createGUI() {
    JButton restartGame = new JButton("RESTART GAME");
    restartGame.addActionListener(e -> restartGame());
    JButton check = new JButton("CHECK");
    check.addActionListener(e -> getResult(checkResult()));
    gameTable = new JFormattedTextField[9][9];
    gameTablePanel = new JPanel();
    gameTablePanel.setLayout(new GridLayout(9, 9));
    gameTablePanel.setBorder(BorderFactory.createLineBorder(Color.black));
    createGameTable();
    fillGameTable();
    gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    gameFrame.getContentPane().add(BorderLayout.CENTER, gameTablePanel);
    gameFrame.getContentPane().add(BorderLayout.NORTH, restartGame);
    gameFrame.getContentPane().add(BorderLayout.SOUTH, check);
    gameFrame.setSize(500, 500);
    gameFrame.setVisible(true);
  }

  private void createGameTable() {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        Font font = new Font("SANS_SERIF", Font.BOLD, 20);
        JFormattedTextField textField = new JFormattedTextField(createFormatter("#"));
        textField.setFont(font);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.addKeyListener(new KeyAdapter() {
          public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!((c >= '1') && (c <= '9') ||
                (c == KeyEvent.VK_BACK_SPACE) ||
                (c == KeyEvent.VK_DELETE))) {
              e.consume();
            }
          }
        });
        textField = createBorder(textField, i, j);
        gameTable[i][j] = textField;
        gameTablePanel.add(textField);
      }
    }
  }

  private void fillGameTable() {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        JFormattedTextField textField = gameTable[i][j];
        textField = setTextFieldValue(textField, i, j);
        gameTable[i][j] = textField;
        gameTablePanel.add(textField);
      }
    }
  }

  private JFormattedTextField createBorder(JFormattedTextField textField, int i, int j) {
    if (i == 2 && j == 2 || i == 5 && j == 5 || i == 2 && j == 5 || i == 5 && j == 2) {
      textField.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
    } else if (i == 2 || i == 5) {
      textField.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.black));
    } else if (j == 2 || j == 5) {
      textField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, Color.black));
    } else {
      textField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }
    return textField;
  }

  private MaskFormatter createFormatter(String s) {
    MaskFormatter formatter = null;
    try {
      formatter = new MaskFormatter(s);
    } catch (java.text.ParseException exc) {
      System.err.println("formatter is bad: " + exc.getMessage());
      System.exit(-1);
    }
    return formatter;
  }

  private JFormattedTextField setTextFieldValue(JFormattedTextField textField, int i, int j) {
    if (gameHelper.gameTable[i][j] != 0) {
      textField.setValue((gameHelper.gameTable[i][j]));
      textField.setEditable(false);
      textField.setBackground(Color.GREEN);
    } else {
      textField.setEditable(true);
      textField.setValue("");
    }
    return textField;
  }

  private void restartGame() {
    gameHelper.setValues(gameHelper.gameTable3);
    fillGameTable();
  }

  private boolean checkResult() {
    gameHelper.setValues(createArrayFromTextFields());
    return gameHelper.checkResult();

  }

  private int[][] createArrayFromTextFields() {
    int[][] arrayFromTExtField = new int[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (gameTable[i][j].getValue().toString().equals("")) {
          arrayFromTExtField[i][j] = 0;
        } else {
          arrayFromTExtField[i][j] = Integer.parseInt(gameTable[i][j].getValue().toString());
        }
      }
    }
    return arrayFromTExtField;
  }

  private void getResult(boolean result) {
    if (result) {
      JOptionPane.showMessageDialog(gameFrame, "You solve it! Congratulations!!! :)");
    } else {
      JOptionPane.showMessageDialog(gameFrame, "This solution is not correct. ");
    }

  }
}
