package uz.librarysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.librarysystem.payload.JwtResponse;
import uz.librarysystem.payload.LoginDto;
import uz.librarysystem.payload.RegisterDto;
import uz.librarysystem.security.JwtAuthenticationProvider;
import uz.librarysystem.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService service;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@Valid @RequestBody RegisterDto registerDto) {
        return service.saveUser(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(new JwtResponse(jwtAuthenticationProvider.generateToken(authentication)));
    }

}
