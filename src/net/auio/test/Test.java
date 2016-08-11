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

	public static void main(String[] args) {
		int i = 0;  
        Object a = new Object();
        String s = "1";
        String s1 = "2";
        System.out.println(a.hashCode());
        System.out.println(s.hashCode());
        System.out.println(s1.hashCode());
	}
	
}