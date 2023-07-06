import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        final int n = Integer.parseInt(s[0]);
        final long m = Long.parseLong(s[1]);

        final long[] longs = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        long max = Arrays.stream(longs).max().getAsLong();
        if (max == 0) {
            System.out.println(0);
            return;
        }
        long min = 0;
        long mid;

        while (min < max) {
            mid = (min + max) / 2;
            long length = 0;

            for (long aLong : longs) {
                if (aLong - mid > 0) {
                    length += aLong - mid;
                }
            }

            if (length < m) {
                max = mid;
                continue;
            }
            min = mid + 1;
        }

        System.out.println(min - 1);
    }
}

