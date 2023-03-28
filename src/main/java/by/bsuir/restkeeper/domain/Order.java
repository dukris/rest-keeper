package by.bsuir.restkeeper.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "table_number", nullable = false)
    private Long tableNumber;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private BigDecimal cost;

    @Column(nullable = false)
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {

        RECEIVED,
        PROCESSING,
        COOKED,
        COMPLETED

    }

}
