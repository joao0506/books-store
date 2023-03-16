package br.com.io.booksstore.books.application.resource;

import br.com.io.booksstore.books.application.domain.Book;
import br.com.io.booksstore.books.application.request.BookRequest;
import br.com.io.booksstore.books.application.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookResource {

    private final BookService service;

    @PostMapping
    public ResponseEntity saveBook(@RequestBody BookRequest request){
        Book book = request.toDomain();
        service.saveBook(book);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(book.getId())
                .buildAndExpand("/{id}").toUri();
        return ResponseEntity.created(location).build();
    }

}
