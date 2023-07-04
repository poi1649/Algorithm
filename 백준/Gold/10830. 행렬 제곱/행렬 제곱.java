import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static int[][] multiple;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        long b = Long.parseLong(s[1]);
        final int[][] a = new int[n][n];
        multiple = new int[n][n];
        for (int i = 0; i < n; i++) {
            a[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(str -> Integer.parseInt(str) % 1000).toArray();
            multiple[i] = Arrays.copyOf(a[i], a[i].length);
        }

        int[][] result = calculate(a, b);

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            final String temp = Arrays.stream(result[i]).mapToObj(String::valueOf).collect(Collectors.joining(" "));
            sb.append(temp).append(System.lineSeparator());
        }
        System.out.print(sb);
    }

    public static int[][] calculate(int[][] arrA, long n) {

        if (n == 1) {
            return arrA;
        } else if (n == 2) {
            return calculate(arrA, arrA);
        } else {
            final int[][] half = calculate(arrA, n / 2);
            if (n % 2 == 1) {
                return calculate(calculate(half, 2), multiple);
            }
            return calculate(half, 2);
        }
    }

    public static int[][] calculate(int[][] a, int[][] b) {

        int[][] result = new int[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                for (int k = 0; k < a.length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
                result[i][j] %= 1000;
            }
        }
        return result;
    }
}
