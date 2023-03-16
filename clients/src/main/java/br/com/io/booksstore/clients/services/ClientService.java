package br.com.io.booksstore.clients.services;

import br.com.io.booksstore.clients.application.domain.Client;
import br.com.io.booksstore.clients.infrastructure.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    @Transactional
    public void saveClient(Client client){
        repository.save(client);
    }

    public Optional<Client> findClientById(String id) {
        return repository.findById(id);
    }
}
