package cn.edu.nju.feignserver.dto;

import java.io.Serializable;

/**
 * @author hongchuanwang
 */
public class InstanceDTO implements Serializable {
    private String serviceId;

    /**
     * 需要默认构造函数，否则 feign 反序列化失败（默认的 jackson）
     */
    public InstanceDTO() {

    }

    public InstanceDTO(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "InstanceDTO{" +
                "serviceId='" + serviceId + '\'' +
                '}';
    }
}
