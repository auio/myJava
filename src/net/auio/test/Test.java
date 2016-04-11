package net.auio.test;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	@Greeting(value="AA")  
	private String test;
	public static void main(String[] args) {
		String str = "2016-0003-19 19:0030:51";
		 System.out.println(filterDate(str,"00"));
	}
	
	public static String filterDate(String str,String flag){
//		String str = "2016-0003-19 19:0030:51";
		Pattern p = Pattern.compile("(\\d+)");
		Matcher m = p.matcher(str);
		ArrayList<String> lst = new ArrayList<String>();
		while (m.find()) {
			lst.add(m.group(1));
		}
		if(lst.size()==6){
			for(int i=1;i<6;i++){
				if(lst.get(i).length()==2+flag.length()){
					lst.set(i, lst.get(i).replaceFirst(flag, ""));
				}
			}
			str = String.format("%s-%s-%s %s:%s:%s", lst.toArray());
		}
		return str;
	}
	
	
	private static void getStrings() {
        String str = "rrwerqq84461376qqasfdasdfrrwerqq84461377qqasfdasdaa654645aafrrwerqq84461378qqasfdaa654646aaasdfrrwerqq84461379qqasfdasdfrrwerqq84461376qqasfdasdf";
        Pattern p = Pattern.compile("qq(.*?)qq");
        Matcher m = p.matcher(str);
        ArrayList<String> strs = new ArrayList<String>();
        while (m.find()) {
            strs.add(m.group(1));            
        } 
        for (String s : strs){
            System.out.println(s);
        }        
    }
	
	 private static String getSubStringDate(String str,int n){
	    	try{
	    		if(str!=null && str.length()>n){
	    			str = str.substring(0, n);
	        	}
	            return str;
	    	}
	    	catch(Exception e){
	    		return null;
	    	}
	    }
	
	@Greeting(value="Hello NUMEN.")  
    public static void sayHello() {  
  
        // do something  
  
    }  
	public static  String getName(Field f,String methodName){
		Annotation[] annotations = f.getAnnotations();
		if(annotations.length<1){
			return null;
		}
		for(Annotation a : annotations){
			if(a.annotationType().isAssignableFrom(Greeting.class)){
				try{
//					String name = (String) ReflectTools.invokeMethod(cls.newInstance(), methodName);
					String name = ((Greeting)a).value();
					System.out.println(f.getName());
					if(null==name || name.equals("")){
						
						return f.getName();
					}else{
						return name;
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return null;
	}


}

//public static String getValue(Field f){
//	Annotation[] annotations = f.getAnnotations();
//	if(annotations.length<1){
//		return null;
//	}
//	for(Annotation a : annotations){
//		if(a.annotationType().isAssignableFrom(ColumnField.class)){
//			String name = ((ColumnField)a).name();
//			if(null==name || name.equals("")){
//				return f.getName();
//			}else{
//				return name;
//			}
//		}
//	}
//	return null;
//}


@Documented  

@Retention(RetentionPolicy.RUNTIME)  
  
@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR,ElementType.FIELD })
  
@interface Greeting {  
	String value();
}  