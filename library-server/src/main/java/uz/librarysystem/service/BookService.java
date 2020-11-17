package uz.librarysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.librarysystem.entity.Book;
import uz.librarysystem.payload.BookDto;
import uz.librarysystem.repository.AttachmentRepository;
import uz.librarysystem.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;

    public Page<BookDto> getAll(Integer page) {
        Page<Book> bookPage = bookRepository.findAll(PageRequest.of(page, 16, Sort.by(Sort.Direction.DESC, "id")));
        return bookPage.map(book -> new BookDto(book.getId(), book.getName(),
                book.getDescription(),
                book.getAuthor(),
                book.getWrittenYear(),
                book.getAmount(),
                book.getAttachment().getId()));
    }

    public Long saveBook(BookDto bookDto) {
        Book book = new Book(
                bookDto.getName(),
                bookDto.getDescription(),
                bookDto.getAuthor(),
                bookDto.getWrittenYear(),
                bookDto.getAmount(),
                attachmentRepository.findById(bookDto.getAttachmentId()).orElseThrow(() ->
                        new RuntimeException("Xatolik!")));

        return bookRepository.save(book).getId();
    }

    public BookDto getOne(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Bunday kitob muvjud emas"));

        return new BookDto(book.getId(),
                book.getName(),
                book.getDescription(),
                book.getAuthor(),
                book.getWrittenYear(),
                book.getAmount(),
                book.getAttachment().getId());
    }

    public Long delete(Long id) {
        bookRepository.deleteById(id);
        return id;
    }
}
