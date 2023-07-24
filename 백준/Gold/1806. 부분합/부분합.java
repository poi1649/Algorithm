import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] shortest;
    static int INF = 1_000_000_000;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s1 = br.readLine().split(" ");
        final int n = Integer.parseInt(s1[0]);
        final int s = Integer.parseInt(s1[1]);
        final int[] ints = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int i = 0;
        int j = 1;
        int sum = ints[i];
        int result = Integer.MAX_VALUE;

        while (i <= j && j <= n) {
            if (sum >= s) {
                result = Math.min(result, j - i);
                sum -= ints[i++];
                continue;
            }
            if (j < n) {
                sum += ints[j];
            }
            j++;
        }

        if (result == Integer.MAX_VALUE) {
            System.out.println(0);
            return;
        }
        System.out.println(result);
    }
}
