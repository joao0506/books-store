package br.com.io.booksstore.books.application.services;

import br.com.io.booksstore.books.application.domain.Book;
import br.com.io.booksstore.books.application.domain.BuyBookMessageObject;
import br.com.io.booksstore.books.application.exceptions.GenericException;
import br.com.io.booksstore.books.application.request.BuyBookRequest;
import br.com.io.booksstore.books.infrastructure.repository.BookRepository;
import br.com.io.booksstore.books.infrastructure.repository.mqueue.BuyBookPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    private final BuyBookPublisher publisher;

    @Transactional
    public void saveBook(Book book){
        repository.save(book);
    }

    public Optional<Book> findBookById(String id) {
        return repository.findById(id);
    }

    public List<Book> findAllBooks() {
        return repository.findAll();
    }

    public void buyBook(BuyBookRequest request) throws JsonProcessingException, GenericException {
        Book book = this.findBookById(request.getBookId()).get();

        if (validateBookStock(book.getStock(), request.getQuantity())){
            BuyBookMessageObject objectMessageRequest = new BuyBookMessageObject(book.getId(), book.getPrice(), request.getQuantity(), request.getClientId());
            publisher.publishBuyBook(objectMessageRequest);

            Integer stockRemaining = book.getStock() - request.getQuantity();
            book.setStock(stockRemaining);
            this.saveBook(book);
        } else throw new GenericException("Quantity on this buy is bigger than stock!");

    }

    private Boolean validateBookStock(Integer stock, Integer quantity) {
        return stock >= quantity;
    }
}
