package org.framework.customutil;

/** 
 * Object Utils 
 *  
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2011-10-24 
 */  
public class ObjectUtils {  
  
    /** 
     * compare two object 
     *  
     * @param actual 
     * @param expected 
     * @return <ul> 
     *         <li>if both are null, return true</li> 
     *         <li>return actual.{@link Object#equals(Object)}</li> 
     *         </ul> 
     */  
    public static boolean isEquals(Object actual, Object expected) {  
        return actual == expected || (actual == null ? expected == null : actual.equals(expected));  
    }  
  
    /** 
     * convert long array to Long array 
     *  
     * @param source 
     * @return 
     */  
    public static Long[] transformLongArray(long[] source) {  
        Long[] destin = new Long[source.length];  
        for (int i = 0; i < source.length; i++) {  
            destin[i] = source[i];  
        }  
        return destin;  
    }  
  
    /** 
     * convert Long array to long array 
     * @param source 
     * @return 
     */  
    public static long[] transformLongArray(Long[] source) {  
        long[] destin = new long[source.length];  
        for (int i = 0; i < source.length; i++) {  
            destin[i] = source[i];  
        }  
        return destin;  
    }  
  
    /** 
     * convert int array to Integer array 
     * @param source 
     * @return 
     */  
    public static Integer[] transformIntArray(int[] source) {  
        Integer[] destin = new Integer[source.length];  
        for (int i = 0; i < source.length; i++) {  
            destin[i] = source[i];  
        }  
        return destin;  
    }  
  
    /** 
     * convert Integer array to int array 
     * @param source 
     * @return 
     */  
    public static int[] transformIntArray(Integer[] source) {  
        int[] destin = new int[source.length];  
        for (int i = 0; i < source.length; i++) {  
            destin[i] = source[i];  
        }  
        return destin;  
    }  
  
    /** 
     * compare two object 
     * @param v1 
     * @param v2 
     * @return 
     */  
    @SuppressWarnings({"unchecked", "rawtypes"})  
    public static <V> int compare(V v1, V v2) {  
        return v1 == null ? (v2 == null ? 0 : -1) : (v2 == null ? 1 : ((Comparable)v1).compareTo(v2));  
    } 
    
    /**
     * @功能 检测是否为null.<br>
     * @param obj 检测的值.<br>
     * @return
     */
    public static boolean isNull(Object obj){
    	if(obj==null){
    		return true;
    	}
    	return false;
    }
    
    /**
     * @功能 检测是否为空值.<br>
     * @param obj 传入的参数.<br>
     * @return 
     */
    public static boolean isNotEmpty(Object obj){
    	if(obj==null||obj.equals("")){
    		return false;
    	}
    	return true;
    }
}  
