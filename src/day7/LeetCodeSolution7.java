package day7;

import java.util.*;

public class LeetCodeSolution7 {

    public boolean checkInclusion(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() > s2.length() || s1.length() == 0) {
            return false;
        }
        int l1 = s1.length();
        int l2 = s2.length();

        int[] chars1 = new int[26];
        for (int i=0; i<l1; i++) {
            chars1[s1.charAt(i) - 'a']++;
        }

        for (int i=0; i<=l2-l1; i++) {
            String subStr = s2.substring(i, i+l1);
            int[] chars2 = new int[26];
            for(int j=0; j<l1; j++) {
                chars2[subStr.charAt(j) - 'a']++;
            }
            if(arraysAreSame(chars1, chars2)) {
                return true;
            }
        }
        return false;
    }

    private boolean arraysAreSame(int[] chars1, int[] chars2) {
        for (int i=0; i<chars1.length; i++) {
            if(chars1[i] != chars2[i]) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int l = 0;
        int r = arr.length - 1;
        while (r - l >= k) {
            if(Math.abs(arr[l] - x) > Math.abs(arr[r] - x)) {
                l++;
            } else {
                r--;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i=l; i<=r; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    public List<Integer> partitionLabels(String s) {
        if(s == null || s.length() == 0) {
            return null;
        }
        Map<Character, Integer> letters = new HashMap<>();
        for (int i=0; i<s.length(); i++){
            letters.put(s.charAt(i), i);
        }
        int prev = -1;
        int max = 0;
        List<Integer> result = new ArrayList<>();
        for (int i=0; i<s.length(); i++) {
            max = Math.max(max, letters.get(s.charAt(i)));
            if (max == i) {
                result.add(max - prev);
                prev = max;
            }
        }
        return result;
    }

    public boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }

    private boolean isValid(TreeNode root, Integer min, Integer max) {
        if(root == null) {
            return true;
        }
        return (min == null || min < root.val) && (max == null || max > root.val)
                && isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }


    /**
     * LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    class LRUCache {

        private int capacity;
        Map<Integer, Integer> cache;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new LinkedHashMap<>();
        }

        public int get(int key) {
            if(cache.containsKey(key)) {
                int val = cache.get(key);
                cache.remove(key);
                cache.put(key, val);
                return val;
            }
            return -1;
        }

        public void put(int key, int value) {
            if(cache.containsKey(key)) {
                cache.remove(key);
            }
            else if(cache.size() == capacity) {
                cache.remove(cache.entrySet().iterator().next().getKey());
            }
            cache.put(key, value);
        }
    }
}
