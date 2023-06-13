package com.my_ecommerce.my_ecommerce.repos;


import com.my_ecommerce.my_ecommerce.enums.ERole;
import com.my_ecommerce.my_ecommerce.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
