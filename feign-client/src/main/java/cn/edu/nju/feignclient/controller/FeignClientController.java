package cn.edu.nju.feignclient.controller;

import cn.edu.nju.feignclient.api.FeignServiceClient;
import cn.edu.nju.feignclient.dto.InstanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hongchuanwang
 */
@RestController
@RequestMapping("/feign-client")
public class FeignClientController {

    @Autowired
    FeignServiceClient feignServiceClient;

    @RequestMapping(value = "/instance/{serviceId}", method = RequestMethod.GET)
    public InstanceDTO getInstanceByServiceId(@PathVariable("serviceId") String serviceId) {
        return feignServiceClient.getInstanceByServiceId(serviceId);
    }

    @RequestMapping(value = "/instance/{serviceId}", method = RequestMethod.DELETE)
    public String deleteInstanceByServiceId(@PathVariable("serviceId") String serviceId) {
        return feignServiceClient.deleteInstanceByServiceId(serviceId);
    }

    @RequestMapping(value = "/instance", method = RequestMethod.POST)
    public String createInstance(@RequestBody InstanceDTO instance) {
        return feignServiceClient.createInstance(instance);
    }

    @RequestMapping(value = "/instance/{serviceId}", method = RequestMethod.PUT)
    public String updateInstanceByServiceId(@RequestBody InstanceDTO instance, @PathVariable("serviceId") String serviceId) {
        return feignServiceClient.updateInstanceByServiceId(instance, serviceId);
    }

}
