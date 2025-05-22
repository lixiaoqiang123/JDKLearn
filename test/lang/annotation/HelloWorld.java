package lang.annotation;

/**
 * 注解其实就相当于一个标记，根据标记属性的不同进行不同的处理
 *
 * vm options : -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true 保留生成的proxy代理类
 */
@AnnotationTest("hello")
public class HelloWorld {

    private static String word = "TRUTH";
    /**
     * 接口本质的具体实现类是Java 运行时生成的动态代理类。而我们通过反射获取注解时，返回的是Java 运行时生成的动态代理对象$Proxy1。
     * 通过代理对象调用自定义注解（接口）的方法，会最终调用AnnotationInvocationHandler 的invoke方法。
     * 该方法会从memberValues 这个Map 中索引出对应的值。而memberValues 的来源是Java 常量池
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hello World");
        Class<HelloWorld> helloWorldClass = HelloWorld.class;
        AnnotationTest annotation = helloWorldClass.getAnnotation(AnnotationTest.class);  //通过反射，返回注解的代理类$Proxy1 （JavaLearn.com.sun.proxy.$Proxy1）
        //生成代理类的时候，会创建AnnotationInvocationHandler，注入到代理类中，里面会有memberValues保存属性及属性对应的值
        String value = annotation.value();  //调用代理类的invoke方法(AnnotationInvocationHandler.invoke)，invoke方法中会根据方法名称，返回相应的属性值，然后根据不同的属性值进行不同的处理
        if("hello".equals(value)){  //相当于对该注解的对象进行一定的处理，如果不进行处理，其实注解是相当于没有作用
            word = "LIE";
        }
        System.out.println("annotation value:"+value+"--------word:"+word);
    }
}
