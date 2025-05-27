package util.collection.list;

import java.util.List;

@FunctionalInterface
interface FunctionTest<T> {

    boolean containElement(T t, List<T> list);

}
