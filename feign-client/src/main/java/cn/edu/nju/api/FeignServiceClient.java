package cn.edu.nju.api;

import cn.edu.nju.dto.TestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author hongchuanwang
 */
@FeignClient(value = "feign-server")
public interface FeignServiceClient {
    /**
     * 调用 feign-server 的 feign-server/instance/{serviceId} 接口，获得返回值（类型 InstanceDTO）
     * @param serviceId
     * @return
     */
    @RequestMapping(value = "/feign-server/instance/{serviceId}", method = RequestMethod.GET)
    TestDTO getInstanceByServiceId(@PathVariable("serviceId") String serviceId);
}
