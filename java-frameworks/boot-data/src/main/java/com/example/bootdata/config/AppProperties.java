package com.example.bootdata.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "spring")
@ConfigurationPropertiesScan
public class AppProperties {
    private Map<Name, String> datasource;
    enum Name {
        URL, USER_NAME, PASSWORD, DRIVER_CLASS_NAME
    }
}
