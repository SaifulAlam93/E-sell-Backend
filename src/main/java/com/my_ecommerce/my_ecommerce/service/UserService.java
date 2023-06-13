package com.my_ecommerce.my_ecommerce.service;

import com.my_ecommerce.my_ecommerce.domain.Role01;
import com.my_ecommerce.my_ecommerce.domain.User01;
import com.my_ecommerce.my_ecommerce.model.UserDTO;
import com.my_ecommerce.my_ecommerce.repos.RoleRepository01;
import com.my_ecommerce.my_ecommerce.repos.UserRepository01;
import com.my_ecommerce.my_ecommerce.util.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Transactional
@Service
public class UserService {

    private final UserRepository01 userRepository01;
    private final RoleRepository01 roleRepository01;

    public UserService(final UserRepository01 userRepository01, final RoleRepository01 roleRepository01) {
        this.userRepository01 = userRepository01;
        this.roleRepository01 = roleRepository01;
    }

    public List<UserDTO> findAll() {
        final List<User01> user01s = userRepository01.findAll(Sort.by("userName"));
        return user01s.stream()
                .map((user01) -> mapToDTO(user01, new UserDTO()))
                .toList();
    }

    public UserDTO get(final String userName) {
        return userRepository01.findById(userName)
                .map(user01 -> mapToDTO(user01, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final UserDTO userDTO) {
        final User01 user01 = new User01();
        mapToEntity(userDTO, user01);
        user01.setUserName(userDTO.getUserName());
        return userRepository01.save(user01).getUserName();
    }

    public void update(final String userName, final UserDTO userDTO) {
        final User01 user01 = userRepository01.findById(userName)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user01);
        userRepository01.save(user01);
    }

    public void delete(final String userName) {
        userRepository01.deleteById(userName);
    }

    private UserDTO mapToDTO(final User01 user01, final UserDTO userDTO) {
        userDTO.setUserName(user01.getUserName());
        userDTO.setUserFirstName(user01.getUserFirstName());
        userDTO.setUserLastName(user01.getUserLastName());
        userDTO.setPassword(user01.getPassword());
        userDTO.setEmail(user01.getEmail());
        userDTO.setEnabled(user01.getEnabled());
        userDTO.setCredentialsNonExpired(user01.getCredentialsNonExpired());
        userDTO.setAccountNonExpired(user01.getAccountNonExpired());
        userDTO.setAccountNonLocked(user01.getAccountNonLocked());
        userDTO.setRoles(user01.getRole01s() == null ? null : user01.getRole01s().stream()
                .map(role01 -> role01.getRoleName())
                .toList());
        return userDTO;
    }

    private User01 mapToEntity(final UserDTO userDTO, final User01 user01) {
        user01.setUserFirstName(userDTO.getUserFirstName());
        user01.setUserLastName(userDTO.getUserLastName());
        user01.setPassword(userDTO.getPassword());
        user01.setEmail(userDTO.getEmail());
        user01.setEnabled(userDTO.getEnabled());
        user01.setCredentialsNonExpired(userDTO.getCredentialsNonExpired());
        user01.setAccountNonExpired(userDTO.getAccountNonExpired());
        user01.setAccountNonLocked(userDTO.getAccountNonLocked());
        final List<Role01> role01s = roleRepository01.findAllById(
                userDTO.getRoles() == null ? Collections.emptyList() : userDTO.getRoles());
        if (role01s.size() != (userDTO.getRoles() == null ? 0 : userDTO.getRoles().size())) {
            throw new NotFoundException("one of role01s not found");
        }
        user01.setRole01s(role01s.stream().collect(Collectors.toSet()));
        return user01;
    }

    public boolean userNameExists(final String userName) {
        return userRepository01.existsByUserNameIgnoreCase(userName);
    }

    public boolean emailExists(final String email) {
        return userRepository01.existsByEmailIgnoreCase(email);
    }

}
