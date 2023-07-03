import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] board;
    static int first = 0;
    static int second = 0;
    static int third = 0;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        checkBoard(0, 0, n);
        System.out.println(first);
        System.out.println(second);
        System.out.println(third);
        br.close();
    }

    public static void checkBoard(int startX, int startY, int length) {
        int target = board[startX][startY];

        if (length == 1) {
            if (target == -1) {
                first++;
                return;
            }
            if (target == 0) {
                second++;
                return;
            }
            third++;
            return;
        }

        for (int i = startX; i < startX + length; i++) {
            for (int j = startY; j < startY + length; j++) {

                if (board[i][j] != target) {
                    final int nextLength = length / 3;
                    checkBoard(startX, startY, nextLength);
                    checkBoard(startX, startY + nextLength, nextLength);
                    checkBoard(startX, startY + nextLength + nextLength, nextLength);
                    checkBoard(startX + nextLength, startY, nextLength);
                    checkBoard(startX + nextLength, startY + nextLength, nextLength);
                    checkBoard(startX + nextLength, startY + nextLength + nextLength, nextLength);
                    checkBoard(startX + nextLength + nextLength, startY, nextLength);
                    checkBoard(startX + nextLength + nextLength, startY + nextLength, nextLength);
                    checkBoard(startX + nextLength +nextLength, startY + nextLength + nextLength, nextLength);
                    return;
                }
            }
        }

        if (target == -1) {
            first++;
            return;
        }
        if (target == 0) {
            second++;
            return;
        }
        third++;
    }
}
