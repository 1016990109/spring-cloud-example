package cn.edu.nju.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hongchuanwang
 */
@Data
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
}
