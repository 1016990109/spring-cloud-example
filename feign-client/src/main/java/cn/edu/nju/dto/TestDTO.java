package cn.edu.nju.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hongchuanwang
 */
@Data
public class TestDTO {
    /**
     * serviceId
     */
    private String serviceId;
    /**
     * type
     */
    private Integer type;

    /**
     * 默认构造函数，feign 反序列化
     */
    public TestDTO() {
    }

    /**
     * 带 serviceId 的构造函数
     * @param serviceId
     */
    public TestDTO(String serviceId) {
        this.serviceId = serviceId;
    }
}
