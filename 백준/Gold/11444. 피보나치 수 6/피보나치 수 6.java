import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static long[][] multiple;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        if (n <= 1) {
            System.out.println(n);
            return;
        }
        multiple = new long[2][2];
        Arrays.fill(multiple[0], 1);
        multiple[1][0] = 1;
        long[][] result = calculate(multiple, n - 1);
        System.out.print(result[0][0]);
    }

    public static long[][] calculate(long[][] arrA, long n) {

        if (n == 1) {
            return arrA;
        } else if (n == 2) {
            return calculate(arrA, arrA);
        } else {
            final long[][] half = calculate(arrA, n / 2);
            if (n % 2 == 1) {
                return calculate(calculate(half, 2), multiple);
            }
            return calculate(half, 2);
        }
    }

    public static long[][] calculate(long[][] a, long[][] b) {

        long[][] result = new long[2][2];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                for (int k = 0; k < a.length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
                result[i][j] %= 1_000_000_007;
            }
        }
        return result;
    }
}
