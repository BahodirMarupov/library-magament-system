package uz.librarysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.librarysystem.exception.UsernameException;
import uz.librarysystem.payload.JwtResponse;
import uz.librarysystem.payload.RegisterDto;
import uz.librarysystem.entity.User;
import uz.librarysystem.repository.RoleRepository;
import uz.librarysystem.repository.UserRepository;
import uz.librarysystem.exception.PasswordNotMatchException;
import uz.librarysystem.security.JwtAuthenticationProvider;

import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("Username is not found!"));
    }

    public ResponseEntity<?> saveUser(RegisterDto registerDto) {
        if (!userRepository.existsByUsername(registerDto.getUsername())) {
            if (registerDto.getPassword().equals(registerDto.getPrePassword())) {
                User user = new User(registerDto.getUsername(),
                        passwordEncoder.encode(registerDto.getPassword()),
                        roleRepository.findAll().stream().filter(role ->
                                role.getRoleName().name().equals("ROLE_USER")).collect(Collectors.toSet()));

                userRepository.save(user);

                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                registerDto.getUsername(),
                                registerDto.getPassword())
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

                return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(jwtAuthenticationProvider.generateToken(authentication)));

            }
            throw new PasswordNotMatchException("Password can not match!");
        }
        throw new UsernameException("Username already exists!");
    }

    public User loadUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Username is not found!"));
    }
}
