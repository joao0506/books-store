package br.com.io.booksstore.buybook.application.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {
    private String id;
    private String name;
    private String documentNumber;
    private String phoneNumber;
    private String username;
    private String email;
}
