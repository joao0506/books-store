package br.com.io.booksstore.books.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyBookMessageObject {
    private String bookId;

    private BigDecimal price;
    private String clienteId;
}
