### <a href="https://leetcode.com/problems/generate-parentheses/">22. Generate Parentheses</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
0 -> [""]
1 -> [()]
2-> [()(), (())]
complexity O(n), space O(n)
```
</blockquote></details>

``` java
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(result, "", 0, 0, n);
        return result;
    }

    private void generate(List<String> result, String s, int openN, int closeN, int n) {
        if(s.length() == 2 * n) {
            result.add(s);
            return;
        }
        if(openN < n) {
            System.out.println(s);
            generate(result, s+"(", openN+1, closeN, n);
        }
        if(closeN < openN) {
            System.out.println(s);
            generate(result, s+")", openN, closeN+1, n);
        }
    }
```
### <a href="https://leetcode.com/problems/merge-k-sorted-lists/">23. Merge k Sorted Lists</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null || [] -> null 
[[]] -> []
[[1]] -> [1]
[[1], [2]] -> [1, 2]
[[1, 2], [2]] -> [1, 2, 2]
[[1,4,5],[1,3,4],[2,6]] -> [1,1,2,3,4,4,5,6]
complexity O(k * n), space O(1)
```
</blockquote></details>

``` java
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        for (int i=1; i<lists.length; i++) {
            lists[0] = merge(lists[0], lists[i]);
        }
        return lists[0];
    }

    private ListNode merge(ListNode ln1, ListNode ln2) {
        ListNode temp = new ListNode();
        ListNode current = temp;
        while(ln1 != null && ln2 != null) {
            if(ln1.val < ln2.val) {
                current.next = ln1;
                ln1 = ln1.next;
            } else {
                current.next = ln2;
                ln2 = ln2.next;
            }
            current = current.next;
        }
        
        if (ln1 != null) {
            current.next = ln1;
        } else {
            current.next = ln2;
        }
        return temp.next;
    }
```
### <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">3. Longest Substring Without Repeating Characters</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null || "" -> -1
"a" -> 1
"aa" -> 1
"ab" -> 2
"abcabcbb" -> 3
complexity O(n), space O(n)
```
</blockquote></details>

``` java
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        int max = 0;
        int i = 0;
        int j = 0;
        Set<Character> window = new HashSet<>();

        while (j < s.length()) {
            if (!window.contains(s.charAt(j))) {
                window.add(s.charAt(j++));
                max = Math.max(max, window.size());
            }
            else
                window.remove(s.charAt(i++));
        }
        return max;
    }
```
### <a href="https://leetcode.com/problems/symmetric-tree/">101. Symmetric Tree</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null -> false
[1] -> true
[1,2,2,3,4,4,3] -> true
[1,2,2,null,3,null,3] -> false
complexity O(n), space O(n)
```
</blockquote></details>

``` java
    public boolean isSymmetric(TreeNode root) {
        return areNodesSymmetric(root, root);
    }

    boolean areNodesSymmetric(TreeNode n1, TreeNode n2) {
        if(n1 == null && n2 == null)
            return true;
        if(n1 == null || n2 == null)
            return false;
        return (n1.val == n2.val && areNodesSymmetric(n1.left, n2.right) && areNodesSymmetric(n2.left, n1.right));
    }
```
### <a href="https://leetcode.com/problems/min-stack/">155. Min Stack</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

complexity O(1), space O(n)
```
</blockquote></details>

``` java
class MinStack {

    private Deque<Integer> stack;
    private int min;

    public MinStack() {
        stack = new LinkedList<>();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int val) {
        if (val <= min) {
            stack.push(min);
            min = val;
        }
        stack.push(val);
    }
    
    public void pop() {
        if (min == stack.peek()) {
            stack.pop();
            min = stack.pop();
        } else {
            stack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}
```
