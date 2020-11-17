package uz.librarysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.librarysystem.payload.BookDto;
import uz.librarysystem.service.BookService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<?> saveBook(@Valid @RequestBody BookDto bookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookDto));
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page) {
        return ResponseEntity.ok(bookService.getAll(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getOne(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.delete(id));
    }
}
