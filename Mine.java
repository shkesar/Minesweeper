
class Mine {

  public int type;
  public int mineCount;
  public boolean open = false;

  public static int NUMBER = 0;
  public static int MINE = 1;
  public static int EMPTY = 2;
  public static int UNINIT = 3;

  public Mine(int _type, int _mineCount) {
    type = _type;
    mineCount = _mineCount;
  }

  @Override
  public String toString() {
    if (type == NUMBER) return String.valueOf(mineCount);
    else if (type == MINE) return "X";
    return "_";
  }

}
