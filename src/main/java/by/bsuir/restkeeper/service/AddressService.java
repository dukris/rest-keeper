package by.bsuir.restkeeper.service;

import by.bsuir.restkeeper.domain.Address;

public interface AddressService {

    Address retrieveById(Long id);

    Address create(Address address);

    Address update(Address address);

    void delete(Long id);

}
