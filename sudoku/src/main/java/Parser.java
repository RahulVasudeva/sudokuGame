public class Parser {
    private sudokuGrid sg = new sudokuGrid();

    public void inputHandler(String inInput) {
        if (inInput.equals("start")){

            while(true) {
                sg.printGrid();
                String input = IO.readln("rules to play");
                input.replaceAll("\\s","");
                


            }
            //return;
        }
        else if(inInput.equals("exit")){
            IO.println("Thank you for playing!");
            return;
        }
    }
}
