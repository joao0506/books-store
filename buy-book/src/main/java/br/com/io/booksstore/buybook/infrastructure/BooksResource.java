package br.com.io.booksstore.buybook.infrastructure;

import br.com.io.booksstore.buybook.application.response.BookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "books-microservice", path = "/books")
public interface BooksResource {

    @GetMapping(params = "id")
    ResponseEntity<BookResponse> findBookById(@RequestParam("id") String id);
}
