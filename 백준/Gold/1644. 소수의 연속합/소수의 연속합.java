import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int[][] shortest;
    static int INF = 1_000_000_000;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());

        final boolean[] notPrime = new boolean[4_000_001];

        notPrime[0] = true;
        notPrime[1] = true;

        final int N = 4_000_000;
        for (int i = 0; i <= N; i++) {
            if (!notPrime[i]) {
                for (int j = 2; j * i <= N; j++) {
                    notPrime[j * i] = true;
                }
            }
        }
        final ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            if (!notPrime[i]) {
                primes.add(i);
            }
        }

        int i = 0;
        int j = 0;
        int size = primes.size();
        int sum = 0;
        int result = 0;

        while (i <= j && j <= size) {
            if (i > 1 && primes.get(i - 1) > n) {
                break;
            }
            if (sum >= n) {
                if (sum == n) {
                    result++;
                }
                sum -= primes.get(i++);
                continue;
            }
            if (j < size) {
                sum += primes.get(j);
            }
            j++;
        }
        System.out.println(result);
    }
}
