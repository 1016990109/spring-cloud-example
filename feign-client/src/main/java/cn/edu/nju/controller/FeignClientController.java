package cn.edu.nju.controller;

import cn.edu.nju.api.FeignServiceClient;
import cn.edu.nju.dto.TestDTO;
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
    public TestDTO getInstanceByServiceId(@PathVariable("serviceId") String serviceId) {
        return feignServiceClient.getInstanceByServiceId(serviceId);
    }
}
