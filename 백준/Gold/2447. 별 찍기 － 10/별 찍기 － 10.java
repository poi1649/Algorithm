import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        char[][] arr = new char[n][n];
        makeStar(0, 0, n, false, arr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(arr[i][j]);
            }
            sb.append(System.lineSeparator());
        }
        System.out.println(sb);
    }

    private static void makeStar(int xStart, int yStart, int n, boolean isBlank, final char[][] arr) {
        if (isBlank) {
            for (int i = xStart; i < xStart + n; i++) {
                for (int j = yStart; j < yStart + n; j++) {
                    arr[i][j] = ' ';
                }
            }
            return;
        }

        if (n == 1) {
            arr[xStart][yStart] = '*';
            return;
        }

        int count = 0;
        final int unit = n / 3;
        for (int i = xStart; i < xStart + n; i = i + unit) {
            for (int j = yStart; j < yStart + n; j = j + unit) {
                boolean flag = count == 4;
                makeStar(i, j, unit, flag, arr);
                count++;
            }
        }
    }

}
