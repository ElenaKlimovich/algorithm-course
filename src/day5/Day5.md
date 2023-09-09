### <a href="https://leetcode.com/problems/binary-search/">704. Binary Search</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null || [] -> -1
[1], 1 -> 0
[1], 2 -> -1
[-1, 2], 2 -> 1
[-1,0,3], 3 -> 2 

complexity O(log n), space O(1)
```
</blockquote></details>

``` java
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int l = 0;
        int r = nums.length ;

        while (l <= r) {
            int mid = (r + l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }
```

### <a href="https://leetcode.com/problems/maximum-depth-of-binary-tree/">104. Maximum Depth of Binary Tree</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null || [] -> 0
[1] -> 1
[1,null,2] -> 2
[3,9,20,null,null,15,7] -> 3

complexity O(n), space O(n)
```
</blockquote></details>

``` java
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
```
### <a href="https://leetcode.com/problems/design-an-atm-machine/">2241. Design an ATM Machine</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
Input
["ATM", "deposit", "withdraw", "deposit", "withdraw", "withdraw"]
[[], [[0,0,1,2,1]], [600], [[0,1,0,1,1]], [600], [550]]
Output
[null, null, [0,0,1,0,1], null, [-1], [0,1,0,0,1]]

complexity O(n) space O(n)
```
</blockquote></details>

``` java
    class ATM {

        private int[] nominals;
        private long[] saves;

        public ATM() {
            nominals = new int[] {20, 50, 100, 200, 500};
            saves = new long[5];
        }

        public void deposit(int[] banknotesCount) {
            for(int i=0; i<saves.length; i++) {
                saves[i] += banknotesCount[i];
            }
        }

        public int[] withdraw(int amount) {
            int[] result = new int[5];
            for (int i=nominals.length-1; i>=0; i--) {
                if(amount>=nominals[i]) {
                    int banknotesCounter = (int)Math.min(amount/nominals[i], saves[i]);
                    result[i] = banknotesCounter;
                    amount -= banknotesCounter * nominals[i];
                }
            }
            if (amount != 0) {
                return new int[]{-1};
            } else {
                for(int i=0; i< result.length; i++) {
                    saves[i] -= result[i];
                }

                return result;
            }
        }
    }
```

### <a href="https://leetcode.com/problems/find-the-difference-of-two-arrays/">2215. Find the Difference of Two Arrays</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null -> null
[1], [] -> [[1], []]
[1], [2] -> [[1], [2]]
[1], [1] -> [[], []]
[1,2,3], [2,4,6] -> [[1,3],[4,6]]
[1,2,3,3], [1,1,2,2] -> [[3],[]]
complexity O(n + m)
```
</blockquote></details>

``` java
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null) {
            return null;
        }
        
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());

        for (int i : nums2) {
            set1.remove(i);
        }

        Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());

        for (int i : nums1) {
            set2.remove(i);
        }

        return List.of(new ArrayList<>(set1), new ArrayList<>(set2));
    }
```

### <a href="https://leetcode.com/problems/reverse-words-in-a-string/">151. Reverse Words in a String</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null -> null
"" -> ""
"EPY2giL"-> "EPY2giL"
"the sky is blue" -> "blue is sky the"
"  hello world  " -> "world hello"
"a good   example" -> "example good a"

complexity O(n), space - O(n)
```
</blockquote></details>

``` java
    public String reverseWords(String s) {
        if (s == null) {
            return null;
        }
        if(s.length() == 0) {
            return "";
        }
        Deque<String> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            } else {
                if (!"".equals(sb.toString())) {
                    stack.push(sb.toString());
                }
                sb = new StringBuilder();
            }
        }
        stack.push(sb.toString());
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop()).append(" ");
        }
        return result.toString().trim();
    }
```
