package com.cjh.springcloud.lb;

import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @projectName: cloud202x
 * @package: com.cjh.springcloud.lb
 * @className: MyLB
 * @author: tinkouka
 * @date: 2022/12/11 18:28
 * @version: 1.0
 */
@Component
@Slf4j
public class MyLB implements LoadBalancer{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;

        do{
            current = this.atomicInteger.get();
            log.info("current = this.atomicInteger.get()>>>>>>" + current);
            log.info("Integer.MAX_VALUE>>>>>>>" + Integer.MAX_VALUE);
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;

            //拿atomicInteger的值和current进行比较，如何值一致的话，就对atomicInteger进行更新为next的值
            //并返回true，即意味着比较和设值成功。
            //然后对ture加上否定符号！，让无限循环即刻结束
        }while(!atomicInteger.compareAndSet(current,next));

        log.info("第几次访问＊＊＊＊＊next=" + next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        ServiceInstance instance = serviceInstances.get(index);
        return instance;
    }
}
