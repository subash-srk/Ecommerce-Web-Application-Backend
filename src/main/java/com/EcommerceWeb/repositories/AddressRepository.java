package com.EcommerceWeb.repositories;

import com.EcommerceWeb.model.Address;
import com.EcommerceWeb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUser(User user);
}
