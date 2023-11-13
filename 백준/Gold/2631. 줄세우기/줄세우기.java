import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int t;
    static int[] arr = new int[201];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        t = parseInt(br.readLine());
        t = 1;
        int cur = 1;
        while (t-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int cur) throws IOException {
        final var n = parseInt(br.readLine());
        int length = 0;
        Arrays.fill(arr, 202);
        for (int i = 0; i < n; i++) {
            final var k = parseInt(br.readLine());
            final var bi = Arrays.binarySearch(arr, k);
            final var insertion = (bi +1) * -1;
            if (length <= insertion) {
                length++;
            }
            arr[insertion] = k;
        }
        System.out.println(n - length);
    }
}


