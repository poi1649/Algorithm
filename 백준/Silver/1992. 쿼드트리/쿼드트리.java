import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] board;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        checkBoard(0, 0, n);
        System.out.println(result);
        br.close();
    }

    public static void checkBoard(int startX, int startY, int length) {
        int first = board[startX][startY];

        if (length == 1) {
            result.append(first);
            return;
        }

        for (int i = startX; i < startX + length; i++) {
            for (int j = startY; j < startY + length; j++) {

                if (board[i][j] != first) {
                    result.append("(");
                    final int nextLength = length / 2;
                    checkBoard(startX, startY, nextLength);
                    checkBoard(startX, startY + nextLength, nextLength);
                    checkBoard(startX + nextLength, startY, nextLength);
                    checkBoard(startX + nextLength, startY + nextLength, nextLength);
                    result.append(")");
                    return;
                }
            }
        }

        result.append(first);
    }
}
