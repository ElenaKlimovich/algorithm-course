package day6;

import java.util.*;

public class LeetCodeSolution6 {

    public int maxDistToClosest(int[] seats) {
        if (seats == null || seats.length == 0) {
            return -1;
        }
        int lZero = -1;
        int rZero = -1;
        int max = -1;
        int curZero = 0;

        for (int s: seats) {
            if (s == 0) {
                curZero++;
            } else {
                if (lZero == -1) {
                    lZero = curZero;
                } else {
                    max = Math.max(max, curZero);
                }
                curZero = 0;
            }
        }
        rZero = curZero;
        return Math.max(Math.max(lZero, rZero), (max + 1) / 2);
    }


    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        if (A == null || B == null) {
            return null;
        }
        int l = A.length;
        int[] result = new int[l];
        int[] numbersOfArrays = new int[l+1];
        for (int i=1; i<=l; i++) {
            numbersOfArrays[A[i-1]]++;
            numbersOfArrays[B[i-1]]++;

            int count = 0;
            for (int j=0; j<=l; j++) {
                if (numbersOfArrays[j] == 2) {
                    count++;
                }
            }
            result[i-1] = count;
        }
        return result;
    }

    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || p == null) {
            return null;
        }
        int l1 = s.length();
        int l2 = p.length();
        if(l1 < l2) {
            return List.of();
        }
        int[] pLetters = new int[26];
        fill(p, pLetters);
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<l1 && i+l2<=l1; i++) {
            int[] subLetters = new int[26];
            fill(s.substring(i, i+l2), subLetters);
            if(isSameArray(pLetters, subLetters)) {
                result.add(i);
            }
        }
        return result;
    }

    private void fill(String s, int[] letters) {
        for(char c: s.toCharArray()) {
            letters[c - 'a']++;
        }
    }

    private boolean isSameArray(int[] arr1, int[] arr2) {
        for (int i=0; i<arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            int n = q.size();
            List<Integer> levelValues = new ArrayList<>();
            for (int i=0; i<n; i++) {
                TreeNode currentNode = q.remove();
                levelValues.add(currentNode.val);
                if (currentNode.left != null) {
                    q.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    q.add(currentNode.right);
                }
            }
            result.add(levelValues);
        }
        return result;
    }

    public int numIslands(char[][] grid) {
        if (grid == null) {
            return -1;
        }
        System.out.println();
        int n = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    setMark(grid, i, j);
                    n++;
                }
            }
        }
        return n;
    }

    private void setMark(char[][] grid, int i, int j) {
        if (i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        setMark(grid, i-1, j);
        setMark(grid, i+1, j);
        setMark(grid, i, j-1);
        setMark(grid, i, j+1);
    }
}
