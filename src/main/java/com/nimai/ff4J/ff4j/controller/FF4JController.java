package com.nimai.ff4J.ff4j.controller;

import com.nimai.ff4J.ff4j.FF4JConfig.FF4jConfig;
import org.ff4j.FF4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FF4JController {

    @Autowired
    FF4j ff4j;

    //Sample get method to test working of service
    @GetMapping(path = "/say-hello")
    public String sayHello(){
        return "Hello";
    }
    /*
    * End-point should Danny and Johny will party together or not
    */
    @GetMapping(path = "/should-we-party")
    public String shouldWeParty(){
        if(ff4j.check(FF4jConfig.PAPA_FEATURE))
            return "Yeah Johny Lets Go for party";
        else
            return "Sorry Johny, Papa is at Home";
    }

    /*
     * End-point will change the status of the feature
     * In our case it will notify johny about status of papa
     */
    @PostMapping(path = "/papa-at-hospital/{isAtHospital}")
    public String papaAtHospital(@PathVariable boolean isAtHospital){
        if(isAtHospital){
            ff4j.enable(FF4jConfig.PAPA_FEATURE);
            return ("Message deliverd to danny: PAPA IS AT HOSPITAL, GO PARTY");
        }
        else {
            ff4j.disable(FF4jConfig.PAPA_FEATURE);
            return ("Message deliverd to danny: PAPA LEFT HOSPITAL, COME BACK");
        }
    }
}
