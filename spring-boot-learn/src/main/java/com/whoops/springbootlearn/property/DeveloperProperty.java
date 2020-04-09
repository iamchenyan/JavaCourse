package com.whoops.springbootlearn.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 开发人员选项
 *
 * @author chenyan
 */
@Data
@ConfigurationProperties(prefix = "developer")
@Component
public class DeveloperProperty {

    private String name;
    private String website;
    private String qq;
    private String phoneNumber;

}
