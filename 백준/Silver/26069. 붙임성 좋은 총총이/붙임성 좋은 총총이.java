import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final HashSet<String> users = new HashSet<>();
        users.add("ChongChong");

        for (int i = 0; i < n; i++) {
            final String[] s = br.readLine().split(" ");
            final String targetOne = s[0];
            final String targetTwo = s[1];
            if (users.contains(targetOne)) {
                users.add(targetTwo);
            } else if (users.contains(targetTwo)) {
                users.add(targetOne);
            }
        }
        System.out.println(users.size());
    }
}


