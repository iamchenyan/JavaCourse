package com.whoops.springbootlearn.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 项目配置
 *
 * @author chenyan
 */
@Data
@Component
public class ApplicationProperty {

    @Value("${application.name}")
    private String name;

    @Value("${application.version}")
    private String version;

}
