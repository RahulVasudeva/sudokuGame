// deals with printing the sudoku grid with the values
public class sudokuGrid {
    private int[][] matrix=new int[9][9];
    sudokuGrid() {
        for(int i=0;i<9;i++){

            for(int j=0;j<9;j++){
                matrix[i][j]= -1;
            }
            matrix[3][3]=7;
            matrix[0][0]=6;

        }
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
        return "valid";

    }

    public void printGrid(){
        System.out.println("    0    1     2     3    4    5     6    7    8");
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
