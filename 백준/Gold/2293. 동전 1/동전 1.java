import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        final int n = Integer.parseInt(s[0]);
        final int k = Integer.parseInt(s[1]);
        final int[] coins = new int[n + 1];
        final int[] canMakes = new int[10001];
        canMakes[0] = 1;
        for (int i = 1; i <= n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = coins[i]; j <= 10000; j++) {
                canMakes[j] += canMakes[j - coins[i]];
            }
        }

        System.out.println(canMakes[k]);
    }
}


