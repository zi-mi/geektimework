package ning.geek.jvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {

	public static void main(String[] args) {

		try {
			
			Class<?> clazz = new HelloClassLoader().findClass("Hello");
			Method method = clazz.getMethod("hello");
			method.invoke(clazz.newInstance());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {

		Class clazz = null;
		String classFilename = name + ".xlass";
		File classFile = new File("src/ning/geek/jvm/" + classFilename);
		if (classFile.exists()) {
			try {
				
				InputStream in = new FileInputStream(classFile);
				byte[] bytes = new byte[in.available()];
				int tembyte;
				int i = 0;
				while ((tembyte = in.read()) != -1) {
					bytes[i] = (byte) (255 - tembyte);
					i++;
				}
				
				in.close();
				clazz = defineClass(name, bytes, 0, bytes.length);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (clazz == null) {
			throw new ClassNotFoundException(name);
		}
		return clazz;
	}

}
