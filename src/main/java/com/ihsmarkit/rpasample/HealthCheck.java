package com.ihsmarkit.rpasample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/info")
    public String statusCheck(){
        return "Healthy";
    }
}
