package net.auio.utils;

import java.math.BigDecimal;

import sun.awt.geom.AreaOp.AddOp;

/**
 * 计算相关
 * @author jh
 *
 */
public class CalculationUtils {
	public static Integer add(Integer a,Integer b){
		return a + b;
	}
	public static Float add(Float a,Float b){
		return a + b;
	}
	public static Double add(Double a,Double b){
		return a + b;
	}
	public static BigDecimal add(BigDecimal a,BigDecimal b){
		return a.add(b);
	}
	
//	public static <T> T add2(T a,T b){
//		class<?> a.getClass().
//		return add(a, b);
//	}
//	
//	public static void  main(String[] args){
//		System.out.println(add(2, 1.1));
//	}
}
