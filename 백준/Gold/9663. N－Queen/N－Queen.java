import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int result = 0;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[] board = new int[n];
        Arrays.fill(board, Integer.MAX_VALUE);
        putQueen(n, 0, board);
        System.out.println(result);
    }

    private static void putQueen(int n, int row, int[] board) {
        if (row == n) {
            result++;
            return;
        }

        for (int col = 0; col < n; col++) {
                board[row] = col;
            if (canPut(n, board, col, row)) {
                putQueen(n, row + 1, board);
            }
        }
    }

    private static boolean canPut(int n, int[] board, int column, int row) {
        for (int i = 0; i < row; i++) {
            if (board[row] == board[i]) {
                return false;
            }

            if (row - i == Math.abs(board[row] - board[i])) {
                return false;
            }
        }
        return true;
    }
}
