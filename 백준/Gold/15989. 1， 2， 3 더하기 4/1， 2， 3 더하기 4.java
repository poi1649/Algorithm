import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] arr = new int[10001][4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final var n = parseInt(br.readLine());
        arr[1][1] = 1;

        arr[2][1] = 1;
        arr[2][2] = 1;

        arr[3][1] = 2;
        arr[3][3] = 1;
        for (int i = 4; i <= 10000; i++) {
            arr[i][1] = arr[i - 1][1] + arr[i - 1][2] + arr[i - 1][3];
            arr[i][2] = arr[i - 2][2] + arr[i - 2][3];
            arr[i][3] = arr[i - 3][3];
        }
        final var sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            final var num = parseInt(br.readLine());
            sb.append(arr[num][1] + arr[num][2] + arr[num][3]).append("\n");
        }
        System.out.print(sb);
    }
}
