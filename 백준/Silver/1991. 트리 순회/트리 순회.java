import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static int n;
    static int m;
    static StringBuilder sb = new StringBuilder();
    static Deque<Integer> deque = new LinkedList<>();
    static char[][] tree;
    static boolean[] visited;
    static int[] result;
    static int[] indexes;
    static int longest = 0;
    static int longestIndex = 1;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tree = new char[n][2];

        for (int i = 0; i < n; i++) {
            final char[] s = br.readLine().replaceAll(" ", "").toCharArray();
            Arrays.fill(tree[s[0] - 'A'], '0');
            if (s[1] != '.') {
                tree[s[0] - 'A'][0] = s[1];
            }
            if (s[2] != '.') {
                tree[s[0] - 'A'][1] = s[2];
            }
        }
        preorder(0);
        System.out.println(sb);

        sb = new StringBuilder();
        inorder(0);
        System.out.println(sb);

        sb = new StringBuilder();
        postorder(0);
        System.out.println(sb);
    }

    static void preorder(int target) {
        sb.append(Character.toChars(target + 'A'));
        if (tree[target][0] != '0') {
            preorder(tree[target][0] - 'A');
        }
        if (tree[target][1] != '0') {
            preorder(tree[target][1] - 'A');
        }
    }

    static void inorder(int target) {
        if (tree[target][0] != '0') {
            inorder(tree[target][0] - 'A');
        }
        sb.append(Character.toChars(target + 'A'));
        if (tree[target][1] != '0') {
            inorder(tree[target][1] - 'A');
        }
    }

    static void postorder(int target) {
        if (tree[target][0] != '0') {
            postorder(tree[target][0] - 'A');
        }
        if (tree[target][1] != '0') {
            postorder(tree[target][1] - 'A');
        }
        sb.append(Character.toChars(target + 'A'));
    }
}

class Node {

    Node left;
    Node right;
    String value;

    public Node(Node left, Node right, String value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }
}


