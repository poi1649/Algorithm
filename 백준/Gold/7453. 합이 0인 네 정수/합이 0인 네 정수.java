import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int q, n, m;
    static long count = 0;

    static int[] a;
    static int[] b;
    static int[] c;
    static int[] d;

    static int[] ab;
    static int[] cd;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        q = parseInt(br.readLine());
        q = 1;
        int cur = 1;
        while (q-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int cur) throws IOException {
        n = parseInt(br.readLine());
        a = new int[n];
        b = new int[n];
        c = new int[n];
        d = new int[n];

        ab = new int[n * n];
        cd = new int[n * n];

        for (int i = 0; i < n; i++) {
            final var split = br.readLine().split(" ");
            a[i] = parseInt(split[0]);
            b[i] = parseInt(split[1]);
            c[i] = parseInt(split[2]);
            d[i] = parseInt(split[3]);
        }
        check();
        Arrays.sort(ab);
        Arrays.sort(cd);

        int left = 0, right = n * n - 1;
        while (left < n * n && right >= 0) {
            if (ab[left] + cd[right] < 0) {
                left++;
            } else if (ab[left] + cd[right] > 0) {
                right--;
            } else {
                long leftCount = 1, rightCount = 1;
                while (left + 1 < n * n && (ab[left] == ab[left + 1])) {
                    leftCount++;
                    left++;
                }
                while (right > 0 && (cd[right] == cd[right - 1])) {
                    rightCount++;
                    right--;
                }
                count += leftCount * rightCount;
                left++;
            }
        }
        System.out.println(count);
    }

    public static void check() {
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ab[idx] = a[i] + b[j];
                cd[idx++] = c[i] + d[j];
            }
        }
    }
}

