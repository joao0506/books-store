package br.com.io.booksstore.clients.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    private String id;
    private String name;
    private String documentNumber;
    private String phoneNumber;
    private String username;
    private String email;

}
