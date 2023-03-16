package br.com.io.booksstore.clients.infrastructure;

import br.com.io.booksstore.clients.application.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}
