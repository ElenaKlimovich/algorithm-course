package day7;

import java.util.HashMap;
import java.util.Map;

class ListNode {
    int key;
    int val;
    ListNode next;
    ListNode prev;

    public ListNode(int key, int val) {
        this.key = key;
        this.val = val;
        this.next = null;
        this.prev = null;
    }
}


public class LRUCache {

    private Map<Integer, ListNode> cache;
    private int capacity;
    private ListNode head;
    private ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new ListNode(0, 0);
        tail = new ListNode(0,0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(cache.containsKey(key)) {
            ListNode cur = cache.get(key);
            deleteNode(cur);
            addNode(cur);
            return cur.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)) {
            deleteNode(cache.get(key));
        }
        if(capacity == cache.size()) {
            deleteNode(tail.prev);
        }
        addNode(new ListNode(key, value));
    }

    private void deleteNode(ListNode n) {
        cache.remove(n.key);
        n.next.prev = n.prev;
        n.prev.next = n.next;
    }

    private void addNode(ListNode n) {
        cache.put(n.key, n);
        n.next = head.next;
        n.prev = head;
        head.next = n;
        head.next.prev = n;
    }
}

