package util.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArraysTest {

    @Test
    public void asList(){
        // Arrays.asList()方法返回的是内部类，该内部类没有重写addAll，方法，所以使用addAll会报java.lang.UnsupportedOperationException
        //这行代码返回的不是一个真正可变的 ArrayList，而是一个固定大小的列表（不能 add/remove），这个实现来自于 Arrays 的内部类：Arrays$ArrayList。
        //List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        //将其包装成可变的ArrayList
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        ArrayList<Integer> collect = Stream.of(1, 2, 3, 4, 56).collect(Collectors.toCollection(ArrayList::new));
        integers.addAll(collect);
        System.out.println(integers);
    }
}
