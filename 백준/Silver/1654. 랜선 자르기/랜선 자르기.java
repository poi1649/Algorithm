import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        final int k = Integer.parseInt(s[0]);
        final int n = Integer.parseInt(s[1]);

        final int[] ints = new int[k];

        for (int i = 0; i < k; i++) {
            ints[i] = Integer.parseInt(br.readLine());
        }

        long max = Arrays.stream(ints).max().getAsInt();
        max++;
        long min = 0;
        long mid;

        while (min < max) {
            long count = 0;
            mid = (max + min) / 2;
            for (int anInt : ints) {
                count += anInt / mid;
            }

            if (count >= n) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        System.out.println(min - 1);
    }
}

