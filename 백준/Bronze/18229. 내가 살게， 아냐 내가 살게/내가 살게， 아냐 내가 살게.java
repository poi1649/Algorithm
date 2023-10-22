import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class Main {



    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final var split = br.readLine().split(" ");
        final var n  = parseInt(split[0]);
        final var m  = parseInt(split[1]);
        final var k  = parseInt(split[2]);
        int[][] input = new int[101][101];
        for (int i = 0; i < n; i++) {
            final var s = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                input[i + 1][j + 1] = parseInt(s[j]);
            }
        }
        int[] result = new int[101];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                result[j] += input[j][i];
                if (result[j] >= k) {
                    System.out.println(j + " " + i);
                    return;
                }
            }
        }
    }
}
