package br.com.io.booksstore.buybook.application.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class BuyBookRequest {

    private String clientId;
    private String bookId;
    private String quantity;

}
