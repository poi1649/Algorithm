import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        int[][] costs = new int[n][3];
        for (int i = 0; i < n; i++) {
            costs[i][2] = Integer.parseInt(br.readLine());
        }

        if (n == 1) {
            System.out.println(costs[0][2]);
            return;
        }
        if (n == 2) {
            System.out.println(costs[0][2] + costs[1][2]);
            return;
        }

        costs[0][0] = costs[0][1] = costs[0][2];

        costs[1][0] = costs[1][2];
        costs[1][1] = costs[0][0] + costs[1][2];

        for (int i = 2; i < n; i++) {
            costs[i][0] = Math.max(costs[i - 2][0], costs[i - 2][1]) + costs[i][2];
            costs[i][1] = costs[i - 1][0] + costs[i][2];
        }

        System.out.println(Math.max(costs[n - 1][0], costs[n - 1][1]));
    }
}
