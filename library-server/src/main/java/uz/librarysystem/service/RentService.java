package uz.librarysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.librarysystem.entity.Book;
import uz.librarysystem.entity.User;
import uz.librarysystem.entity.UserBook;
import uz.librarysystem.payload.BookDto;
import uz.librarysystem.payload.UserBookDto;
import uz.librarysystem.repository.BookRepository;
import uz.librarysystem.repository.RentRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private BookRepository bookRepository;

    public List<UserBookDto> getAll() {
        return rentRepository.findAll(Sort.by(Sort.Direction.DESC,"id")).stream().map(userBook -> new UserBookDto(
                userBook.getId(),
                userBook.getUser(),
                new BookDto(userBook.getBook().getId(),
                        userBook.getBook().getName(),
                        userBook.getBook().getDescription(),
                        userBook.getBook().getAuthor(),
                        userBook.getBook().getWrittenYear(),
                        userBook.getBook().getAmount(),
                        userBook.getBook().getAttachment().getId()
                ),
                userBook.getCreatedAt()
        )).collect(Collectors.toList());
    }

    public Long rentBook(User user, Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Bunday kitob mavjud emas!!!"));
        boolean exists = rentRepository.existsByUserIdAndBookId(user.getId(), bookId);
        if (book.getAmount() > 0) {
            if (!exists) {
                UserBook userBook = new UserBook();
                userBook.setBook(book);
                userBook.setUser(user);
                try {
                    UserBook save = rentRepository.save(userBook);
                    book.setAmount(book.getAmount() - 1);
                    bookRepository.save(book);
                    return save.getId();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                throw new RuntimeException("Bu kitob sizda bor.");
            }
        } else {
            throw new RuntimeException("Bu kitob hozircha bo`sh emas.");
        }
        return bookId;
    }

    public List<UserBookDto> getOwnBooks(User user) {
        return rentRepository.findAllByUser(user).stream().map(userBook -> new UserBookDto(
                userBook.getId(),
                userBook.getUser(),
                new BookDto(userBook.getBook().getId(),
                        userBook.getBook().getName(),
                        userBook.getBook().getDescription(),
                        userBook.getBook().getAuthor(),
                        userBook.getBook().getWrittenYear(),
                        userBook.getBook().getAmount(),
                        userBook.getBook().getAttachment().getId()
                ),
                userBook.getCreatedAt()
        )).collect(Collectors.toList());
    }

    public Long delete(Long id, User user) {
        try {
            boolean exists = rentRepository.existsByUserAndId(user, id);
            if (user.getRoles().stream().anyMatch(role -> role.getRoleName().name().equals("ROLE_ADMIN")) || exists) {
                Book book = rentRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Xatolik!")).getBook();
                book.setAmount(book.getAmount() + 1);
                bookRepository.save(book);
                rentRepository.deleteById(id);
                return id;
            }
            throw new RuntimeException("Bu sizing kitobingiz emas.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
