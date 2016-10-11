
import java.util.Scanner;
import java.io.IOException;

class CommandLineInterface {

  private Scanner scanner;

  public CommandLineInterface() {
    scanner = new Scanner(System.in);
  }

  public Command input() throws IOException {
    // System.out.print("> ");

    String word = scanner.next();

    if (word.equals("x"))
      return (new Command(Command.EXIT, 0, 0));
    else if (word.equals("r"))
      return (new Command(Command.RESET, 0, 0));

    int row = Integer.parseInt(word);
    int col = scanner.nextInt();
    return (new Command(Command.POSITION, row, col));
  }

}
