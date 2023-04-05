package br.com.io.booksstore.books.infrastructure.repository.mqueue;

import br.com.io.booksstore.books.application.domain.BuyBookMessageObject;
import br.com.io.booksstore.books.application.request.BuyBookRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuyBookPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueBuyBook;

    public void publishBuyBook(BuyBookMessageObject request) throws JsonProcessingException {
        String requestAsString = getRequestAsString(request);
        rabbitTemplate.convertAndSend(queueBuyBook.getName(), requestAsString);
    }

    private String getRequestAsString(BuyBookMessageObject request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(request);
    }

}
