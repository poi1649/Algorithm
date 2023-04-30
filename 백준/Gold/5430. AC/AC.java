import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            final char[] chars = br.readLine().toCharArray();
            br.readLine();
            final String s = br.readLine();
            ArrayDeque<Integer> inputs;
            try {
                inputs = Arrays.stream(s.substring(1, s.length() - 1).split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toCollection(ArrayDeque::new));
            } catch (NumberFormatException e) {
                inputs = new ArrayDeque<>();
            }
            boolean errored = false;
            boolean reversed = false;
            for (char aChar : chars) {
                if (aChar == 'R') {
                    reversed = !reversed;
                    continue;
                }
                if (inputs.isEmpty()) {
                    errored = true;
                    System.out.println("error");
                    break;
                }
                if (reversed) {
                    inputs.removeLast();
                    continue;
                }
                inputs.removeFirst();
            }
            if (!errored) {
                StringBuilder sb = new StringBuilder();
                Iterator iterator;
                if (reversed) {
                    iterator = inputs.descendingIterator();
                } else {
                    iterator = inputs.iterator();
                }
                while (iterator.hasNext()) {
                    sb.append(iterator.next()).append(",");
                }
                System.out.print("[");
                if(sb.length() != 0) {
                    System.out.print(sb.substring(0, sb.length() -1));
                }
                System.out.println("]");
            }
        }
    }
}
