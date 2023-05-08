package by.bsuir.restkeeper.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_number", nullable = false)
    private Integer tableNumber;

    @Column(name = "guests", nullable = false)
    private Integer amountOfGuests;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "orders_dishes",
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "order_dish_amount"),
            joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "dish")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "dish_amount", nullable = false)
    private Map<Dish, Integer> dishAmountMap;

    @Column(nullable = false)
    private BigDecimal cost;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")

    private User user;

    public enum Status {

        RECEIVED,
        PROCESSING,
        COOKED,
        COMPLETED

    }

}
