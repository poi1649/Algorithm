import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] A = new int[200_010];
    static int r;
    static int c;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final var s = br.readLine().toCharArray();
        StringBuilder target = new StringBuilder();
        target.append("*");
        for (char c : s) {
            target.append(c).append("*");
        }
        final var st = target.toString().toCharArray();
        for (int i = 0; i < st.length; i++) {
            if (r + c < i) {
                A[i] = 0;
            } else {
                final var j = 2 * c - i;
                if (j >= 0) {
                    A[i] = Math.min(A[j], c + r - i);
                }
            }
            while (i - A[i] - 1 >= 0 && i + A[i] + 1 < st.length && st[i - A[i] - 1] == st[i + A[i] + 1]) {
                A[i]++;
            }
            if (i + A[i] >= c + r) {
                r = A[i];
                c = i;
            }
        }
        final var asInt = Arrays.stream(A).max().getAsInt();
        System.out.println(asInt);
    }

}
