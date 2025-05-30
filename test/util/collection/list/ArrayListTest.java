package util.collection.list;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrayListTest {

    /**
     * 题目 1：添加和遍历
     * 编写一个 Java 方法，向一个 ArrayList<String> 中添加如下 5 个字符串："Java", "Python", "C++", "Go", "Rust"，
     * 并使用 for-each 和 for 两种方式输出。
     *
     *  List是有序的，实现了Iterable接口，所以支持for-each循环，本质是使用iterator
     */
    @Test
    public void addAndForeach(){
        ArrayList<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("C++");
        list.add("Go");
        list.add("Rust");
        //for-each loop
        System.out.println("for-each loop");
        for(String element : list){
            System.out.println(element);
        }
        //for循环，根据索引循环
        System.out.println("for loop");
        for(int i = 0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }

    /**
     * 题目 2：删除元素
     * 给定一个 List<Integer>，其中包含若干整数。请删除其中所有小于 10 的元素（注意避免 ConcurrentModificationException）。
     */
    @Test
    public void deleteElement(){
        //Java 9 之后可以使用of方法创建list
        //List<Integer> exapmle = new ArrayList<>(List.of(5, 12, 3, 18, 7, 25));
        //使用Stream的collect创建
        List<Integer> numbers = Stream.of(5, 12, 3, 18, 7, 25).collect(Collectors.toList());
        //方法一：使用removeIf方法;底层也是迭代器
        numbers.removeIf(e->e<10);
        System.out.println(numbers);
        //一步一步 add
        ArrayList<Integer> list = new ArrayList<>();
        list.add(15);
        list.add(8);
        list.add(9);
        list.add(11);
        list.add(12);
        //实际返回的是ArrayList中的Itr对象
        /**
         * 迭代器的底层原理
         * 4. 为什么要 checkForComodification？
         *          Java 集合类是非线程安全的；
         *          Iterator 使用 expectedModCount 与集合的实际 modCount 进行比对；
         *          如果中途集合结构被改变（比如调用了 list.remove()），modCount 变了 → 抛出 ConcurrentModificationException。
         *          这是一种典型的 fail-fast 机制。
         */
        //方法二：使用迭代器
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            //只有调用了next()方法，lastRet才会赋值
            if(iterator.next()<10){
                //直接使用remove方法会报IllegalStateException
                iterator.remove();
            }
        }
        System.out.println(list);

        //方法三：使用倒序索引遍历（避免跳过元素）
        //Java 8 可以使用Arrays.asList()创建集合
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(5, 12, 3, 18, 7, 25));
        for(int i = integers.size()-1;i>=0;i--){
            if(integers.get(i)<10){
                integers.remove(i);
            }
        }
        System.out.println(integers);
        //正序索引遍历删除
        //与倒序索引遍历有区别，由于删除元素后，列表长度缩短，后续元素左移，而索引 i 仍然递增，导致跳过了下一个元素。
        ArrayList<Integer> positiveSequence = new ArrayList<>(Arrays.asList(5, 2, 3, 1));
        for(int i = 0;i<positiveSequence.size();i++){
            if(positiveSequence.get(i)<10){
                positiveSequence.remove(i);
            }
        }
        System.out.println(positiveSequence);
        //for-each loop 会报ConcurrentModifyException
        //为什么？
        /**
         * for-each 循环使用的是底层的迭代器（Iterator）实现，
         * 而你直接在循环中调用 list.remove()，不是通过迭代器的 remove() 方法来删除的。
         * 这会破坏迭代器的结构一致性检查机制，从而抛出 ConcurrentModificationException。
         */
        ArrayList<Integer> forEachList = new ArrayList<>(Arrays.asList(5, 12, 3, 18, 7, 25));
        for (Integer n : forEachList) {
            if (n < 10) {
                forEachList.remove(n);
            }
        }
    }

    /**
     * 题目 3：元素查找
     * 给定一个 List<String>，判断是否包含元素 "Java"，如果包含，输出其索引。
     */
    @Test
    public void containElement(){
        List<String> list = Arrays.asList("Python", "Test", "Java", "Winner");
        if(list.contains("Java")){
            System.out.println(list.indexOf("Java"));
        }else {
            System.out.println("Java string is not found");
        }

        //使用 Stream 查找索引（不推荐查索引，因为 Stream 不支持索引）：
        //如果你真的想结合 Stream，可以搭配 IntStream.range(...) 使用：
        IntStream.range(0, list.size())  // 遍历 list 的索引：0 ~ list.size()-1
                .filter(i -> "Java".equals(list.get(i)))  // 过滤出值为 "Java" 的索引
                .forEach(i -> System.out.println("Found at index: " + i));  // 打印这些索引

        //使用自定义函数式接口实现
        FunctionTest<String> functionTest = (t,u)->u.contains(t);
        boolean contain = functionTest.containElement("Java", list);
        System.out.println(contain);
    }


    /**
     * 题目 4：去重并排序
     * 编写方法，接收一个 List<Integer>，返回一个去重后从小到大排序的新列表。
     */
    @Test
    public void deduplicateAndSort(){
        //使用TreeSet实现去重并排序
        List<Integer> list = Arrays.asList(1,4,5,2,7,23,452,12,3,2,4,5);
        TreeSet<Integer> integers = new TreeSet<>(list);
        System.out.println(integers);
        //使用Stream流去重并排序
        List<Integer> list2 = Arrays.asList(1,4,5,2,7,23,452,12,3,2,4,5);
        ArrayList<Integer> collect = list2.stream().distinct().sorted().collect(Collectors.toCollection(ArrayList::new));
        System.out.println(collect);
        //降序排序
        List<Integer> list3 = Arrays.asList(1,4,5,2,7,23,452,12,3,2,4,5);
        // b - a 是简单整数比较法，但当数据很大时可能会溢出。
        //更安全的写法是： return Integer.compare(o2, o1);
        //ArrayList<Integer> collect2 = list3.stream().distinct().sorted((a,b)->b-a).collect(Collectors.toCollection(ArrayList::new));
        //Comparator.reverseOrder()   Comparator.naturalOrder()
        ArrayList<Integer> collect2 = list3.stream().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(collect2);
        //保留顺序 + 去重
        List<Integer> list4 = Arrays.asList(1,4,5,2,7,23,452,12,3,2,4,5);
        LinkedHashSet<Integer> collect3 = new LinkedHashSet<>(list4);
        System.out.println(collect3);
        //仅排序不去重
        List<Integer> list5 = Arrays.asList(1,4,5,2,7,23,452,12,3,2,4,5);
        Collections.sort(list5);
        System.out.println(list5);
        //仅去重不排序
        List<Integer> list6 = Arrays.asList(1,4,5,2,7,23,452,12,3,2,4,5);
        LinkedHashSet<Integer> collect4 = new LinkedHashSet<>(list6);
        System.out.println(collect4);
    }

    /**
     * 题目 5：交集操作
     * 给定两个 List<String>，找出它们的交集（保留相同元素），要求不修改原始列表。
     */
    @Test
    public void getIntersection(){
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1,2,3,4,6,7,8));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(3,5,6,2,1,7,8));
        //retainAll() 取两个集合中都有的-交集  removeAll() 取传入集合中没有的
        //该方法使用了读写两个指针，在原数组进行操作，效率要比remove高
        //“相比于在遍历中逐个 remove，retainAll() 底层使用的是批量处理算法（batchRemove），只需要遍历一遍原始数据，
        // 通过读写指针优化内存操作，同时根据入参类型（Set vs List）进行条件性优化，性能和 GC 行为都更优。”
        //remove操作会导致后边的数据往前移动，会进行 System.arraycopy（）操作
        list2.retainAll(list1);
        System.out.println(list2);
        System.out.println(list1);
        //使用stream流
        //list1.stream().filter(list2::contains).collect(Collectors.toList());
    }

    /**
     * 题目 6：统计出现频率
     * 编写方法，统计一个 List<String> 中每个字符串出现的次数，并返回 Map<String, Integer>。
     */
    @Test
    public void statistic(){
        //自己实现
        List<String> list = Arrays.asList("J","A","C","G","H","J","C","G");
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        HashSet<String> uniqueString = new HashSet<>(list);
        for(String key : uniqueString){
            result.put(key,0);
        }
        for(String key : list){
            if(result.containsKey(key)){
                result.put(key,result.get(key)+1);
            }
        }
        System.out.println(result);
        //优化
        List<String> list2 = Arrays.asList("J","A","C","G","H","J","C","G");
        HashMap<String, Integer> result2 = new HashMap<String, Integer>();
        for(String key : list2){
            result2.put(key,result2.getOrDefault(key,0)+1);
        }
        System.out.println(result2);
        //使用Stream流
        List<String> list3 = Arrays.asList("J","A","C","G","H","J","C","G");
        Map<String, Long> collect = list3.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        System.out.println(collect);
    }

    /**
     * 题目 7：模拟 ArrayList 扩容机制
     * 写一个类 MyArrayList，实现简单的动态数组逻辑，包括 add() 和 get() 方法，并支持自动扩容（可设置初始容量为 2，每次扩容为原容量的 1.5 倍）。
     */



    /**
     * 题目 8：手动实现 fail-fast 行为
     * 写一个类 MyList<E>，内部维护一个 modCount 字段，每次结构修改（add/remove）都会 +1。
     * 在 MyIterator 的 next() 方法中校验是否与初始 modCount 一致，如果不一致抛出异常。
     */

    /**
     * 题目 9：线程安全 List 编程题
     * 使用 CopyOnWriteArrayList 写一个多线程读写程序，模拟多个线程并发读，少量线程写，观察线程安全是否正常。
     */
}
