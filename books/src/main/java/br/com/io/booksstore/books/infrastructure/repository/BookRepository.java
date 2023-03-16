package br.com.io.booksstore.books.infrastructure.repository;

import br.com.io.booksstore.books.application.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
