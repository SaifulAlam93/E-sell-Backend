package com.my_ecommerce.my_ecommerce.repos;

import com.my_ecommerce.my_ecommerce.domain.User01;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository01 extends JpaRepository<User01, String> {

    boolean existsByUserNameIgnoreCase(String userName);

    boolean existsByEmailIgnoreCase(String email);

}
