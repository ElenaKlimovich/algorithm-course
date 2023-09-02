package day4;

import java.util.*;

public class LeetCodeSolution4 {

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

    public int[] intersect(int[] nums1, int[] nums2) {
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
}
