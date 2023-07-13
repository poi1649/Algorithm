import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.OptionalInt;

public class Main {

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final HashSet<String> users = new HashSet<>();
        int result = 0;
        for (int i = 0; i < n; i++) {
            final String target = br.readLine();
            if (target.equals("ENTER")) {
                result += users.size();
                users.clear();
                continue;
            }
            users.add(target);
        }
        System.out.println(result + users.size());
    }
}


