package org.framework.customutil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
/**
 * 
 * 项目名称：dt-贵州交建项目建设运营养护管理系统
 * 公司名称：贵州交建信息科技有限公司
 * 类名称：ValidateUtil   
 * 类描述：   使用正则表达式验证输入格式
 * 创建人：郑成功   
 * 创建时间：2016-7-2 下午2:31:51   
 * 修改人：郑成功   
 * 修改时间：2016-7-2 下午2:31:51   
 * 修改备注：   
 * @version
 */
public class ValidateUtil {
	
	private static boolean flag = false;
	
    public static void main(String[] args) {
    }
    
    /**
     * 
     * @Title: check
     * @Description: 检测验证通用方法
     * @param str
     * @param regex
     * @return
     */
    private static boolean check(String str, String regex) {
    	 try {
    	     Pattern pattern = Pattern.compile(regex);
    	     Matcher matcher = pattern.matcher(str);
    	     flag = matcher.matches();
    	 } catch (Exception e) {
    	     flag = false;
    	 }
    	 return flag;
    }
    
    /**
     * 
     * @Title: checkEmail
     * @Description: 验证邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
    	String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    	return  check(email, check);       
    }
     
    /**
     * 
     * @Title: checkMobileNumber
     * @Description: 验证手机号码
     *   移动：134、135、136、137、138、139、150、151、157(TD)、158、159、183、187、188
     *   联通：130、131、132、152、155、156、185、186
     *   电信：133、153、180、189、177、（1349卫通）
     * 
     * @param mobileNumber
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber){
    	//String check = "^(((13[0-9])|(15([0-3]|[5-9]))|(18([0-3]|[5-9])))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";
    	String check = "^[1][3,4,5,7,8][0-9]{9}$";
    	return  check(mobileNumber, check);       
    }
    
    /**
     * 验证固话号码
     * 
     * @param telephone
     * @return
     */
    public static boolean checkTelephone(String telephone) {
		 String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
		 return  check(telephone, regex);
    }

    /**
     * 验证传真号码
     * 
     * @param fax
     * @return
     */
    public static boolean checkFax(String fax) {
		 String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$"; 
		 return check(fax, regex);
    }

    /**
     * 验证QQ号码
     * 
     * @param QQ
     * @return
     */
    public static boolean checkQQ(String QQ) {
		 String regex = "^[1-9][0-9]{4,} $";
		 return check(QQ, regex);
    }

}
