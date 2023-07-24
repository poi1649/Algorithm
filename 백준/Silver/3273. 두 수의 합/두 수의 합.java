import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] shortest;
    static int INF = 1_000_000_000;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[] ints = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        final int x = Integer.parseInt(br.readLine());
        Arrays.sort(ints);
        int i = 0;
        int j = n - 1;
        int result = 0;
        while (i < j) {
            if (ints[i] + ints[j] == x) {
                result++;
                i++;
                j--;
                continue;
            }
            if (ints[i] + ints[j] > x) {
                j--;
                continue;
            }
            if (ints[i] + ints[j] < x) {
                i++;
            }
        }
        System.out.println(result);
    }
}
