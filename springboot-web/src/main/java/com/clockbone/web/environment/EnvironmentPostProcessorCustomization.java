package com.clockbone.web.environment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Created by clock on 2018/4/12.
 */
@Slf4j
public class EnvironmentPostProcessorCustomization  implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment configurableEnvironment, SpringApplication springApplication) {
        log.info("postProcessEnvironment");
    }
}
