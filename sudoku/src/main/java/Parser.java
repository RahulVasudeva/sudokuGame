import javax.json.Json;
import javax.json.Json.*;
import javax.json.JsonObject;

public class Parser    {
    private sudokuGrid sg = new sudokuGrid();

    public void inputHandler(String inInput) {
        if (inInput.equals("start")){

            while(true) {
                //ToDO: add checks for errors
                sg.printGrid();
                String input = IO.readln("rules to play");

                if(input.equals("q")) {
                    IO.println("Thank you for playing");
                    break;
                }

                JsonObject inputJson = Json.createObjectBuilder().build();
                inputJson = jsonCreater(input);
                IO.println(inputJson.getJsonString("instruction"));
                if(inputJson.getString("instruction").equals("p")){
                    //sends the x,y index and the value to the checker function
                    //to see that the value inputted by the user is correct or not
                    String status = sg.checker(inputJson.getInt("x-axis"),
                                                inputJson.getInt("y-axis"),
                                                inputJson.getInt("value"));

                    switch (status) {
                        case "columnErr" -> IO.println("\n\u001B[31m The column cannot have repeated values \u001B[0m");

                        case "rowErr" -> IO.println("\n\u001B[31mThe row cannot have repeated values\u001B[0m");

                        case "valid" -> {
                            sg.setMatrix(inputJson.getInt("x-axis"),
                                    inputJson.getInt("y-axis"),
                                    inputJson.getInt("value"));
                            sg.printGrid();
                        }
                    }
                }


            }
        }
        else if(inInput.equals("exit")){
            IO.println("Thank you for playing!");
            return;
        }
    }

    private JsonObject jsonCreater(String str) {
        //removes all the white space
        str.replaceAll("\\s","");
        //ToDo: add a check to see the length is 4 exactly

        JsonObject obj = Json.createObjectBuilder()
                .add("instruction",str.substring(0,1))
                .add("x-axis",Integer.parseInt(str.substring(1,2)))
                .add("y-axis",Integer.parseInt(str.substring(2,3)))
                .add("value",Integer.parseInt(str.substring(3)))
                .build();

        return obj;
    }

}
