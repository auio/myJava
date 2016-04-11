package net.auio.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;



/**
 * 反射相关
 * @author jh
 *
 */
public class ReflectUtils {
	    
	/**
	 * 创建对象
	 * @param cls	
	 * @param params 构造函数参数
	 * @return
	 */
	public static <T> T newInstance(Class<T> cls,Object ...params){
		try {
			return  getConstructor(cls, params).newInstance(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据参数获取适当的构造函数
	 * @param cls
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <T> Constructor<T> getConstructor(Class<T> cls,Object ...params){
		Constructor<?>[] cons=cls.getDeclaredConstructors();
		for(int i=0;i<cons.length;i++){
			Class<?> [] parameterTypes = cons[i].getParameterTypes();
			if(params.length==parameterTypes.length){
				int j = 0;
				while(j<parameterTypes.length){
					if(!parameterTypes[j].isAssignableFrom(params[j].getClass()))
						break;
					j++;
				}
				if(j==parameterTypes.length)
					return (Constructor<T>) cons[i];
			}
			
		}
		return null;
	}
	
	/**
	 * 根据熟悉名称获取属性值
	 * @param bean
	 * @param name
	 * @return
	 */
	public static <T> Object getValue(T bean,String name){
		Field field = getField(bean.getClass(), name);
		try {
			return field.get(bean);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static <T> void setValue(T bean,String name,Object value){
		Field field = getField(bean.getClass(), name);
		try {
			field.set(bean,value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取属性域
	 * @param cls
	 * @param name
	 * @return
	 */
	public static Field getField(Class<?> cls,String name){
		 Field[] fs = cls.getDeclaredFields();  
	     for(int i = 0 ; i < fs.length; i++){
	    	 Field f = fs[i];  
	         f.setAccessible(true);
	    	 if(f.getName().equals(name))return f;
	     }
	     return null;
	}
	
	
    public static void main(String[] args){
    	Test aTest = new Test("dfdf");
    	Test.class.isPrimitive();
    	setValue(aTest, "age", 11);
    	System.out.println(getValue(aTest, "age"));
    	System.out.println("sdfgf");

    }
}

class Test{
	private String name;
	private float age;
	public Test(List<String> a){
		name = a.get(0);
	}
	public Test(String a){
		name = a;
	}
	public Test(Integer a){
		age = a;
	}
	public Test(){
		name = "a";
	}
	public void printName(){
		System.out.println(name+" "+ age);
	}
}