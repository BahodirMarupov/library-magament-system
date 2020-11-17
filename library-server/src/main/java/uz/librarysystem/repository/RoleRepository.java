package uz.librarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.librarysystem.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

}
