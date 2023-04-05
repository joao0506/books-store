package br.com.io.booksstore.buybook.infrastructure.mqueue;

import br.com.io.booksstore.buybook.application.domain.BuyBookMessageObject;
import br.com.io.booksstore.buybook.application.exceptions.GenericException;
import br.com.io.booksstore.buybook.application.exceptions.NotFoundException;
import br.com.io.booksstore.buybook.application.services.BuyBookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class BuyBookSubscriber {

    private final BuyBookService service;

    @RabbitListener(queues = "${mq.queue.buy-book}")
    public void getBuyBookOrder(@Payload String buyBookMessagePayload) throws JsonProcessingException, NotFoundException, GenericException {
        log.info("Receiving book purchase order...");

        BuyBookMessageObject object = convertStringToBuyBookObject(buyBookMessagePayload);
        service.buyBook(object);
    }

    private BuyBookMessageObject convertStringToBuyBookObject(String buyBookMessagePayload) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(buyBookMessagePayload, BuyBookMessageObject.class);
    }

}
