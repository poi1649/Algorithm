import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    static int n, r, q;
    static Set<Integer> set = new HashSet<>();
    static ArrayList<Integer>[] graph;
    static StringBuilder sb = new StringBuilder();

    static List<int[]> vectors = List.of(new int[]{1, 0}, new int[]{-1, 0}, new int[]{0, 1}, new int[]{0, -1});


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            final String[] s =  br.readLine().split(" ");
            String instruct = s[0];
            int x;
            switch (instruct) {
                case "add":
                    x = parseInt(s[1]);
                    add(x);
                    break;
                case "remove":
                    x = parseInt(s[1]);
                    remove(x);
                    break;
                case "check":
                    x = parseInt(s[1]);
                    sb.append(check(x)).append("\n");
                    break;
                case "toggle":
                    x = parseInt(s[1]);
                    toggle(x);
                    break;
                case "all":
                    all();
                    break;
                case "empty":
                    empty();
                    break;
            }
        }
        System.out.print(sb);
    }
    static void add(int x) {
        set.add(x);
    }
    static void remove(int x) {
        set.remove(x);
    }
    static int check(int x) {
        return set.contains(x) ? 1 : 0;
    }
    static void toggle(int x) {
        if (set.contains(x)) {
            set.remove(x);
        } else {
            set.add(x);
        }
    }
    static void all() {
        set.clear();
        for (int i = 1; i <= 20; i++) {
            set.add(i);
        }
    }
    static void empty() {
        set.clear();
    }
}

