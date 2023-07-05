import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static long[] histogram;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        final int m = Integer.parseInt(br.readLine());
        final int[] b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        final StringBuilder sb = new StringBuilder();
        for (int i : b) {
            if (Arrays.binarySearch(a, i) >= 0) {
                sb.append(1).append(System.lineSeparator());
                continue;
            }
            sb.append(0).append(System.lineSeparator());
        }
        System.out.println(sb);
    }
}

