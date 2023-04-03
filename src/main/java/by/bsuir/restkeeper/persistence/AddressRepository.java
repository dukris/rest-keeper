package by.bsuir.restkeeper.persistence;

import by.bsuir.restkeeper.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
