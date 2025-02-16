package hyu.erica.capstone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Hello, World!";
    }
}
