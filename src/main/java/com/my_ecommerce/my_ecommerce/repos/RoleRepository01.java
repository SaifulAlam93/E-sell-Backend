package com.my_ecommerce.my_ecommerce.repos;

import com.my_ecommerce.my_ecommerce.domain.Role01;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository01 extends JpaRepository<Role01, String> {

    boolean existsByRoleNameIgnoreCase(String roleName);

}
