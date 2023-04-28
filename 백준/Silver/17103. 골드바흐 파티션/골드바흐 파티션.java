import static java.lang.Math.abs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        Map<Integer, Set<Integer>> result = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            result.put(Integer.parseInt(br.readLine()), new HashSet<>());
        }
        final boolean[] primes = new boolean[1000001];
        Arrays.fill(primes, true);
        for (int i = 2; i < primes.length; i++) {
            if (primes[i]) {
                for (int j = i * 2; j < primes.length; j += i) {
                    if (primes[j]) {
                        primes[j] = false;
                    }
                }
            }
        }
        for (int input : result.keySet()) {
            for (int i = 2; i <= input/2; i++) {
                if (primes[i]) {
                    if (primes[(input - i)]) {
                        final Set<Integer> integers = result.get(input);
                        integers.add(i);
                    }
                }
            }
        }
        result.forEach((key, value) -> {
            System.out.println(value.size());
        });
    }
}
