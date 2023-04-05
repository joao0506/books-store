package br.com.io.booksstore.buybook.application.services;

import br.com.io.booksstore.buybook.application.domain.OrderBook;
import br.com.io.booksstore.buybook.application.exceptions.GenericException;
import br.com.io.booksstore.buybook.application.exceptions.NotFoundException;
import br.com.io.booksstore.buybook.application.request.BuyBookRequest;
import br.com.io.booksstore.buybook.application.response.Book;
import br.com.io.booksstore.buybook.application.response.Client;
import br.com.io.booksstore.buybook.infrastructure.BooksResource;
import br.com.io.booksstore.buybook.infrastructure.ClientsResource;
import br.com.io.booksstore.buybook.infrastructure.repository.OrderRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuyBookService {

    private final ClientsResource clientsResource;

    private final BooksResource booksResource;

    private final OrderRepository orderRepository;

    public String buyBook(BuyBookRequest request) throws NotFoundException, GenericException {
        Optional<Client> client = getClientFromClientsMicroservice(request.getClientId());
        Optional<Book> book = getBookFromBooksMicroservice(request.getBookId());

        if (!client.isPresent() || !book.isPresent())
            throw new GenericException("");
        if (request.getQuantity() > book.get().getStock())
            throw new GenericException("Quantity on this buy is bigger than stock!");

        BigDecimal buyValue = calculateBuyValue(BigDecimal.valueOf(request.getQuantity()), book.get().getPrice());

        OrderBook order = OrderBook.builder()
                .orderBookId(UUID.randomUUID().toString())
                .orderBookDateTime(LocalDateTime.now())
                .quantity(request.getQuantity())
                .orderBookTotal(buyValue)
                .bookId(book.get().getId())
                .unitBookPrice(book.get().getPrice())
                .clientId(client.get().getId())
                .build();

        log.info("Saving the order: {}", order.toString());
        saveOrder(order);
        return order.getOrderBookId();
    }

    @Transactional
    private void saveOrder(OrderBook order) {
        orderRepository.save(order);
    }

    private Optional<Client> getClientFromClientsMicroservice(String clientId) throws NotFoundException {
        Optional<Client> client = Optional.empty();
        try{
            log.info("Getting client information from clients-microservice with client id: {}", clientId);
            ResponseEntity<Client> response = clientsResource.findClientById(clientId);

            return Optional.of(response.getBody());
        } catch (FeignException e){
            if (e.status() == HttpStatus.NOT_FOUND.value()) throw new NotFoundException("Client not found!");
            log.error("Error getting object from clients-microservice: {}", e.getMessage());
            return client;
        }
    }

    private Optional<Book> getBookFromBooksMicroservice(String bookId) throws NotFoundException {
        Optional<Book> book = Optional.empty();
        try{
            log.info("Getting book information from books-microservice with book id: {}", bookId);
            ResponseEntity<Book> response = booksResource.findBookById(bookId);

            return Optional.of(response.getBody());
        } catch (FeignException e){
            if (e.status() == HttpStatus.NOT_FOUND.value()) throw new NotFoundException("Book not found!");
            log.error("Error getting object from books-microservice: {}", e.getMessage());
            return book;
        }
    }

    private BigDecimal calculateBuyValue(BigDecimal quantity, BigDecimal price) {
        return price.multiply(quantity);
    }

}
