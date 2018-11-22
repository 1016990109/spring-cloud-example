package cn.edu.nju.feignserver.controller;

import cn.edu.nju.feignserver.dto.InstanceDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongchuanwang
 */
@RestController
@RequestMapping("/feign-server")
public class FeignServiceController {
    @RequestMapping(value = "/instance/{serviceId}", method = RequestMethod.GET)
    public InstanceDTO getInstanceByServiceId(@PathVariable("serviceId") String serviceId) {
        return new InstanceDTO(serviceId);
    }
}
