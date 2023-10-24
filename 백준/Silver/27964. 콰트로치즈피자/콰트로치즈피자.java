import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static Set<String> cheeses = new HashSet<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final var n = parseInt(br.readLine());
        final var s = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            if (s[i].endsWith("Cheese")) cheeses.add(s[i]);
        }
        if (cheeses.size() < 4) {
            System.out.println("sad");
            return;
        }
        System.out.println("yummy");
    }
}
