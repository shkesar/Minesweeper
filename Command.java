
final class Command {
  // Input command types
  public static int POSITION = 0;
  public static int RESET = 1;
  public static int EXIT = 2;

  public Position position;
  public int type;

  public Command(int _type, int row, int col) {
    type = _type;
    position = new Position(row, col);
  }
}
