### <a href="https://leetcode.com/problems/merge-two-sorted-lists/">21. Merge Two Sorted Lists</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null, null -> null
[], [] -> []
[], [2] -> [2]
[1,2,4], [1,3,4] -> [1,1,2,3,4,4]

complexity O(n * m)
```
</blockquote></details>

``` java
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
```

### <a href="https://leetcode.com/problems/squares-of-a-sorted-array">977. Squares of a Sorted Array</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null -> null
[] -> []
[2] -> [4]
[2, 3] -> [4, 9]
[-3, -1] -> [1, 9] 
[-4,-1,0,3,10] -> [0,1,9,16,100]

complexity O(n)
```
</blockquote></details>


``` java
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
```

### <a href="https://leetcode.com/problems/add-two-numbers/">2. Add Two Numbers</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
[0], [0] -> [0]
[2,4,3], [5,6,4] -> [7,0,8]
[9,9], [9,9] -> [8,9,1]

complexity O(n * m)
```
</blockquote></details>


``` java
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
```

### <a href="https://leetcode.com/problems/reverse-linked-list/">206. Reverse Linked List</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
null -> null
[] -> []
[2] -> [2]
[1, 0, 5] - > [5, 0 ,1]

complexity O(n)
```
</blockquote></details>

``` java
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
```

### <a href="https://leetcode.com/problems/insert-delete-getrandom-o1">380. Insert Delete GetRandom O(1)</a>

<details><summary>Test Cases & Big O</summary><blockquote>

``` 
insert 1 -> true
remove 2 -> false
insert 2 -> true
getRandom -> 2 || 1
remove 1 -> true
insert 2 -> false
getRandom -> 2

complexity: insert - O(1), remove - O(1), getRandom - O(1)
```
</blockquote></details>

``` java
    class RandomizedSet {

        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        Random random = new Random();

        public RandomizedSet() {

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
```
