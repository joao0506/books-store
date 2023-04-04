package br.com.io.booksstore.buybook.application.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private String id;
    private String name;
    private String author;
    private BigDecimal price;
    private Integer pages;

    private Integer stock;
}
