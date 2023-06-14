import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

    final static ArrayList<Integer> results = new ArrayList<>();
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                .toArray();
        int[] operators = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                .toArray();

        final LinkedList<Integer> locatedOperators = new LinkedList<>();
        calculate(operators, locatedOperators);
        System.out.println(results.stream().max(Integer::compareTo).get());
        System.out.println(results.stream().min(Integer::compareTo).get());
    }

    private static void calculate(int[] operators, List<Integer> locatedOperators) {

        if (locatedOperators.size() == numbers.length - 1) {
            int result = numbers[0];
            for (int i = 0; i < numbers.length - 1; i++) {
                final int operator = locatedOperators.get(i);
                result = calculateResult(result, numbers[i + 1], operator);
            }
            results.add(result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {
                if (i == 3 && (numbers[locatedOperators.size() + 1] == 0)) {
                    continue;
                }

                operators[i] = operators[i] - 1;
                locatedOperators.add(i);
                calculate(operators, locatedOperators);
                locatedOperators.remove(locatedOperators.size() - 1);
                operators[i] = operators[i] + 1;
            }
        }
    }

    private static int calculateResult(int r1, int r2, int operator) {
        if (operator == 0) {
            return r1 + r2;
        }

        if (operator == 1) {
            return r1 - r2;
        }

        if (operator == 2) {
            return r1 * r2;
        }

        return r1 / r2;
    }
}
