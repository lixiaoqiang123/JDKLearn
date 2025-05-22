package lang.proxy.staticProxy;

public class StaticProxyTest {

    public static void main(String[] args) {
        Calculator proxy = CalculatorProxy.getProxy(new MyCalculator());
        int add = proxy.add(1, 1);
        System.out.println(add);
        System.out.println(proxy.getClass());
    }

}
