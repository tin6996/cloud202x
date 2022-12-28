package com.cjh.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 常用的几个注解：
 * @Data ： 注在类上，提供类的get、set、equals、hashCode、canEqual、toString方法
 * @AllArgsConstructor ： 注在类上，提供类的全参构造
 * @NoArgsConstructor ： 注在类上，提供类的无参构造
 * @Setter ： 注在属性上，提供 set 方法
 * @Getter ： 注在属性上，提供 get 方法
 * @EqualsAndHashCode ： 注在类上，提供对应的 equals 和 hashCode 方法
 * @Log4j/@Slf4j ： 注在类上，提供对应的 Logger 对象，变量名为 log
 *
 * 作者：Jason_M_Ho
 * 链接：https://www.jianshu.com/p/c1ee7e4247bf
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

/**
 * @projectName: cloud202x
 * @package: com.cjh.springcloud.entity
 * @className: Payment
 * @author: tinkouka
 * @date: 2022/11/12 12:31
 * @version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    private Long id;
    private String serial;

}
