package day2;

import java.util.*;

public class LeetCodeSolution2 {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode result = new ListNode(Integer.MIN_VALUE);
        ListNode pointer = result;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                pointer.next = list1;
                list1 = list1.next;
            } else {
                pointer.next = list2;
                list2 = list2.next;
            }
            pointer = pointer.next;
        }

        pointer.next = list1 == null ? list2 : list1;
        return result.next;
    }

    public int[] sortedSquares(int[] nums) {
        if (nums == null) {
            return null;
        }
        if (nums.length == 0) {
            return new int[0];
        }

        int[] result = new int[nums.length];
        int left = 0, right = nums.length - 1;
        int k = nums.length - 1;
        while (left <= right) {
            if (Math.abs(nums[left]) < Math.abs(nums[right])) {
                result[k--] = nums[right] * nums[right];
                right--;
            } else {
                result[k--] = nums[left] * nums[left];
                left++;
            }
        }
        return result;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode pointer = result;
        int temp = 0;
        while (temp > 0 || l1 != null || l2 != null) {
            int currentSum = 0;
            if (l1 != null) {
                currentSum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                currentSum += l2.val;
                l2 = l2.next;
            }
            currentSum += temp;
            temp = currentSum / 10;
            pointer.next = new ListNode(currentSum % 10);
            pointer = pointer.next;
        }
        return result.next;
    }

    public ListNode reverseList(ListNode head) {
        ListNode reversed = null;

        while(head != null) {
            ListNode tempNext = head.next;
            head.next = reversed;
            reversed = head;
            head = tempNext;
        }
        return reversed;
    }

    class RandomizedSet {

        private Map<Integer, Integer> map;
        private List<Integer> list;
        private Random random;

        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
            random = new Random();
        }

        public boolean insert(int val) {
            boolean result = !map.containsKey(val);
            if (result) {
                map.put(val, list.size());
                list.add(val);
            }
            return result;
        }

        public boolean remove(int val) {
            Integer removedIndex = map.remove(val);
            if (removedIndex != null) {
                Integer lastVal = list.remove(list.size() - 1);
                if (val != lastVal) {
                    list.set(removedIndex, lastVal);
                    map.put(lastVal, removedIndex);
                }
            }
            return removedIndex != null;
        }

        public int getRandom() {
            int rndIndex = random.nextInt(list.size());
            return list.get(rndIndex);
        }
    }
}