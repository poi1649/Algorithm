import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final Stack<Character> result = new Stack<>();
        final char[] str = br.readLine().toCharArray();
        final char[] bombs = br.readLine().toCharArray();

        for (char c : str) {
            result.push(c);
            if (result.size() >= bombs.length) {
                boolean isSame = true;
                for (int i = 0; i < bombs.length; i++) {
                    if (result.get(result.size() - bombs.length + i) != bombs[i]) {
                        isSame = false;
                        break;
                    }
                }
                if (isSame) {
                    for (int i = 0; i < bombs.length; i++) {
                        result.pop();
                    }
                }
            }

        }

        if (result.isEmpty()) {
            System.out.println("FRULA");
            return;
        }
        final StringBuilder sb = new StringBuilder();
        while(!result.isEmpty()) {
            sb.append(result.pop());
        }
        System.out.println(sb.reverse());
    }
}


