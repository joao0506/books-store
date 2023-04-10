package br.com.io.booksstore.buybook.infrastructure.repository;

import br.com.io.booksstore.buybook.application.domain.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderBook, String> {

    List<OrderBook> findAllByClientId(String clientId);

}
