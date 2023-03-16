package br.com.io.booksstore.books.application.resource;

import br.com.io.booksstore.books.application.domain.Book;
import br.com.io.booksstore.books.application.request.BookRequest;
import br.com.io.booksstore.books.application.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

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
                .path("/{id}")
                .buildAndExpand(book.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(params = "id")
    public ResponseEntity findBookById(@RequestParam("id") String id){
        Optional<Book> book = service.findBookById(id);
        if (book.isPresent())
            return ResponseEntity.ok(book);
        return ResponseEntity.notFound().build();
    }

}
