package com.cjh.springcloud.lb;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @projectName: cloud202x
 * @package: com.cjh.lb
 * @interfaceName: LoadBanlacer
 * @author: tinkouka
 * @date: 2022/12/11 18:14
 * @version: 1.0
 */
public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
    //InstanceInfo instances(List<InstanceInfo> serviceInstances);
}
