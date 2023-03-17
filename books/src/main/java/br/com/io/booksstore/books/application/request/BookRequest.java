package br.com.io.booksstore.books.application.request;

import br.com.io.booksstore.books.application.domain.Book;
import br.com.io.booksstore.books.application.utils.GenerateUUID;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BookRequest {

    private String name;
    private String author;
    private BigDecimal price;
    private Integer pages;
    private Integer stock;

    public Book toDomain(){
        String id = GenerateUUID.getUUID();
        return new Book(id, name, author, price, pages, stock);
    }

}
