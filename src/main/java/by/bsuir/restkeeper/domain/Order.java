package by.bsuir.restkeeper.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "orders_dishes",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "dish_id")}
    )
    private List<Dish> dishes;

    @ElementCollection
    @CollectionTable(name = "orders_amount", joinColumns = @JoinColumn(name = "order_id"))
    private List<Integer> amount;

    @Column(nullable = false)
    private BigDecimal cost;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {

        RECEIVED,
        PROCESSING,
        COOKED,
        COMPLETED

    }

}
