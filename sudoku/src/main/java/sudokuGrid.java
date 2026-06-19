import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// deals with printing the sudoku grid with the values
public class sudokuGrid {
    private int[][] matrix=new int[9][9];
    sudokuGrid() {
        for(int i=0;i<9;i++) {
            for (int j = 0; j < 9; j++) {
                matrix[i][j] = -1;
            }
        }
        fillMatrix();
    }

    public void setMatrix(int i,int j,int value) {
        matrix[i][j]=value;
    }

    public int[][] getMatrix(){
        return matrix;
    }


    public String checker(int i,int j, int value) {
        //toDO: complete the third rule
        //toDO: add multithreading for the linear search

        for (int k=0;k<9;k++){
            if (matrix[i][k]==value){
                return "rowErr";
            }
            else if (matrix[k][j]==value){
                return "columnErr";
            }
        }
        int[][] subMatrix = new int[3][3];
        subMatrix=subMatrixChooser(i,j);
        for (int k=0;k<3;k++){
            for(int l=0;l<3;l++){
                if(subMatrix[k][l]==value){
                    return "gridErr";
                }
            }
        }
        return "valid";

    }

    private int[][] subMatrixChooser(int i, int j) {
        int[][] subMatrix = new int[3][3];

        if(i==0||i==1||i==2){
            if(j==0 || j==1 || j==2){
               return subMatrix=subMatrixCreator(0,0);
            }
            else if(j==3 || j==4 || j==5){
                return subMatrix=subMatrixCreator(0,3);
            }
            else if(j==6 || j==7 || j==8){
                return subMatrix=subMatrixCreator(0,6);

            }
        }

        else if(i==3||i==4||i==5){
            if(j==0 || j==1 || j==2){
                return subMatrix=subMatrixCreator(3,0);

            }
            else if(j==3 || j==4 || j==5){
                return subMatrix=subMatrixCreator(3,3);

            }
            else if(j==6 || j==7 || j==8){
                return subMatrix=subMatrixCreator(3,6);

            }
        }

        else if(i==6||i==7||i==8){
            if(j==0 || j==1 || j==2){
                return subMatrix=subMatrixCreator(6,0);
            }
            else if(j==3 || j==4 || j==5){
                return subMatrix=subMatrixCreator(6,3);

            }
            else if(j==6 || j==7 || j==8){
                return subMatrix=subMatrixCreator(6,6);

            }
        }
        return subMatrix;
    }

    private int[][] subMatrixCreator(int i,int j){
        int[][] subMatrix = new int[3][3];
        for(int k=0;k<3;k++){
            for(int l=0;l<3;l++){
                subMatrix[k][l]=matrix[i][j];
                j++;
            }
            i++;
            j=j-3;
        }
        return subMatrix;
    }


    //fills the matrix with random numbers from 0-9 while following all of sudoku rules
    //This is used to create a puzzle for the user to solve
    //Todo: add the third rule so that the printing is correct
    private void fillMatrix() {
        try {
            var rand = new Random();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    var valuesToTest = new ArrayList<Integer>();
                    valuesToTest.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
                    for (int k = 0; k < 9; k++) {
                        int index = rand.nextInt(0, valuesToTest.size());

                        if (checker(i, j, valuesToTest.get(index)).equals("valid")) {
                            matrix[i][j] = valuesToTest.get(index);
                            break;
                        } else {
                            valuesToTest.remove(index);
                        }

                    }
                    if (matrix[i][j] == -1) {
                        for(int a=0;a<9;a++){
                            for(int b=0;b<9;b++){
                                matrix[a][b]= -1;
                            }
                        }
                        fillMatrix();
                    }
                }
            }
        }catch (StackOverflowError e) {
            IO.println("Fatal crash, Restart the program");
            //fillMatrix();
        }
    }


    public void printGrid(){
        System.out.println("\n    0    1     2     3    4    5     6    7    8");
        System.out.println("  =================================================");

        for (int i =0;i<9;i++) {
            System.out.print(i+" ");
            for (int j = 0; j < 9; j++) {
                if (j==3 || j==6){
                    //-1 is the value in the matrix for empty spots
                    if (matrix[i][j] == -1) {
                        System.out.print("||    ");
                    } else {
                        System.out.printf("|| %d  ", matrix[i][j]);
                    }
                }
                else {
                    if (matrix[i][j] == -1) {
                        System.out.print("|    ");
                    } else {
                        System.out.printf("| %d  ", matrix[i][j]);
                    }
                }
            }

            if(i==2 || i==5 || i==8){
                System.out.println("|| "+"\n  =================================================");
            }

            else {
                System.out.println("|| "+"\n  -------------------------------------------------");
            }
        }
    }
}
