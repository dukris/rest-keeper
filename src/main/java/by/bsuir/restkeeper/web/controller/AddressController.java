package by.bsuir.restkeeper.web.controller;

import by.bsuir.restkeeper.domain.Address;
import by.bsuir.restkeeper.service.AddressService;
import by.bsuir.restkeeper.web.dto.AddressDto;
import by.bsuir.restkeeper.web.dto.group.OnCreate;
import by.bsuir.restkeeper.web.dto.group.OnUpdate;
import by.bsuir.restkeeper.web.dto.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restkeeper/v1/addresses")
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @GetMapping("/{id}")
    public AddressDto getById(@PathVariable Long id) {
        Address address = this.addressService.retrieveById(id);
        return this.addressMapper.toDto(address);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDto create(@Validated(OnCreate.class) @RequestBody AddressDto addressDto) {
        Address address = this.addressMapper.toEntity(addressDto);
        address = this.addressService.create(address);
        return this.addressMapper.toDto(address);
    }

    @PutMapping("/{id}")
    public AddressDto update(@Validated(OnUpdate.class) @RequestBody AddressDto addressDto) {
        Address address = this.addressMapper.toEntity(addressDto);
        address = this.addressService.update(address);
        return this.addressMapper.toDto(address);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.addressService.delete(id);
    }

}
