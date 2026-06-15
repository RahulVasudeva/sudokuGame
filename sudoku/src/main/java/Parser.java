import javax.json.Json;
import javax.json.Json.*;
import javax.json.JsonObject;

public class Parser    {
    private sudokuGrid sg = new sudokuGrid();

    public void inputHandler(String inInput) {
        if (inInput.equals("start")){

            while(true) {
                sg.printGrid();
                String input = IO.readln("rules to play");
                JsonObject inputJson = Json .createObjectBuilder().build();
                inputJson = jsonCreater(input);
                System.out.printf("%s",inputJson.getString("instruction"));
                break;

            }
            //return;
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
