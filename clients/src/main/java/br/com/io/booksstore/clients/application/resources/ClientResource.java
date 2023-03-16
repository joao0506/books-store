package br.com.io.booksstore.clients.application.resources;

import br.com.io.booksstore.clients.application.domain.Client;
import br.com.io.booksstore.clients.application.request.ClientRequest;
import br.com.io.booksstore.clients.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

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
    
    @GetMapping(params = "id")
    public ResponseEntity findClientById(@RequestParam("id") String id){
        Optional<Client> client = service.findClientById(id);
        if (client.isPresent())
            return ResponseEntity.ok(client);
        return ResponseEntity.notFound().build();
    }


}
