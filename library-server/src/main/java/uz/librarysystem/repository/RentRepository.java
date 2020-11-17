package uz.librarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.librarysystem.entity.User;
import uz.librarysystem.entity.UserBook;

import java.util.List;

public interface RentRepository extends JpaRepository<UserBook, Long> {
    boolean existsByUserIdAndBookId(Long user_id, Long book_id);

    List<UserBook> findAllByUser(User user);

    boolean existsByUserAndId(User user, Long id);
}
