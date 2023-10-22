import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final var s = br.readLine();
        final var charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            StringBuilder start = new StringBuilder();
            for (int j = 0; j <= i; j++) {
                start.append(charArray[j]);
            }
            int next = Integer.parseInt(start.toString());
            StringBuilder cur = new StringBuilder();
            while (cur.length() < charArray.length) {
                cur.append(next);
                next++;
            }
            if (cur.toString().equals(s)) {
                System.out.println(start + " " + (next - 1));
                return;
            }
        }
    }
}
