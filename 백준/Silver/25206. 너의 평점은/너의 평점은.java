import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[][] inputs = new String[20][2];
        for (int i = 0; i < 20; i++) {
            final String[] s = br.readLine().split(" ");
            inputs[i][0] = s[1];
            inputs[i][1] = s[2];
        }
        double sum = 0;
        double realSum = 0;
        for (String[] input : inputs) {
            if (input[1].equals("P")) {
                continue;
            }
            sum += Double.parseDouble(input[0]);
            realSum += Double.parseDouble(input[0]) * Score.getScore(input[1]);
        }
        System.out.println(realSum/sum);
    }

    enum Score {
        APLUS("A+", 4.5),
        AZERO("A0", 4.0),
        BPLUS("B+", 3.5),
        BZERO("B0", 3.0),
        CPLUS("C+", 2.5),
        CZERO("C0", 2.0),
        DPLUS("D+", 1.5),
        DZERO("D0", 1.0),
        F("F", 0.0),
        ;

        private final String name;
        private final double value;

        Score(final String name, final double value) {
            this.name = name;
            this.value = value;
        }

        public static double getScore(String name) {
            return Arrays.stream(values())
                    .filter(value -> value.name.equals(name))
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException())
                    .value;
        }
    }
}
