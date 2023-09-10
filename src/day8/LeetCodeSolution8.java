package day8;

import java.util.*;

public class LeetCodeSolution8 {

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
}
