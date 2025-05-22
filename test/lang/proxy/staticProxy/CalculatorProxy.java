package lang.proxy.staticProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CalculatorProxy {

    /**
     * 静态代理
     *
     * 通过静态代理，我们达到了功能增强的目的，而且没有侵入原代码，这是静态代理的一个优点。
     *
     * 静态代理的缺点
     *     虽然静态代理实现简单，且不侵入原代码，但是，当场景稍微复杂一些的时候，静态代理的缺点也会暴露出来。
     *
     * 1、 当需要代理多个类的时候，由于代理对象要实现与目标对象一致的接口，有两种方式：
     *        只维护一个代理类，由这个代理类实现多个接口，但是这样就导致代理类过于庞大
     *        新建多个代理类，每个目标对象对应一个代理类，但是这样会产生过多的代理类
     * 2、 当接口需要增加、删除、修改方法的时候，目标对象与代理类都要同时修改，不易维护。
     */
    public static Calculator getProxy(final Calculator calculator){
        ClassLoader loader = calculator.getClass().getClassLoader();
        Class<?>[] interfaces = calculator.getClass().getInterfaces();
        InvocationHandler h = new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("进入代理-----");
                Object result = null;
                try {
                    result = method.invoke(calculator, args);
                } catch (Exception e) {
                } finally {
                }
                System.out.println("代理结束-----");
                return result;
            }
        };
        Object proxy = Proxy.newProxyInstance(loader, interfaces, h);
        return (Calculator) proxy;
    }
}
