package br.com.io.booksstore.buybook.application.resources;

import br.com.io.booksstore.buybook.application.domain.OrderBook;
import br.com.io.booksstore.buybook.application.services.BuyBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("buy-book")
@RequiredArgsConstructor
@Slf4j
public class BuyBookResource {

    private final BuyBookService service;

    @GetMapping(value = "/orders", params = "clientId")
    public ResponseEntity<List<OrderBook>> getOrderByCliente(@RequestParam("clientId") String clientId){
        try{
            return ResponseEntity.ok(service.findOrderBooksById(clientId));
        } catch (Exception e){
            return ResponseEntity.ok().build();
        }
    }


}
