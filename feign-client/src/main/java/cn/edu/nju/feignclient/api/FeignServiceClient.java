package cn.edu.nju.feignclient.api;

import cn.edu.nju.feignclient.dto.InstanceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author hongchuanwang
 */
@FeignClient(value = "feign-server", fallback = FeignServiceClientFallBack.class)
public interface FeignServiceClient {
    @RequestMapping(value = "/feign-server/instance/{serviceId}", method = RequestMethod.GET)
    InstanceDTO getInstanceByServiceId(@PathVariable("serviceId") String serviceId);

    @RequestMapping(value = "/instance/{serviceId}", method = RequestMethod.DELETE)
    String deleteInstanceByServiceId(@PathVariable("serviceId") String serviceId);

    @RequestMapping(value = "/instance", method = RequestMethod.POST)
    String createInstance(@RequestBody InstanceDTO instance);

    @RequestMapping(value = "/instance/{serviceId}", method = RequestMethod.PUT)
    String updateInstanceByServiceId(@RequestBody InstanceDTO instance, @PathVariable("serviceId") String serviceId);
}
