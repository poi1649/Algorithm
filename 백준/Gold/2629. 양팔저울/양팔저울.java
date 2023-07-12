import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static boolean[][] canWeigh;

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] weights = new int[n + 1];
        System.arraycopy(inputs, 0, weights, 1, inputs.length);
        final int m = Integer.parseInt(br.readLine());
        final int[] balls = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        canWeigh = new boolean[40001][n + 1];

        Arrays.fill(canWeigh[0], true);

        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= 40000; i++) {
//                canWeigh[i][j] = canWeigh[i][j - 1] || canWeigh[Math.abs(i - weights[j])][j - 1];
                if (canWeigh[i][j - 1]) {
                    canWeigh[i][j] = true;
                    canWeigh[Math.abs(i - weights[j])][j] = true;
                    if (i + weights[j] <= 40000) {
                        canWeigh[Math.abs(i + weights[j])][j] = true;
                    }
                }
            }
            canWeigh[weights[j]][j] = true;
        }

        final StringBuilder sb = new StringBuilder();
        for (int ball : balls) {
            if (canWeigh[ball][n]) {
                sb.append("Y").append(" ");
                continue;
            }
            sb.append("N").append(" ");
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }
}


