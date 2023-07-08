import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        long k = Long.parseLong(br.readLine());

        long min = 1;
        long max = n * n;

        while (min < max) {
            long mid = (min + max) / 2;
            long count = 0;

            for (int i = 1; i <= n; i++) {
                count += Math.min(mid / i, n);
            }

            if (count >= k) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        System.out.print(min);
    }
}


