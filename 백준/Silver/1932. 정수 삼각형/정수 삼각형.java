import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        int[][] costs = new int[n][n];
        for (int i = 0; i < n; i++) {
            costs[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < costs[i].length; j++) {
                if (j == 0) {
                    costs[i][j] += costs[i - 1][j];
                    continue;
                }
                if (j == costs[i].length - 1) {
                    costs[i][j] += costs[i - 1][j - 1];
                    continue;
                }
                costs[i][j] += Math.max(costs[i - 1][j - 1], costs[i - 1][j]);
            }
        }

        final int max = Arrays.stream(costs[n - 1]).max().getAsInt();
        System.out.println(max);
    }
}
