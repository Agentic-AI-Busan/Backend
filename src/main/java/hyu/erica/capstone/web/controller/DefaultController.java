package hyu.erica.capstone.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @Operation(summary = "Health Check", description = "서버 상태 확인")
    @GetMapping("/health-check")
    public String healthCheck() {
        return "Hello, World!";
    }
}
