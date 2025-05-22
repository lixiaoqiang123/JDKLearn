package classloader;
import java.io.FileInputStream;
import static java.lang.System.out;

public class ClassIsolationPrinciple {
    public static void main(String[] args) {
        try {
            String className = "dto.User"; //定义要加载类的全限定名
            Class<?> class1 = Demo.class;  //第一个类又系统默认类加载器加载
            //第二个类MyClassLoader为自定义类加载器，自定义的目的是覆盖加载类的逻辑
            Class<?> class2 = new MyClassLoader("E:\\WorkSpace\\JavaLearn\\out\\production\\JavaLearn").loadClass(className);
            out.println("-----------------class name-----------------");
            out.println(class1.getName());
            out.println(class2.getName());
            out.println("-----------------classLoader name-----------------");
            out.println(class1.getClassLoader());
            out.println(class2.getClassLoader());
            Demo.example = 1;//这里修改的系统类加载器加载的那个类的对象，而自定义加载器加载进去的类的对象保持不变，也即是同时存在内存，但没有修改example的值。
            out.println("-----------------field value-----------------");
            out.println(class1.getDeclaredField("example").get(null));
            out.println(class2.getDeclaredField("example").get(null));
        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static class Demo {
        public static int example = 0;
    }

    public static class MyClassLoader extends ClassLoader{
        private String classPath;
        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }
        //自定义类加载器继承了ClassLoader，称为一个可以加载类的加载器，同时覆盖了loadClass方法，实现自己的逻辑
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            if(!name.contains("java.lang")){//排除掉加载系统默认需要加载的内心类，因为些类只能又默认类加载器去加载，第三方加载会抛异常，具体原因下文解释
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
        //把影片的二进制类文件读入内存字节流
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