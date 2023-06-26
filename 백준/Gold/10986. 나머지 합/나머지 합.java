import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        final int n = Integer.parseInt(s[0]);
        final int m = Integer.parseInt(s[1]);
        final int[] remains = Arrays.stream(br.readLine().split(" ")).mapToInt(i -> Integer.parseInt(i) % m).toArray();
        long result = 0;
        final int[] sums = new int[n];
        final int[] count = new int[m];

        count[0]++;
        sums[0] = remains[0];

        count[sums[0]]++;

        for (int i = 1; i < n; i++) {
            sums[i] = (sums[i - 1] + remains[i]) % m;
            count[sums[i]]++;
        }


        for (int i = 0; i < count.length; i++) {
            final int t = count[i];
            result += (((long) t * (t - 1)) / 2);
        }

        System.out.println(result);
    }
}
