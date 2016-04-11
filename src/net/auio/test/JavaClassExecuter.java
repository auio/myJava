package net.auio.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

public class JavaClassExecuter {
	public static String execute(byte[] classByte){
		HackSystem.clearBuffer();
		ClassModifier cm = new ClassModifier(classByte);
		byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", "/home/jh/workspace/myJava/src/net/auio/test/HackSystem");
		
		HotSwapClassLoader loader = new HotSwapClassLoader();
		Class clazz = loader.loadByte(modiBytes);
		try{
			Method method = clazz.getMethod("main", new Class[]{String[].class});
			method.invoke(null, new String[]{null});
		}catch(Throwable e){
			e.printStackTrace();
		}
		return HackSystem.getBufferString();
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println("aa");
		JavaClassExecuter a = new JavaClassExecuter();
		InputStream is = new FileInputStream("/home/jh/tmp/Test.class");
		byte[] b = new byte[is.available()];
		is.read(b);
		is.close();
		a.execute(b);
	}
}
