public class main {
    public static void main(String[] args){
        //toDo: add parser for this

        String initialcmd = IO.readln("""
                Welcome to Sudoku!
                Enter start to play a game!
                
                Controls:
                Write "p [x-index] [y-index] [value] to insert a number"
                (eg: p 0 0 4 will insert 4 at index 0 0)
                
                Write "d [x-index] [y-index] to delete an inserted number"
                (eg d 0 0 will delete the value at index 0 0)
                
                Enter "q" to quit the ongoing session
                """);
        if (initialcmd.equals("start")) {
            IO.println("Creating the puzzle...");
            Parser parse = new Parser();
            parse.inputHandler();
        }



    }

}
