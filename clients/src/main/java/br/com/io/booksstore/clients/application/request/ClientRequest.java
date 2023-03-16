package br.com.io.booksstore.clients.application.request;

import br.com.io.booksstore.clients.application.domain.Client;
import br.com.io.booksstore.clients.application.utils.GenerateUUID;
import lombok.Data;

@Data
public class ClientRequest {

    private String name;
    private String documentNumber;
    private String phoneNumber;
    private String username;
    private String email;

    public Client toDomain(){
        String id = GenerateUUID.getUUID();
        return new Client(id, name, documentNumber, phoneNumber, username, email);
    }

}
