import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final char[] arr = br.readLine().toCharArray();
        final char[] arr2 = br.readLine().toCharArray();
        final int[][] lcs = new int[arr.length][arr2.length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr[i] == arr2[j]) {
                    if (i - 1 < 0 || j - 1 < 0) {
                        lcs[i][j] = 1;
                        continue;
                    }
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                    continue;
                }
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0) {
                    lcs[i][j] = lcs[i][j - 1];
                    continue;
                }
                if (j == 0) {
                    lcs[i][j] = lcs[i - 1][j];
                    continue;
                }
                lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
            }
        }

        final int result = Arrays.stream(lcs).flatMapToInt(Arrays::stream).max().getAsInt();
        System.out.println(result);
    }
}

