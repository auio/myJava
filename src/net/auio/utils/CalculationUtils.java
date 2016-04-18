package net.auio.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


/**
 * 计算相关
 * @author jh
 *
 */
public class CalculationUtils {
	
	/**数值类型*/
	public static List<String> NUMERALTYPES = Arrays.asList("int","Integer","float","Float","double","Double","BigDecimal");

	/**
	 * 数值类型相加
	 * "int","Integer","float","Float","double","Double","BigDecimal"
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> Object add(T a,T b){
		if(a==null||b==null)return a!=null?a:b;
		Class<?> cls = a.getClass();
		String type = cls.getSimpleName();
		if(!NUMERALTYPES.contains(type))return a;
		if("Integer".equals(type)||"int".equals(type)){
			return (Integer)a+(Integer)b;
		}
		else if("Float".equals(type)||"float".equals(type)){
			return (Float)a+(Float)b;
		}
		else if("Double".equals(type)||"double".equals(type)){
			return (Double)a+(Double)b;
		}
		else if("BigDecimal".equals(type)){
			Object t = new BigDecimal(0);
			Method m;
			try {
				m = BigDecimal.class.getDeclaredMethod("add",new Class[]{BigDecimal.class});
				t = m.invoke(t,a);
				t = m.invoke(t,b);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return t;
		}
		else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T sum(List<T> lst) throws InstantiationException, IllegalAccessException{
		if(lst==null||lst.size()==0)return null;
		Class<T> cls = (Class<T>) lst.get(0).getClass();
		T result =  cls.newInstance();
		Field[] fields = cls.getDeclaredFields();
		for(T item:lst){
			for (int i = 0; i < fields.length; i++) {
	            // 权限修饰符
	        	Field field = fields[i];
	        	field.setAccessible(true);
	            int mo = field.getModifiers();
	            String priv = Modifier.toString(mo);
	            if(!priv.contains("static")){
	            	field.set(result,add(field.get(result), field.get(item)));
	            }
	        }
		}
        
		return result;
	}
	
	public static void  main(String[] args) throws InstantiationException, IllegalAccessException{
		sum(Arrays.asList(new T("a",1,new BigDecimal(2)),new T("b",2,new BigDecimal(2.5)))).printName();
	}
}

class T{
	private String name;
	private float age;
	private BigDecimal t; 
	public T(String a,float b,BigDecimal c){
		name = a;
		age = b;
		t=c;
	}
	public T(){
	}
	
	public void printName(){
		System.out.println(name+" "+ age+t);
	}
}
