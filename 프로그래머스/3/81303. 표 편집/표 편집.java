import java.util.*;

class Solution {
    
    class Node {
        int v;
        Node prev;
        Node next;
        
        Node (int v) {
            this.v = v;
        }
        
        public void delete() {
            prev.next = next;
            next.prev = prev;
            check[v]= true;
        }
        
        public void restore() {
            prev.next = this;
            next.prev = this;
            check[v] = false;
        }
        
        public Node getNext() {
            return next;
        }
        
        public Node getPrev() {
            return prev;
        }
        
        public void addNext(Node o) {
            next.prev = o;
            o.next = next;
            
            this.next = o;
            o.prev = this;
        }
    }
    
    static Deque<Node> deleted = new LinkedList<>();
    
    static boolean[] check = new boolean[1000000];
    
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        Node head = new Node(-1);
        Node tail = new Node(1_000_000);
        
        Node cur = head;
        cur.next = tail;
        tail.prev = head;
        
        Node tmp = head;
        
        for (int i = 0; i < n; i ++) {
            Node next = new Node(i);
            if (i == k) {
                tmp = next;
            }
            cur.addNext(next);
            cur = cur.next;
        } 
        
        cur = tmp;
        
        for (int i = 0; i < cmd.length ; i++ ) {
            char[] cm = cmd[i].toCharArray();
            char a = cm[0];
            
            if (a == 'C') {
                tmp = cur;
                if (cur.getNext() == tail) {
                    cur = cur.getPrev();
                }
                else {
                    cur = cur.getNext();
                }
                
                tmp.delete();
                deleted.addLast(tmp);
                continue;
            }
            if (a == 'Z') {
                Node polled = deleted.pollLast();
                polled.restore();
                continue;
            }
            if (a == 'U') {
                int x = Integer.parseInt(cmd[i].split(" ")[1]);
                for (int j = 0; j< x; j++) {
                    cur = cur.getPrev();
                }
                continue;
            }
            int x = Integer.parseInt(cmd[i].split(" ")[1]);
            for (int j = 0; j< x; j++) {
                cur = cur.getNext();
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (check[i]) {
                sb.append("X");
                continue;
            }
            sb.append("O");
        }
        return sb.toString();
    }
}