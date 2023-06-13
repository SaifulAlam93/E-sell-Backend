package com.my_ecommerce.my_ecommerce.service;

import com.my_ecommerce.my_ecommerce.domain.Role01;
import com.my_ecommerce.my_ecommerce.model.RoleDTO;
import com.my_ecommerce.my_ecommerce.repos.RoleRepository01;
import com.my_ecommerce.my_ecommerce.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class RoleService {

    private final RoleRepository01 roleRepository01;

    public RoleService(final RoleRepository01 roleRepository01) {
        this.roleRepository01 = roleRepository01;
    }

    public List<RoleDTO> findAll() {
        final List<Role01> role01s = roleRepository01.findAll(Sort.by("roleName"));
        return role01s.stream()
                .map((role01) -> mapToDTO(role01, new RoleDTO()))
                .toList();
    }

    public RoleDTO get(final String roleName) {
        return roleRepository01.findById(roleName)
                .map(role01 -> mapToDTO(role01, new RoleDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final RoleDTO roleDTO) {
        final Role01 role01 = new Role01();
        mapToEntity(roleDTO, role01);
        role01.setRoleName(roleDTO.getRoleName());
        return roleRepository01.save(role01).getRoleName();
    }

    public void update(final String roleName, final RoleDTO roleDTO) {
        final Role01 role01 = roleRepository01.findById(roleName)
                .orElseThrow(NotFoundException::new);
        mapToEntity(roleDTO, role01);
        roleRepository01.save(role01);
    }

    public void delete(final String roleName) {
        roleRepository01.deleteById(roleName);
    }

    private RoleDTO mapToDTO(final Role01 role01, final RoleDTO roleDTO) {
        roleDTO.setRoleName(role01.getRoleName());
        roleDTO.setRoleDescription(role01.getRoleDescription());
        return roleDTO;
    }

    private Role01 mapToEntity(final RoleDTO roleDTO, final Role01 role01) {
        role01.setRoleDescription(roleDTO.getRoleDescription());
        return role01;
    }

    public boolean roleNameExists(final String roleName) {
        return roleRepository01.existsByRoleNameIgnoreCase(roleName);
    }

}
