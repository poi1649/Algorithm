import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {



    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final var s = br.readLine().split(" ");
        final var k = parseInt(s[0]);
        final var l = parseInt(s[1]);
        final var n = parseInt(s[2]);
        boolean[] input = new boolean[11001];
        final var s1 = br.readLine().toCharArray();
        for (int i = 1; i <= s1.length; i++) {
            input[i] = s1[i - 1] == '1';
        }
        boolean using = false;
        var useCount = 0;
        var flushCount = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < input.length; i++) {
            if(!using) {
                if (input[i]) {
                    if (++useCount >= k) {
                        using = true;
                    }
                }
                else {
                    useCount = 0;
                }
                continue;
            }

            if (input[i]) {
                flushCount = 0;
                continue;
            }
            if (++flushCount >= l) {
                sb.append(i).append("\n");
                using = false;
                flushCount = 0;
                useCount = 0;
            }
        }
        if (sb.length() == 0) {
            System.out.println("NIKAD");
            return;
        }
        System.out.print(sb);
    }

}
