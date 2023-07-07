import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        final int n = Integer.parseInt(s[0]);
        final int c = Integer.parseInt(s[1]);

        final int[] house = new int[n];
        for (int i = 0; i < n; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);

        int max = house[n - 1] - house[0] + 1;
        int min = 1;

        while (min < max) {
            int distance = (max + min) / 2;


            int count = 1;
            int previous = house[0];
            for (int i = 1; i < house.length; i++) {
                if (house[i] - previous >= distance) {
                    previous = house[i];
                    count++;
                }
            }

            if (count < c) {
                max = distance;
                continue;
            }
            min = distance + 1;
        }
        System.out.println(min - 1);
    }

}

