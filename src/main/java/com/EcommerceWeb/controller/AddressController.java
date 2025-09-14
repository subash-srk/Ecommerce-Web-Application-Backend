package com.EcommerceWeb.controller;

import com.EcommerceWeb.model.Address;
import com.EcommerceWeb.model.User;
import com.EcommerceWeb.payload.AddressDTO;
import com.EcommerceWeb.repositories.AddressRepository;
import com.EcommerceWeb.service.AddressService;
import com.EcommerceWeb.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private AuthUtil authUtil;

    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> addAddress(@Valid @RequestBody AddressDTO addressDTO){
        User user = authUtil.loggedInUser();
        AddressDTO savedAddress = addressService.addAddress(addressDTO, user);
        return new ResponseEntity<AddressDTO>(savedAddress, HttpStatus.CREATED);
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAddresses(){
        List<AddressDTO> addresses = addressService.getAllAddresses();
        return new ResponseEntity<List<AddressDTO>>(addresses, HttpStatus.OK);
    }

    @GetMapping("/address/{addressId}")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable Long addressId){
        AddressDTO addressDTO = addressService.getAddress(addressId);
        return new ResponseEntity<AddressDTO>(addressDTO, HttpStatus.OK);
    }

    @GetMapping("/user/addresses")
    public ResponseEntity<List<AddressDTO>> getUserAddresses(){
        User user = authUtil.loggedInUser();
        List<AddressDTO> addresses = addressService.getUserAddresses(user);
        return new ResponseEntity<List<AddressDTO>>(addresses, HttpStatus.OK);
    }

    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> updateAddressById(@PathVariable Long addressId,
                                                        @RequestBody AddressDTO addressDTO){
        AddressDTO updatedAddress = addressService.updateAddress(addressId, addressDTO);
        return new ResponseEntity<AddressDTO>(updatedAddress, HttpStatus.OK);
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<String> deleteAddressById(@PathVariable Long addressId){
        String status = addressService.deleteAddress(addressId);
        return new ResponseEntity<String>(status, HttpStatus.OK);
    }


}
