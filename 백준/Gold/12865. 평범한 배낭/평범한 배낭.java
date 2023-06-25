import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        final int n = Integer.parseInt(s[0]);
        final int k = Integer.parseInt(s[1]);
        final int[][] bag = new int[k + 1][n];
        final int[] w = new int[n];
        final int[] v = new int[n];

        for (int i = 0; i < n; i++) {
            final String[] s1 = br.readLine().split(" ");
            w[i] = Integer.parseInt(s1[0]);
            v[i] = Integer.parseInt(s1[1]);
        }

        for (int i = w[0]; i <= k; i++) {
            bag[i][0] = v[0];
        }

        for (int i = 1; i <= k; i++) {
            for (int j = 1; j < n; j++) {
                bag[i][j] = bag[i][j - 1];
                if (i >= w[j]) {
                    bag[i][j] = Math.max(bag[i][j], bag[i - w[j]][j - 1] + v[j]);
                }
            }
        }

        System.out.println(bag[k][n - 1]);
    }
}

