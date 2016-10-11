
class Runner {

    static boolean exitGame = false;

    public static void main(String args[]) {
        while (true) {
          loop();
        }
    }

    public static void loop() {
        MineField mineField = new MineField();
        mineField.generate();
        CommandLineInterface cli = new CommandLineInterface();
        boolean blast, restart, exit;
        blast = restart = exit = false;

        do {
          mineField.displayWithBorder();
          Command cmd;

          try {
            cmd = cli.input();
          } catch (Exception e) {
            System.out.println("Wrong command!");
            continue;
          }

          if (cmd.type == Command.POSITION) {
              blast = mineField.open(cmd.position.row, cmd.position.col);
          }
          else if (cmd.type == Command.RESET) {
              restart = true;
          }
          else if (cmd.type == Command.EXIT) {
              exit = true;
          }
        } while(!blast && !restart && !exit);

        if (restart) {
          return;
        }
        if (exit) {
          System.exit(0);
        }
        if (blast) {
          System.out.println("You opened a mine! Game Over!");
          return;
        }

        System.out.println("You won!");
    }

}
