### <a href="https://leetcode.com/problems/top-k-frequent-words/"></a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null -> null
[] -> []
["a"], k = 1 -> ["a"]
["a", "b"], k = 2 -> ["a", "b"]
["i","love","leetcode","i","love","coding"], k = 2 -> ["i","love"]
["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4 -> ["the","is","sunny","day"]
complexity O(n log n)
```
</blockquote></details>

``` java
    public List<String> topKFrequent(String[] words, int k) {
        if (words == null) {
            return null;
        }
        if (words.length == 0) {
            return List.of();
        }
            
        Map<String, Integer> frMap = new HashMap<>();
        for (String w : words) {
            frMap.merge(w, 1, Integer::sum);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((v1, v2) -> {
            if (v2.getValue() == v1.getValue()) {
                return v1.getKey().compareTo(v2.getKey());
            }
            return v2.getValue() - v1.getValue();
        });
        pq.addAll(frMap.entrySet());

        List<String> res = new ArrayList<>();
        while (k-- > 0) {
            res.add(pq.poll().getKey());
        }
        return res;
    }
```
### <a href="https://leetcode.com/problems/intersection-of-two-arrays-ii/"></a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null -> null
[], [] -> [] 
[1], [] -> []
[1], [2] -> []
[1], [1] -> [1]
[1,2,2,1], [2,2] -> [2,2]

complexity O(n+m)
```
</blockquote></details>

``` java
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null && nums2 == null) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> resultList = new ArrayList<>();
        for (int n: nums1) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        for (int n: nums2) {
            if (map.containsKey(n) && map.get(n) > 0) {
                resultList.add(n);
                map.put(n, map.get(n) - 1);
            }
        }
        int[] result = new int[resultList.size()];
        for (int i=0; i<resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
```

### <a href="https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/"></a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null || [] -> 0
[1] -> 0
[1,0] -> 1
[1,1] -> 1 
[1,1,0,1] -> 3
[0,1,1,1,0,1,1,0,1] -> 5
complexity O(n)
```
</blockquote></details>

``` java
    public int longestSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int current = 0;
        int prev = 0;
        int result = 0;
        for (int n: nums) {
            if (n == 1) {
                current++;
            } else {
                result = Math.max(result, current + prev);
                prev = current;
                current = 0;
            }
        }

        result = Math.max(result, current + prev);
        if (result == nums.length) {
            result = result - 1;
        }
        return result;
    }
```


### <a href="https://leetcode.com/problems/valid-palindrome-ii/"></a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null -> null
"" -> true
"a" -> true
"aba" -> true
"abca" -> true
"abc" -> false

complexity O(n)
```
</blockquote></details>

``` java
    public boolean validPalindrome(String s) {
        if(s == null) {
            return false;
        }
        int left = 0; 
        int right = s.length() - 1;
        while(left <= right) {
            if(s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return checkIfSubstringIsPalindrome(s, left, right-1) || checkIfSubstringIsPalindrome(s, left+1, right);
            }
        }
        return true;
    }

    private boolean checkIfSubstringIsPalindrome(String s, int left, int right) {
        while (left <= right) {
            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
```

### <a href="https://leetcode.com/problems/merge-intervals/"></a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null -> null
[[1,4],[4,5]] -> [[1,5]]
[[1,3],[2,6],[8,10],[15,18]] -> [[1,6],[8,10],[15,18]]
complexity O(nlogn)
```
</blockquote></details>

``` java
    public int[][] merge(int[][] intervals) {
        if (intervals == null) {
            return null;
        }
        if (intervals.length == 0) {
            return new int[0][0];
        }
        
        Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[0]));
        List<int[]> res = new ArrayList<>();
        int[] newInterval = intervals[0];
        res.add(newInterval);
        for (int[] current : intervals) {
            if (newInterval[1] >= current[0])
                newInterval[1] = Math.max(newInterval[1], current[1]);
            else {
                newInterval = current;
                res.add(newInterval);
            }
        }

        return res.toArray(new int[res.size()][]);
    }
```
