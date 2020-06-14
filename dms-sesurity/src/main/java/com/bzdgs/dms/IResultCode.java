package com.bzdgs.dms;

/**
 * @ClassName: IResultCode
 * @Description: 通用错误码接口
 * @Author: caohuan
 * @Date: 2018/5/25 18:10
 * @Version: 1.0
 **/
public interface IResultCode {

    /**
     * 获取错误码
     */
    String getKey();

    /**
     * 获取错误描述
     */
    String getValue();

}