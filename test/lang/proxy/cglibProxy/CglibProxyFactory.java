package lang.proxy.cglibProxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyFactory {

    public Object getProxy(Object targetObject) {

        Enhancer enhancer = new Enhancer();

        //Cglib代理基于创建子类重写父类方法实现，所以这里要确定父类，也就是被代理类。
        Class<?> superClass = targetObject.getClass();
        enhancer.setSuperclass(superClass);

        /*
        创建了一个MethodInterceptor拦截器接口的实现类对象，重写intercept回调方法，
        参数依次为：代理对象、代理方法、方法参数、方法代理
        */
        MethodInterceptor interceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("这是前置增强");
                Object res = methodProxy.invokeSuper(o, objects);
                System.out.println("这是后置增强");
                return res;
            }
        };

        enhancer.setCallback(interceptor);

        return enhancer.create();
    }

    public static void main(String[] args) {

        //这里设置一个系统属性，保存Cglib动态代理类的字节码文件
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "target/classes");

        //创建原始对象
        Librarian librarian = new Librarian();
        Book book = new Book();
        book.setBookName("钢铁是怎样炼成的");
        librarian.addBook(book);
        librarian.getBook("111");

        //创建代理对象
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory();
        Librarian librarianProxy = (Librarian) cglibProxyFactory.getProxy(librarian);
        librarianProxy.getBook("111");
    }
}
