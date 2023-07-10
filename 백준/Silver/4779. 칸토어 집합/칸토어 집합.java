import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder sb = new StringBuilder();
        String str;
        while((str = br.readLine()) != null) {
            int target = Integer.parseInt(str);
            int n = (int) Math.pow(3, target);
            final char[] chars = new char[n];
            Arrays.fill(chars, '-');
            recursive(chars, 0, n - 1);
            sb.append(chars).append(System.lineSeparator());
        }

        System.out.print(sb);
        br.close();
    }

    static void recursive(char[] chars, int start, int end) {
        if (start == end) {
            return;
        }
//        0 1 2 3 4 5 6 7 8;
//        start = 0;
//        end = 9;
//        unit = 3;
        final int unit = (end - start + 1) / 3;
        for (int i = unit; i < unit + unit; i++) {
            chars[start + i] = ' ';
        }
        recursive(chars, start, start + unit - 1);
        recursive(chars, start + unit + unit, end);
    }
}


