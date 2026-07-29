public class main {
    public static void main(String[] args){
        //toDo: add parser for this

        String initialcmd = IO.readln("""
                Welcome to Sudoku!
                Enter start to play a game and exit to close the game.
                """);
        if (initialcmd.equals("start")) {
            IO.println("Creating the puzzle...");
            Parser parse = new Parser();
            parse.inputHandler();
        }



    }

}
