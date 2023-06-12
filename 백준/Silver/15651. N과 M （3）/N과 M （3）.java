import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int[] arr = new int[m];
        boolean[] used = new boolean[n + 1];
        Arrays.fill(used, false);
        StringBuilder sb = new StringBuilder();
        nm(n, m, 0, arr, used, sb);
        System.out.print(sb.substring(0, sb.length() - 1));
    }

    private static void nm(int n, int m, int depth, int[] arr, boolean[] used, final StringBuilder sb) {
        if (m == depth) {
            for (int i : arr) {
                sb.append(i).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(System.lineSeparator());
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!used[i]) {
                arr[depth] = i;
                nm(n, m, depth + 1, arr, used, sb);
            }
        }
    }
}
