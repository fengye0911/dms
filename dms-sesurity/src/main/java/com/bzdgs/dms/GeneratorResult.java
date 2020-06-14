package com.bzdgs.dms;

import com.bzdgs.dms.enums.CommonEnumClass;
import com.bzdgs.dms.enums.ResponseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName:ResponseEntityGenerator <br/>
 * Function: 通用的返回结果工具. <br/>
 * Date:
 * @author   lzy
 * @version
 * @since    JDK 1.7
 * @see
 */
public class GeneratorResult {
    /**
	 * 日志对象
	 */
	private final static Logger logger = LoggerFactory.getLogger(GeneratorResult.class);
	
	/**
     * 组装通用的返回结果对象
     * @param succ
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseEntity<T> genResult(int succ, T data, String message) {
    	ResponseEntity<T> result = new ResponseEntity<T>(succ, message, data);
        
        if (logger.isDebugEnabled()) {
        	logger.debug("generate rest result:{}", result);
        }
        
        return result;
    }

    /**
     * 组装通用的返回结果对象
     * @param code
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseEntity<T> genResult(String code, T data, String message) {
    	ResponseEntity<T> result = new ResponseEntity<>(code, message, data);
        if (logger.isDebugEnabled()) {
        	logger.debug("generate rest result:{}", result);
        }
        return result;
    }

    /**
     * genResult:(创建通用返回结果). <br/> 
     * @author hechu 
     * @param succ
     * @param message
     * @return 
     * @since JDK 1.7
     */
    public static ResponseEntity<String> genResult(int succ, String message) {
    	return genResult(succ, null, message);
    }

    /**
     * genResult:(创建通用返回结果). <br/>
     * @author caohuan
     * @param code
     * @param message
     * @return
     * @since JDK 1.7
     */
    public static ResponseEntity<String> genResult(String code, String message) {
    	return genResult(code, null, message);
    }

    /**
     * 返回成功的结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseEntity<T> genSuccessResult() {
        return genResult(CommonEnumClass.CommonInterfaceEnum.SUCCESS.getKey(),null,CommonEnumClass.CommonInterfaceEnum.SUCCESS.getValue());
    }

    /**
     * 返回失败结果
     * @param message error message
     * @param <T>
     * @return
     */
    public static <T> ResponseEntity<T> genErrorResult() {
        return genResult(ResponseEnum.FAIL.getCode(), null, ResponseEnum.FAIL.getMessage());
    }
}
  