import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) throws Exception {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(reader.readLine());
        int[] s = Arrays.stream(reader.readLine()
                                      .split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
        int[] right = new int[n];
        int[] left = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            if (s[i] >= s[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        for (int i = 1; i < n; i++) {
            if (s[i] >= s[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int cur = right[i] + left[i] + 1;
            ans = Math.max(ans, cur);
        }
        System.out.println(ans);
    }
}