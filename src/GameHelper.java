import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GameHelper {

  int[][] gameTable = new int[9][9];
  int[][] gameTable2 = {
      {2, 4, 8, 3, 9, 5, 7, 1, 6},
      {5, 7, 1, 6, 2, 8, 3, 4, 9},
      {9, 3, 6, 7, 4, 1, 5, 8, 2},
      {6, 8, 2, 5, 3, 9, 1, 7, 4},
      {3, 5, 9, 1, 7, 4, 6, 2, 8},
      {7, 1, 4, 8, 6, 2, 9, 5, 3},
      {8, 6, 3, 4, 1, 7, 2, 9, 5},
      {1, 9, 5, 2, 8, 6, 4, 3, 7},
      {4, 2, 7, 9, 5, 3, 8, 6, 1}
  };
  int[][] gameTable3 = {
      {0, 9, 0, 0, 3, 0, 1, 5, 7},
      {2, 1, 8, 7, 0, 5, 0, 0, 6},
      {7, 5, 0, 0, 0, 6, 0, 0, 4},
      {9, 0, 0, 0, 7, 8, 4, 0, 0},
      {1, 8, 5, 4, 2, 0, 7, 6, 0},
      {3, 7, 0, 0, 6, 0, 0, 2, 8},
      {0, 0, 1, 0, 0, 0, 0, 7, 0},
      {0, 0, 9, 0, 5, 7, 0, 0, 1},
      {8, 0, 7, 0, 0, 3, 0, 4, 0}
  };

  public void fillTable() {
    int randomIndex;
    int randomValue;
    List<Integer> listOfValues;
    Random random = new Random();
    for (int i = 0; i < 9; i += 3) {
      for (int j = 0; j < 9; j += 3) {
        listOfValues = arrayOfValues();
        for (int k = 0; k < 3; k++) {
          for (int l = 0; l < 3; l++) {
            randomIndex = random.nextInt(listOfValues.size());
            randomValue = listOfValues.get(randomIndex);
            gameTable[i + k][j + l] = randomValue;
            listOfValues.remove(randomIndex);
          }
        }
      }
    }
  }

  public ArrayList<Integer> arrayOfValues() {
    List<Integer> arrayOfValues = new ArrayList<>();
    for (int i = 1; i < 10; i++) {
      arrayOfValues.add(i);
    }
    return (ArrayList<Integer>) arrayOfValues;
  }

  public boolean checkResult() {
    if (checkRows() && checkColumns() && checkSquares()) {
      return true;
    } else {
      return false;
    }
  }

  private boolean checkColumns() {
    Set<Integer> columnValues = new HashSet<>();
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        columnValues.add(gameTable[i][j]);
      }
      if (columnValues.size() != 9) {
        return false;
      }
      columnValues.clear();
    }
    return true;
  }

  private boolean checkRows() {
    Set<Integer> rowsValues = new HashSet<>();
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        rowsValues.add(gameTable[i][j]);
      }
      if (rowsValues.size() != 9) {
        return false;
      }
      rowsValues.clear();
    }
    return true;
  }

  private boolean checkSquares() {
    Set<Integer> squareValues = new HashSet<>();
    for (int i = 0; i < 9; i += 3) {
      for (int j = 0; j < 9; j += 3) {
        for (int k = 0; k < 3; k++) {
          for (int l = 0; l < 3; l++) {
            squareValues.add(gameTable[i + k][j + l]);
          }
        }
        if (squareValues.size() != 9) {
          return false;
        }
        squareValues.clear();
      }
    }
    return true;
  }

  public void showTable() {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        System.out.print(gameTable[i][j] + " ");
      }
      System.out.println("");
    }
  }

  public void setValues(int table[][]) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        gameTable[i][j] = table[i][j];
      }
    }
  }
}
