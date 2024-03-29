import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final char[] chars = br.readLine().toCharArray();
        final int[][] alphabets = new int[chars.length]['z' - 'a' + 1];
        alphabets[0][chars[0] - 'a']++;
        for (int i = 1; i < chars.length; i++) {
            for (int j = 0; j < alphabets.length; j++) {
                alphabets[i][chars[j] - 'a'] = alphabets[i - 1][chars[j] - 'a'];
            }
            alphabets[i][chars[i] - 'a']++;
        }

        final StringBuilder sb = new StringBuilder();
        final int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            final char c = st.nextToken().charAt(0);
            final int start = Integer.parseInt(st.nextToken());
            final int end = Integer.parseInt(st.nextToken());

            if (start == 0) {
                sb.append(alphabets[end][c - 'a']).append(System.lineSeparator());
                continue;
            }
            final int result = alphabets[end][c - 'a'] - alphabets[start - 1][c - 'a'];
            sb.append(result).append(System.lineSeparator());
        }

        System.out.println(sb);
        br.close();
    }
}

