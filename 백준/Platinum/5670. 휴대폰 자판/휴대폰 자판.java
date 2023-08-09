import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int n;
    static Deque<String> deque = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String first;

        while ((first = reader.readLine()) != null) {
            n = parseInt(first);
            final Trie trie = new Trie();

            for (int i = 0; i < n; i++) {
                final String s = reader.readLine();
                deque.add(s);
                trie.insert(s);
            }

            int answer = 0;
            while (!deque.isEmpty()) {
                final String s = deque.poll();
                answer += trie.calculateNumberOfBranchesAndLastWordsUntilFind(s);
            }
            sb.append(String.format("%.2f\n", (double) answer / n));
        }
        System.out.print(sb);
    }

}

class Trie {

    Map<Character, Trie> children;
    boolean isEndOfWord;

    public Trie() {
        children = new HashMap<>();
        isEndOfWord = false;
    }

    public void insert(String word) {
        Trie current = this;
        final int length = word.length();
        for (int i = 0; i < length; i++) {
            current = current.children.computeIfAbsent(word.charAt(i), c -> new Trie());
        }
        current.isEndOfWord = true;
    }

    public int calculateNumberOfBranchesAndLastWordsUntilFind(String word) {

        int result = 1;
        final int length = word.length();
        Trie current = this;
        for (int i = 0; i < length; i++) {
            if (current.children.size() > 1 || current.isEndOfWord) {
                if (i != 0) {
                    result++;
                }
            }
            current = current.children.get(word.charAt(i));
        }
        return result;
    }
}


