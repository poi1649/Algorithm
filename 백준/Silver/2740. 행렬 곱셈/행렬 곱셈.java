import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static int[][] result;
    public static int m2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        final int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            a[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        s = br.readLine().split(" ");
        int n1 = Integer.parseInt(s[0]);
        m2 = Integer.parseInt(s[1]);
        final int[][] b = new int[n1][m2];
        for (int i = 0; i < n1; i++) {
            b[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        result = new int[n][m2];
        calculate(a, b);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            final String temp = Arrays.stream(result[i]).mapToObj(String::valueOf).collect(Collectors.joining(" "));
            sb.append(temp).append(System.lineSeparator());
        }
        System.out.print(sb);
    }

    public static void calculate(int[][] arrA, int[][] arrB) {
        for (int i = 0; i < arrA.length; i++) {
            for (int j = 0; j < m2; j++) {
                for (int k = 0; k < arrB.length; k++) {
                    result[i][j] += arrA[i][k]*arrB[k][j];
                }
            }
        }
    }
}
