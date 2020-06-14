package com.bzdgs.dms.enums;

/**
 * ClassName: ResponseEnum <br/>
 * Description: 应答结果枚举对象. <br/>
 * Date: 2016年3月19日 下午4:49:49 <br/>
 *
 * @author Chunjie He
 * @version 1.0.0
 * @since JDK 1.7
 */
public enum ResponseEnum {

	/**
	 * 请求处理成功
	 */
	SUCC(0, "请求处理成功"),
	
	/**
	 * 默认请求处理失败
	 */
	FAIL(1, "请求处理失败");
	
	/**  
     * 相应编码
     */  
    private int code;
    
    /**  
     * 响应描述
     */  
    private String message;
    
    /**
     * 私有的构造方法
     * @param code
     * @param value
     */
    private ResponseEnum(int code, String message) {  
        this.code = code;
        this.message = message;
    } 
    
    /**
     * 通过编号获取日志类型描述
     * @param code
     * @return
     */
    public static String getMessage(int code) {  
        for (ResponseEnum re : ResponseEnum.values()) {
            if (re.getCode() == code) {
                return re.getMessage();
            }
        }
        
        return "";
    }  
  
    public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
