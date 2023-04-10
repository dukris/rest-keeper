package by.bsuir.restkeeper.service.impl;

import by.bsuir.restkeeper.domain.Address;
import by.bsuir.restkeeper.domain.exception.ResourceNotFoundException;
import by.bsuir.restkeeper.persistence.AddressRepository;
import by.bsuir.restkeeper.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address retrieveById(Long id) {
        return this.addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address with id = " + id + " not found!"));
    }

    @Override
    public Address create(Address address) {
        return this.addressRepository.save(address);
    }

    @Override
    public Address update(Address address) {
        Address foundAddress = this.retrieveById(address.getId());
        foundAddress.setCity(address.getCity());
        foundAddress.setStreet(address.getStreet());
        foundAddress.setHouse(address.getHouse());
        foundAddress.setFlat(address.getFlat());
        return this.addressRepository.save(foundAddress);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.addressRepository.deleteById(id);
    }
}
