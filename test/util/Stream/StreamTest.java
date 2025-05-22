package util.Stream;

import dto.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    public static void test(String[] args) {
        Double data[] = {1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0};
        long count = Arrays.stream(data).filter(i -> {
            System.out.println(i / 2);
            return  i.doubleValue() / 2 == 1;
        }).count();
        System.out.println(count);
    }

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setId("123");
        user.setName("测试1");
        user.setAge(22);
        User user1 = new User();
        user1.setId("1244");
        user1.setName("测试2");
        user1.setAge(24);
        User user2 = new User();
        user2.setId("125");
        user2.setName("测试3");
        user2.setAge(25);
        list.add(user);
        list.add(user1);
        list.add(user2);

        String[] strings = list.stream().map(e -> e.getName()).collect(Collectors.toList()).toArray(new String[list.size()]);
        for(int i = 0;i<strings.length;i++){
            System.out.println(strings[i]);
        }
    }
}
