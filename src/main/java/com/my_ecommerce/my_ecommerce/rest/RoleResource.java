package com.my_ecommerce.my_ecommerce.rest;

import com.my_ecommerce.my_ecommerce.model.RoleDTO;
import com.my_ecommerce.my_ecommerce.service.RoleService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleResource {

    private final RoleService roleService;

    public RoleResource(final RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{roleName}")
    public ResponseEntity<RoleDTO> getRole(@PathVariable(name = "roleName") final String roleName) {
        return ResponseEntity.ok(roleService.get(roleName));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Void> createRole(@RequestBody @Valid final RoleDTO roleDTO,
            final BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (!bindingResult.hasFieldErrors("roleName") && roleDTO.getRoleName() == null) {
            bindingResult.rejectValue("roleName", "NotNull");
        }
        if (!bindingResult.hasFieldErrors("roleName") && roleService.roleNameExists(roleDTO.getRoleName())) {
            bindingResult.rejectValue("roleName", "Exists.role.roleName");
        }
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(new MethodParameter(
                    this.getClass().getDeclaredMethods()[0], -1), bindingResult);
        }
        roleService.create(roleDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{roleName}")
    public ResponseEntity<Void> updateRole(@PathVariable(name = "roleName") final String roleName,
            @RequestBody @Valid final RoleDTO roleDTO) {
        roleService.update(roleName, roleDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{roleName}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteRole(@PathVariable(name = "roleName") final String roleName) {
        roleService.delete(roleName);
        return ResponseEntity.noContent().build();
    }

}
