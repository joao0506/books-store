package br.com.io.booksstore.buybook.application.services;

import br.com.io.booksstore.buybook.application.exceptions.GenericException;
import br.com.io.booksstore.buybook.application.exceptions.NotFoundException;
import br.com.io.booksstore.buybook.application.request.BuyBookRequest;
import br.com.io.booksstore.buybook.application.response.ClientResponse;
import br.com.io.booksstore.buybook.infrastructure.ClientsResource;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.ConnectException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuyBookService {

    private final ClientsResource clientsResource;

    public void buyBook(BuyBookRequest request) throws NotFoundException, GenericException {
        Optional<ClientResponse> client = getClienteFromClientMicroservice(request.getClientId());

        if (!client.isPresent()) throw new GenericException();

    }

    private Optional<ClientResponse> getClienteFromClientMicroservice(String clientId) throws NotFoundException {
        Optional<ClientResponse> client = Optional.empty();
        try{
            log.info("Getting client information from clients-microservice with client id: {}", clientId);
            ResponseEntity<ClientResponse> response = clientsResource.findClientById(clientId);

            return Optional.of(response.getBody());
        } catch (FeignException e){
            if (e.status() == HttpStatus.NOT_FOUND.value()) throw new NotFoundException();
            log.error("Error getting object from clients-microservice: {}", e.getMessage());
            return client;
        }
    }

}
