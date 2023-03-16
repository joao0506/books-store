package br.com.io.booksstore.books.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books/health")
@Slf4j
public class HealthCheck {

    private static final String MICROSERVICE_IS_UP = "The requested microservice is working!";
    @GetMapping
    public String microserviceHealthCheck(){
        log.info(MICROSERVICE_IS_UP);
        return MICROSERVICE_IS_UP;
    }

}
