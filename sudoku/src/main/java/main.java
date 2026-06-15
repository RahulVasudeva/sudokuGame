public class main {
    public static void main(String[] args){
        //toDo: add parser for this

        String initialcmd = IO.readln("""
                Welcome to Sudoku!
                Enter start to play a game and exit to close the game.
                """);

        Parser parse = new Parser();
        parse.inputHandler(initialcmd);


    }

}
