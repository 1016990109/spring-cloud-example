package cn.edu.nju.controller;

import cn.edu.nju.dto.InstanceDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongchuanwang
 */
@RestController
@RequestMapping("/feign-server")
@RefreshScope
public class FeignServiceController {
    @Value("${feign-server.version}")
    String version;

    @RequestMapping(value = "/instance/{serviceId}", method = RequestMethod.GET)
    public InstanceDTO getInstanceByServiceId(@PathVariable("serviceId") String serviceId) {
        return new InstanceDTO("version:" + version + ",serviceId:" + serviceId);
    }
}
