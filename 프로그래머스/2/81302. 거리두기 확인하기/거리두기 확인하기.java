import java.util.*;

class Solution {
    
    int[] px = {0 ,0, 1 , -1};
    int[] py = {1, -1, 0, 0};
    
    class Node {
        int x, y, count;
        
        Node (int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for (int i = 0; i < places.length ; i++) {
            answer[i] = check(places[i]);
        }
        return answer;
    }
    
    public int check(String[] input) {
        char[][] arr = new char[5][5];
        for(int i = 0; i < input.length; i++) {
            arr[i] = input[i].toCharArray();
        }
        Deque<Node> q = new LinkedList<>();
        for(int i = 0; i < arr.length; i++) {
            for (int j =0; j <arr[i].length; j++) {
                if (arr[i][j] == 'P') {
                    q.addLast(new Node(i, j, 0));
                    if (!checkTwo(arr, q)) {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }
    
    public boolean checkTwo(char[][] arr, Deque<Node> q) {
        boolean checked[][] =new boolean[5][5];
        while(!q.isEmpty()) {
            Node cur = q.pollFirst();
            checked[cur.x][cur.y] = true;
            for (int i =0; i < 4; i++) {
                int dx = cur.x + px[i];
                int dy = cur.y + py[i];
                if (0 > dx || dx >= 5 || 0 > dy || dy >= 5 || checked[dx][dy]) {
                    continue;
                }
                checked[dx][dy] = true;
                if (arr[dx][dy] == 'P') {
                    return false;
                }
                if (arr[dx][dy] == 'O' && cur.count < 1) {
                    q.addLast(new Node(dx, dy, cur.count+1));
                }
            }
        }
        return true;
    }
}