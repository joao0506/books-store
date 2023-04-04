package br.com.io.booksstore.buybook.application.resources;

import br.com.io.booksstore.buybook.application.exceptions.GenericException;
import br.com.io.booksstore.buybook.application.exceptions.NotFoundException;
import br.com.io.booksstore.buybook.application.request.BuyBookRequest;
import br.com.io.booksstore.buybook.application.response.ClientResponse;
import br.com.io.booksstore.buybook.application.services.BuyBookService;
import br.com.io.booksstore.buybook.infrastructure.ClientsResource;
import ch.qos.logback.core.net.server.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("buy-book")
@RequiredArgsConstructor
@Slf4j
public class BuyBookResource {

    private final BuyBookService service;

    @PostMapping
    public ResponseEntity buyBook(@RequestBody BuyBookRequest request) {
        try{
            service.buyBook(request);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (GenericException e){
            return ResponseEntity.internalServerError().build();
        }
    }

}
