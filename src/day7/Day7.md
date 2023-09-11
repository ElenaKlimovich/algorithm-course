### <a href="https://leetcode.com/problems/permutation-in-string/">567. Permutation in String</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null || "" -> false
"ab", s2 = "eidbaooo" -> true
s1 = "ab", s2 = "eidboaoo" -> false
complexity O(n), space O(n)
```
</blockquote></details>

``` java
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
```
### <a href="https://leetcode.com/problems/find-k-closest-elements/">658. Find K Closest Elements</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null || [] -> null
[1], k = 1, x = 1  -> [1]
[1,2,3,4,5], k = 4, x = 3  -> [1,2,3,4]
[1,2,3,4,5], k = 4, x = -1 -> [1,2,3,4]
complexity O(n), space O(1)
```
</blockquote></details>

``` java
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
```
### <a href="https://leetcode.com/problems/partition-labels/">763. Partition Labels</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null || "" -> null
"a" -> [1]
"ab" -> [1, 1]
"aa" -> [2]
"ababcbacadefegdehijhklij" -> [9,7,8]
complexity O(n), space O(n)
```
</blockquote></details>

``` java
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
```
### <a href="https://leetcode.com/problems/validate-binary-search-tree/">98. Validate Binary Search Tree</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null -> true
[2,1,3] -> true
[5,1,4,null,null,3,6] -> false

complexity O(n), space O(n)
```
</blockquote></details>

``` java
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
```
### <a href="https://leetcode.com/problems/lru-cache/">146. LRU Cache</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

complexity O(1), space O(capacity)
```
</blockquote></details>

``` java
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
```
