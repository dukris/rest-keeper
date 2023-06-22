package by.bsuir.restkeeper.web.controller;

import by.bsuir.restkeeper.domain.Address;
import by.bsuir.restkeeper.service.AddressService;
import by.bsuir.restkeeper.web.dto.AddressDto;
import by.bsuir.restkeeper.web.dto.group.OnCreate;
import by.bsuir.restkeeper.web.dto.group.OnUpdate;
import by.bsuir.restkeeper.web.dto.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * Get address by id.
     *
     * @param id Id
     * @return Address
     */
    @GetMapping("/{id}")
    @PreAuthorize("@securityExpressions.hasAddress(#id) "
            + "|| @securityExpressions.hasAdminRole()")
    public AddressDto getById(@PathVariable final Long id) {
        Address address = this.addressService.retrieveById(id);
        return this.addressMapper.toDto(address);
    }

    /**
     * Create address.
     *
     * @param addressDto Address
     * @return Address
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDto create(@Validated(OnCreate.class)
                             @RequestBody final AddressDto addressDto) {
        Address address = this.addressMapper.toEntity(addressDto);
        address = this.addressService.create(address);
        return this.addressMapper.toDto(address);
    }

    /**
     * Update address.
     *
     * @param id         Id
     * @param addressDto Address
     * @return Address
     */
    @PutMapping("/{id}")
    @PreAuthorize("@securityExpressions.hasAddress(#id)")
    public AddressDto update(@PathVariable final Long id,
                             @Validated(OnUpdate.class)
                             @RequestBody final AddressDto addressDto) {
        Address address = this.addressMapper.toEntity(addressDto);
        address = this.addressService.update(address);
        return this.addressMapper.toDto(address);
    }

    /**
     * Delete address.
     *
     * @param id Id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@securityExpressions.hasAddress(#id)")
    public void delete(@PathVariable final Long id) {
        this.addressService.delete(id);
    }

}
