# Building Outline
暂时用PriorityQueue; 要学HashHeap的实现，但是这个面试会现场实现？？
改动也容易，把PriorityQueue替换为HashHeap就OK了

```java
public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
     
    // 暂时用PriorityQueue; 要学HashHeap的实现，但是这个面试会现场实现？？
    final static int START = 0;
    final static int END = 1;
    class Point {
        int x, type, height;
        Point(int x, int type, int height) {
            this.x = x;
            this.type = type;
            this.height = height;
        }
    }
    class mycomparator1 implements Comparator<Integer> {
        public int compare(Integer i1, Integer i2) {
            return i2 - i1;
        }
    }
    class mycomparator2 implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            return p1.x - p2.x;
        }
    }
    public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        // 这道题先用PriorityQueue做，再看九章的答案 应该不能直接用PriorityQueue吧。。。
        // 
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return new ArrayList<ArrayList<Integer>>();
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(100, new mycomparator1());
        List<Point> list = new ArrayList<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        for (int i = 0; i < buildings.length; i++) {
            list.add(new Point(buildings[i][0], START, buildings[i][2]));
            list.add(new Point(buildings[i][1], END, buildings[i][2]));
        }
        Collections.sort(list, new mycomparator2());
        int curtHeight = 0, curtStart = 0;
        for (int i = 0; i < list.size(); i++) {
            // 在p.x 处应该处理完该处所有点 remove所有end 加入所有start 然后再判断height
            // 如果只有一个元素 相同，则while直接跳过
            while (i < list.size() - 1 && list.get(i).x == list.get(i + 1).x) {
                if (list.get(i).type == START) {
                    pq.offer(list.get(i).height);
                } else {
                    pq.remove(list.get(i).height);
                }
                i++;
            }
            // 此时x坐标相同的元素中，就剩最后一个元素没有处理
            if (list.get(i).type == START) {
                pq.offer(list.get(i).height);
            } else {
                pq.remove(list.get(i).height);
            }
            
            if (pq.isEmpty()) {
                // 这个是目前点之后，紧接着是空白，那么就一种情况
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(curtStart);
                temp.add(list.get(i).x);
                temp.add(curtHeight);
                res.add(temp);
                curtHeight = 0;
            } else {
                // 这个是目前点后面还有building，则有三种情况, 穷举一下 分类讨论即可
                if (curtHeight == 0) {
                    curtStart = list.get(i).x;
                    curtHeight = pq.peek();
                } else if (pq.peek() != curtHeight) {
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(curtStart);
                    temp.add(list.get(i).x);
                    temp.add(curtHeight);
                    res.add(temp);
                    curtStart = list.get(i).x;
                    curtHeight = pq.peek();
                } else {
                    
                }
            }
            
            // if (p.type == START) {
            //     // add to max heap; 
            //     // 如果高度变化 and curtHeight != 0，则加入结果 改变curtHeight 改变curtStart
                
            //     // start 一共有三种情况，穷举一下 分类讨论
            //     if (pq.isEmpty()) {
            //         if (curtStart == p.x) {
            //             // 接上了
                        
            //         } else {
            //             curtStart = p.x;
            //             curtHeight = p.height;
            //         }
                    
            //     } else if (pq.peek() != curtHeight) {
            //         ArrayList<Integer> temp = new ArrayList<Integer>();
            //         temp.add(curtStart);
            //         temp.add(p.x);
            //         temp.add(curtHeight);
            //         res.add(temp);
            //         curtStart = p.x;
            //         curtHeight = pq.peek();
            //     } else {
            //         // do nothing
            //     }
            //     pq.offer(p.height);
            // } else {
            //     // remove from max heap
            //     // 如果高度变化，则加入结果； 如果之后curtHeight不是0(pq不为空)，则改变curtStart
            //     pq.remove(p.height);
            //     if (pq.isEmpty()) {
            //         if (i < buildings.length - 1 && list.get(i + 1).x == p.x && curtHeight == list.get(i + 1).height) {
            //             // 接上了, curtHeight和curtStart都不变
            //         } else {
            //             ArrayList<Integer> temp = new ArrayList<Integer>();
            //             temp.add(curtStart);
            //             temp.add(p.x);
            //             temp.add(curtHeight);
            //             res.add(temp);
            //         }
            //         // curtHeight = 0;
            //     } else if (pq.peek() != curtHeight) {
            //         ArrayList<Integer> temp = new ArrayList<Integer>();
            //         temp.add(curtStart);
            //         temp.add(p.x);
            //         temp.add(curtHeight);
            //         res.add(temp);
            //         curtStart = p.x;
            //         curtHeight = pq.peek();
            //     } else {
            //         // do nothing
            //     }
            // }
        }
        return res;
    }
}

```
