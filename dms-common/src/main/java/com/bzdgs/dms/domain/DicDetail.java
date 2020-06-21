package com.bzdgs.dms.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * (TDicdetail)实体类
 *
 * @author Hoony96
 * @since 2019-12-03 20:45:59
 */
@Getter
@Setter
@ToString
public class DicDetail{
    private Long id;
    //证件名
    private String name;
    //证件类型
    private Long typeId;
    // 嵌套查询  多对一
    private DicType dicType;


}
