import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main {

    static int q, n, k, m, result;
    static StringBuilder sb = new StringBuilder();
    static Deque<Integer> deque = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        q = parseInt(br.readLine());
        q = 1;
        int cur = 1;

        while (q-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int c) throws IOException {
        final var arr = br.readLine().toCharArray();
        char[] list = new char[2500];
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            char cur = arr[i];
            char prev = findPrev(cur);
            boolean flag = false;
            for (int j = 0; j < max; j++) {
                if (list[j] == prev) {
                    list[j] = cur;
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }
            if (cur != 'q') {
                System.out.println(-1);
                return;
            }

            list[max] = 'q';
            max++;
        }
        for (int i = 0; i < max; i++) {
            if (list[i] != 'k') {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(max);
    }

    public static char findPrev(char c) {
        switch (c) {
            case 'q':
                return 'k';
            case 'u':
                return 'q';
            case 'a':
                return 'u';
            case 'c':
                return 'a';
            case 'k':
            default:
                return 'c';
        }
    }
}


