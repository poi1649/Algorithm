import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final var line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        final var map = new HashMap<String, Boolean>();
        int keywords = n;
        for (int i = 0; i < n; i++) {
            map.put(br.readLine(), true);
        }
        final var stringBuilder = new StringBuilder();
        for (int i = 0; i < m; i++) {
            final var split = br.readLine().split(",");
            for (String s : split) {
                if (map.containsKey(s) && map.get(s)) {
                    keywords--;
                    map.put(s, false);
                }
            }
            stringBuilder.append(keywords).append("\n");
        }
        System.out.print(stringBuilder);
    }
}
