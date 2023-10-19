import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final var n = parseInt(br.readLine());
        final var input = br.readLine().toCharArray();
        for (int i = 1; i < input.length; i++) {
            int sum  = 0;
            for (int j = 0; j + i < input.length; j++) {
                if (input[j + i] != input[j]) {
                    sum++;
                    if (sum > 1) {
                        break;
                    }
                }
            }
            if (sum == 1) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }

}
