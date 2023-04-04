package br.com.io.booksstore.buybook.infrastructure;

import br.com.io.booksstore.buybook.application.response.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "clients-microservice", path = "/clients")
public interface ClientsResource {

    @GetMapping(params = "id")
    ResponseEntity<ClientResponse> findClientById(@RequestParam("id") String id);
}
