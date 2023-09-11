package day7;

public class TestLRU {

    static LRUCache test = new LRUCache(2);

    public static void main(String[] args) {
        test.put(1,1);
        test.put(2,2);
        test.get(1);
        test.put(3,3);
        test.get(2);
        test.put(4,4);
    }
}
