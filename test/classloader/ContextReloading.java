package classloader;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;

/**
 * 应用上下文热加载
 * Created with IntelliJ IDEA. User: linzhenhua Date: 2019/2/24 Time: 10:25 PM
 * @author linzhenhua
 *
 * 输出结果跟上一个例子相似，可以自己运行试试。我们更新业务方法编译通过后，无需重启main方法，
 * 新的业务就能生效，而且也解决了旧类卸载的核心问题，因为context的应用对象的跟节点，context是由我们自定义类加载器所加载，
 * 由于User/Dao/Service都是依赖context，所以其类也是由自定义类加载器所加载。
 * 根据GC roots原理，在创建新的自定义类加载器之后，旧的类加载器已经没有任何引用链可访达，符合GC回收规则，将会被GC收集器回收释放内存。
 * 至此已经完成应用热部署的流程，但是细心的朋友可能会发现，我们热部署的策略是整个上下文context都替换成新的，那么用户的状态也将无法保留。
 * 而实际情况是我们只需要动态更新某些模块的功能，而不是全局。
 * 这个其实也好办，就是我们从业务上把需要热部署的由自定义类加载器加载，而持久化的类资源则由系统默认类加载器去完成。
 */
public class ContextReloading {
    public static void main(String[] args)
            throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InstantiationException,
            InvocationTargetException, InterruptedException {
        for (;;){
            Object context = newContext();//创建应用上下文
            invokeContext(context);//通过上下文对象context调用业务方法
            Thread.sleep(5000);
        }
    }

    //创建应用的上下文，context是整个应用的GC roots，创建完返回对象之前调用init()初始化对象
    public static Object newContext()
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException,
            InvocationTargetException {
        String className = "classloader.ContextReloading$Context";
        //通过自定义类加载器加载Context类
        Class<?> contextClass = new MyClassLoader("E:\\WorkSpace\\JavaLearn\\test").loadClass(className);
        Object context = contextClass.newInstance();//通过反射创建对象
        contextClass.getDeclaredMethod("init").invoke(context);//通过反射调用初始化方法init()
        return context;
    }
    //业务方法，调用context的业务方法showUser()
    public static void invokeContext(Object context)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        context.getClass().getDeclaredMethod("showUser").invoke(context);
    }


    public static class Context{
        private UserService userService = new UserService();
        public String showUser(){
            return userService.getUserMessage();
        }
        //初始化对象
        public void init(){
            UserDao userDao = new UserDao();
            userDao.setUser(new User());
            userService.setUserDao(userDao);
        }
    }


    public static class UserService{
        private UserDao userDao;
        public String getUserMessage(){
            return userDao.getUserName();
        }
        public void setUserDao(UserDao userDao) {
            this.userDao = userDao;
        }
    }


    public static class UserDao{
        private User user;
        public String getUserName(){
            //关键操作，运行main方法后切换下面方法，编译后下一次调用生效
            return user.getName();
            //return user.getFullName();
        }
        public void setUser(User user) {
            this.user = user;
        }
    }

    public static class User{
        private String name = "lucy";
        private String fullName = "hank.lucy";

        public String getName() {
            System.out.println("my name is " + name);
            return name;
        }
        public String getFullName() {
            System.out.println("my full name is " + fullName);
            return name;
        }
    }

    //跟之前的类加载器一模一样，可以略过
    public static class MyClassLoader extends ClassLoader{
        private String classPath;
        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            if(!name.contains("java")){
                byte[] data = new byte[0];
                try {
                    data = loadByte(name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return defineClass(name,data,0,data.length);
            }else{
                return super.loadClass(name);
            }
        }
        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            String dir = classPath + "/" + name + ".class";
            FileInputStream fis = new FileInputStream(dir);
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }
    }
}
