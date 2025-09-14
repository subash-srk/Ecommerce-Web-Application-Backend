package com.EcommerceWeb.service;

import com.EcommerceWeb.model.User;
import com.EcommerceWeb.payload.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO addAddress(AddressDTO addressDTO, User user);

    List<AddressDTO> getAllAddresses();

    AddressDTO getAddress(Long addressId);

    List<AddressDTO> getUserAddresses(User user);

    AddressDTO updateAddress(Long addressId, AddressDTO addressDTO);

    String deleteAddress(Long addressId);
}
