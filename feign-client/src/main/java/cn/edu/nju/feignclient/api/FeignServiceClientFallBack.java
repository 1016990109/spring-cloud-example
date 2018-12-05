package cn.edu.nju.feignclient.api;

import cn.edu.nju.dto.InstanceDTO;
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
}
