package ru.clevertec.ecl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class RootConfig {
    /**
     * Resource handler for frontend part.
     * Uses for frontend part.
     * @param registry - registrations of resource handlers for serving static resources such as images
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

}
