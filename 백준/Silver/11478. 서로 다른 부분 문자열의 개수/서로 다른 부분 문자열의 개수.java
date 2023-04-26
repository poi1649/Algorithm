import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String s = br.readLine();
        Set<String> set = new HashSet<>();
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (j + i > s.length()) {
                    break;
                }
                set.add(s.substring(j, j + i));
            }
        }
        System.out.println(set.size());
    }
}
