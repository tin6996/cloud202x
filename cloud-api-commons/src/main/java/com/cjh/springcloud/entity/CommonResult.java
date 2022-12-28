package com.cjh.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: cloud202x
 * @package: com.cjh.springcloud.entity
 * @className: CommonResult
 * @author: tinkouka
 * @date: 2022/11/12 12:38
 * @version: 1.0
 */

@Data //@Data ： 注在类上，提供类的get、set、equals、hashCode、canEqual、toString方法
@AllArgsConstructor  //全参
@NoArgsConstructor   //空参
public class CommonResult<T> {
    private Integer code;
    private String  message;
    private T       data;

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
