import java.util.HashMap;


public class LRUCache {
    public int capacity;
    public Node head;
    public Node tail;
    public HashMap<Integer, Node> map;
    
    public class Node {
        public int key;
        public int val;
        public Node next;
        public Node prev;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<Integer, Node>(); 
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            moveToHead(map.get(key));
            return map.get(key).val;
        } else {
            return -1;
        }
    }
    
    public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node p = map.get(key);
            moveToHead(p);
            p.val = value;
        } else {
            if (map.size()==capacity) {
                removeLast();
            }
            Node n = new Node(key, value);
            moveToHead(n);
            map.put(key, n);
        }
    }
    
    public void moveToHead(Node p) {
        if (map.containsValue(p)) {
            if (head.next == p) {
                return;
            }
            p.prev.next = p.next;
            p.next.prev = p.prev;
            Node a = head.next;
            head.next = p;
            p.next = a;
            a.prev = p;
            p.prev = head;
        } else {
            Node a = head.next;
            head.next = p;
            p.next = a;
            a.prev = p;
            p.prev = head;
        }
    }
    
    public void removeLast() {
        if (tail.prev==head) {
            return;
        }
        Node a = tail.prev;
        Node b = a.prev;
        map.remove(a.key);
        b.next = tail;
        tail.prev = b;
    }
}
