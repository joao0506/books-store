package br.com.io.booksstore.buybook.application.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderBook {
    @Id
    private String orderBookId;
    private LocalDateTime orderBookDateTime;

    private Integer quantity;

    private BigDecimal orderBookTotal;
    private String bookId;

    private BigDecimal unitBookPrice;
    private String clientId;

}
