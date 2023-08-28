package day1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCodeSolution1 {

    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }

        int k = 0;
        for (int i=0; i<nums.length; i++) {
            if(nums[i] != 0) {
                if (i != k) {
                    nums[k] = nums[i];
                    nums[i] = 0;
                }
                k++;
            }
        }
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        int left = 0;
        int rigth = s.length() - 1;
        char leftCh, rigthCh;
        while (left <= rigth) {
            leftCh = Character.toLowerCase(s.charAt(left));
            rigthCh = Character.toLowerCase(s.charAt(rigth));
            if (!Character.isLetterOrDigit(leftCh)) {
                left++;
            } else if (!Character.isLetterOrDigit(rigthCh)) {
                rigth--;
            } else {
                if (leftCh != rigthCh) {
                    return false;
                }
                left++;
                rigth--;
            }
        }
        return true;
    }

    public int compress(char[] chars) {
        if(chars == null || chars.length == 0) {
            return 0;
        }
        int p = 0;
        int i = 0;
        while (i < chars.length) {
            int currCount = 0;
            char current = chars[i];
            while (i < chars.length && current == chars[i]) {
                currCount++;
                i++;
            }
            chars[p++] = current;
            if (currCount > 1) {
                String numStr = String.valueOf(currCount);
                int k = 0;
                while (k < numStr.length()) {
                    chars[p++] = numStr.charAt(k++);
                }
            }
        }

        System.out.println(chars);
        return p;
    }

    public List<String> summaryRanges(int[] nums) {
        if (nums == null) {
            return null;
        }
        if (nums.length == 0) {
            return List.of();
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i<nums.length; i++) {
            int n = nums[i];
            while(i < nums.length - 1 && nums[i] + 1 == nums[i+1]) {
                i++;
            }
            if (n != nums[i]) {
                result.add(n + "->" + nums[i]);
            } else {
                result.add(String.valueOf(n));
            }
        }
        return result;
    }

    public int[] twoSum(int[] nums, int target) {
        if (nums == null) {
            return null;
        }
        if (nums.length == 0) {
            return new int[0];
        }
        int[] result = new int[2];
        int n = nums.length;
        Map<Integer, Integer> tempMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if(tempMap.containsKey(target - nums[i])) {
                result[0] = i;
                result[1] = tempMap.get(target - nums[i]);
            }
            tempMap.put(nums[i], i);
        }
        return result;
    }
}