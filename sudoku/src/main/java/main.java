public class main {
    public static void main(String[] args){
        //toDo: add parser for this
//       sudokuGrid grid= new sudokuGrid();
//       grid.setMatrix(0,1,5);
//       grid.printGrid();
//
////       if(grid.checker(0,2,2).equals("rowErr"))
////        {
////           System.out.println("A row cannot have two same numbers");
////        }
////       else if(grid.checker(0,2,2).equals("columnErr")){
////           System.out.println("A column cannot have two same numbers");
////       }
////       else if(grid.checker(0,2,2).equals("valid")){
////           grid.setMatrix(0,2,2);
////           grid.printGrid();
////       }
        String initialcmd = IO.readln("""
                Welcome to Sudoku!
                Enter start to play a game and exit to close the game.
                """);

        Parser parse = new Parser();
        parse.inputHandler(initialcmd);


    }

}
