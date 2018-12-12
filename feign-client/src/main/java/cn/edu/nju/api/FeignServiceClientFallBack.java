package cn.edu.nju.api;

import cn.edu.nju.dto.TestDTO;
import org.springframework.stereotype.Component;

/**
 * @author hongchuanwang
 */
@Component
public class FeignServiceClientFallBack implements FeignServiceClient {
    @Override
    public TestDTO getInstanceByServiceId(String serviceId) {
        return new TestDTO("error");
    }
}
