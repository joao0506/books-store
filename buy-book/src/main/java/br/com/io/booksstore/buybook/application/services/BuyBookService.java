package br.com.io.booksstore.buybook.application.services;

import br.com.io.booksstore.buybook.application.domain.BuyBookMessageObject;
import br.com.io.booksstore.buybook.application.domain.OrderBook;
import br.com.io.booksstore.buybook.application.exceptions.GenericException;
import br.com.io.booksstore.buybook.application.exceptions.NotFoundException;
import br.com.io.booksstore.buybook.application.response.Client;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuyBookService {

    private final ClientsResource clientsResource;

    private final OrderRepository orderRepository;

    public void buyBook(BuyBookMessageObject object) throws NotFoundException, GenericException {
        Optional<Client> client = getClientFromClientsMicroservice(object.getClienteId());

        if (!client.isPresent())
            throw new GenericException("");

        BigDecimal buyValue = calculateBuyValue(BigDecimal.valueOf(object.getQuantity()), object.getPrice());

        OrderBook order = OrderBook.builder()
                .orderBookId(UUID.randomUUID().toString())
                .orderBookDateTime(LocalDateTime.now())
                .quantity(object.getQuantity())
                .orderBookTotal(buyValue)
                .bookId(object.getBookId())
                .unitBookPrice(object.getPrice())
                .clientId(client.get().getId())
                .build();

        log.info("Saving the order: {}", order.toString());
        saveOrder(order);
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

    private BigDecimal calculateBuyValue(BigDecimal quantity, BigDecimal price) {
        return price.multiply(quantity);
    }

    public List<OrderBook> findOrderBooksById(String clientId) {
        return orderRepository.findAllByClientId(clientId);
    }
}
