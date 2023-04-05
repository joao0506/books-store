package br.com.io.booksstore.books.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageQueueConfig {

    @Value("${mq.queue.buy-book}")
    private String queueBuyBook;

    @Bean
    public Queue buyBookQueue(){
        return new Queue(queueBuyBook, true);
    }

}
