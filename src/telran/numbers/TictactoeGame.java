package telran.numbers;

import java.util.*;

import static telran.numbers.MatrixInt.transposition;

public class TictactoeGame {

    public static void main(String[] args) {
        char[][] matrix = new char[3][3];
        tictactoeGame(matrix, 'X');
    }
    public static int tictactoeMove(char[][] matrix, int nRow, int nCol, char symb) {
        int res = 0;
        boolean curRow = false, curCol = false;
        int curDiagonal = 1;
        if (matrix[nRow][nCol] == 'X' || matrix[nRow][nCol] == '0') {
            System.out.println("Wrong sell");
            return -1;
        } else matrix[nRow][nCol] = symb;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                curRow = matrix[i][j - 1] == matrix[i][j] && matrix[i][j] != 0;
                if ((i == j || i + j + 1 == matrix.length) && matrix[i][j] == symb) curDiagonal++;
            }
            if (curRow || curDiagonal == 3) {
                res = 1;
                break;
            }
        }
        char[][] transposeMatrix = transposition(matrix);
        for (int i = 0; i < transposeMatrix.length; i++) {
            for (int j = 1; j < transposeMatrix[i].length; j++) {
                curCol = transposeMatrix[i][j - 1] == transposeMatrix[i][j] && transposeMatrix[i][j] != 0;
            }
            if (curCol) {
                res = 1;
                break;
            }
        }
        return res;
    }


    public static int tictactoeGame(char[][] matrix, char symb) {
        List<Character> players = new ArrayList<>(List.of('X', '0'));
        if (players.get(0) == symb) Collections.reverse(players);
        System.out.println("Next move for " + symb);
        int row = getRowAndCol(matrix.length, "row");
        int col = getRowAndCol(matrix.length, "col");
        int res = tictactoeMove(matrix, row, col, symb);
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(matrix[i]);
        }
        if (res < 0) tictactoeGame(matrix, symb);
        getWinner(res);
        return res == 0 ? tictactoeGame(matrix, players.get(0)) : res;
    }

    private static void getWinner(int res) {
        if (res == 1) {
            System.out.println("Game is over with the winner's move");
        } else if (res == 2) {
            System.out.println("Game is over with draw");
        }
    }

    private static int getRowAndCol(int length, String sell) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Choose " + sell + ": ");
        int input = scan.nextInt();
        if (input >= length || input < 0) {
            System.out.println("Wrong sell");
            getRowAndCol(length, sell);
        }
        return input;
    }
}
