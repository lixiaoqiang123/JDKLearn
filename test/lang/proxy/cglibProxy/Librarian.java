package lang.proxy.cglibProxy;

import java.util.HashMap;
import java.util.Map;

public class Librarian {

    private Map<String, Book> books = new HashMap<>();

    public void addBook(Book book) {
        String name = book.getBookName();
        if (!books.containsKey(name)) {
            books.put(name, book);
        }
    }

    public Book getBook(String name) {
        System.out.println("这里是 " + this.getClass().getName() + " 的getBook方法");
        return books.getOrDefault(name, null);
    }

}
