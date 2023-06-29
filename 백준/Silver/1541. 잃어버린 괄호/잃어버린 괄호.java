import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final ArrayList<Integer> numbers = new ArrayList<>();
        final LinkedList<Character> delimiters = new LinkedList<>();
        final char[] inputs = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char input : inputs) {
            if (Character.isDigit(input)) {
                sb.append(input);
                continue;
            }
            if (sb.length() != 0) {
                numbers.add(Integer.parseInt(sb.toString()));
                sb = new StringBuilder();
            }
            delimiters.add(input);
        }
        numbers.add(Integer.parseInt(sb.toString()));

        int temp = numbers.remove(0);
        final ArrayList<Integer> results = new ArrayList<>();

        final int size = delimiters.size();
        for (int i = 0; i < size; i++) {
            final char target = delimiters.remove(0);
            final int targetNumber = numbers.remove(0);
            if (target == '-') {
                results.add(temp);
                temp = targetNumber;
                continue;
            }
            temp += targetNumber;
        }
        results.add(temp);
        final int result = results.stream()
                .skip(1)
                .reduce(results.get(0), (a, b) -> a - b);
        System.out.println(result);
        br.close();
    }
}
