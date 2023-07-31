package com.nimai.ff4J.ff4j.FF4JConfig;

import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.core.FlippingStrategy;
import org.ff4j.strategy.DarkLaunchStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FF4jConfig {
    //Feature Name Definition
    public static final String PAPA_FEATURE = "PAPA_FEATURE";

    /*
    FF4J Configuration
    1. Create one bean of type FF4J

    */
    @Bean
    public FF4j ff4j(){
        FF4j ff4j = new FF4j();
        Feature papaFeature = new Feature(PAPA_FEATURE);
        papaFeature.disable(); //Default we will disable this feature later we will be enable
        ff4j.createFeature(papaFeature);
        return ff4j;
    }
}
