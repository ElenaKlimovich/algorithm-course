### <a href="https://leetcode.com/problems/valid-parentheses/">20. Valid Parentheses</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null -> null
"" -> true
")" -> false
"()" -> true
"(]" -> false
"()[]{}" -> true
"([{}])" -> true
complexity O(n)
```
</blockquote></details>

``` java
    // I
    public boolean isValid(String s) {
        Deque<Character> tempStack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char br = s.charAt(i);
            if (br == '{' || br == '(' || br == '[') {
                tempStack.push(br);
            } else if (tempStack.isEmpty()) {
                return false;
            } else if (tempStack.element() == '{' && br == '}') {
                tempStack.pop();
            } else if (tempStack.element() == '(' && br == ')') {
                tempStack.pop();
            } else if (tempStack.element() == '[' && br == ']')
                tempStack.pop();
            else {
                return false;
            }
        }
        return tempStack.isEmpty();
    }
    
    // II
    public boolean isValid(String s) {
        if (s == null) {
            return true;
        }
        Deque<Character> tempStack = new LinkedList<>();
        Map<Character, Character> brMap = Map.of(
                '}', '{',
                ')', '(',
                ']', '[');
        for (int i = 0; i < s.length(); i++) {
            char br = s.charAt(i);
            if (br == '{' || br == '(' || br == '[') {
                tempStack.push(br);
            } else if (tempStack.peek() == brMap.get(br)) {
                tempStack.pop();
            }
            else {
                return false;
            }
        }
        return tempStack.isEmpty();
    }
```
### <a href="https://leetcode.com/problems/group-anagrams/">49. Group Anagrams</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null -> null
[] -> []
[""] -> [[""]]
["a"] -> [["a"]]
["eat","tea","tan","ate","nat","bat"] -> [["bat"],["nat","tan"],["ate","eat","tea"]]
complexity O(n*(k * log k))
```
</blockquote></details>

``` java
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null) {
            return null;
        }
        Map<String, List<String>> groups = new HashMap<>();
        for (String str : strs) {
            char[] letters = str.toCharArray();
            Arrays.sort(letters);
            String keyAnagram = String.valueOf(letters);
            groups.putIfAbsent(keyAnagram, new ArrayList<>());
            groups.get(keyAnagram).add(str);
        }
        return new ArrayList<>(groups.values());
    }
```
### <a href="https://leetcode.com/problems/maximum-product-of-three-numbers/">628. Maximum Product of Three Numbers</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null -> 0
[] -> 0
[1, 3, 5] -> 15
[1, 2, 3, 4] -> 24
[-1, 2, -3, -2] -> 12

// I - complexity O(n log(n)) (for sorting)

// II - complexity O(n)
```
</blockquote></details>

``` java
    // I
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[0]*nums[1]*nums[nums.length-1], 
                        nums[nums.length-3]*nums[nums.length-2]*nums[nums.length-1]);
    }
    
    // II
    public int maximumProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num <= min1) {
                min2 = min1;
                min1 = num;
            } else if (num <= min2) {
                min2 = num;
            }
            if (num >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num >= max2) {
                max3 = max2;
                max2 = num;
            } else if (num >= max3) {
                max3 = num;
            }
        }
        return Math.max(min1*min2*max1, max3*max2*max1);
    }
```
### <a href="https://leetcode.com/problems/maximum-average-subarray-i/">643. Maximum Average Subarray I</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null -> 0.0
[] -> 0.0
[5], k = 1 -> 5.00000
[1,12,-5,-6,50,3], k = 4 ->  12.75000
complexity O(n)
```
</blockquote></details>

``` java
    public double findMaxAverage(int[] nums, int k) {
        int maxSum = 0;
        for (int i=0; i<k; i++) {
            maxSum += nums[i];
        }
        int temp = maxSum;
        for (int i=k; i<nums.length; i++) {
            temp = temp + nums[i] - nums[i-k];
            if (temp > maxSum) {
                maxSum = temp;
            }
        }
        return (maxSum * 1.0) / k;
    }
```
### <a href="https://leetcode.com/problems/subarray-sum-equals-k/">560. Subarray Sum Equals K</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
[] -> 0
[1], k = 1  -> 1
[1,1,1], k = 2 -> 2
complexity O(n)
```
</blockquote></details>

``` java
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        for (int num: nums) {
            sum += num;
            count += preSum.getOrDefault(sum - k, 0);
            preSum.merge(sum, 1, Integer::sum);
        }
        return count;
    }
```
