import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        final int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);

        int[] coins = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        for (int coin : coins) {
            count += k / coin;
            k = k % coin;
        }

        System.out.println(count);
    }
}
