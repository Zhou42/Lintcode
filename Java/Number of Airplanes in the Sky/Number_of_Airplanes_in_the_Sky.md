# Number of Airplanes in the Sky
这道题需要知道每个time point是start还是end，只需要定义一个新的point的类即可

```java
    // 这种方法 为了区别每个point是start还是end，多用了两个堆或者队列
    // 实际只要定义一种新的类point, 里面包含flag即可
    
    // public int countOfAirplanes(List<Interval> airplanes) { 
    //     // write your code here
    //     Queue<Integer> points = new PriorityQueue<Integer>();
    //     Queue<Integer> start_points = new PriorityQueue<Integer>();
    //     Queue<Integer> end_points = new PriorityQueue<Integer>();
    //     int num_planes = 0;
    //     int max = 0;
    //     // O(n)
    //     for (Interval airplane : airplanes) {
    //         points.offer(airplane.start);
    //         points.offer(airplane.end);
    //         start_points.offer(airplane.start);
    //         end_points.offer(airplane.end);
    //     }
        
    //     // O(nlong(n))... 跟排序的复杂度一样 为什么排序超时 － 这里其实就是堆排序
    //     while (!points.isEmpty()) {
    //         int point = points.poll();
    //         if (end_points.contains(point)) {
    //             num_planes--;
    //             end_points.poll();
    //         } else {
    //             num_planes++;
    //             // only after increase, can num_planes results in the new max value
    //             if (num_planes > max) {
    //                 max = num_planes;
    //             }
    //             start_points.poll();
    //         } 
    //     }
    //     return max;
    // }
    
    class Point {
        int time, flag;
        Point(int time, int flag) {
            this.time = time;
            this.flag = flag;
        }
    }
    
    class pointComparator implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            if (p1.time == p2.time) {
                return p1.flag - p2.flag;
            } else {
                return p1.time - p2.time;
            }
        }
    }

    public int countOfAirplanes(List<Interval> airplanes) { 
        List<Point> points = new ArrayList<>();
        int count = 0;
        int max = 0;
        for (Interval airplane : airplanes) {
            points.add(new Point(airplane.start, 1));
            points.add(new Point(airplane.end, 0));
        }
        
        Collections.sort(points, new pointComparator());
        for (Point point : points) {
            if (point.flag == 1) {
                count++;
                // max value can only appear after increase！！
                // 所以只需要check increase之后的值即可！
                // 原因是，increase之前 比之后小；decrease之后，比之前小；而decrease之前，等价于前面某个increase之后的值(因为上升下降的step为1)，所以已经check过，不需要check
                if (count > max) {
                    max = count;
                }
            } else {
                count--;
            }
        }
        return max;
    }

```