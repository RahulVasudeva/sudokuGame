import javax.json.Json;
import javax.json.JsonObject;

public class Parser    {
    private sudokuGrid sg = new sudokuGrid();

    private int iIndex;
    private int jIndex;
    private int value;

    //created to make the execution wait so the user can read the error before the grid gets printed again
    //This also puts the catch block for Thread.sleep() at one place so you don't have to do nested try catch
    private void wait(int i){
        try {
            Thread.sleep(i*1000);
        } catch (InterruptedException e) {
        }
    }

    public void inputHandler() {
        try {
            while(true) {
                //ToDO: add checks for errors
                sg.printGrid();
                String input = IO.readln("rules to play ");
                String[] parts = input.split("\s+");
                if(input.equals("q")) {
                    IO.println("Thank you for playing");
                    break;
                }
                else if(parts[0].equals("p")){
                    iIndex=Integer.parseInt(parts[1]);
                    jIndex=Integer.parseInt(parts[2]);
                    value=Integer.parseInt(parts[3]);
                    play(iIndex,jIndex,value);
                }
                else if(parts[0].equals("d")){
                    iIndex=Integer.parseInt(parts[1]);
                    jIndex=Integer.parseInt(parts[2]);
                    deleteVal(iIndex,jIndex);
                }
                else throw new RuntimeException();
            }
    }catch(RuntimeException e) {
        IO.println("""
                \n\u001B[31mPlease check the syntax for your command.
                Type help for the command list with examples\u001B[0m""");
        wait(1);
        inputHandler();
        }
    }

    private void deleteVal(int iIndex1,int jIndex1) {
        sg.setMatrix(iIndex1,jIndex1,-1);
    }

    private void play(int iIndex1,int jIndex1,int value1) {
        try {
            //sends the x,y index and the value to the checker function
            //to see that the value inputted by the user is correct or not
            String status = sg.checker(iIndex1,jIndex1,value1);

            switch (status) {
                case "columnErr" -> {
                    IO.println("\n\u001B[31m The column cannot have repeated values \u001B[0m");
                    wait(1);
                }

                case "rowErr" -> {
                    IO.println("\n\u001B[31mThe row cannot have repeated values\u001B[0m");
                    wait(1);
                }

                case "valid" -> {
                    sg.setMatrix(iIndex1, jIndex1, value1);
                    sg.printGrid();
                }
            }

        } catch (Exception e) {
            IO.println("error");
        }
    }


}
