package br.com.io.booksstore.clients.services;

import br.com.io.booksstore.clients.application.domain.Client;
import br.com.io.booksstore.clients.infrastructure.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    @Transactional
    public void saveClient(Client client){
        repository.save(client);
    }


}
