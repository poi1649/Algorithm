import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int x = n;
        boolean[] primes = new boolean[x + 1];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for (int j = 2; j <= x; j++) {
            if (primes[j]) {
                while (n % j == 0) {
                    System.out.println(j);
                    n /= j;
                }
                for (int i = j * 2; i <= x; i += j) {
                    primes[i] = false;
                }
            }
        }
    }
}
