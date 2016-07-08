# A list of the solutions 
http://www.geeksforgeeks.org/k-largestor-smallest-elements-in-an-array/
## 我的方法是其中 Method 5 (Use Oder Statistics)
http://www.lintcode.com/en/problem/top-k-largest-numbers/

先找第k大的数 

1. 使用了quicksort partition，先任意选择一个元素，这里用第一个元素作key，把所有数值分成：大于等于／小于 两组。
2. check使用的key的位置
3. 递归找到第k大的数target

再找前k大的数

1. 把大于target的放入result
2. check 如果发现result不足k个，说明有重复的等于target的数值；加入target直到result够k个数

## 做其他的方法
1. 尝试geeksforgeeks上的方法
2. 试试九章上的quicksort方法