package br.com.io.booksstore.clients.application.resources;

import br.com.io.booksstore.clients.application.domain.Client;
import br.com.io.booksstore.clients.application.request.ClientRequest;
import br.com.io.booksstore.clients.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientResource {

    private final ClientService service;

    @PostMapping
    public ResponseEntity saveClient(@RequestBody ClientRequest request){
        Client client = request.toDomain();
        service.saveClient(client);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


}
