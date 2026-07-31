package sudoku;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// deals with creating the sudoku puzzle and printing it
public class sudokuGrid {
    private int[][] matrix=new int[9][9];
    private int[][] matrixClone=new int[9][9];
    private int[][] filledMatrix=new int[9][9];
    private ArrayList<String> cluesIndexArr=new ArrayList<String>();
    sudokuGrid() {
        fillMatrixDefault();
        fillMatrix();
        removeElem();
        cluesIndexFinder();
    }

    public void setMatrix(int i,int j,int value) {matrix[i][j]=value;}
    public int[][] getMatrix(){return matrix;}

    private void fillMatrixDefault() {
        for(int a=0;a<9;a++){
            for(int b=0;b<9;b++){
                matrix[a][b]= -1;
            }
        }
    }

    private void cluesIndexFinder(){
        for(int a=0;a<9;a++){
            for(int b=0;b<9;b++){
                if (matrix[a][b]!= -1){
                    String k = String.valueOf(a)+String.valueOf(b);
                    cluesIndexArr.add(k);
                }
            }
        }
    }

    public void finishGame(){
        if(Arrays.deepEquals(matrix,filledMatrix)){
            IO.println("\u001B[32m Congratulations on completing the puzzle!! \u001B[0m");
        }
        else {
            String status = IO.readln("It looks like you still need to complete the puzzle.\n Do you wish to see the result? (y/n)");
            String statusL=status.toLowerCase();
            if(statusL.equals("y")){
                printGrid(filledMatrix);
                return;
            }
            else if(statusL.equals("n")) {
                return;
            }
            else {
                IO.println("Please use the correct format: y or n");
                finishGame();
            }
        }

    }

    //checks if the index given by the user is not already filled with clues provided by the software
    //couldn't add in checker as checker is used by the fillmatrix
    public boolean indexValidityChecker(int i,int j) {
        for(int k=0;k<cluesIndexArr.size();k++)
         if (cluesIndexArr.get(k).equals(String.valueOf(i)+String.valueOf(j))){
            return false;
        }
        return true;

    }

    public String checker(int i,int j, int value) {
        //toDO: add multithreading for the linear search

        for (int k=0;k<9;k++){
            if (matrix[i][k]==value){
                return "rowErr";
            }
            else if (matrix[k][j]==value){
                return "columnErr";
            }
        }
        //gives value 0-8 for each submatrix
        int subMatrixIndex = (i/3)*3+j/3;
        if (subMatrixCheck(subMatrixIndex,value)){
            return "gridErr";
        }
        return "valid";

    }

    //Checks the 3x3 rule for the sudoku
    private boolean subMatrixCheck(int index,int value){
        switch (index){
            case 0 -> {
                for(int i =0;i<3;i++){
                    for (int j=0;j<3;j++){
                        if(matrix[i][j]==value) return true;
                    }
                }
            }
            case 1 -> {
                for(int i =0;i<3;i++){
                    for (int j=3;j<6;j++){
                        if(matrix[i][j]==value) return true;
                    }
                }
            }
            case 2 -> {
                for(int i =0;i<3;i++){
                    for (int j=6;j<9;j++){
                        if(matrix[i][j]==value) return true;
                    }
                }
            }
            case 3 -> {
                for(int i =3;i<6;i++){
                    for (int j=0;j<3;j++){
                        if(matrix[i][j]==value) return true;
                    }
                }
            }
            case 4 -> {
                for(int i =3;i<6;i++){
                    for (int j=3;j<6;j++){
                        if(matrix[i][j]==value) return true;
                    }
                }
            }
            case 5 -> {
                for(int i =3;i<6;i++){
                    for (int j=6;j<9;j++){
                        if(matrix[i][j]==value) return true;
                    }
                }
            }
            case 6 -> {
                for(int i =6;i<9;i++){
                    for (int j=0;j<3;j++){
                        if(matrix[i][j]==value) return true;
                    }
                }
            }
            case 7 -> {
                for(int i =6;i<9;i++){
                    for (int j=3;j<6;j++){
                        if(matrix[i][j]==value) return true;
                    }
                }
            }
            case 8 -> {
                for(int i =6;i<9;i++){
                    for (int j=6;j<9;j++){
                        if(matrix[i][j]==value) return true;
                    }
                }
            }

        }
        return false;
    }

    //fills the matrix with random numbers from 0-9 while following all of sudoku rules
    //This is used to create a puzzle for the user to solve
    private void fillMatrix() {
        try {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if(matrix[i][j]==-1) {
                    var valuesToTest = new ArrayList<Integer>();
                    valuesToTest.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
                    Collections.shuffle(valuesToTest);

                    for (int k = 0; k < 9; k++) {
                        if (checker(i, j, valuesToTest.getFirst()).equals("valid")) {
                            matrix[i][j] = valuesToTest.getFirst();
                            break;
                        } else {
                            valuesToTest.removeFirst();
                        }
                    }
                    }
                    if (matrix[i][j] == -1) {
                        fillMatrixDefault();
                        fillMatrix();
                    }
                }
            }
        }catch (StackOverflowError e) {
            IO.println("Fatal crash, Restart the program");
        }
    }


    public void printGrid(int[][] matrix1){
        //clears the terminal
        System.out.print("\033[H\033[2J");

        System.out.println("\n    0    1     2     3    4    5     6    7    8");
        System.out.println("  =================================================");

        for (int i =0;i<9;i++) {
            System.out.print(i+" ");
            for (int j = 0; j < 9; j++) {
                if (j==3 || j==6){
                    //-1 is the value in the matrix for empty spots
                    if (matrix1[i][j] == -1) {
                        System.out.print("!!    ");
                    } else {
                        if (cluesIndexArr.contains(Integer.toString(i)+Integer.toString(j))){
                            System.out.printf("!! \u001B[31m%d\u001B[0m  ", matrix[i][j]);
                        }
                        else {
                            System.out.printf("!! %d  ", matrix1[i][j]);
                        }                    }
                }
                else {
                    if (matrix1[i][j] == -1) {
                        System.out.print("|    ");
                    } else {
                        if (cluesIndexArr.contains(Integer.toString(i)+Integer.toString(j))){
                            System.out.printf("| \u001B[31m%d\u001B[0m  ", matrix1[i][j]);
                        }
                        else {
                            System.out.printf("| %d  ", matrix1[i][j]);
                        }
                    }
                }
            }

            if(i==2 || i==5 || i==8){
                System.out.println("!! "+"\n  =================================================");
            }

            else {
                System.out.println("!! "+"\n  -------------------------------------------------");
            }
        }
    }

    private void removeElem () {
        int count=0;
        var indexList= new ArrayList<String>();

        for(int i=0;i<89;i++){
            //formats i to 00,01 and such so that we can have both index i and j
            if(i%10==9) continue;
            String k = new DecimalFormat("00").format(i);
            indexList.add(k);
        }
        //shuffles the list
        Collections.shuffle(indexList);
        int previousValue=0;
        copyMatrix(matrix,matrixClone);
        copyMatrix(matrix,filledMatrix);
        while(!(indexList.isEmpty())){
            int i= Integer.parseInt(indexList.get(0).substring(0,1));
            int j= Integer.parseInt(indexList.get(0).substring(1));
            previousValue=matrix[i][j];
            indexList.removeFirst();
            matrix[i][j]=-1;
            copyMatrix(matrix,matrixClone);
            if(solver(i,j,previousValue,matrixClone)==false){
                count++;
                matrix[i][j] = previousValue;
            }
        }
        if(count>35){
            //IO.println(count);
            fillMatrixDefault();
            fillMatrix();
            removeElem();
        }


    }

    private boolean solver(int indexI, int indexJ,int value,int[][] matrix1){

        for(int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                if(matrix1[i][j]==-1){
                    var values = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
                    if(i==indexI && j==indexJ) {
                        values.remove(values.indexOf(value));
                    }
                    int size = values.size();
                    for(int k=0;k<size;k++){
                        if(checker(i,j,values.getFirst()).equals("valid")){
                            matrix1[i][j]=values.getFirst();
                            break;
                        }
                        else {
                            values.removeFirst();
                        }
                    }

                }
            }
        }

        if(matrix1[indexI][indexJ]==-1) return true;
        return false;

    }

    private void copyMatrix(int[][] matrix1,int[][] matrix2){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                matrix2[i][j]=matrix1[i][j];
            }
        }
    }


}
