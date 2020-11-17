package uz.librarysystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.librarysystem.entity.Attachment;
import uz.librarysystem.entity.Book;
import uz.librarysystem.payload.BookDto;

public interface BookRepository extends JpaRepository<Book,Long> {
}
