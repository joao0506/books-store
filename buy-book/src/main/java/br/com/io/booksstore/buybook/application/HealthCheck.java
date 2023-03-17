package br.com.io.booksstore.buybook.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("buy-book/health")
@Slf4j
public class HealthCheck {

    private static final String MICROSERVICE_IS_UP = "The microservice buy book is working!";
    @GetMapping
    public String microserviceHealthCheck(){
        log.info(MICROSERVICE_IS_UP);
        return MICROSERVICE_IS_UP;
    }

}
