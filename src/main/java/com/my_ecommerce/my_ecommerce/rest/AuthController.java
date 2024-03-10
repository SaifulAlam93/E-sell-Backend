package com.my_ecommerce.my_ecommerce.rest;


import com.my_ecommerce.my_ecommerce.domain.Role01;
import com.my_ecommerce.my_ecommerce.domain.User01;
import com.my_ecommerce.my_ecommerce.enums.ERole;
import com.my_ecommerce.my_ecommerce.model.JwtResponse;
import com.my_ecommerce.my_ecommerce.payload.request.LoginRequest;
import com.my_ecommerce.my_ecommerce.payload.request.SignupRequest;
import com.my_ecommerce.my_ecommerce.payload.response.MessageResponse;
import com.my_ecommerce.my_ecommerce.repos.RoleRepository01;
import com.my_ecommerce.my_ecommerce.repos.UserRepository01;
import com.my_ecommerce.my_ecommerce.security.jwt.JwtUtils;
import com.my_ecommerce.my_ecommerce.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
//for Angular Client (withCredentials)
//@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository01 userRepository;

  @Autowired
  RoleRepository01 roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


//    List<String> roles = userDetails.getAuthorities().stream()
//        .map(item -> item.getAuthority())
//        .collect(Collectors.toList());

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

//    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//        .body(new UserInfoResponse(userDetails.getId(),
//                                   userDetails.getUsername(),
//                                   userDetails.getEmail(),
//                                   roles));

    String token = jwtUtils.generateJwtToken(userDetails);


    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
            .body(new JwtResponse(token));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUserName(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User01 user = new User01(signUpRequest.getUsername(),
                         signUpRequest.getEmail(),
                         encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role01> roles = new HashSet<>();

    if (strRoles == null) {
      Role01 userRole = roleRepository.findById(ERole.ROLE_USER.toString())
          .orElseThrow(() -> new RuntimeException("Error: Role01 is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role01 adminRole = roleRepository.findById(ERole.ROLE_ADMIN.toString())
              .orElseThrow(() -> new RuntimeException("Error: Role01 is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role01 modRole = roleRepository.findById(ERole.ROLE_MODERATOR.toString())
              .orElseThrow(() -> new RuntimeException("Error: Role01 is not found."));
          roles.add(modRole);

          break;
        default:
          Role01 userRole = roleRepository.findById(ERole.ROLE_USER.toString())
              .orElseThrow(() -> new RuntimeException("Error: Role01 is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRole01s(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User01 registered successfully!"));
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new MessageResponse("You've been signed out!"));
  }
}
