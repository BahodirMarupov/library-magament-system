package uz.librarysystem.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.librarysystem.entity.Role;
import uz.librarysystem.entity.User;
import uz.librarysystem.repository.RoleRepository;
import uz.librarysystem.repository.UserRepository;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initialization-mode}")
    private String modeInitial;

    @Override
    public void run(String... args) throws Exception {
        if (modeInitial.equals("always")) {
            HashSet<Role> roles = new HashSet<>(roleRepository.findAll());

            userRepository.save(new User(
                    "admin",
                    passwordEncoder.encode("password"),
                    roles.stream().filter(role -> role.getRoleName().name()
                            .equals("ROLE_ADMIN")).collect(Collectors.toSet())
            ));
        }

    }
}
