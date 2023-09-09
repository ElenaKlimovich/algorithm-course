package day5;

import java.util.*;
import java.util.stream.Collectors;

public class LeetCodeSolution5 {

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

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

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

    /**
     * Your ATM object will be instantiated and called as such:
     * ATM obj = new ATM();
     * obj.deposit(banknotesCount);
     * int[] param_2 = obj.withdraw(amount);
     */

    public String reverseWords(String s) {
        if (s == null) {
            return null;
        }
        if(s.length() == 0) {
            return "";
        }
        Deque<String> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(1);
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
}
