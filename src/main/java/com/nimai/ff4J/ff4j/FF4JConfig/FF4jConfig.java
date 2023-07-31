package com.nimai.ff4J.ff4j.FF4JConfig;

import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.core.FlippingStrategy;
import org.ff4j.strategy.DarkLaunchStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //This piece of shit will tell spring boot that this is a configuration class
public class FF4jConfig {
    //Feature Name Definition
    public static final String PAPA_FEATURE = "PAPA_FEATURE";

    /*
    * FF4J Configuration
    * Create one bean of type FF4J. Why? Because concept of overriding is dying
    * Create of instance of FF4j. Obviously to use it further
    * Register your Feature
    * Enable or disable according to you
    * You are done, Thank me later (Code Below ⬇️)_
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
