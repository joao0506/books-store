package br.com.io.booksstore.buybook.application.services;

import br.com.io.booksstore.buybook.application.exceptions.GenericException;
import br.com.io.booksstore.buybook.application.exceptions.NotFoundException;
import br.com.io.booksstore.buybook.application.request.BuyBookRequest;
import br.com.io.booksstore.buybook.application.response.BookResponse;
import br.com.io.booksstore.buybook.application.response.ClientResponse;
import br.com.io.booksstore.buybook.infrastructure.BooksResource;
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

    private final BooksResource booksResource;

    public void buyBook(BuyBookRequest request) throws NotFoundException, GenericException {
        Optional<ClientResponse> client = getClientFromClientsMicroservice(request.getClientId());
        Optional<BookResponse> book = getBookFromBooksMicroservice(request.getBookId());

        if (!client.isPresent() || !book.isPresent()) throw new GenericException();


    }

    private Optional<ClientResponse> getClientFromClientsMicroservice(String clientId) throws NotFoundException {
        Optional<ClientResponse> client = Optional.empty();
        try{
            log.info("Getting client information from clients-microservice with client id: {}", clientId);
            ResponseEntity<ClientResponse> response = clientsResource.findClientById(clientId);

            return Optional.of(response.getBody());
        } catch (FeignException e){
            if (e.status() == HttpStatus.NOT_FOUND.value()) throw new NotFoundException("Client not found!");
            log.error("Error getting object from clients-microservice: {}", e.getMessage());
            return client;
        }
    }

    private Optional<BookResponse> getBookFromBooksMicroservice(String bookId) throws NotFoundException {
        Optional<BookResponse> book = Optional.empty();
        try{
            log.info("Getting book information from books-microservice with book id: {}", bookId);
            ResponseEntity<BookResponse> response = booksResource.findBookById(bookId);

            return Optional.of(response.getBody());
        } catch (FeignException e){
            if (e.status() == HttpStatus.NOT_FOUND.value()) throw new NotFoundException("Book not found!");
            log.error("Error getting object from books-microservice: {}", e.getMessage());
            return book;
        }
    }

}
