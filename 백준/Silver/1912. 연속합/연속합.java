import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        final int[] dp = new int[arr.length];
        int result = arr[0];
        dp[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            dp[i] = Integer.max(dp[i - 1] + arr[i], arr[i]);
            result = Integer.max(dp[i], result);
        }

        System.out.println(result);
    }
}
