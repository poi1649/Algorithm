import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalInt;

public class Main {

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int i = Integer.parseInt(br.readLine());
        final int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        if (i == 1) {
            System.out.println(s[0] * s[0]);
            return;
        }
        final int max = Arrays.stream(s).max().getAsInt();
        final int min = Arrays.stream(s).min().getAsInt();
        System.out.println(max*min);
    }
}


