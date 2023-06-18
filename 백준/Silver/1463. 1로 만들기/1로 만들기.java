import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        int[] trials = new int[n + 1];
        trials[1] = 0;
        int[] temp = new int[3];
        for (int i = 2; i <= n; i++) {
            Arrays.fill(temp, Integer.MAX_VALUE);
            if ((i % 3) == 0) {
                temp[0] = trials[i / 3] + 1;
            }
            if ((i % 2) == 0) {
                temp[1] = trials[i / 2] + 1;
            }
            temp[2] = trials[i - 1] + 1;
            trials[i] = Math.min(temp[0], Math.min(temp[1], temp[2]));
        }
        System.out.println(trials[n]);
    }
}
