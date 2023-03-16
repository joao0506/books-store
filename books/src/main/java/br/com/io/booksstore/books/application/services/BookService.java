package br.com.io.booksstore.books.application.services;

import br.com.io.booksstore.books.application.domain.Book;
import br.com.io.booksstore.books.infrastructure.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    @Transactional
    public void saveBook(Book book){
        repository.save(book);
    }

}
