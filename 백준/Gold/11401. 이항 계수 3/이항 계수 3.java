import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int DIVIDER = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        long n = Long.parseLong(s[0]);
        long k = Long.parseLong(s[1]);
        long nFactorial = 1;
        for (int i = 1; i <= n; i++) {
            nFactorial *= i;
            nFactorial %= DIVIDER;
        }
        long kFactorial = 1;
        for (int i = 1; i <= k; i++) {
            kFactorial *= i;
            kFactorial %= DIVIDER;
        }
        long nMinusKFactorial = 1;
        for (int i = 1; i <= n - k; i++) {
            nMinusKFactorial *= i;
            nMinusKFactorial %= DIVIDER;
        }
        final long result = (nFactorial % DIVIDER) * pow(kFactorial * nMinusKFactorial, DIVIDER - 2) % DIVIDER;
        System.out.println(result);
    }

    private static long pow(long a, long b) {

        if (b == 1) {
            return a % DIVIDER;
        } else {
            long halfVal = pow(a, b / 2);

            final long temp = halfVal * halfVal % DIVIDER;
            if (b % 2 == 1) {
                return (temp) * (a % DIVIDER) % DIVIDER;
            }
            return temp;
        }
    }
}
