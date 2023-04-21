import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int count = 0;

        for (int i = 0; i < n; i++) {
            int tmpCount = 1;
            String input = br.readLine();
            final char[] chars = input.toCharArray();
            Set<Character> characterSet = new HashSet<>();
            char current = chars[0];
            characterSet.add(current);
            for (int j = 1; j < chars.length; j++) {
                characterSet.add(chars[j]);
                if (current != chars[j]) {
                    tmpCount += 1;
                }
                current = chars[j];
            }
            if (tmpCount == characterSet.size()) {
                count++;
            }
        }

        System.out.println(count);
    }
}
