package cn.edu.nju.feignclient.api;

import cn.edu.nju.feignclient.dto.InstanceDTO;
import org.springframework.stereotype.Component;

/**
 * @author hongchuanwang
 */
@Component
public class FeignServiceClientFallBack implements FeignServiceClient {
    @Override
    public InstanceDTO getInstanceByServiceId(String serviceId) {
        return new InstanceDTO("error");
    }

    @Override
    public String deleteInstanceByServiceId(String serviceId) {
        return null;
    }

    @Override
    public String createInstance(InstanceDTO instance) {
        return null;
    }

    @Override
    public String updateInstanceByServiceId(InstanceDTO instance, String serviceId) {
        return null;
    }
}
