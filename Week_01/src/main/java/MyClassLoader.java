import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {

    private String path;

    public MyClassLoader(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = new byte[0];
        try {
            data = readFile(this.path);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return defineClass(name, data, 0, data.length);
    }

    /**
     * 读取文件字节码并转码
     *
     * @param classPath
     * @return
     */
    private byte[] readFile(String classPath) {
        File classFile = new File(classPath);
        InputStream file = null;
        ByteArrayOutputStream stream = null;
        byte[] bytes = new byte[1024];
        byte[] readBytes = null;

        try {
            stream = new ByteArrayOutputStream();
            file = new FileInputStream(classFile);
            int length;

            while ((length = file.read(bytes)) != -1) {
                stream.write(bytes, 0, length);
            }
            readBytes = stream.toByteArray();
            for (int i = 0; i < readBytes.length; i++) {
                readBytes[i] = (byte) (255 - Integer.valueOf(readBytes[i]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readBytes;
    }

    public static void main(String[] args) {
        String path = "/Users/huangdan/Documents/workSpace/java0/JAVA-000/Week_01/src/main/java/Hello.xlass";
        MyClassLoader myClassLoader = new MyClassLoader(path);
        try {
            Class<?> clazz = myClassLoader.findClass("Hello");
            Method mtd = clazz.getMethod("hello");
            mtd.invoke(clazz.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
