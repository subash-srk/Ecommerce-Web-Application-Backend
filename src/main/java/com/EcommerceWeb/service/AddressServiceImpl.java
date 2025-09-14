package com.EcommerceWeb.service;

import com.EcommerceWeb.exceptions.APIException;
import com.EcommerceWeb.exceptions.ResourceNotFoundException;
import com.EcommerceWeb.model.Address;
import com.EcommerceWeb.model.User;
import com.EcommerceWeb.payload.AddressDTO;
import com.EcommerceWeb.repositories.AddressRepository;
import com.EcommerceWeb.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AddressDTO addAddress(AddressDTO addressDTO, User user) {
        Address address = modelMapper.map(addressDTO, Address.class);
        List<Address> addresses = user.getAddresses();
        addresses.add(address);
        user.setAddresses(addresses);

        address.setUser(user);

        Address savedAddress = addressRepository.save(address);
        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        List<Address> addressList = addressRepository.findAll();
        if(addressList.isEmpty()){
            throw new APIException("No Address Found");
        }
        List<AddressDTO> addressDTOList = addressList.stream().map(address ->
                modelMapper.map(address, AddressDTO.class)).toList();

        return addressDTOList;
    }

    @Override
    public AddressDTO getAddress(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));
        return modelMapper.map(address, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getUserAddresses(User user) {
        List<Address> addressList = user.getAddresses();
        if(addressList.isEmpty()){
            throw new APIException("No Address Found.");
        }
        List<AddressDTO> addressDTOList = addressList.stream().map(address ->
                modelMapper.map(address, AddressDTO.class)).toList();
        return addressDTOList;
    }

    @Override
    public AddressDTO updateAddress(Long addressId, AddressDTO addressDTO) {
        Address addressFromDB = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));

        addressFromDB.setCity(addressDTO.getCity());
        addressFromDB.setPincode(addressDTO.getPincode());
        addressFromDB.setCountry(addressDTO.getCountry());
        addressFromDB.setBuildingName(addressDTO.getBuildingName());
        addressFromDB.setStreet(addressDTO.getStreet());
        addressFromDB.setState(addressDTO.getState());

        Address savedAddress = addressRepository.save(addressFromDB);
        User user = addressFromDB.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        user.getAddresses().add(savedAddress);
        userRepository.save(user);

        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    @Override
    public String deleteAddress(Long addressId) {
        Address addressFromDB = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));

        User user = addressFromDB.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        userRepository.save(user);

        addressRepository.delete(addressFromDB);
        return "Address deleted successfully with addressId: " + addressId;
    }
}
