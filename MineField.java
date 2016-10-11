
import java.util.Random;
import java.util.Arrays;

class MineField {

  public static int WIDTH = 10;
  public static int HEIGHT = 10;
  public static int MINE_COUNT = 20;
  public static int CELL_COUNT = WIDTH * HEIGHT;

  public static int BLAST = 666;
  public static int SAFE = 777;

  Mine[][] field;

  private Random random;

  public MineField() {
    random = new Random();
    field = new Mine[WIDTH][HEIGHT];

    for (int i = 0; i < WIDTH; i++)
      for (int j = 0; j < HEIGHT; j++)
        field[i][j] = new Mine(Mine.UNINIT, 0);
  }

  public void generate() {
    placeBombs();
    setNumbers();
  }

  private void placeBombs() {

    int placedMines = 0;
    int position = nextRandomNumber() % CELL_COUNT;
    char[] mineBitmap = new char[CELL_COUNT];

    do {
      if (mineBitmap[position] != 'X') {
        field[position/WIDTH][position%HEIGHT] = new Mine(Mine.MINE, 0);
        mineBitmap[position] = 'X';
        placedMines++;
      }
      position += nextRandomNumber();
      position %= CELL_COUNT;
    } while (placedMines != MINE_COUNT);

  }

  private void setNumbers() {
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {

        // skip mine cells
        if (field[i][j].type == Mine.MINE)
          continue;

        // counting mines
        int mineCount = 0;

        // check upper row
        if (i-1 >= 0) {
          if ((j-1 >= 0) && field[i-1][j-1].type == Mine.MINE) mineCount += 1;
          if (field[i-1][j].type == Mine.MINE) mineCount += 1;
          if ((j+1 < WIDTH) && field[i-1][j+1].type == Mine.MINE) mineCount += 1;
        }

        // check middle row
        if ((j-1 >= 0) && field[i][j-1].type == Mine.MINE) mineCount += 1;
        if ((j+1 < WIDTH) && field[i][j+1].type == Mine.MINE) mineCount += 1;

        // check bottom row
        if (i+1 < WIDTH) {
          if ((j-1 >= 0) && field[i+1][j-1].type == Mine.MINE) mineCount += 1;
          if (field[i+1][j].type == Mine.MINE) mineCount += 1;
          if ((j+1 < WIDTH) && field[i+1][j+1].type == Mine.MINE) mineCount += 1;
        }

        // insert empty field if no mines
        if (mineCount == 0)
          field[i][j] = new Mine(Mine.EMPTY, 0);
        else
          field[i][j] = new Mine(Mine.NUMBER, mineCount);
      }
    }
  }

  public boolean open(int row, int col) {
    if (field[row][col].type == Mine.MINE)
      return true;

    recursive_open(row, col);
    return false;
  }

  private void recursive_open(int i, int j) {
    openCell(i, j);

    if (field[i][j].type == Mine.NUMBER) {
      return;
    }
    // check upper row
    if (i-1 >= 0) {
      if ((j-1 >= 0) && field[i-1][j-1].open == false) {
        if (field[i-1][j-1].type == Mine.EMPTY)
          recursive_open(i-1, j-1);
        else if(field[i-1][j-1].type == Mine.NUMBER)
          openCell(i-1, j-1);
      }
      if (field[i-1][j].open == false) {
        if (field[i-1][j].type == Mine.EMPTY)
          recursive_open(i-1, j);
        else if (field[i-1][j].type == Mine.NUMBER)
          openCell(i-1, j);
      }
      if ((j+1 < WIDTH) && field[i-1][j+1].open == false) {
        if (field[i-1][j+1].type == Mine.EMPTY)
          recursive_open(i-1, j+1);
        else if (field[i-1][j+1].type == Mine.NUMBER)
          openCell(i-1, j+1);
      }
    }

    // check middle row
    if ((j-1 >= 0) && field[i][j-1].open == false) {
      if (field[i][j-1].type == Mine.EMPTY)
        recursive_open(i, j-1);
      else if (field[i][j-1].type == Mine.NUMBER)
        openCell(i, j-1);
    }
    if ((j+1 < WIDTH) && field[i][j+1].open == false) {
      if (field[i][j+1].type == Mine.EMPTY)
        recursive_open(i, j+1);
      else if (field[i][j+1].type == Mine.NUMBER)
        openCell(i, j+1);
    }

    // check bottom row
    if (i+1 < WIDTH) {
      if ((j-1 >= 0) && field[i+1][j-1].open == false) {
        if (field[i+1][j-1].type == Mine.EMPTY)
          recursive_open(i+1, j-1);
        else if (field[i+1][j-1].type == Mine.NUMBER)
          openCell(i+1, j-1);
      }
      if (field[i+1][j].open == false) {
        if (field[i+1][j].type == Mine.EMPTY)
          recursive_open(i+1, j);
        else if (field[i+1][j].type == Mine.NUMBER)
          openCell(i+1, j);
      }
      if ((j+1 < WIDTH) && field[i+1][j+1].open == false) {
        if (field[i+1][j+1].type == Mine.EMPTY)
          recursive_open(i+1, j+1);
        else if (field[i+1][j+1].type == Mine.NUMBER)
          openCell(i+1, j+1);
      }
    }
  }

  private void openCell(int row, int col) {
    field[row][col].open = true;
  }

  public void display() {
    for (int i = 0; i < MineField.WIDTH; i++) {
      for (int j = 0; j < MineField.HEIGHT; j++) {
        if (field[i][j].open == true)
          System.out.print(field[i][j].toString() + " ");
        else
          System.out.print("* ");
      }
      System.out.println("");
    }
  }

  private int nextRandomNumber() {
    return random.nextInt(32768);
  }

}
