package day3;

import java.util.*;

public class LeetCodeSolution3 {

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

    public boolean isValid2(String s) {
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

    public List<List<String>> groupAnagrams(String[] strs) {
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

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[0]*nums[1]*nums[nums.length-1],
                        nums[nums.length-3]*nums[nums.length-2]*nums[nums.length-1]);
    }

    public int maximumProduct2(int[] nums) {
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
}
