package by.bsuir.restkeeper.service;

import by.bsuir.restkeeper.domain.Address;

public interface AddressService {

    /**
     * Retrieve address by id.
     *
     * @param id Id
     * @return Address
     */
    Address retrieveById(Long id);

    /**
     * Create new address.
     *
     * @param address Address
     * @return Address
     */
    Address create(Address address);

    /**
     * Update address.
     *
     * @param address Address
     * @return Address
     */
    Address update(Address address);

    /**
     * Delete address.
     *
     * @param id Id
     */
    void delete(Long id);

}
